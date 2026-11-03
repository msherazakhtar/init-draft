package com.init.draft.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.init.draft.dtos.GmailDTO;
import com.init.draft.models.MailConfigurationORM;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtility {
    public static Map<String, Object> sendUserVerificationEmail(MailConfigurationORM mailConfiguration,
                                                                String recipient, String code) {
        mailConfiguration.setReceiver(recipient);
        mailConfiguration.setMailTemplate(
                mailConfiguration.getMailTemplate().replace("{verificationCode}",
                        code));
        String mailSubject = "Verification Code for Expense Tracker";
        GmailDTO gmailDTO = EmailUtility.mapToGmailDTO(mailConfiguration, mailSubject);
        return sendMailUsingGmail(gmailDTO);
    }

    public static Map<String, Object> sendMailUsingGmail(GmailDTO gmailDTO) {
        System.out.println("Sending Verification Email......");
        Map<String, Object> emailResponseMap = new HashMap<>();
        final String username = gmailDTO.username();
        final String password = gmailDTO.password();
        Session session = intiateMailSession(username, password);
        try {
            Transport.send(prepareMimeMessage(gmailDTO, session));
            System.out.println("Done");
            emailResponseMap.put("success", "Email sent successfully");
            return emailResponseMap;
        } catch (MessagingException e) {
            emailResponseMap.put("error", "Email can't be sent!!!");
            System.out.println("Email can't be sent!!!"+e.getMessage());
            return emailResponseMap;
        }
    }

    private static Session intiateMailSession(String userName, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        return session;
    }

    private static MimeMessage prepareMimeMessage(GmailDTO gmailDTO, Session session) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setContent(gmailDTO.body(), "text/html");
            message.setFrom(new InternetAddress(gmailDTO.sender()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(gmailDTO.receiver()));
            message.setSubject(gmailDTO.subject());
            return message;
        } catch (MessagingException e) {
            return null;
        }

    }

    public static GmailDTO mapToGmailDTO(MailConfigurationORM config, String subject) {
        return new GmailDTO(
                config.getSender(),
                config.getReceiver(),
                config.getMailTemplate(),
                subject,
                config.getUsername(),
                config.getPassword());
    }

}
