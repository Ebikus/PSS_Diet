package com.example.diet;

import com.example.diet.model.Delegarion;
import com.example.diet.model.User;
import com.example.diet.repository.DelegationRepository;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.DelegationService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;



@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class RemoveDelegation {

    @Autowired
    private DelegationRepository testDelegationRepository;

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DelegationService testDelegationService;

    private List<Delegarion> createdDelegation;

    @Before
    public void setUp(){
        User testUser = new User(
                1,
                "xD",
                "xD",
                "123123123",
                "Sebek",
                "xD",
                "xD@xD.xD",
                "omegalul");

        entityManager.persist(testUser);
        entityManager.flush();

        createdDelegation = new ArrayList<>();

        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setYear(2020);
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        Delegarion testDelegation1 = new Delegarion("xD", startDate, endDate);
        createdDelegation.add(testDelegation1);
        entityManager.persist(testDelegation1);

        startDate = new Date();
        endDate = new Date();
        endDate.setYear(2022);
        endDate.setMonth(Calendar.JANUARY);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        Delegarion testDelegation2 = new Delegarion("Ufff", startDate, endDate);
        createdDelegation.add(testDelegation2);
        entityManager.persist(testDelegation2);
        entityManager.flush();

        createdDelegation.remove(0);
    }

    @Test
    public void removeDelegation(){
        testDelegationService.removeDelegation(1,1);
        List<Delegarion> delegationsInDatabase = testDelegationRepository.findAll();
        Assertions.assertThat(delegationsInDatabase).isEqualTo(createdDelegation);
    }
}
