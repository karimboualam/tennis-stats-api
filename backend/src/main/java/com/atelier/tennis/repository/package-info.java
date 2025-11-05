/**
 * Contient les interfaces d’accès aux données pour l’application Tennis Stats API.
 *
 * Ces interfaces étendent {@link org.springframework.data.jpa.repository.JpaRepository}
 * afin de fournir automatiquement les opérations CRUD sur les entités.
 *
 * Ce package inclut notamment :
 * - {@code PlayerRepository} : gestion des accès à la table des joueurs.
 *
 * Les repositories sont utilisés par la couche service pour interagir
 * avec la base de données de manière abstraite et typée.
 *
 * @author Karim Boualam
 * @version 1.0.0
 * @since 2025-11
 */
package com.atelier.tennis.repository;
