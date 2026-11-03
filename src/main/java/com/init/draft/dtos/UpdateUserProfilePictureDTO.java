package com.expense.tracker.dtos;

public class UpdateUserProfilePictureDTO {
    private String userId;
    private String profilePictureUrl;

    public UpdateUserProfilePictureDTO() {
    }

    public UpdateUserProfilePictureDTO(String userId, String profilePictureUrl) {
        this.userId = userId;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
