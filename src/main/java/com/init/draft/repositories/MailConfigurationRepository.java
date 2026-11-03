package com.init.draft.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.draft.models.MailConfigurationORM;

public interface MailConfigurationRepository extends JpaRepository<MailConfigurationORM,Long> {
    Optional<MailConfigurationORM> findBymailServer(String serverType);
}
