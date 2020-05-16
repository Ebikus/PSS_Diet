package com.example.diet.controller;

import com.example.diet.model.User;
import com.example.diet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public void registerUser(@RequestBody User userNew){
        userService.registerUser(userNew);
    }

    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUsersByRole")
    @ResponseBody
    public List<User> gettAllUsersByRole(String roleName){
        return userService.getAllUsersByRoleName(roleName);
    }

    @PutMapping("/changePassword")
    @ResponseBody
    public void changePasword(@RequestParam long userId, @RequestParam String passwordNew){
        userService.changePassword(userId, passwordNew);
    }

    @PutMapping("/makeAdmin")
    @ResponseBody
    public void makeAdmin(@RequestParam long userId){
        userService.makeAdmin(userId);
    }

    @PutMapping("/makeUser")
    @ResponseBody
    public void makeUser(@RequestParam long userId){
        userService.makeUser(userId);
    }

    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public boolean deleteUserById(@RequestParam long userId){
        return userService.deleteUserById(userId);
    }

}
