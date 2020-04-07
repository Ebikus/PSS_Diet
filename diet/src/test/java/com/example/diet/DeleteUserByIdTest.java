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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class DeleteUserByIdTest {

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService testUserService;

    private List<User> expectedUsers;

    @Before
    public void setUp(){
        expectedUsers = new ArrayList<>();

        User testUser1 = new User(
                1,
                "xD1",
                "xD1",
                "1231231231",
                "Sebek1",
                "xD1",
                "xD@xD.xD1",
                "omegalul1");

        User testUser2 = new User(
                2,
                "xD2",
                "xD2",
                "1231231232",
                "Sebek2",
                "xD2",
                "xD@xD.xD2",
                "omegalul2");

        entityManager.persist(testUser1);
        entityManager.persist(testUser2);

        expectedUsers.add(testUser1);
        expectedUsers.add(testUser2);

        testUserService.deleteUserById(2);
        expectedUsers.remove(1);
    }

    @Test
    public void deleteUserById(){
        List<User> allUsersInDatabase = testUserService.getAllUsers();

        Assertions.assertThat(allUsersInDatabase).isEqualTo(expectedUsers);
    }
}
