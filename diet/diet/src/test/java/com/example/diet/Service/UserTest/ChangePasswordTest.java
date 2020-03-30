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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class ChangePasswordTest {

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService testUserService;

    private User testUser;

    @Before
    public void setUp(){
        testUser = new User(
                "xD",
                "xD",
                "123123123",
                "Sebek",
                "xD",
                "xD@xD.xD",
                "omegalul");

        entityManager.persist(testUser);

        testUser.setPassword("alfalul");
        testUserService.changePassword(1,"alfalul");
    }

    @Test
    public void changePassword(){
        User addedUser = entityManager.find(User.class,1);

        Assertions.assertThat(addedUser.getPassword()).isEqualTo(testUser.getPassword());
    }
}
