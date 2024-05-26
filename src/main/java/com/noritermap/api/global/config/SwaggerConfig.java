package com.noritermap.api.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "놀이터맵 API", version = "GJ/v1"),
        servers = {@Server(url = "/", description = "Default Server URL")})
@Configuration
public class SwaggerConfig {

}