package com.woowahan.myticket.controller;

import com.google.gson.*;
import com.woowahan.myticket.entity.Ticket;
import com.woowahan.myticket.entity.TicketType;
import com.woowahan.myticket.service.AdminService;
import com.woowahan.myticket.service.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by junyoung on 2016. 4. 25..
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "welcome myticket service!!";
    }

    @RequestMapping(value = "/admin/register", method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response registerNewTicket(final String data) {
        Response response = new Response();
        try {
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

            Ticket ticketObj = getTicketObj(obj);
            response = adminService.registerNewTicket(ticketObj);
        } catch (JsonSyntaxException ex) {
            response.error(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/admin/sales", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response checkSales(final Integer ticketId) {
        Response response = new Response();
        try {
            response.ok();
            response = adminService.getTicketSales(ticketId);
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    private Ticket getTicketObj(JsonObject obj) {
        Ticket ticket = new Ticket();

        String ticketType = obj.get("type").getAsString().toUpperCase().trim();
        ticket.type = TicketType.valueOf(ticketType);

        JsonElement o1 = obj.get("description");
        ticket.description = o1 == null ? "" : o1.getAsString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        o1 = obj.get("opened_date");
        final String openedDate = o1 == null ? "" : o1.getAsString();

        LocalDate date = LocalDate.now();
        if (!openedDate.isEmpty()) {
            date = LocalDate.parse(openedDate, formatter);
        }
        ticket.openedDate = java.sql.Date.valueOf(date);

        JsonElement o2 = obj.get("closed_date");
        final String closedDate = o2 == null ? "" : obj.get("closed_date").getAsString();
        if (!closedDate.isEmpty()) {
            date = LocalDate.parse(closedDate, formatter);
        }
        ticket.closedDate = java.sql.Date.valueOf(date);

        ticket.reserved_num = obj.get("reserved_num").getAsInt();
        ticket.sold_num = 0;

        return ticket;
    }

    @RequestMapping(value = "/admin/issue", method = RequestMethod.POST)
    @ResponseBody
    public String issueNewTicket(final String data) {
        logger.info("issuing new ticket. : " + data);

        return "OK";
    }

}
