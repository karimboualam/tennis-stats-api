package com.atelier.tennis.service;

import com.atelier.tennis.entity.Player;
import com.atelier.tennis.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayersSorted() {
        return playerRepository.findAll().stream()
                .sorted(Comparator.comparingInt(p -> p.getData().getRank()))
                .toList();
    }
}
