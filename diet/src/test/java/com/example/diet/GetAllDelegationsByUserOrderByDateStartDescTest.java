package com.example.diet;

import com.example.diet.model.Delegarion;
import com.example.diet.model.User;
import com.example.diet.repository.DelegationRepository;
import com.example.diet.repository.UserRepository;
import com.example.diet.service.DelegationService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class GetAllDelegationsByUserOrderByDateStartDescTest {

    @Autowired
    private DelegationRepository testDelegationRepository;

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DelegationService testDelegationService;

    @Autowired
    private UserService testUserService;

    private List<Delegarion> createdDelegations;

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

        createdDelegations = new ArrayList<>();

        Date startDate = new Date();
        startDate.setTime(1234);
        Date endDate = new Date();
        endDate.setYear(2020);
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        Delegarion testDelegation1 = new Delegarion("xD", startDate, endDate);


        startDate = new Date();
        startDate.setTime(12334);
        endDate = new Date();
        endDate.setYear(2021);
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        Delegarion testDelegation2 = new Delegarion("Uffff", startDate, endDate);


        startDate = new Date();
        startDate.setTime(123344);
        endDate = new Date();
        endDate.setYear(2022);
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        Delegarion testDelegation3 = new Delegarion("SmallDream", startDate, endDate);


        testDelegationService.addDelegation(1,testDelegation1);
        testDelegationService.addDelegation(1,testDelegation2);
        testDelegationService.addDelegation(1,testDelegation3);

        /*testDelegation1.setIddelegation(1);
        testDelegation1.setIddelegation(2);
        testDelegation3.setIddelegation(3);*/

        createdDelegations.add(testDelegation3);
        createdDelegations.add(testDelegation2);
        createdDelegations.add(testDelegation1);
    }

    @Test
    public void getAllDelegationsByUserOredrByDateStartDesc(){
        List<Delegarion> orderedDelegations = testDelegationService.getAllDelegationsByUserOrderByDateStartDesc(1);

        Assertions.assertTrue(testDelegations(orderedDelegations.get(0), createdDelegations.get(0)));
    }

    private Boolean testDelegations(Delegarion delegation, Delegarion expectedDelegation){
        if (!delegation.getDateTimeStrat().equals(expectedDelegation.getDateTimeStrat())) return false;
        if (!delegation.getDescription().equals(expectedDelegation.getDescription())) return false;
        if (!delegation.getDataTimeStop().equals(expectedDelegation.getDataTimeStop())) return false;

        return true;
    }
}
