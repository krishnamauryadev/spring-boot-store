package com.codetechsolution.store.payments;

import com.codetechsolution.store.orders.Order;
import com.codetechsolution.store.carts.CartEmptyException;
import com.codetechsolution.store.carts.CartNotFoundException;
import com.codetechsolution.store.carts.CartRepository;
import com.codetechsolution.store.orders.OrderRepository;
import com.codetechsolution.store.auth.AuthService;
import com.codetechsolution.store.carts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;


    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }

        if(cart.getItems().isEmpty()){
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart,authService.getCurrentUser());

        orderRepository.save(order);

       try{
           var checkoutSession = paymentGateway.createCheckoutSession(order);
           cartService.clearCart(cart.getId());
           return new CheckoutResponse(order.getId(), checkoutSession.getCheckoutUrl());
       }
       catch (PaymentException ex){
           orderRepository.delete(order);
           throw ex;
       }
    }
    public void handleWebhook(WebhookRequest request){
       paymentGateway.handleWebhookEvent(request)
               .ifPresent((paymentResult)->{
                   var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                   order.setStatus(paymentResult.getPaymentStatus());
                   orderRepository.save(order);
               });
    }
}
