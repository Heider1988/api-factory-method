package apiabstractmethod.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI bankingAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:" + serverPort)))
                .info(new Info()
                        .title("Banking API with Abstract Method Pattern")
                        .description("A simple API for banking operations using the Abstract Method design pattern")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Banking API Team")
                                .email("contact@bankingapi.com")
                                .url("https://bankingapi.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("apiabstractmethod.controller")
                .pathsToMatch("/api/**")
                .build();
    }
}
