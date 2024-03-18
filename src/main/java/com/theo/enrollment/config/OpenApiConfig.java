package com.theo.enrollment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Enrollment API")
                        .description("Enrollment Restful API.")
                        .version("1.0").contact(new Contact().name("Theo Sihotang"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }
}
