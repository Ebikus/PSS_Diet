package com.example.diet;

import com.example.diet.model.User;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class RegisterUserTest {

    @Autowired(required = true)
    private UserRepository testUserRepository;

    @Autowired(required = true)
    private UserService testUserService;

    @Before
    public void setUp(){

    }

    @Test
    public void registerUser(){
        User testUser = new User(
                "xD",
                "xD",
                "123123123",
                "Sebek",
                "xD",
                "xD@xD.xD",
                "omegalul");

        testUserService.registerUser(testUser);

        List<User> findUsers = testUserRepository.findAll();

        for (User u: findUsers){
            if(u.equals(testUser)){
                Assertions.assertThat(u).isEqualTo(testUser);
            }
        }

    }
}
