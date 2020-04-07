package com.example.diet.repository;

import com.example.diet.model.Role;
import com.example.diet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRoles(Role role);
    User findByEmail(String email);

}
