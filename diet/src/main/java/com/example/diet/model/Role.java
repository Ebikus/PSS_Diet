package com.example.diet.model;

import com.example.diet.Other.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Getter
@Setter
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private RoleEnum roleName;
}
