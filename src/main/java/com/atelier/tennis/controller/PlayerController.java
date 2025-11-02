package com.atelier.tennis.controller;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{id}")
    @Operation(summary = "Get player by ID")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

     @PostMapping
    @Operation(summary = "Create a new player")
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerCreateDTO dto) {
        PlayerDTO createdPlayer = playerService.createPlayer(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPlayer.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdPlayer);
    }
}
