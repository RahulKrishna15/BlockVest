package com.blockvest.service;

import com.blockvest.model.PaymentDetails;
import com.blockvest.model.User;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, User user);
    public PaymentDetails getUsersPaymentDetails(User user);
}
