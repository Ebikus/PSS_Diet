package com.example.diet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "iduser")
    private Integer iduser;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "companyAddress")
    private String companyAddress;
    @Column(name = "comapnyNip")
    private String companyNip;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "registrationDate")
    private Date registrationDate;

    
    @Column(name = "idrole")
    private Integer idrole;

    @Column(name = "iddelegation")
    private Integer iddelegation;
}
