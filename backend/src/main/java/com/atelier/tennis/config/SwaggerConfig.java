package com.atelier.tennis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration de la documentation OpenAPI / Swagger pour l’application Tennis Stats API.
 *
 * Cette classe définit la configuration principale de la documentation interactive,
 * accessible via l’interface Swagger UI :
 * {@code http://localhost:8080/swagger-ui.html}.
 *
 * L’objectif est de fournir une interface graphique simple permettant
 * d’explorer et de tester les endpoints REST disponibles dans l’application.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configure et expose les métadonnées de l’API pour Swagger/OpenAPI.
     *
     * @return une instance d’{@link OpenAPI} contenant le titre, la description et la version de l’API
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tennis Stats API")
                        .description("API REST permettant de consulter les joueurs de tennis et leurs statistiques.")
                        .version("1.0.0"));
    }
}
