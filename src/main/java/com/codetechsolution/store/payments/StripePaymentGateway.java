package com.codetechsolution.store.payments;

import com.codetechsolution.store.orders.Order;
import com.codetechsolution.store.orders.OrderItem;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class StripePaymentGateway implements PaymentGateway{

    @Value("${websiteUrl}")
    private String websiteUrl;

    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        try {
            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl + "/checkout-success?orderId=" + order.getId())
                    .setCancelUrl(websiteUrl + "/cancel.html")
                    .setPaymentIntentData(createPaymentIntent(order));

            order.getOrderItems().forEach((item) -> {
                var lineItem = createLineItem(item);
                builder.addLineItem(lineItem);
            });

            var session = Session.create(builder.build());
            return new CheckoutSession(session.getUrl());
        } catch (StripeException ex) {
            throw new PaymentException(ex.getMessage());
        }

    }

    private static SessionCreateParams.PaymentIntentData createPaymentIntent(Order order) {
        return SessionCreateParams.PaymentIntentData.builder().putMetadata("order_id", order.getId().toString()).build();
    }

    @Override
    public Optional<PaymentResult> handleWebhookEvent(WebhookRequest request) {
        try {
            System.out.println("request.getHeader()"+request.getHeader());
            var signature = request.getHeader().get("Stripe-Signature");
            var payload = request.getPayload();

            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);

            return switch (event.getType()){
                case "payment_intent.succeeded" ->
                    Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.PAID));

                case "payment_intent.payment_failed" ->
                    Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.FAILED));

                default -> Optional.empty();
            };
        }catch (SignatureVerificationException ex){
            throw new PaymentException("Invalid signature");
        }
    }

    private Long extractOrderId(Event event){
        var stripeObject = event.getDataObjectDeserializer().getObject().orElseThrow();
        var paymentIntent = (PaymentIntent)stripeObject;
        var orderId =  paymentIntent.getMetadata().get("order_id");
        return Long.valueOf(orderId);
    }
    private static SessionCreateParams.LineItem createLineItem(OrderItem item) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(item.getQuantity()))
                .setPriceData(createPriceData(item))
                .build();
    }

    private static SessionCreateParams.LineItem.PriceData createPriceData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100)))
                .setProductData(createProductData(item))
                .build();
    }

    private static SessionCreateParams.LineItem.PriceData.ProductData createProductData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(item.getProduct().getName())
                .build();
    }
}
