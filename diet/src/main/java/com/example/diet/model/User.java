package com.example.diet.model;

import com.example.diet.Other.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "iduser")
    private Integer iduser;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @Column(name = "companyAddress", nullable = false)
    private String companyAddress;

    @Column(name = "comapnyNip", nullable = false)
    private String companyNip;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "registrationDate")
    private LocalDate registrationDate = LocalDate.now();


    @Column(name = "roleName")
    @ManyToMany
    private Set<Role> roleName;

    @OneToOne
    @JoinColumn(name = "iddelegation")
    private Delegarion delegarion;
}
