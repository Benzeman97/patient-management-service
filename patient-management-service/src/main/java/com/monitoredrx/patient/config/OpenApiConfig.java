package com.monitoredrx.patient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Management API")
                        .version("1.x")
                        .description("API for managing patients")
                        .contact(new Contact().name("Nafaz").email("nafaz@gmail.com"))
                )
                .addServersItem(new Server().url("http://localhost:8195"));
    }
}
