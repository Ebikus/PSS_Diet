package com.example.diet.Service.UserTest;

import com.example.diet.Other.RoleEnum;
import com.example.diet.model.Role;
import com.example.diet.model.User;
import com.example.diet.repository.RoleRepository;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
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
    public void setUp() {
        Set<Role> USERRoles = new HashSet<>();
        Set<Role> ADMINRoles = new HashSet<>();

        Role userRole = new Role();
        userRole.setRoleName(RoleEnum.USER);
        Role adminRole = new Role();
        adminRole.setRoleName(RoleEnum.ADMIN);
        USERRoles.add(userRole);
        ADMINRoles.add(adminRole);

        entityManager.persist(adminRole);
        entityManager.persist(userRole);
        entityManager.flush();

        createdUsersWithRoleUSER = new ArrayList<>();

        User testUser1 = new User(
                "Nazwa Firmy",
                "Adresowa 69",
                "123456789",
                "Krystian",
                "Bursztynski",
                "xd@xd.xd",
                "haselo123");
        testUser1.setRoles(ADMINRoles);
        User testUser2 = new User(
                "Nazwa Firmy2",
                "Adresowa 96",
                "123456789",
                "Sebastian",
                "Kemnitz",
                "xd2@xd.xd",
                "haselo123");
        testUser2.setRoles(ADMINRoles);
        User testUser3 =new User(
                "Nazwa Firmy3",
                "Uliczna 69",
                "123456789",
                "Kebastian",
                "Semnitz",
                "xd3@xd.xd",
                "haselo123");
        testUser3.setRoles(USERRoles);
        User testUser4 =new User(
                "Nazwa Firmy4",
                "Uliczna 96",
                "123456789",
                "Brystian",
                "Kursztyński",
                "xd4@xd.xd",
                "haselo123");
        testUser4.setRoles(USERRoles);

        createdUsersWithRoleUSER.add(testUser3);
        createdUsersWithRoleUSER.add(testUser4);

        entityManager.persist(testUser1);
        entityManager.persist(testUser2);
        entityManager.persist(testUser3);
        entityManager.persist(testUser4);
        entityManager.flush();
    }

    @Test
    public void getAllUsersByRoleName() {
        List<User> usersWithUserRole = testUserService.getAllUsersByRoleName("USER");
        Assert.assertEquals(createdUsersWithRoleUSER, usersWithUserRole);
    }
}


