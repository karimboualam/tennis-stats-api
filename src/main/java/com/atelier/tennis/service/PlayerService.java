package com.atelier.tennis.service;

import com.atelier.tennis.dto.PlayerCreateDTO;
import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Country;
import com.atelier.tennis.entity.Player;
import com.atelier.tennis.entity.Stats;
import com.atelier.tennis.exception.PlayerNotFoundException;
import com.atelier.tennis.mapper.PlayerMapper;
import com.atelier.tennis.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Service métier gérant les opérations liées aux joueurs de tennis.
 *
 * Cette classe contient la logique d’accès et de manipulation des entités {@link Player},
 * tout en utilisant {@link PlayerMapper} pour convertir les données vers les objets
 * de transfert {@link PlayerDTO}.
 *
 * Les principales fonctionnalités incluent :
 * <ul>
 *   <li>Récupération triée des joueurs par classement</li>
 *   <li>Recherche d’un joueur par identifiant</li>
 *   <li>Création d’un nouveau joueur avec validation transactionnelle</li>
 * </ul>
 *
 * @see com.atelier.tennis.repository.PlayerRepository
 * @see com.atelier.tennis.mapper.PlayerMapper
 * @see com.atelier.tennis.exception.PlayerNotFoundException
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    /**
     * Constructeur injectant les dépendances requises.
     *
     * @param playerRepository le repository JPA gérant les entités {@link Player}
     * @param playerMapper le mapper responsable de la conversion entité ↔ DTO
     */
    public PlayerService(final PlayerRepository playerRepository, final PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    /**
     * Récupère la liste complète des joueurs, triés par rang (1 = meilleur joueur).
     *
     * @return une liste triée de {@link PlayerDTO}
     */
    public List<PlayerDTO> getAllPlayersSorted() {
        return playerRepository.findAll().stream()
                .sorted(Comparator.comparingInt(p -> p.getData().getRank()))
                .map(playerMapper::toDto)
                .toList();
    }

    /**
     * Recherche un joueur par son identifiant.
     *
     * @param id identifiant unique du joueur
     * @return le joueur correspondant sous forme de {@link PlayerDTO}
     * @throws PlayerNotFoundException si aucun joueur n’est trouvé
     */
    public PlayerDTO getPlayerById(final Long id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    /**
     * Crée et enregistre un nouveau joueur à partir d’un objet {@link PlayerCreateDTO}.
     *
     * Cette méthode est annotée {@link Transactional} afin d’assurer la cohérence
     * de la transaction en cas d’erreur.
     *
     * @param dto données du joueur à créer
     * @return le joueur nouvellement créé sous forme de {@link PlayerDTO}
     */
    @Transactional
    public PlayerDTO createPlayer(final PlayerCreateDTO dto) {
        Player player = new Player();

        // Affectation des informations personnelles
        player.setFirstname(dto.getFirstname());
        player.setLastname(dto.getLastname());
        player.setShortname(dto.getShortname());
        player.setSex(dto.getSex());
        player.setPicture(dto.getPicture());

        // Construction de l’objet Country
        Country country = new Country();
        country.setCode(dto.getCountryCode());
        country.setPicture(dto.getCountryPicture());
        player.setCountry(country);

        // Création et conversion des statistiques
        Stats stats = new Stats();
        stats.setRank(dto.getRank());
        stats.setPoints(dto.getPoints());
        stats.setWeight(dto.getWeight() * 1000.0); // Conversion kg → grammes
        stats.setHeight(dto.getHeight());
        stats.setAge(dto.getAge());
        player.setData(stats);

        // Persistance du joueur et conversion en DTO
        Player saved = playerRepository.save(player);
        return playerMapper.toDto(saved);
    }
}
