package com.atelier.tennis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire de la configuration Swagger/OpenAPI.
 *
 * Cette classe vérifie que la configuration OpenAPI est correctement initialisée
 * et contient les métadonnées attendues (titre, description, version).
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
class SwaggerConfigTest {

    @Test
    void shouldCreateOpenApiBeanWithExpectedInfo() {
        // GIVEN
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // WHEN
        OpenAPI openAPI = swaggerConfig.apiInfo();
        Info info = openAPI.getInfo();

        // THEN
        assertThat(openAPI).isNotNull();
        assertThat(info).isNotNull();
        assertThat(info.getTitle()).isEqualTo("Tennis Stats API");
        assertThat(info.getDescription()).contains("joueurs de tennis");
        assertThat(info.getVersion()).isEqualTo("1.0.0");
    }
}
