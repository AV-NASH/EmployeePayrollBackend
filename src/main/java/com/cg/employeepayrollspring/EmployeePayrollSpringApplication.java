package com.cg.employeepayrollspring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Slf4j
public class EmployeePayrollSpringApplication {

    public static void main(String[] args) {
       ApplicationContext applicationContext =SpringApplication.run(EmployeePayrollSpringApplication.class, args);
        log.info("Addressbook started in {} env",applicationContext.getEnvironment().getProperty("environment") );
        log.info("addressbook DB user is {} ",applicationContext.getEnvironment().getProperty("spring.datasource.username"));
    }


}
