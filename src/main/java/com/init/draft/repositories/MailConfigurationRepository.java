package com.expense.tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.MailConfigurationORM;

public interface MailConfigurationRepository extends JpaRepository<MailConfigurationORM,Long> {
    Optional<MailConfigurationORM> findBymailServer(String serverType);
}
