package com.example.diet;

import com.example.diet.Other.RoleEnum;
import com.example.diet.model.Role;
import com.example.diet.model.User;
import com.example.diet.repository.RoleRepository;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class GetAllUsersByRoleNameTest {

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private RoleRepository testRoleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService testUserService;

    List<User> createdUsersWithRoleUSER;

    @Before
    public void setUp(){
        createdUsersWithRoleUSER = new ArrayList<>();

        Role user = testRoleRepository.save(new Role(RoleEnum.USER));
        Role admin = testRoleRepository.save(new Role(RoleEnum.ADMIN));

        Set<Role> userRole = new HashSet<Role>();
        userRole.add(user);
        Set<Role> adminRole = new HashSet<Role>();
        adminRole.add(admin);
        Set<Role> userAdminRole = new HashSet<Role>();
        userAdminRole.add(user);
        userAdminRole.add(admin);

        User testUser1 = new User(
                1,
                "xD1",
                "xD1",
                "1231231231",
                "Sebek1",
                "xD1",
                "xD@xD.xD1",
                "omegalul1");
        testUser1.setRoles(userRole);
        testUser1 = testUserService.registerUser(testUser1);

        User testUser2 = new User(
                2,
                "xD2",
                "xD2",
                "1231231232",
                "Sebek2",
                "xD2",
                "xD@xD.xD2",
                "omegalul2");
        testUser2.setRoles(adminRole);
        testUser2 = testUserService.registerUser(testUser2);

        User testUser3 = new User(
                3,
                "xD3",
                "xD3",
                "1231231233",
                "Sebek3",
                "xD3",
                "xD@xD.xD3",
                "omegalul3");
        testUser3.setRoles(userAdminRole);
        testUser3 = testUserService.registerUser(testUser3);

        User testUser4 = new User(
                4,
                "xD4",
                "xD4",
                "1231231234",
                "Sebek4",
                "xD4",
                "xD@xD.xD4",
                "omegalul4");
        testUser4 = testUserService.registerUser(testUser4);

        createdUsersWithRoleUSER.add(testUser1);
        createdUsersWithRoleUSER.add(testUser3);

    }

    @Test
    public void getAllUsersByRoleName(){
        List<User> usersWithUserRole = testUserService.getAllUsersByRoleName("USER");

        Assertions.assertEquals(usersWithUserRole, createdUsersWithRoleUSER);
    }
}
