package com.atelier.tennis.repository;

import com.atelier.tennis.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour l’entité {@link Player}.
 *
 * Cette interface hérite de {@link JpaRepository}, ce qui lui permet de bénéficier
 * automatiquement de toutes les opérations CRUD standards (Create, Read, Update, Delete)
 * sans implémentation explicite.
 *
 * Elle peut également être étendue ultérieurement pour ajouter des méthodes de
 * requêtes personnalisées, par exemple {@code findByCountryCode(String code)}.
 *
 * @see com.atelier.tennis.entity.Player
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
