package com.atelier.tennis.exception;

/**
 * Exception levée lorsqu’un joueur recherché n’existe pas dans la base de données.
 *
 * Cette exception est typiquement utilisée dans les services ou les contrôleurs
 * lorsqu’un appel à {@code PlayerRepository.findById(id)} ne renvoie aucun résultat.
 *
 * Elle est interceptée globalement par {@link GlobalExceptionHandler}
 * afin de renvoyer une réponse HTTP 404 (Not Found) au client.
 *
 * @see com.atelier.tennis.exception.GlobalExceptionHandler
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
public class PlayerNotFoundException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message précisant
     * l’identifiant du joueur non trouvé.
     *
     * @param id identifiant du joueur inexistant
     */
    public PlayerNotFoundException(final Long id) {
        super("Player not found with ID: " + id);
    }
}
