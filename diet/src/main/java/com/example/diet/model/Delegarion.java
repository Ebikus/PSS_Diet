package com.example.diet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Delegarion {

    @Id
    @GeneratedValue
    @Column(name = "iddelegation")
    private Integer iddelegation;
    @Column(name = "description")
    private String description;
    @Column(name = "dataTimeStart")
    private Date dateTimeStrat;
    @Column(name = "dataTimeStop")
    private Date dataTimeStop;
    @Column(name = "travelDietAmount")
    private Integer travelDietAmount;
    @Column(name = "breakfastNumber")
    private Integer breakfastNumber;
    @Column(name = "dinnerNumber")
    private Integer dinnerNumber;
    @Column(name = "supperNumber")
    private Integer supperNumber;
    @Column(name = "transportType")
    //dorobić enum od typu transportu
    private String transportType;
    @Column(name = "ticketPrice")
    private Float ticketPrice;
    @Column(name = "autoCapacity")
    private Integer autoCapacity;
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
