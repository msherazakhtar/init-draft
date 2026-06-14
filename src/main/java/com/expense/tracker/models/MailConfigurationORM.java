package com.expense.tracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mail_configuration")
public class MailConfigurationORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id")
    private Long configurationId;
    @Column(name = "sender")
    private String sender;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mail_server")
    private String mailServer;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "mail_template",columnDefinition = "VARCHAR(5000)")
    private String mailTemplate;
    public Long getConfigurationId() {
        return configurationId;
    }
    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMailServer() {
        return mailServer;
    }
    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getMailTemplate() {
        return mailTemplate;
    }
    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }
    
}
