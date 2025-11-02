package com.atelier.tennis.config;

import com.atelier.tennis.entity.Player;
import com.atelier.tennis.repository.PlayerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Composant responsable du chargement initial des données de joueurs dans la base de données.
 *
 * Ce chargeur est exécuté automatiquement au démarrage de l’application
 * lorsque le profil actif est {@code dev}. Il lit le fichier JSON
 * {@code headtohead.json} depuis les ressources et insère les joueurs
 * dans la base si celle-ci est vide.
 *
 * Les statistiques de victoires/défaites sont calculées à partir
 * du tableau {@code data.last} présent dans le fichier source.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Slf4j
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    /** Référentiel JPA pour la gestion des entités {@link Player}. */
    private final PlayerRepository playerRepository;

    /** Utilitaire Jackson pour la désérialisation du fichier JSON. */
    private final ObjectMapper objectMapper;

    /**
     * Constructeur d’injection des dépendances principales.
     *
     * @param playerRepository le référentiel de joueurs
     * @param objectMapper l’utilitaire de conversion JSON
     */
    public DataLoader(final PlayerRepository playerRepository, final ObjectMapper objectMapper) {
        this.playerRepository = playerRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Méthode exécutée au démarrage de l’application.
     *
     * Elle charge les données de joueurs depuis le fichier
     * {@code /data/headtohead.json} et les enregistre dans la base.
     * Si des joueurs existent déjà, le chargement est ignoré.
     *
     * @param args arguments de la ligne de commande
     */
    @Override
    public void run(final String... args) {
        if (playerRepository.count() > 0) {
            log.info("Players already loaded, skipping initialization.");
            return;
        }

        try (InputStream input = getClass().getResourceAsStream("/data/headtohead.json")) {
            if (input == null) {
                log.error("headtohead.json file not found!");
                return;
            }

            JsonNode root = objectMapper.readTree(input).path("players");
            List<Player> players = new ArrayList<>();

            for (JsonNode node : root) {
                Player player = objectMapper.treeToValue(node, Player.class);

                // Calcul du ratio de victoire basé sur la liste des derniers matchs
                JsonNode lastNode = node.path("data").path("last");
                if (lastNode.isArray()) {
                    long wins = 0;
                    for (JsonNode result : lastNode) {
                        if (result.asInt() == 1) {
                            wins++;
                        }
                    }
                    double winRatio = (double) wins / lastNode.size();
                    player.getData().setWinRatio(winRatio);
                }

                players.add(player);
            }

            playerRepository.saveAll(players);
            log.info("Players loaded successfully: {}", players.size());

        } catch (Exception e) {
            log.error("Error loading players data: {}", e.getMessage(), e);
        }
    }
}
