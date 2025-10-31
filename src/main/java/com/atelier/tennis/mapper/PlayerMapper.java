package com.atelier.tennis.mapper;

import com.atelier.tennis.dto.PlayerDTO;
import com.atelier.tennis.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDTO toDto(Player player) {
        PlayerDTO dto = new PlayerDTO();

        dto.setId(player.getId());
        dto.setFirstname(player.getFirstname());
        dto.setLastname(player.getLastname());
        dto.setShortname(player.getShortname());
        dto.setSex(player.getSex());
        dto.setPicture(player.getPicture());

        
        if (player.getCountry() != null) {
            dto.setCountryCode(player.getCountry().getCode());
            dto.setCountryPicture(player.getCountry().getPicture());
        }

       
        if (player.getData() != null) {
            dto.setRank(player.getData().getRank());
            dto.setPoints(player.getData().getPoints());
            dto.setWeight(player.getData().getWeight() / 1000.0);
            dto.setHeight(player.getData().getHeight());
            dto.setAge(player.getData().getAge());
            dto.setWinRatio(player.getData().getWinRatio());
        }

        return dto;
    }
}
