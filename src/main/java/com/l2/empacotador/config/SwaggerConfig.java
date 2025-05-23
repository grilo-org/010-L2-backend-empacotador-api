package com.l2.empacotador.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Empacotamento do Seu Manoel")
                        .version("1.0.0")
                        .description("Esta API determina em quais caixas os produtos de cada pedido devem ser embalados."));
    }
}
