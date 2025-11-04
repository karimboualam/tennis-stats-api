package com.atelier.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application <b>Tennis Stats API</b>.
 *
 * Cette application Spring Boot expose une API REST permettant
 * de gérer les joueurs de tennis, leurs statistiques et les performances globales.
 *
 * L’exécution démarre un serveur web intégré (Tomcat par défaut)
 * et initialise les composants Spring (contrôleurs, services, référentiels, etc.).
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@SpringBootApplication
public class TennisStatsApiApplication {

    /**
     * Méthode principale lançant l’application Spring Boot.
     *
     * @param args les arguments passés en ligne de commande
     */
    public static void main(final String[] args) {
        SpringApplication.run(TennisStatsApiApplication.class, args);
    }
}
