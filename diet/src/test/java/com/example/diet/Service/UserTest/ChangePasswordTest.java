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

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChangePasswordTest {

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService testUserService;

    private User testUser;

    @Before
    public void setUp() {
         testUser = new User(
                "Nazwa Firmy",
                "Adresowa 69",
                "123456789",
                "Krystian",
                "Bursztynski",
                "xd@xd.xd",
                "haselo123");

        entityManager.persist(testUser);

        testUser.setPassword("nowehaselo321");
        testUserService.changePassword(1, "nowehaselo321");
    }

    @Test
    public void changePassword() {
        User addedUser = entityManager.find(User.class, 1);

        Assertions.assertThat(addedUser.getPassword()).isEqualTo(testUser.getPassword());
    }
}


