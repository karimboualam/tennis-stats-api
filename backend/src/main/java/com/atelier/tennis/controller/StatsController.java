package com.atelier.tennis.controller;

import com.atelier.tennis.dto.StatsResponseDTO;
import com.atelier.tennis.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST fournissant les statistiques globales sur les joueurs de tennis.
 *
 * Cette classe expose un seul endpoint permettant de calculer et de récupérer
 * les statistiques globales (moyennes, ratios de victoires, poids, taille, etc.)
 * à partir des données stockées dans la base.
 *
 * Les données sont renvoyées sous forme d’un objet {@link StatsResponseDTO}.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@RestController
@RequestMapping("/api/stats")
public class StatsController {

    /** Service métier chargé du calcul des statistiques. */
    private final StatsService statsService;

    /**
     * Constructeur d’injection du service {@link StatsService}.
     *
     * @param statsService service de calcul des statistiques globales
     */
    public StatsController(final StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * Récupère les statistiques globales de tous les joueurs enregistrés.
     *
     * @return un objet {@link StatsResponseDTO} contenant les statistiques calculées
     */
    @GetMapping
    @Operation(summary = "Obtenir les statistiques globales des joueurs de tennis")
    public StatsResponseDTO getStats() {
        return statsService.computeStats();
    }
}
