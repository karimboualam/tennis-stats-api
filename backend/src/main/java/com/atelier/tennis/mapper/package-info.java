/**
 * Contient les classes responsables de la conversion entre les entités JPA et les objets DTO.
 *
 * Les mappers servent d’intermédiaires entre la couche de persistance et la couche REST.
 * Ils permettent de séparer la logique de transformation des données, évitant ainsi
 * la duplication de code dans les services.
 *
 * Ce package inclut notamment :
 * - {@code PlayerMapper} : conversion entre {@code Player} et {@code PlayerDTO}.
 *
 * Chaque mapper est annoté avec {@link org.springframework.stereotype.Component}
 * afin d’être automatiquement géré par le conteneur Spring.
 *
 * @author Karim Boualam
 * @version 1.0.0
 * @since 2025-11
 */
package com.atelier.tennis.mapper;
