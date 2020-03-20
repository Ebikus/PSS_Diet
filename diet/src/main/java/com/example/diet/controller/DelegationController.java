package com.example.diet.controller;

import com.example.diet.model.Delegarion;
import com.example.diet.service.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("rest/delegation")
public class DelegationController {

    @Autowired
    DelegationService delegationService;

    @PostMapping("/addDelegation")
    @ResponseBody
    public void addDelegation(long userId, Delegarion delegarion){
        delegationService.addDelegation(userId,delegarion);
    }

    @DeleteMapping("/removeDelegation")
    @ResponseBody
    public void removeDelegation(long userId, long delegationId){
        delegationService.removeDelegation(userId, delegationId);
    }

    @PutMapping("/changeDelegation")
    @ResponseBody
    public void changeDelegation(long delegationId, Delegarion delegarion){
        delegationService.changeDelegation(delegationId, delegarion);
    }

    @GetMapping("/getAllDelegtions")
    @ResponseBody
    List<Delegarion> getAllDelegations(){
        return delegationService.getAllDelegation();
    }

    @GetMapping("/getAllDelegationsOrderByDateStartDesc")
    @ResponseBody
    List<Delegarion> getAllDelegationsOrderByDateStartDesc(){
        return delegationService.getAllDelegationsOrderByDateStartDesc();
    }

    @GetMapping("/getAllDelegationsByUserOrderByDateStartDesc")
    @ResponseBody
    List<Delegarion> getAllDelegationsByUserOrderByDateStartDesc(long userId){
        return delegationService.getAllDelegationsByUserOrderByDateStartDesc(userId);
    }
}
