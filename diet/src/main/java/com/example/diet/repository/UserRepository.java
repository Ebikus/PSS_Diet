package com.example.diet.repository;

import com.example.diet.model.Role;
import com.example.diet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRoles(Role role);
}
