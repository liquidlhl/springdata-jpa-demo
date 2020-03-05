package com.st.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringdataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataJpaApplication.class, args);
    }

}
