package com.atelier.tennis.service;

import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.exception.PlayerNotFoundException;
import com.atelier.tennis.mapper.PlayerMapper;
import com.atelier.tennis.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDTO> getAllPlayersSorted() {
        return playerRepository.findAll().stream()
                .sorted(Comparator.comparingInt(p -> p.getData().getRank()))
                .map(playerMapper::toDto)
                .toList();
    }

    public PlayerDTO getPlayerById(Long id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }
}
