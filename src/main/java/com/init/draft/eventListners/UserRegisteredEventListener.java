package com.init.draft.eventListners;

import com.init.draft.events.UserRegisteredEvent;
import com.init.draft.models.MailConfigurationORM;
import com.init.draft.repositories.MailConfigurationRepository;
import com.init.draft.utilities.EmailUtility;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
