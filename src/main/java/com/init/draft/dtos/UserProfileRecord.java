package com.expense.tracker.dtos;

public record UserProfileRecord(Long userId,String firstName,String lastName,String email,String password,Boolean isVerified) {

}
