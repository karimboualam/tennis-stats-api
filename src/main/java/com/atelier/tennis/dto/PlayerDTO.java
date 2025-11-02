package com.atelier.tennis.dto;

import lombok.Data;

/**
 * Représente les données transférées d’un joueur de tennis.
 *
 * Ce DTO (Data Transfer Object) est utilisé pour renvoyer les informations
 * d’un joueur dans les réponses HTTP de l’API, par exemple lors de la récupération
 * d’un joueur ou de la liste complète des joueurs.
 *
 * Contrairement à {@link PlayerCreateDTO}, cette classe contient également
 * l’identifiant unique du joueur et son ratio de victoires calculé.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Data
public class PlayerDTO {

    /** Identifiant unique du joueur. */
    private Long id;

    /** Prénom du joueur. */
    private String firstname;

    /** Nom de famille du joueur. */
    private String lastname;

    /** Nom abrégé ou alias du joueur (ex: R.Nadal → RNAD). */
    private String shortname;

    /** Sexe du joueur (M ou F). */
    private String sex;

    /** URL de la photo du joueur. */
    private String picture;

    /** Code ISO du pays du joueur (ex: FRA, ESP, USA). */
    private String countryCode;

    /** URL de l’image du drapeau du pays. */
    private String countryPicture;

    /** Classement mondial du joueur (1 = meilleur). */
    private int rank;

    /** Nombre total de points ATP/WTA. */
    private int points;

    /** Poids du joueur en kilogrammes. */
    private double weight;

    /** Taille du joueur en centimètres. */
    private double height;

    /** Âge du joueur. */
    private int age;

    /** Ratio de victoires du joueur (valeur entre 0.0 et 1.0). */
    private double winRatio;
}
