package com.example.diet.model;

import com.example.diet.Other.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private RoleEnum roleName;
}
