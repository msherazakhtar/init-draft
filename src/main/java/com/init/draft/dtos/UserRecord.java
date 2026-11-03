package com.expense.tracker.dtos;

public record UserRecord(Long userId,
        String firstName,
        String lastName,
        String email,
        String password,
        Boolean isActive,
        Boolean isVerified) {

}
