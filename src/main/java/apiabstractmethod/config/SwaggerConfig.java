package apiabstractmethod.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI bankingAPI() {
        return new OpenAPI()
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
}