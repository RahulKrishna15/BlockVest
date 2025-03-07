package com.blockvest.service;

import com.blockvest.domain.PaymentMethod;
import com.blockvest.domain.PaymentOrderStatus;
import com.blockvest.model.PaymentOrder;
import com.blockvest.model.User;
import com.blockvest.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
    PaymentResponse createRazorpayPaymentLink (User user, Long amount,Long orderId);
    PaymentResponse createStripePaymentLink (User user, Long amount, Long orderId) throws StripeException;
}
