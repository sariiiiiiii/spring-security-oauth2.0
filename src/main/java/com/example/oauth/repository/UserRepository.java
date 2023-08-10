package com.example.oauth.repository;

import com.example.oauth.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String username);
}
