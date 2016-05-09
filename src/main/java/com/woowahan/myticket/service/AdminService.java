package com.woowahan.myticket.service;

import com.woowahan.myticket.entity.Perform;
import com.woowahan.myticket.entity.User;
import com.woowahan.myticket.repository.PerformRepository;
import com.woowahan.myticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Service
public class AdminService {

    @Autowired
    private PerformRepository performRepository;

    @Autowired
    private UserRepository userRepository;

    public Response registerNewPerform(Perform perform) {
        Response response = new Response();

        try {
            Perform ret = performRepository.save(perform);
            response.setResult(ret);
        } catch (Exception ex) {
            response.error(ex.getMessage());
            return response;
        }

        response.ok();

        return response;
    }

    public Response registerNewUser(User userObj) {
        Response response = new Response();

        try {
            User ret = userRepository.save(userObj);
            response.setResult(ret);
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    class Sales {
        private int performId;
        private int occupied_num;
        private int sold_num;
        private double percentage;
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOccupied_num(int occupied_num) {
            this.occupied_num = occupied_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
        }

        private void setPercentage() {
            percentage = ((double)sold_num / occupied_num) * 100;
        }

        private double getPercentage() {
            return percentage;
        }

        public void setPerformId(int performId) {
            this.performId = performId;
        }

        public int getPerformId() {
            return performId;
        }
    }

    public Response getPerformSales(Integer performId) {
        Response response = new Response();
        List<Perform> performs = new ArrayList<>();
        try {
            if (performId == null) {
                performs = performRepository.findAll();
            } else {
                Perform perform = performRepository.findOne(performId);

                if (perform == null) {
                    response.ok();
                    response.setResult(new ArrayList<>());
                    return response;
                }

                performs.add(perform);
            }
        } catch (Exception ex) {
            response.error(ex.getMessage());
            return response;
        }

        List<Sales> sales = new ArrayList<>();
        for (Perform ticket: performs) {
            Sales sale = new Sales();
            sale.name = ticket.name;
            sale.performId = ticket.id;
            sale.occupied_num = ticket.occupied_num;
            sale.sold_num = ticket.sold_num;
            sale.setPercentage();

            sales.add(sale);
        }

        response.ok();
        response.setResult(sales);

        return response;
    }
}
