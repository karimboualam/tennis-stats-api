package com.atelier.tennis.mapper;

import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Player;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de la conversion entre les entités {@link Player}
 * et les objets de transfert de données {@link PlayerDTO}.
 *
 * Cette classe permet d’isoler la logique de transformation entre la couche
 * de persistance (entités JPA) et la couche d’exposition (DTO REST).
 *
 * Elle est annotée {@link Component} afin d’être injectée automatiquement
 * dans les services via le mécanisme d’injection de dépendance Spring.
 *
 * @see com.atelier.tennis.entity.Player
 * @see com.atelier.tennis.dto.PlayerDTO
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Component
public class PlayerMapper {

    /**
     * Convertit une entité {@link Player} en objet {@link PlayerDTO}.
     *
     * Cette méthode copie les informations principales du joueur,
     * y compris ses données physiques et statistiques.
     *
     * Les conversions spécifiques incluent :
     * <ul>
     *   <li>Le poids est converti de grammes en kilogrammes (division par 1000.0)</li>
     *   <li>Les informations de pays et de statistiques sont mappées si présentes</li>
     * </ul>
     *
     * @param player l’entité {@link Player} à convertir
     * @return un {@link PlayerDTO} contenant les données prêtes pour la couche REST
     */
    public PlayerDTO toDto(final Player player) {
        PlayerDTO dto = new PlayerDTO();

        // Informations générales
        dto.setId(player.getId());
        dto.setFirstname(player.getFirstname());
        dto.setLastname(player.getLastname());
        dto.setShortname(player.getShortname());
        dto.setSex(player.getSex());
        dto.setPicture(player.getPicture());

        // Informations de pays
        if (player.getCountry() != null) {
            dto.setCountryCode(player.getCountry().getCode());
            dto.setCountryPicture(player.getCountry().getPicture());
        }

        // Données statistiques
        if (player.getData() != null) {
            dto.setRank(player.getData().getRank());
            dto.setPoints(player.getData().getPoints());
            dto.setWeight(player.getData().getWeight() / 1000.0); // Conversion g → kg
            dto.setHeight(player.getData().getHeight());
            dto.setAge(player.getData().getAge());
            dto.setWinRatio(player.getData().getWinRatio());
        }

        return dto;
    }
}
