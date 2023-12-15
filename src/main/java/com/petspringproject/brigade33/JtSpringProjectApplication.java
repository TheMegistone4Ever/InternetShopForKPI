package com.petspringproject.brigade33;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Головний клас, який запускає додаток Spring Boot, зроблено: Brigade 33.
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class JtSpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtSpringProjectApplication.class, args);
    }

}
