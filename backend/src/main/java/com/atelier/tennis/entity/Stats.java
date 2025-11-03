package com.atelier.tennis.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Représente les statistiques sportives d’un joueur de tennis.
 *
 * Cette classe est marquée avec {@link jakarta.persistence.Embeddable},
 * ce qui signifie qu’elle est intégrée dans une autre entité JPA
 * (ici {@link Player}) et ne possède pas sa propre table dans la base de données.
 *
 * Elle regroupe les données de performance individuelles d’un joueur :
 * classement, points, caractéristiques physiques et ratio de victoires.
 *
 * @see Player
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Data
@Embeddable
public class Stats {

    /** Classement mondial actuel du joueur (1 = meilleur joueur). */
    private int rank;

    /** Nombre total de points ATP/WTA du joueur. */
    private int points;

    /** Poids du joueur en kilogrammes. */
    private double weight;

    /** Taille du joueur en centimètres. */
    private double height;

    /** Âge actuel du joueur. */
    private int age;

    /** Ratio de victoires du joueur (entre 0 et 1). */
    private double winRatio;
}
