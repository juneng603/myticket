package com.woowahan.myticket.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public int id;

    @Column(name="type")
    @NotNull
    public String type;

    @Column(name="description")
    public String description;

    @Column(name="opened_date")
    @Temporal(TemporalType.DATE)
    public Date openedDate;

    @Column(name="closed_date")
    @Temporal(TemporalType.DATE)
    public Date closedDate;

    @Column(name="amount")
    public int amount;

    @Override
    public String toString() {
        return "Ticket [id=" + id + "]";
    }
}
