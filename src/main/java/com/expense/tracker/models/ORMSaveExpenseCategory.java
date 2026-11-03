package com.expense.tracker.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class ORMSaveExpenseCategory {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "category_id")
    private Long categoryId;
    private String name;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "is_global")
    private Boolean isGloabal;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "date_modified")
    private LocalDate dateModified;
    @Column(name = "modified_by")
    private String modifiedBy;
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Boolean getIsGloabal() {
        return isGloabal;
    }
    public void setIsGloabal(Boolean isGloabal) {
        this.isGloabal = isGloabal;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDate getDateModified() {
        return dateModified;
    }
    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    

}
