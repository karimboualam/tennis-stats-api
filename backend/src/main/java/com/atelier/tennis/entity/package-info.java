/**
 * Contient les entités JPA représentant les objets métier de l’application Tennis Stats API.
 *
 * Les classes de ce package sont directement mappées aux tables de la base de données.
 * Elles définissent la structure des données persistées, ainsi que leurs relations.
 *
 * Ce package inclut notamment :
 * - {@code Player} : entité principale représentant un joueur de tennis.
 * - {@code Country} : sous-objet embarqué contenant les informations du pays.
 * - {@code Stats} : sous-objet embarqué regroupant les statistiques sportives.
 *
 * Chaque entité est annotée avec {@link jakarta.persistence.Entity} ou {@link jakarta.persistence.Embeddable}.
 *
 * @author Karim Boualam
 * @version 1.0.0
 * @since 2025-11
 */
package com.atelier.tennis.entity;
