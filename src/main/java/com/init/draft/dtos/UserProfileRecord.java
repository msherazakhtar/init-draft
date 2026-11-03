package com.init.draft.dtos;

public record UserProfileRecord(Long userId,String firstName,String lastName,String email,String password,Boolean isVerified) {

}
