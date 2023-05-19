package com.user.management.services;

/**
 * @author Carine Iradukunda
 */
public interface EmailServiceOp {
    void sendEmail(String to, String subject, String body);
}
