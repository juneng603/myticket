package com.woowahan.myticket.entity;

import java.io.Serializable;

/**
 * Created by junyoung on 2016. 5. 5..
 */
public class IssuedTicketPK implements Serializable {
    protected int id;
    protected int userId;

    public IssuedTicketPK() {
    }

    public IssuedTicketPK(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }
}
