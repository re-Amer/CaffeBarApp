package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom queries can be added here if needed, for example:
    User findByUsername(String username);
}
