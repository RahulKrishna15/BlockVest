package com.blockvest.service;

import com.blockvest.domain.VerificationType;
import com.blockvest.model.ForgotPasswordToken;
import com.blockvest.model.User;

public interface ForgotPasswordService
{
    ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo);
    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken token);
}
