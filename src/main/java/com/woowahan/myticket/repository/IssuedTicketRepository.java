package com.woowahan.myticket.repository;

import com.woowahan.myticket.entity.IssuedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by junyoung on 2016. 5. 8..
 */
@Repository
public interface IssuedTicketRepository extends JpaRepository<IssuedTicket, Integer> {

    List<IssuedTicket> findByUserId(Integer userId);

}
