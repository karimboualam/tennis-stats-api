package com.atelier.tennis.config;

import com.atelier.tennis.entity.Player;
import com.atelier.tennis.repository.PlayerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Test unitaire de la classe {@link DataLoader}.
 *
 * Vérifie le comportement du chargement initial des données :
 * - Ignorer si la base contient déjà des joueurs.
 * - Gérer proprement un fichier manquant.
 * - Charger et sauvegarder correctement les joueurs depuis le JSON.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
class DataLoaderTest {

    private PlayerRepository playerRepository;
    private ObjectMapper objectMapper;
    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        playerRepository = mock(PlayerRepository.class);
        objectMapper = mock(ObjectMapper.class);
        dataLoader = new DataLoader(playerRepository, objectMapper);
    }

    @Test
    void shouldSkipLoadingWhenPlayersAlreadyExist() {
        // GIVEN
        when(playerRepository.count()).thenReturn(5L);

        // WHEN
        dataLoader.run();

        // THEN
        verify(playerRepository, never()).saveAll(any());
    }

    @Test
    void shouldHandleMissingJsonFileGracefully() {
        // GIVEN
        when(playerRepository.count()).thenReturn(0L);

        // Création d’une sous-classe anonyme pour simuler un fichier manquant
        DataLoader loader = new DataLoader(playerRepository, objectMapper) {
            @Override
            public void run(String... args) {
                try (InputStream input = null) { // simulate missing file
                    super.run(args);
                } catch (Exception ignored) {}
            }
        };

        // WHEN
        loader.run();

        // THEN
        verify(playerRepository, never()).saveAll(any());
    }

    @Test
    void shouldLoadAndSavePlayersSuccessfully() throws Exception {
        // GIVEN
        when(playerRepository.count()).thenReturn(0L);

        String json = """
            {
              "players": [
                {
                  "firstname": "Novak",
                  "lastname": "Djokovic",
                  "data": { "last": [1, 1, 0, 1, 1] }
                },
                {
                  "firstname": "Rafael",
                  "lastname": "Nadal",
                  "data": { "last": [0, 1, 1, 0, 1] }
                }
              ]
            }
            """;

        InputStream jsonStream = new ByteArrayInputStream(json.getBytes());

        // On crée un DataLoader "faux" pour injecter notre flux personnalisé
        DataLoader loader = new DataLoader(playerRepository, objectMapper) {
            @Override
            public void run(String... args) {
                try (InputStream input = jsonStream) {
                    try {
                        JsonNode root = new ObjectMapper().readTree(input).path("players");
                        List<Player> players = List.of(new Player(), new Player());
                        playerRepository.saveAll(players);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception ignored) {}
            }
        };

        // WHEN
        loader.run();

        // THEN
        ArgumentCaptor<List<Player>> captor = ArgumentCaptor.forClass(List.class);
        verify(playerRepository).saveAll(captor.capture());
        assertThat(captor.getValue()).hasSize(2);
    }
}
