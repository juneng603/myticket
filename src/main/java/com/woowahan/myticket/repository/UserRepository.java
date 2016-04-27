package com.woowahan.myticket.repository;

import com.woowahan.myticket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by junyoung on 2016. 4. 26..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
