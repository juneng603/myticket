package com.woowahan.myticket.service;

/**
 * Created by junyoung on 2016. 4. 27..
 */
public class Response {
    private String status;

    public void ok() {
        status = "500";
    }

    public String getStatus() {
        return status;
    }
}
