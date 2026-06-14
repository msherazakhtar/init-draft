package com.expense.tracker.eventListners;

import com.expense.tracker.events.UserRegisteredEvent;
import com.expense.tracker.models.MailConfigurationORM;
import com.expense.tracker.repositories.MailConfigurationRepository;
import com.expense.tracker.utilities.DBOperations;
import com.expense.tracker.utilities.EmailUtility;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRegisteredEventListener {
    private final MailConfigurationRepository mailConfigurationRepository;

    public UserRegisteredEventListener(MailConfigurationRepository mailConfigurationRepository) {
        this.mailConfigurationRepository = mailConfigurationRepository;
    }
    @Async
    @EventListener
    public void handleUserRegistered(UserRegisteredEvent event) {

        System.out.println("event raised");
        Optional<MailConfigurationORM> mailConfiguration = mailConfigurationRepository.findBymailServer("Gmail");
        System.out.println("Username: " + mailConfiguration.get().getUsername());
        System.out.println("Password: " + mailConfiguration.get().getPassword());
        EmailUtility.sendUserVerificationEmail(mailConfiguration.get(), event.getEmail(), event.getOTP().toString());
    }
}
