package com.example.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for EmailServiceImpl.
 */
public class EmailServiceImplTest {

    /**
     * Mock for EmailServiceImpl
     */
    @Mock
    private EmailServiceImpl emailServiceMock;

    /**
     * Mock for JavaMailSender
     */
    @Mock
    private JavaMailSender javaMailSenderMock;

    /**
     * EmailServiceImpl
     */
    private EmailServiceImpl emailService;

    /**
     * Sample email text to be sent in email body.
     */
    private static final String sampleEmailText = "Testing email service";

    /**
     * Initialize test dependencies.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emailService = new EmailServiceImpl(javaMailSenderMock);
    }

    /**
     * Tests sendEmail() method exposed by EmailServiceImpl.
     */
    @Test
    public void testSendEmail() {
        emailService.sendEmail(sampleEmailText);
        verify(javaMailSenderMock, times(1)).send(any(SimpleMailMessage.class));
    }
}
