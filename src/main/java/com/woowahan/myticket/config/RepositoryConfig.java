package com.woowahan.myticket.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by junyoung on 2016. 4. 22..
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.woowahan.myticket.entity"})
@EnableJpaRepositories(basePackages = {"com.woowahan.myticket.repository"})
@EnableTransactionManagement
public class RepositoryConfig {
}
