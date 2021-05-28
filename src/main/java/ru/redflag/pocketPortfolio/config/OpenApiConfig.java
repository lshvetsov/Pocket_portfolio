package ru.redflag.pocketPortfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pocket Portfolio API")
                        .version("v 0.0.1")
                        .description("Service to contol your portfolio"));
    }
}
