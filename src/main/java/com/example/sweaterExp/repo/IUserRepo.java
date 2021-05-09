package com.example.sweaterExp.repo;

import com.example.sweaterExp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
