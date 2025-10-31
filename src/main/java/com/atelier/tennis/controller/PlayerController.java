package com.atelier.tennis.controller;

import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    @Operation(summary = "Get all players sorted by rank (1 = best)")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayersSorted();
    }
}
