package com.atelier.tennis.controller;

import com.atelier.tennis.entity.Player;
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
    @Operation(summary = "Get all players sorted by rank")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayersSorted();
    }
}
