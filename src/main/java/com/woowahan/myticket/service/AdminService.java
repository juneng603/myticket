package com.woowahan.myticket.service;

import com.woowahan.myticket.entity.Ticket;
import com.woowahan.myticket.repository.TicketRepository;
import com.woowahan.myticket.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Service
public class AdminService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;


    public Response registerNewTicket(Ticket ticket) {
        Response response = new Response();

        try {
            ticketRepository.save(ticket);
        } catch (Exception ex) {
            response.error(ex.getMessage());
            return response;
        }

        response.ok();
        response.setResult(ticket);

        return response;
    }

    class Sales {
        private int ticketId;
        private int reserved_num;
        private int sold_num;
        private double percentage;

        public void setReserved_num(int reserved_num) {
            this.reserved_num = reserved_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
        }

        private void setPercentage() {
            percentage = ((double)sold_num / reserved_num) * 100;
        }

        private double getPercentage() {
            return percentage;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public int getTicketId() {
            return ticketId;
        }
    }

    public Response getTicketSales(Integer ticketId) {
        Response response = new Response();
        List<Ticket> tickets = new ArrayList<>();
        try {
            if (ticketId == null) {
                tickets = ticketRepository.findAll();
            } else {
                Ticket ticket = ticketRepository.findOne(ticketId);
                tickets.add(ticket);
            }
        } catch (Exception ex) {
            response.error(ex.getMessage());
            return response;
        }

        List<Sales> sales = new ArrayList<>();
        for (Ticket ticket: tickets) {
            Sales sale = new Sales();
            sale.ticketId = ticket.id;
            sale.reserved_num = ticket.reserved_num;
            sale.sold_num = ticket.sold_num;
            sale.setPercentage();

            sales.add(sale);
        }

        response.ok();
        response.setResult(sales);

        return response;
    }
}
