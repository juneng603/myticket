package com.woowahan.myticket.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.woowahan.myticket.entity.Perform;
import com.woowahan.myticket.entity.PerformType;
import com.woowahan.myticket.entity.User;
import com.woowahan.myticket.service.AdminService;
import com.woowahan.myticket.service.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

            Perform performObj = getPerformObj(obj);
            response = adminService.registerNewPerform(performObj);
        } catch (JsonSyntaxException ex) {
            response.error(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.error(ex.getMessage());
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/admin/user/register", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response registerNewUser(final String data) {
        Response response = new Response();
        if (data == null) {
            response.error("empty data.");
            return response;
        }

        try {
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

            User userObj= getUserObj(obj);
            response = adminService.registerNewUser(userObj);
        } catch (JsonSyntaxException ex) {
            response.error(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.error(ex.getMessage());
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    private User getUserObj(JsonObject obj) throws Exception {
        User user = new User();

        JsonElement j1 = obj.get("name");
        if (j1 == null) {
            throw new Exception("'name' should not be empty.");
        }
        String name = j1.getAsString();
        user.name = name;

        j1 = obj.get("phone");
        String phone = j1 == null ? "" : j1.getAsString();
        user.phoneNum = phone;

        j1 = obj.get("password");
        if (j1 == null) {
            throw new Exception("'pasword' shoudl not be empty.");
        }
        String passwd = j1.getAsString();
        user.password = passwd;

        j1 = obj.get("gender");
        if (j1 == null) {
            throw new Exception("'gender' should not be empty.");
        }
        char gender = j1.getAsCharacter();
        user.gender = gender;

        return user;
    }

    @RequestMapping(value = "/admin/sales", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response checkSales() {
        return checkSales(null);
    }

    @RequestMapping(value = "/admin/sales/{performId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response checkSales(@PathVariable final Integer performId) {
        Response response = new Response();
        try {
            response = adminService.getPerformSales(performId);
            response.ok();
        } catch (Exception ex) {
            response.error(ex.getMessage());
        }

        return response;
    }

    private Perform getPerformObj(JsonObject obj) throws Exception {
        Perform perform = new Perform();

        String performName = obj.get("name").getAsString();
        perform.name = performName;

        String ticketType = obj.get("type").getAsString().toUpperCase().trim();
        perform.type = PerformType.valueOf(ticketType);

        JsonElement o1 = obj.get("description");
        perform.description = o1 == null ? "" : o1.getAsString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        o1 = obj.get("opened_date");
        final String openedDate = o1 == null ? "" : o1.getAsString();

        LocalDate date = LocalDate.now();
        if (!openedDate.isEmpty()) {
            date = LocalDate.parse(openedDate, formatter);
        }
        perform.openedDate = java.sql.Date.valueOf(date);

        JsonElement o2 = obj.get("closed_date");
        final String closedDate = o2 == null ? "" : obj.get("closed_date").getAsString();
        if (!closedDate.isEmpty()) {
            date = LocalDate.parse(closedDate, formatter);
        }
        perform.closedDate = java.sql.Date.valueOf(date);

        perform.occupied_num = obj.get("occupied_num").getAsInt();
        perform.sold_num = 0;

        return perform;
    }

}
