package com.blockvest.service;

import com.blockvest.domain.PaymentMethod;
import com.blockvest.domain.PaymentOrderStatus;
import com.blockvest.model.PaymentOrder;
import com.blockvest.model.User;
import com.blockvest.repository.PaymentOrderRepository;
import com.blockvest.response.PaymentResponse;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.v2.Amount;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecretKey;

    @Override
    public PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod) {
       PaymentOrder paymentOrder = new PaymentOrder();
       paymentOrder.setUser(user);
       paymentOrder.setAmount(amount);
       paymentOrder.setPaymentMethod(paymentMethod);
       paymentOrder.setStatus(PaymentOrderStatus.PENDING);
       return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        return paymentOrderRepository.findById(id).orElseThrow(()-> new Exception("Payment Order Not found"));
    }

    @Override
    public boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException {
        if(paymentOrder.getStatus()==null)
        {
            paymentOrder.setStatus(PaymentOrderStatus.PENDING);
        }
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING))
        {
            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY))
            {
                RazorpayClient razorpay = new RazorpayClient(apiKey,apiSecretKey);
                Payment payment = razorpay.payments.fetch(paymentId);
                Integer amount = payment.get("amount");
                String status = payment.get("status");
                if(status.equals("captured"))
                {
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    return true;
                }
                paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                paymentOrderRepository.save(paymentOrder);
                return false;
            }
            paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
            paymentOrderRepository.save(paymentOrder);
            return true;
        }
        return false;
    }

    @Override
    public PaymentResponse createRazorpayPaymentLink(User user, Long amount, Long orderId) {
        Long Amount = amount * 100; // Convert rupees to paise

        try {
            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecretKey);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", Amount); // Ensure amount is in paise
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true); // Allow partial payment
            paymentLinkRequest.put("first_min_partial_amount", 100); // Minimum partial amount

            // Expiry time (example: current time + 1 day)
            long expireBy = System.currentTimeMillis() / 1000 + (24 * 60 * 60);
            paymentLinkRequest.put("expire_by", expireBy);

            String referenceId = "REF-" + UUID.randomUUID().toString().substring(0, 8);
            paymentLinkRequest.put("reference_id", referenceId);

            paymentLinkRequest.put("description", "Payment for policy no "+referenceId);

            // Customer details
            JSONObject customer = new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("contact", "+919000090000"); // Add actual contact number
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);

            // Notification settings
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            // Reminder enable
            paymentLinkRequest.put("reminder_enable", true);

            // Notes (custom metadata)
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);

            // Callback details
            paymentLinkRequest.put("callback_url", "http://localhost:5173/wallet?order_id="+orderId);
            paymentLinkRequest.put("callback_method", "get");

            // Send the request
            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            // Construct response
            PaymentResponse res = new PaymentResponse();
            res.setPayment_url(paymentLinkUrl);

            return res;

        } catch (RazorpayException e) {
            System.out.println("Error Creating Payment Link: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/wallet?order_id="+orderId)
                .setCancelUrl("http://localhost:5173/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount*100)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder()
                                        .setName("Top Up Wallet")
                                        .build())
                                .build())
                        .build()
                ).build();
        Session session = Session.create(params);
        System.out.println("Session _________"+session);

        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(session.getUrl());
        return response;
    }
}
