package com.example.diet.model;

import com.example.diet.Other.TransportType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Delegation")
@Getter
@Setter
public class Delegarion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "iddelegation")
    private Integer iddelegation;

    @Column(name = "description")
    private String description;

    @Column(name = "dataTimeStart", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTimeStrat;

    @Column(name = "dataTimeStop", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataTimeStop;

    @Column(name = "travelDietAmount")
    private Integer travelDietAmount = 30;

    @Column(name = "breakfastNumber")
    private Integer breakfastNumber = 0;

    @Column(name = "dinnerNumber")
    private Integer dinnerNumber = 0;

    @Column(name = "supperNumber")
    private Integer supperNumber = 0;

    @Column(name = "transportType")
    @Enumerated(EnumType.STRING)
    private TransportType transportType;

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

    public Delegarion(){}

    public Delegarion(String description, Date dateTimeStrat, Date dataTimeStop){
        this.description = description;
        this.dateTimeStrat = dateTimeStrat;
        this.dataTimeStop = dataTimeStop;
    }

}
