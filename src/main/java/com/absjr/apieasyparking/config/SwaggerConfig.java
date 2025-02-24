package com.absjr.apieasyparking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
         return  new OpenAPI()
                 .info(new Info()
                         .title("Easy Parking")
                         .version("1.0.0")
                         .description("Manager parking")
                         .contact(new Contact()
                                 .name("Arnaldo Junior")
                                 .url("https://github.com/absjuniordev")
                                 .email("abs.junnior@hotmail.com")
                         )
                 );
    }

}
