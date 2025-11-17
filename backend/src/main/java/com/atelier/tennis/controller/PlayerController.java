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

/**
 * Contrôleur REST gérant les opérations liées aux joueurs de tennis.
 *
 * Cette classe expose les endpoints permettant de :
 * <ul>
 *     <li>Récupérer la liste complète des joueurs triés par classement.</li>
 *     <li>Consulter un joueur par son identifiant unique.</li>
 *     <li>Créer un nouveau joueur dans la base de données.</li>
 * </ul>
 *
 * Toutes les réponses sont conformes au format JSON.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    /** Service métier pour la gestion des joueurs. */
    private final PlayerService playerService;

    /**
     * Constructeur d’injection du service {@link PlayerService}.
     *
     * @param playerService service métier des joueurs
     */
    public PlayerController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Récupère la liste de tous les joueurs triés par classement (1 = meilleur).
     *
     * @return la liste triée des joueurs sous forme de {@link PlayerDTO}
     */
    @GetMapping
    @Operation(summary = "Obtenir tous les joueurs triés par classement (1 = meilleur)")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayersSorted();
    }

    /**
     * Récupère un joueur spécifique en fonction de son identifiant.
     *
     * @param id identifiant unique du joueur
     * @return le joueur correspondant sous forme de {@link PlayerDTO}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un joueur par son identifiant")
    public PlayerDTO getPlayerById(@PathVariable final Long id) {
        return playerService.getPlayerById(id);
    }

    /**
     * Crée un nouveau joueur à partir des informations fournies dans la requête.
     *
     * En cas de succès, la réponse inclut l’en-tête {@code Location}
     * pointant vers la ressource nouvellement créée.
     *
     * @param dto données du joueur à créer
     * @return la réponse HTTP contenant le joueur créé et son URI
     */
    @PostMapping
    @Operation(summary = "Créer un nouveau joueur")
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody final PlayerCreateDTO dto) {
        PlayerDTO createdPlayer = playerService.createPlayer(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPlayer.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdPlayer);
    }
}
