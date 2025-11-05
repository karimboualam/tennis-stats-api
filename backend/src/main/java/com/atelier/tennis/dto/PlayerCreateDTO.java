package com.atelier.tennis.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Représente les données nécessaires à la création d’un joueur de tennis.
 *
 * Cette classe est utilisée lors de la réception d’une requête HTTP POST
 * pour créer un nouveau joueur via le contrôleur {@code PlayerController}.
 *
 * Les annotations {@link jakarta.validation.constraints.NotBlank},
 * {@link jakarta.validation.constraints.Positive} et {@link jakarta.validation.constraints.PositiveOrZero}
 * assurent la validation automatique des champs avant l’enregistrement.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Data
public class PlayerCreateDTO {

    /** Prénom du joueur. Ne peut pas être vide. */
    @NotBlank
    private String firstname;

    /** Nom de famille du joueur. Ne peut pas être vide. */
    @NotBlank
    private String lastname;

    /** Nom abrégé ou alias du joueur (ex: R.Nadal -> RNAD). */
    @NotBlank
    private String shortname;

    /** Sexe du joueur (M ou F). */
    @NotBlank
    private String sex;

    /** URL de la photo du joueur. */
    @NotBlank
    private String picture;

    /** Code ISO du pays du joueur (ex: FRA, ESP, USA). */
    @NotBlank
    private String countryCode;

    /** URL de l’image du drapeau du pays. */
    @NotBlank
    private String countryPicture;

    /** Classement mondial du joueur (1 = meilleur). */
    @Positive
    private int rank;

    /** Nombre total de points ATP/WTA. */
    @PositiveOrZero
    private int points;

    /** Poids du joueur en kilogrammes. */
    @Positive
    private double weight;

    /** Taille du joueur en centimètres. */
    @Positive
    private double height;

    /** Âge du joueur. */
    @Positive
    private int age;
}
