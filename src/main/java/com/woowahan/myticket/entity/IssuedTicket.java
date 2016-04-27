package com.woowahan.myticket.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Entity
@Table(name="issuedticket")
public class IssuedTicket {

    @Id
    @Column(name="ticket_id")
    public int ticketId;

    @Column(name="user_id")
    public int userId;

    @Column(name="issued_amount")
    public int issuedAmount;

    @Column
    @Temporal(TemporalType.DATE)
    public Date issuedDate;
}
