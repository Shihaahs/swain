package com.swain;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ComponentScan(basePackages = { "com.swain.core.dal", "com.swain.core.service","com.swain.core.common","com.swain.web"})
@MapperScan(basePackages = {"com.swain.core.dal.dao"})
@EnableSwagger2Doc
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class },scanBasePackages = "com.swain")
public class SwainApplication {
    private final static Logger logger = LoggerFactory.getLogger(SwainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwainApplication.class, args);
        logger.info("--- Swain started ---> http://localhost:8079/");
    }

}
