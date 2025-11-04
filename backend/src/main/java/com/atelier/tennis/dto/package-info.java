/**
 * Contient les classes de transfert de données (DTO) de l’application Tennis Stats API.
 *
 * Les DTO servent d’objets intermédiaires entre la couche de persistance et la couche REST.
 * Ils permettent de structurer les données échangées entre le client et le serveur
 * sans exposer directement les entités JPA.
 *
 * Ce package inclut notamment :
 * - {@code PlayerDTO} : représentation complète d’un joueur.
 * - {@code PlayerCreateDTO} : données nécessaires à la création d’un joueur.
 * - {@code StatsResponseDTO} : structure de réponse pour les statistiques globales.
 *
 * @author Karim Boualam
 * @version 1.0.0
 * @since 2025-11
 */
package com.atelier.tennis.dto;
