package com.atelier.tennis.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Représente un joueur de tennis enregistré dans la base de données.
 *
 * Cette entité est mappée sur la table {@code players}. Elle contient
 * les informations d'identité, le pays d’origine et les statistiques
 * sportives du joueur.
 *
 * Les données de pays sont stockées via la classe {@link Country}
 * (embeddable), et les statistiques via {@link Stats}.
 *
 * @see Country
 * @see Stats
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Entity
@Data
@Table(name = "players")
public class Player {

    /** Identifiant unique du joueur (clé primaire). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Prénom du joueur. */
    private String firstname;

    /** Nom de famille du joueur. */
    private String lastname;

    /** Nom abrégé du joueur (exemple : R.Nadal → RNAD). */
    private String shortname;

    /** Sexe du joueur (M pour homme, F pour femme). */
    private String sex;

    /** URL de la photo du joueur. */
    private String picture;

    /**
     * Informations sur le pays du joueur.
     *
     * Ces données sont embarquées dans la table {@code players}
     * via les colonnes {@code country_code} et {@code country_picture}.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "country_code")),
            @AttributeOverride(name = "picture", column = @Column(name = "country_picture"))
    })
    private Country country;

    /**
     * Données statistiques du joueur (classe {@link Stats}).
     *
     * Inclut par exemple la taille, le poids, le classement, les points ATP/WTA,
     * et le ratio de victoires.
     */
    @Embedded
    private Stats data;
}
