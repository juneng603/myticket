package com.woowahan.myticket.service;

import com.woowahan.myticket.repository.TicketRepository;
import com.woowahan.myticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Service
public class AdminService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;


    public Response registerNewTicket() {
        Response response = new Response();
        response.ok();

        return response;
    }
}
