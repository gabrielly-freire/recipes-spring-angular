package imd.ufrn.foodrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .addServersItem(new Server().url("/api").description("Contexto da aplicação"))
                .info(new Info()
                        .title("Food Recipes API")
                        .description("API para gerenciamento de receitas")
                        .version("v1")
                );
    }
}