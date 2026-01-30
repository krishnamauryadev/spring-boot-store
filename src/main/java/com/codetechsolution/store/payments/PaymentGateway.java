package com.codetechsolution.store.payments;


import com.codetechsolution.store.orders.Order;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
   Optional<PaymentResult> handleWebhookEvent(WebhookRequest request);
}
