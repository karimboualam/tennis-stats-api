package com.atelier.tennis.service;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Country;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.entity.Stats;
import com.atelier.tennis.exception.PlayerNotFoundException;
import com.atelier.tennis.mapper.PlayerMapper;
import com.atelier.tennis.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public PlayerDTO createPlayer(PlayerCreateDTO dto) {
        Player player = new Player();

        player.setFirstname(dto.getFirstname());
        player.setLastname(dto.getLastname());
        player.setShortname(dto.getShortname());
        player.setSex(dto.getSex());
        player.setPicture(dto.getPicture());

        Country country = new Country();
        country.setCode(dto.getCountryCode());
        country.setPicture(dto.getCountryPicture());
        player.setCountry(country);

        Stats stats = new Stats();
        stats.setRank(dto.getRank());
        stats.setPoints(dto.getPoints());
        stats.setWeight(dto.getWeight() * 1000.0); 
        stats.setHeight(dto.getHeight());
        stats.setAge(dto.getAge());
        player.setData(stats);

        Player saved = playerRepository.save(player);
        return playerMapper.toDto(saved);
    }
}
