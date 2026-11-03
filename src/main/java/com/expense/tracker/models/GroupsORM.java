package com.expense.tracker.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class GroupsORM extends GlobalFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;
    private String name;
    @Column(name = "user_id")
    private Long userId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public GroupsORM() {
        super();
    }

    public GroupsORM(Long groupId, String name, Long userId, Boolean isDeleted, LocalDateTime createdAt,
            String createdBy, LocalDateTime modifiedAt,
            String modifiedBy) {
        super(isDeleted, createdAt, createdBy, modifiedAt, modifiedBy);
        this.groupId = groupId;
        this.name = name;
        this.userId = userId;
    }

}
