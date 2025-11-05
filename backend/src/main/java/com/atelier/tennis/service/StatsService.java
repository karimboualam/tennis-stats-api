package com.atelier.tennis.service;

import com.atelier.tennis.dto.StatsResponseDTO;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.repository.PlayerRepository;
import com.atelier.tennis.util.MathUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service métier chargé du calcul des statistiques globales sur les joueurs de tennis.
 *
 * Cette classe regroupe la logique d’analyse des données provenant de la base,
 * notamment les indicateurs de performance et les mesures physiques.
 *
 * Les statistiques calculées incluent :
 * <ul>
 *   <li>Le pays ayant le meilleur ratio de victoires moyen</li>
 *   <li>L’indice de masse corporelle (IMC) moyen des joueurs</li>
 *   <li>La taille médiane (en cm) de l’ensemble des joueurs</li>
 * </ul>
 *
 * @see com.atelier.tennis.dto.StatsResponseDTO
 * @see com.atelier.tennis.repository.PlayerRepository
 * @see com.atelier.tennis.util.MathUtils
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Service
public class StatsService {

    private final PlayerRepository playerRepository;

    /**
     * Constructeur injectant le repository nécessaire à la récupération des données.
     *
     * @param playerRepository le repository JPA gérant les entités {@link Player}
     */
    public StatsService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Calcule et agrège les statistiques globales sur les joueurs.
     *
     * Les valeurs retournées sont arrondies et structurées dans un objet
     * {@link StatsResponseDTO} pour exposition via l’API.
     *
     * Détails des calculs :
     * <ul>
     *   <li><b>Best country</b> : pays avec le plus haut ratio de victoires moyen.</li>
     *   <li><b>Average BMI</b> : moyenne de l’IMC calculé pour chaque joueur (poids/taille²).</li>
     *   <li><b>Median height</b> : médiane des tailles des joueurs (en cm).</li>
     * </ul>
     *
     * @return un objet {@link StatsResponseDTO} contenant les statistiques globales
     */
    public StatsResponseDTO computeStats() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            return new StatsResponseDTO("N/A", 0.0, 0.0);
        }

        // Calcul du meilleur pays selon le ratio de victoires moyen
        Map<String, Double> avgWinRatioByCountry = players.stream()
                .filter(p -> p.getData() != null && p.getCountry() != null)
                .collect(Collectors.groupingBy(
                        p -> p.getCountry().getCode(),
                        Collectors.averagingDouble(p -> p.getData().getWinRatio())
                ));

        String bestCountry = avgWinRatioByCountry.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        // Calcul de la moyenne d’IMC (poids en kg / taille² en mètres)
        double averageBMI = players.stream()
                .filter(p -> p.getData() != null && p.getData().getHeight() > 0)
                .mapToDouble(p -> {
                    double weightKg = p.getData().getWeight() / 1000.0;
                    double heightM = p.getData().getHeight() / 100.0;
                    return weightKg / (heightM * heightM);
                })
                .average()
                .orElse(0.0);

        // Calcul de la médiane des tailles
        double medianHeight = MathUtils.median(players.stream()
                .filter(p -> p.getData() != null)
                .map(p -> (double) p.getData().getHeight())
                .sorted()
                .toList());

        return new StatsResponseDTO(
                bestCountry,
                MathUtils.round(averageBMI, 2),
                medianHeight
        );
    }
}
