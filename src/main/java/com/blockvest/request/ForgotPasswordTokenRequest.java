package com.blockvest.request;

import com.blockvest.domain.VerificationType;

public class ForgotPasswordTokenRequest {

    private String sendTo;
    private VerificationType verificationType;

    public String getSendTo() {
        return sendTo;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }


}
