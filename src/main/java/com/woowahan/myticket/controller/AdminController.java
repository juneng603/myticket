package com.woowahan.myticket.controller;

import com.google.gson.Gson;
import com.woowahan.myticket.service.AdminService;
import com.woowahan.myticket.service.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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
        return adminService.registerNewTicket();
    }

    @RequestMapping(value = "/admin/issue", method = RequestMethod.POST)
    @ResponseBody
    public String issueNewTicket(final String data) {
        logger.info("issuing new ticket. : " + data);

        return "OK";
    }
}
