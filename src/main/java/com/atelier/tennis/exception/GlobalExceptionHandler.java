package com.atelier.tennis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Gestion centralisée des exceptions pour l’application Tennis Stats API.
 *
 * Cette classe intercepte les exceptions levées dans les contrôleurs REST
 * et retourne une réponse HTTP structurée, conforme au format JSON standard.
 *
 * Les exceptions prises en charge incluent :
 * <ul>
 *   <li>{@link PlayerNotFoundException} → renvoie une erreur 404 (Not Found)</li>
 *   <li>{@link MethodArgumentNotValidException} → renvoie une erreur 400 (Bad Request)</li>
 * </ul>
 *
 * Chaque réponse contient :
 * <ul>
 *   <li>un timestamp</li>
 *   <li>le code HTTP</li>
 *   <li>le message d’erreur</li>
 *   <li>les détails éventuels des champs invalides</li>
 * </ul>
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les exceptions {@link PlayerNotFoundException} lorsque
     * l’identifiant du joueur recherché n’existe pas dans la base de données.
     *
     * @param ex exception levée contenant l’ID non trouvé
     * @return une réponse HTTP 404 contenant les détails de l’erreur
     */
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePlayerNotFound(final PlayerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", ex.getMessage()
        ));
    }

    /**
     * Gère les erreurs de validation provenant de la couche {@code @Valid}.
     *
     * Cette méthode collecte les messages d’erreur pour chaque champ invalide
     * et les renvoie dans une structure JSON claire et lisible.
     *
     * @param ex exception de validation contenant les erreurs de champs
     * @return une réponse HTTP 400 contenant les détails de validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(final MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage(),
                        (msg1, msg2) -> msg1
                ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Validation failed",
                "messages", errors
        ));
    }
}
