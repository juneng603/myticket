package com.woowahan.myticket.repository;

import com.woowahan.myticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
