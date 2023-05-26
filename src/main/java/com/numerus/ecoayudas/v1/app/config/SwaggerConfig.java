package com.numerus.ecoayudas.v1.app.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

@SecurityScheme(name = "Bearer Authentication", scheme = "bearer", type = SecuritySchemeType.HTTP,bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {

        return GroupedOpenApi
                .builder()
                .group("apiGroup")
                .packagesToScan("com.numerus.ecoayudas")
                .build();
    }


}
