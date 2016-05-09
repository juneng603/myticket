package com.woowahan.myticket.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@Entity
@Table(name="perform")
public class Perform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public int id;

    @Column(name="name")
    @NotNull
    public String name;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    public PerformType type;

    @Column(name="description")
    public String description;

    @Column(name="opened_date")
    @Temporal(TemporalType.DATE)
    public Date openedDate;

    @Column(name="closed_date")
    @Temporal(TemporalType.DATE)
    public Date closedDate;

    @Column(name="occupied_num")
    public int occupied_num;

    @Column(name="sold_num")
    public int sold_num;

    @Override
    public String toString() {
        return "Perform [id=" + id + "]";
    }
}
