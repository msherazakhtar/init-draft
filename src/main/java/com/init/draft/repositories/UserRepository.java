package com.init.draft.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.draft.models.UsersORM;

public interface UserRepository extends JpaRepository<UsersORM, Long> {
    boolean existsByEmail(String email);

    Optional<UsersORM> findByEmail(String email);

}
