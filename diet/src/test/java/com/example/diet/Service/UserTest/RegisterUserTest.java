package com.example.diet.Service.UserTest;


import com.example.diet.model.User;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegisterUserTest {

        @Autowired
        private UserRepository testUserRepository;

        @Autowired
        private UserService testUserService;

        @Before
        public void setUp() {

        }
        @Test
        public void registerUser() {
            User testUser = new User(
                    "Nazwa Firmy",
                    "Adresowa 69",
                    "123456789",
                    "Krystian",
                    "Bursztynski",
                    "xd@xd.xd",
                    "haselo123");

            testUserService.registerUser(testUser);

            List<User> foundUsers = testUserRepository.findAll();

            for (User u: foundUsers) {
                if (u.equals(testUser)) {
                    Assertions.assertThat(u).isEqualTo(testUser);
                }
            }
        }
    }
