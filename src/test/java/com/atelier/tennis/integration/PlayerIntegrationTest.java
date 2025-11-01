package com.atelier.tennis.integration;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  
class PlayerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void cleanDatabase() {
        playerRepository.deleteAll();
    }

    @Test
    void shouldCreateAndRetrievePlayerSuccessfully() throws Exception {
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

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.firstname", is("Carlos")));

        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastname", is("Alcaraz")))
                .andExpect(jsonPath("$[0].rank", is(4)));
    }

    @Test
    void shouldReturnStatsAfterDataLoaded() throws Exception {
        PlayerCreateDTO p1 = new PlayerCreateDTO();
        p1.setFirstname("Novak");
        p1.setLastname("Djokovic");
        p1.setShortname("N.DJO");
        p1.setSex("M");
        p1.setPicture("https://example.com/djokovic.png");
        p1.setCountryCode("SRB");
        p1.setCountryPicture("https://example.com/serbia.png");
        p1.setRank(1);
        p1.setPoints(2500);
        p1.setWeight(80);
        p1.setHeight(188);
        p1.setAge(36);

        PlayerCreateDTO p2 = new PlayerCreateDTO();
        p2.setFirstname("Rafael");
        p2.setLastname("Nadal");
        p2.setShortname("R.NAD");
        p2.setSex("M");
        p2.setPicture("https://example.com/nadal.png");
        p2.setCountryCode("ESP");
        p2.setCountryPicture("https://example.com/spain.png");
        p2.setRank(2);
        p2.setPoints(2400);
        p2.setWeight(85);
        p2.setHeight(185);
        p2.setAge(37);

        mockMvc.perform(post("/api/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryWithBestWinRatio", not(emptyString())))
                .andExpect(jsonPath("$.averageBMI", greaterThan(0.0)))
                .andExpect(jsonPath("$.medianHeight", greaterThan(0.0)));
    }
}
