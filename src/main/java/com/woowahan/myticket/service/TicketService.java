package com.woowahan.myticket.service;

import com.woowahan.myticket.entity.IssuedTicket;
import com.woowahan.myticket.repository.IssuedTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by junyoung on 2016. 5. 9..
 */
@Service
public class TicketService {

    @Autowired
    IssuedTicketRepository issuedTicketRepository;

    public IssuedTicket reserve(Integer userId, Integer ticketId, Integer reserveNum) {
        IssuedTicket issueTicket = new IssuedTicket();
        issueTicket.userId = userId;
        issueTicket.performId = ticketId;
        issueTicket.issuedAmount = reserveNum;
        issueTicket.issuedDate = new Date();

        return issuedTicketRepository.save(issueTicket);
    }
}
