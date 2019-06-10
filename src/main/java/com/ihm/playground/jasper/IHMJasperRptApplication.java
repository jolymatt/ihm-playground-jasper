package com.ihm.playground.jasper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;




/**
 * This IHMJasperRptApplication
 * @author Joly Mathew
 */
@SpringBootApplication
@EnableWebMvc
@Slf4j
public class IHMJasperRptApplication {


    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication springApplication = new SpringApplication(IHMJasperRptApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
    }


}
