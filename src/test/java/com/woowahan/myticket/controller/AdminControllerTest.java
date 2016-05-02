package com.woowahan.myticket.controller;

import com.woowahan.myticket.entity.TicketType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by junyoung on 2016. 4. 29..
 */
public class AdminControllerTest {

    @Test
    public void 문자열을_enum으로_치환테스트() throws Exception {
        final String input = "sports";
        TicketType type = TicketType.valueOf(input);

        assertEquals(input, type.toString());
    }
}