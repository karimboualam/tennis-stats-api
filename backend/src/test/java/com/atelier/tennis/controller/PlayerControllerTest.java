package com.atelier.tennis.controller;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    private PlayerDTO player;

    @BeforeEach
    void setUp() {
        player = new PlayerDTO();
        player.setId(1L);
        player.setFirstname("Novak");
        player.setLastname("Djokovic");
        player.setShortname("N.DJO");
        player.setRank(2);
        player.setAge(36);
    }

    @Test
    void shouldReturnListOfPlayers() throws Exception {
        when(playerService.getAllPlayersSorted()).thenReturn(List.of(player));

        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname", is("Novak")))
                .andExpect(jsonPath("$[0].rank", is(2)));
    }

    @Test
    void shouldReturnPlayerById() throws Exception {
        when(playerService.getPlayerById(1L)).thenReturn(player);

        mockMvc.perform(get("/api/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", is("Djokovic")));
    }

    @Test
    void shouldCreatePlayerSuccessfully() throws Exception {
        PlayerCreateDTO dto = new PlayerCreateDTO();
        dto.setFirstname("Carlos");
        dto.setLastname("Alcaraz");
        dto.setShortname("C.ALC");
        dto.setSex("M");
        dto.setPicture("https://example.com/alcaraz.png");
        dto.setCountryCode("ESP");
        dto.setCountryPicture("https://example.com/spain.png");
        dto.setRank(4);
        dto.setPoints(2400);
        dto.setWeight(80);
        dto.setHeight(183);
        dto.setAge(21);

        when(playerService.createPlayer(Mockito.any(PlayerCreateDTO.class))).thenReturn(player);

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.firstname", is("Novak"))); 
    }

    @Test
void shouldReturnBadRequestWhenMissingRequiredFields() throws Exception {
    PlayerCreateDTO invalidDto = new PlayerCreateDTO();
    invalidDto.setLastname("SansPrenom"); 

    mockMvc.perform(post("/api/players")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", is("Validation failed")))
            .andExpect(jsonPath("$.messages.firstname").exists()); 
}

}
