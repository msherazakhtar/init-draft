package com.init.draft.events;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {

    private final String email;
    private final String username;
    private final Integer OTP;

    public UserRegisteredEvent(Object source, String email, String username, Integer OTP) {
        super(source);
        this.email = email;
        this.username = username;
        this.OTP = OTP;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Integer getOTP() {
        return OTP;
    }

}