package com.atelier.tennis.config;

import com.atelier.tennis.entity.Player;
import com.atelier.tennis.entity.Stats;
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

@Slf4j
@Component
@Profile("dev") 
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(PlayerRepository playerRepository, ObjectMapper objectMapper) {
        this.playerRepository = playerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() > 0) {
            log.info(" Players already loaded, skipping initialization.");
            return;
        }

        try (InputStream input = getClass().getResourceAsStream("/data/headtohead.json")) {
            if (input == null) {
                log.error(" headtohead.json file not found!");
                return;
            }

            JsonNode root = objectMapper.readTree(input).path("players");
            List<Player> players = new ArrayList<>();

            for (JsonNode node : root) {
                Player player = objectMapper.treeToValue(node, Player.class);

                JsonNode lastNode = node.path("data").path("last");
                if (lastNode.isArray()) {
                    long wins = 0;
                    for (JsonNode result : lastNode) {
                        if (result.asInt() == 1) wins++;
                    }
                    double winRatio = (double) wins / lastNode.size();
                    player.getData().setWinRatio(winRatio);
                }

                players.add(player);
            }

            playerRepository.saveAll(players);
            log.info(" Players loaded successfully: {}", players.size());

        } catch (Exception e) {
            log.error(" Error loading players data: {}", e.getMessage(), e);
        }
    }
}
