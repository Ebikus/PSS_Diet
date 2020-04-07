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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"com.example.diet.service"})
public class AddDelegationTest {

    @Autowired
    private DelegationRepository testDelegationRepository;

    @Autowired
    private UserRepository testUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DelegationService testDelegationService;

    private Delegarion testDelegation;

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

        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setYear(2020);
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(23);
        endDate.setHours(3);
        endDate.setMinutes(0);
        endDate.setSeconds(0);

        testDelegation = new Delegarion("xD", startDate, endDate);

        testDelegationService.addDelegation(1,testDelegation);
    }

    @Test
    public void addDelegation(){
        List<Delegarion> foundDelegation = testDelegationRepository.findAll();

        for(Delegarion d: foundDelegation){
            if(d.equals(testDelegation)){
                Assertions.assertThat(d).isEqualTo(testDelegation);
            }
        }
    }
}
