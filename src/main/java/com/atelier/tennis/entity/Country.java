package com.atelier.tennis.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Country {
    private String code;
    private String picture;
}
