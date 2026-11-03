package com.expense.tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.UsersORM;

public interface UserRepository extends JpaRepository<UsersORM, Long> {
    boolean existsByEmail(String email);

    Optional<UsersORM> findByEmail(String email);

}
