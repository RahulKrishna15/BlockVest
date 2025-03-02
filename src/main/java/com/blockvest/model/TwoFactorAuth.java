package com.blockvest.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class TwoFactorAuth {
    private String sendTo;
    private boolean enabled;

    // Default constructor required by JPA
    public TwoFactorAuth() {
        this.enabled = false;
        this.sendTo = null;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}