package com.woowahan.myticket.controller;

import com.woowahan.myticket.entity.Ticket;
import com.woowahan.myticket.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@RestController
public class UserController {

    @Autowired
    private TicketRepository ticketRepository;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user")
    @ResponseBody
    public List<Ticket> findAll() {
        try {
            return ticketRepository.findAll();
        } catch (Exception ex) {
            logger.error("unexpected error : " + ex.getMessage());
        }

        return null;
    }

}
