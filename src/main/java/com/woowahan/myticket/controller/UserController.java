package com.woowahan.myticket.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.woowahan.myticket.entity.IssuedTicket;
import com.woowahan.myticket.entity.User;
import com.woowahan.myticket.service.Response;
import com.woowahan.myticket.service.TicketService;
import com.woowahan.myticket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response findAll() {
        Response response = new Response();

        try {
            List<User> users = userService.getAllUsers();
            response.ok();
            response.setResult(users);

            return response;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        response.error("empty result");

        return response;
    }

    @RequestMapping(value = "/user/{userId}/reserve", method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response reserveTicket(@PathVariable final Integer userId, final String data) {
        Response response = new Response();

        if (data == null) {
            response.error("'data' argument is necessary");
            return response;
        }

        try {
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

            Integer ticketId = obj.get("ticket_id").getAsInt();
            Integer reserveNum = obj.get("reserve_num").getAsInt();

            IssuedTicket reserve = ticketService.reserve(userId, ticketId, reserveNum);
            response.setResult(reserve);
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response listTickets(@PathVariable final Integer userId) {
        Response response = new Response();

        if (userId == null) {
            response.error("'userId' should not be empty.");
            return response;
        }

        try {
            List<IssuedTicket> tickets = userService.findUserTickets(userId);
            response.setResult(tickets);
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

}
