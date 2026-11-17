package com.jotTracker.data.repositories;

import com.jotTracker.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends
        JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
