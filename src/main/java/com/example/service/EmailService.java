package com.example.service;

/**
 * Email service to send an email out.
 */
public interface EmailService {

    /**
     * Sends email with a given text content.
     *
     * @param emailText Text content sent in the email body.
     */
    void sendEmail(String emailText);
}
