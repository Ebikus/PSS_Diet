package com.example.diet.model;

import com.example.diet.Other.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
@Transactional
public class User {

    @Id
    @GeneratedValue
    @Column(name = "iduser")
    private Integer iduser;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Column(name = "companyName", nullable = false)
    private String companyName;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Column(name = "companyAddress", nullable = false)
    private String companyAddress;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Column(name = "comapnyNip", nullable = false)
    private String companyNip;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Wrong Email")
    private String email;

    @NotBlank(message = "Field cannot be blank!")
    @NotNull(message = "Field cannot be null!")
    @NotEmpty(message = "Field cannot be empty!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password must contain: one digit, at least one upper case letter, at least one lower case letter, at least 8 characters without whitespaces")
    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "registrationDate")
    private Date registrationDate = new Date();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleName")
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Delegarion> delegarion;


    public User(){
        delegarion = new ArrayList<>();
    }

    public User(String companyName, String companyAddress, String companyNip, String name, String lastName, String email, String password){
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        delegarion = new ArrayList<>();
    }
}
