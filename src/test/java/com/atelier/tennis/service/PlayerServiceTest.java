package com.atelier.tennis.service;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.entity.Stats;
import com.atelier.tennis.exception.PlayerNotFoundException;
import com.atelier.tennis.mapper.PlayerMapper;
import com.atelier.tennis.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    private PlayerRepository playerRepository;
    private PlayerMapper playerMapper;
    private PlayerService playerService;

    @BeforeEach
    void setup() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerMapper = new PlayerMapper();
        playerService = new PlayerService(playerRepository, playerMapper);
    }

    @Test
    void shouldReturnPlayersSortedByRank() {
        Player p1 = new Player();
        Stats s1 = new Stats(); s1.setRank(2); p1.setData(s1);
        Player p2 = new Player();
        Stats s2 = new Stats(); s2.setRank(1); p2.setData(s2);

        when(playerRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PlayerDTO> result = playerService.getAllPlayersSorted();

        assertEquals(1, result.get(0).getRank());
        assertEquals(2, result.get(1).getRank());
    }

    @Test
    void shouldThrowWhenPlayerNotFound() {
        when(playerRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(999L));
    }

    @Test
    void shouldCreatePlayerSuccessfully() {
        PlayerCreateDTO dto = new PlayerCreateDTO();
        dto.setFirstname("Carlos");
        dto.setLastname("Alcaraz");
        dto.setShortname("C.ALC");
        dto.setSex("M");
        dto.setPicture("pic.png");
        dto.setCountryCode("ESP");
        dto.setCountryPicture("flag.png");
        dto.setRank(1);
        dto.setPoints(5000);
        dto.setWeight(80);
        dto.setHeight(185);
        dto.setAge(22);

        when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> {
            Player p = invocation.getArgument(0);
            p.setId(10L);
            return p;
        });

        PlayerDTO result = playerService.createPlayer(dto);

        assertNotNull(result);
        assertEquals("Carlos", result.getFirstname());
        assertEquals("ESP", result.getCountryCode());
        verify(playerRepository, times(1)).save(any(Player.class));
    }
}
