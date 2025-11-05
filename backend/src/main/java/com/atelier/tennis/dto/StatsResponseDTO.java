package com.atelier.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les statistiques globales calculées à partir des données des joueurs.
 *
 * Ce DTO est utilisé pour renvoyer la réponse du service {@code /api/stats}
 * via le contrôleur {@code StatsController}. Il regroupe les principales
 * informations statistiques agrégées :
 *
 * <ul>
 *   <li>Le pays avec le meilleur ratio de victoires</li>
 *   <li>La moyenne des indices de masse corporelle (IMC)</li>
 *   <li>La taille médiane de l’ensemble des joueurs</li>
 * </ul>
 *
 * Généré automatiquement par {@link com.atelier.tennis.service.StatsService}.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponseDTO {

    /** Nom du pays présentant le meilleur ratio de victoires. */
    private String countryWithBestWinRatio;

    /** Moyenne de l'indice de masse corporelle (IMC) de tous les joueurs. */
    private double averageBMI;

    /** Taille médiane des joueurs en centimètres. */
    private double medianHeight;
}
