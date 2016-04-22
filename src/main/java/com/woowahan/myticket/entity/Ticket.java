package com.woowahan.myticket.entity;


import javax.persistence.*;

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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + "]";
    }
}
