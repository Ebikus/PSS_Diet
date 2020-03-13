package com.example.diet.model;

import com.example.diet.Other.AutoCapacity;
import com.example.diet.Other.TransportType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Delegation")
@Getter
@Setter
public class Delegarion {

    @Id
    @GeneratedValue
    @Column(name = "iddelegation")
    private Integer iddelegation;

    @Column(name = "description")
    private String description;

    @Column(name = "dataTimeStart", nullable = false)
    private LocalDate dateTimeStrat;

    @Column(name = "dataTimeStop", nullable = false)
    private LocalDate dataTimeStop;

    @Column(name = "travelDietAmount")
    private Integer travelDietAmount = 0;

    @Column(name = "breakfastNumber")
    private Integer breakfastNumber = 0;

    @Column(name = "dinnerNumber")
    private Integer dinnerNumber = 0;

    @Column(name = "supperNumber")
    private Integer supperNumber = 0;

    @Column(name = "transportType")
    @Enumerated(EnumType.STRING)
    //dorobić enum od typu transportu
    private TransportType transportType;

    @Column(name = "ticketPrice")
    private Float ticketPrice;

    @Column(name = "autoCapacity")
    @Enumerated(EnumType.STRING)
    private AutoCapacity autoCapacity;

    @Column(name = "km")
    private Float km;

    @Column(name = "accomodationPrice")
    private Float accomodationPrice;

    @Column(name = "otherticketsPrice")
    private Float otherTicketsPrice;

    @Column(name = "otherOutlayDesc")
    private String otherOutlayDesc;

    @Column(name = "otherOutlayPrice")
    private Float otherOutlayPrice;

}
