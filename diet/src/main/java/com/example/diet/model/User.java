package com.example.diet.model;

import com.example.diet.Other.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    private Boolean status = true;

    @Column(name = "registrationDate")
    private Date registrationDate = new Date();


    @Column(name = "roleName")
    @ManyToMany
    @JoinColumn(name = "roleName")
    private Set<Role> roleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    private List<Delegarion> delegarion;

    public User(){}

    public User(String companyName, String companyAddress, String companyNip, String name, String lastName, String email, String password){
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
