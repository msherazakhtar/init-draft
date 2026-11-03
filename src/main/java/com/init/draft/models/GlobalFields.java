package com.expense.tracker.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class GlobalFields {
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "date_created")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "date_modified")
    private LocalDateTime modifiedAt = LocalDateTime.now();
    @Column(name = "modified_by")
    private String modifiedBy;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    
    public GlobalFields() {
    }

    public GlobalFields(Boolean isDeleted, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
            String modifiedBy) {
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

}
