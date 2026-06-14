package com.expense.tracker.dtos;

public record GmailDTO(String sender, String receiver, String body, String subject, String username, String password) {
}