package com.atelier.tennis.service;

import com.atelier.tennis.dto.StatsResponseDTO;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final PlayerRepository playerRepository;

    public StatsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public StatsResponseDTO computeStats() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            return new StatsResponseDTO("N/A", 0.0, 0.0);
        }

        Map<String, Double> avgWinRatioByCountry = players.stream()
                .filter(p -> p.getData() != null && p.getCountry() != null)
                .collect(Collectors.groupingBy(
                        p -> p.getCountry().getCode(),
                        Collectors.averagingDouble(p -> p.getData().getWinRatio())
                ));

        String bestCountry = avgWinRatioByCountry.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        double averageBMI = players.stream()
                .filter(p -> p.getData() != null && p.getData().getHeight() > 0)
                .mapToDouble(p -> {
                    double weightKg = p.getData().getWeight() / 1000.0;
                    double heightM = p.getData().getHeight() / 100.0;
                    return weightKg / (heightM * heightM);
                })
                .average()
                .orElse(0.0);

      List<Double> sortedHeights = players.stream()
        .filter(p -> p.getData() != null)
        .map(p -> p.getData().getHeight())
        .sorted()
        .toList();

     double medianHeight;
        int size = sortedHeights.size();
        if (size == 0) {
            medianHeight = 0.0;
        } else if (size % 2 == 0) {
            medianHeight = (sortedHeights.get(size / 2 - 1) + sortedHeights.get(size / 2)) / 2.0;
        } else {
            medianHeight = sortedHeights.get(size / 2);
        }


        return new StatsResponseDTO(bestCountry, round(averageBMI), medianHeight);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
