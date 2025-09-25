package com.health.patientservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI patientServiceOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(serverList())
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT Authorization header using the Bearer scheme. Enter 'Bearer' [space] and then your token.")));
    }

    private Info apiInfo() {
        return new Info()
                .title("Patient Service API")
                .description("RESTful API for Patient Management in Healthcare Microservices System")
                .version(appVersion)
                .contact(new Contact()
                        .name("Healthcare Development Team")
                        .email("dev-team@healthcare.com")
                        .url("https://healthcare.com/support"))
                .license(new License()
                        .name("Apache License 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));
    }

    private List<Server> serverList() {
        return List.of(
                new Server()
                        .url("http://localhost:" + serverPort)
                        .description("Local Development Server"),
                new Server()
                        .url("https://dev-api.healthcare.com/patient-service")
                        .description("Development Environment"),
                new Server()
                        .url("https://staging-api.healthcare.com/patient-service")
                        .description("Staging Environment"),
                new Server()
                        .url("https://api.healthcare.com/patient-service")
                        .description("Production Environment")
        );
    }
}