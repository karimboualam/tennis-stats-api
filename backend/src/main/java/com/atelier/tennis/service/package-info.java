/**
 * Contient la couche métier de l’application Tennis Stats API.
 *
 * Les classes de ce package implémentent la logique fonctionnelle principale
 * et orchestrent les interactions entre les contrôleurs, les mappers et les repositories.
 *
 * Ce package inclut notamment :
 * - {@code PlayerService} : gestion des opérations liées aux joueurs (création, lecture, tri).
 * - {@code StatsService} : calcul et agrégation des statistiques globales.
 *
 * Chaque service est annoté avec {@link org.springframework.stereotype.Service}
 * et peut être enrichi de transactions via {@link org.springframework.transaction.annotation.Transactional}.
 *
 * @author Karim Boualam
 * @version 1.0.0
 * @since 2025-11
 */
package com.atelier.tennis.service;
