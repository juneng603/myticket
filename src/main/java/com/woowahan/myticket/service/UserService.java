package com.woowahan.myticket.service;

import com.woowahan.myticket.entity.IssuedTicket;
import com.woowahan.myticket.entity.User;
import com.woowahan.myticket.repository.IssuedTicketRepository;
import com.woowahan.myticket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by junyoung on 2016. 5. 9..
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IssuedTicketRepository issuedTicketRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<IssuedTicket> findUserTickets(Integer userId) {
        return issuedTicketRepository.findByUserId(userId);
    }
}
