package com.woowahan.myticket.entity;

import javax.persistence.*;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public int id;

    @Column(name="type")
    public String type;

    @Column(name="name")
    public String name;

    @Column(name="phone")
    public String phoneNum;

    @Column(name="password")
    public String password;

    @Column(name="gender")
    public char gender;

    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }
}
