package com.atelier.tennis.service;

import com.atelier.tennis.dto.StatsResponseDTO;
import com.atelier.tennis.entity.Country;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.entity.Stats;
import com.atelier.tennis.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StatsServiceTest {

    private PlayerRepository playerRepository;
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        playerRepository = mock(PlayerRepository.class);
        statsService = new StatsService(playerRepository);
    }

    @Test
    void shouldReturnZeroStatsWhenNoPlayers() {
        when(playerRepository.findAll()).thenReturn(List.of());

        StatsResponseDTO stats = statsService.computeStats();

        assertThat(stats.getCountryWithBestWinRatio()).isEqualTo("N/A");
        assertThat(stats.getAverageBMI()).isEqualTo(0.0);
        assertThat(stats.getMedianHeight()).isEqualTo(0.0);
    }

    @Test
    void shouldComputeStatsCorrectly() {
        Player p1 = new Player();
        Country c1 = new Country(); c1.setCode("ESP"); c1.setPicture("flag.png");
        Stats s1 = new Stats();
        s1.setWinRatio(0.8);
        s1.setWeight(80000);  
        s1.setHeight(180);    
        s1.setAge(30);
        p1.setCountry(c1);
        p1.setData(s1);

        Player p2 = new Player();
        Country c2 = new Country(); c2.setCode("FRA"); c2.setPicture("flag.png");
        Stats s2 = new Stats();
        s2.setWinRatio(0.5);
        s2.setWeight(90000);  
        s2.setHeight(190);    
        s2.setAge(25);
        p2.setCountry(c2);
        p2.setData(s2);

        Player p3 = new Player();
        p3.setCountry(c1);
        Stats s3 = new Stats();
        s3.setWinRatio(0.9);
        s3.setWeight(70000);  
        s3.setHeight(170);    
        s3.setAge(22);
        p3.setData(s3);

        when(playerRepository.findAll()).thenReturn(List.of(p1, p2, p3));

        StatsResponseDTO stats = statsService.computeStats();

        assertThat(stats.getCountryWithBestWinRatio()).isEqualTo("ESP");  
        assertThat(stats.getAverageBMI()).isBetween(22.0, 25.0);         
        assertThat(stats.getMedianHeight()).isEqualTo(180.0);            
    }
}
