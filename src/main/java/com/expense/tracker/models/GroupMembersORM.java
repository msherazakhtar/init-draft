package com.expense.tracker.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_members")
public class GroupMembersORM extends GlobalFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    private Long groupMemberId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "group_id")
    private Long groupId;

    public GroupMembersORM() {
        super();
    }

    public GroupMembersORM(Long groupMemberId, String name, String email, String phone, Long groupId, Boolean isDeleted,
            LocalDateTime createdAt,
            String createdBy, LocalDateTime modifiedAt,
            String modifiedBy) {
        super(isDeleted, createdAt, createdBy, modifiedAt, modifiedBy);
        this.groupMemberId = groupMemberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.groupId = groupId;
    }

    public GroupMembersORM(Long groupMemberId, String name, String email, String phone) {
        this.groupMemberId = groupMemberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(Long groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
