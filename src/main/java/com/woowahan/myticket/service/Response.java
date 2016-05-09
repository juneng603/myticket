package com.woowahan.myticket.service;

/**
 * Created by junyoung on 2016. 4. 27..
 */
public class Response {
    private String status;
    private String error;
    private int code;
    private Object result;

    public Response() {
        ok();
    }

    public void ok() {
        status = "OK";
        code = 500;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void error(String error) {
        this.status = "ServerError";
        this.code = 400;
        this.error = error;
        this.result = null;
    }
}
