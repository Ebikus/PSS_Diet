package com.example.diet.resources;


import com.example.diet.model.Delegarion;
import com.example.diet.model.Role;
import com.example.diet.model.User;
import com.example.diet.repository.DelegationRepository;
import com.example.diet.repository.RoleRepository;
import com.example.diet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class Resource {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DelegationRepository delegationRepository;

    @GetMapping(value = "/role")
    public List<Role> getAllRole(){return roleRepository.findAll();}

    @GetMapping(value = "/user")
    public List<User> getAllUser(){return userRepository.findAll();}

    @GetMapping(value = "/delegation")
    public List<Delegarion> getAllDelegation(){return delegationRepository.findAll();}
}
