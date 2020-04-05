package com.example.diet.service;

import com.example.diet.Other.RoleEnum;
import com.example.diet.model.Role;
import com.example.diet.model.User;
import com.example.diet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(User userNew){
        return userRepository.save(userNew);
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public List<User> getAllUsersByRoleName(String roleName){
        if(roleName.equals("USER")) return userRepository.findByRoles(new Role(RoleEnum.USER));
        else{
            return userRepository.findByRoles(new Role(RoleEnum.ADMIN));
        }
    }

    public void changePassword(long userId, String passwordNew){
        userRepository.findById((int)userId).map(user -> {
            user.setPassword(passwordNew);
            return userRepository.save(user);
        });
    }

    public boolean deleteUserById(long userId){
        boolean exist = userRepository.findAll().removeIf(user -> user.getIduser() == (int)userId);
        if(exist){
            userRepository.deleteById((int)userId);
            return true;
        }else{
            return false;
        }
    }
}
