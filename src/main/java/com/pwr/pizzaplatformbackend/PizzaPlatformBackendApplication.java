package com.pwr.pizzaplatformbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class PizzaPlatformBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaPlatformBackendApplication.class, args);
    }

}
