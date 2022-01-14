package com.hw6;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Api",version = "1.0",description = "Books shop web service"))
public class BookRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookRestApplication.class);
    }
}
