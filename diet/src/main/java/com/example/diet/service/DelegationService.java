package com.example.diet.service;

import com.example.diet.model.Delegarion;
import com.example.diet.model.User;
import com.example.diet.repository.DelegationRepository;
import com.example.diet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DelegationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DelegationRepository delegationRepository;

    public List<Delegarion> getAllByUserId(long userId){
        return userRepository.findById((int)userId).map(User::getDelegarion).get();
    }

    public void addDelegation(long userId, Delegarion delegarion){
        List<Delegarion> userDelegation = userRepository.findById((int)userId).map(User::getDelegarion).get();
        userDelegation.add(delegarion);
        userRepository.findById((int)userId).map(user -> {
            user.setDelegarion(userDelegation);
            return userRepository.save(user);
        });
    }
    @Transactional
    public boolean removeDelegation(long userId, long delegationId){
        boolean userExists = userRepository.findById((int) userId).isPresent();
        boolean delegationExists = delegationRepository.findById((int) delegationId).isPresent();
        if(userExists && delegationExists) {
            User user = userRepository.getOne((int) userId);
            user.getDelegarion().remove(delegationRepository.getOne((int) delegationId));
            userRepository.save(user);
            delegationRepository.deleteById((int) delegationId);
            return true;
        }
        return false;

    }

    public void changeDelegation(long delegationId, Delegarion delegarion){
        delegationRepository.findById((int)delegationId).map(delegarion1 -> {
            delegarion1 = delegarion;
            return delegationRepository.save(delegarion1);
        });
    }

    public List<Delegarion> getAllDelegation(){
        return delegationRepository.findAll();
    }

    public List<Delegarion> getAllDelegationsOrderByDateStartDesc(){
        return delegationRepository.findAll().stream()
                .sorted(Comparator.comparing(Delegarion::getDateTimeStrat, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public List<Delegarion> getAllDelegationsByUserOrderByDateStartDesc(long userId){
        return  getAllByUserId(userId).stream()
                .sorted(Comparator.comparing(Delegarion::getDateTimeStrat, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
