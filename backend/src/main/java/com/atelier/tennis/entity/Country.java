package com.atelier.tennis.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Représente un pays associé à un joueur de tennis.
 *
 * Cette classe est marquée avec {@link jakarta.persistence.Embeddable},
 * ce qui signifie qu’elle n’est pas stockée dans une table indépendante
 * mais intégrée dans une autre entité JPA, notamment {@link com.atelier.tennis.entity.Player}.
 *
 * Elle contient les informations essentielles du pays :
 * <ul>
 *   <li>Le code ISO du pays (exemple : FRA, ESP, USA)</li>
 *   <li>L’URL du drapeau du pays</li>
 * </ul>
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Data
@Embeddable
public class Country {

    /** Code ISO du pays du joueur (exemple : FRA, ESP, USA). */
    private String code;

    /** URL de l’image du drapeau du pays. */
    private String picture;
}
