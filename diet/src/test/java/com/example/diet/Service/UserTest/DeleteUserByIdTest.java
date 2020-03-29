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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeleteUserByIdTest {

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService testUserService;

    private List<User> expectedUsers = new ArrayList<>();

    @Before
    public void setUp() {
        expectedUsers = new ArrayList<>();

        User testUser1 = new User(
                "Nazwa Firmy",
                "Adresowa 69",
                "123456789",
                "Krystian",
                "Bursztynski",
                "xd@xd.xd",
                "haselo123");
        User testUser2 = new User(
                "Nazwa Firmy2",
                "Adresowa 96",
                "123456789",
                "Sebastian",
                "Kemnitz",
                "xd2@xd.xd",
                "haselo123");

        entityManager.persist(testUser1);
        entityManager.persist(testUser2);

        expectedUsers.add(testUser1);
        expectedUsers.add(testUser2);

        testUserService.deleteUserById(2);
        expectedUsers.remove(1);
    }

    @Test
    public void deleteUserById() {
        List<User> allUsersInDatabase = testUserService.getAllUsers();

        Assertions.assertThat(allUsersInDatabase).isEqualTo(expectedUsers);
    }

}
