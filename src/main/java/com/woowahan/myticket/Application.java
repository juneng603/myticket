package com.woowahan.myticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by junyoung on 2016. 4. 21..
 */
@SpringBootApplication
public class Application {
    static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("[start] application");

        SpringApplication.run(Application.class, args);
    }

}
