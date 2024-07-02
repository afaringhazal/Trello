package com.trello.repository;


import com.trello.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String userUsername);
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
