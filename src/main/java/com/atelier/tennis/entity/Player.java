package com.atelier.tennis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "players")
public class Player {

    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    private String picture;

    @Embedded
    private Country country;

    @Embedded
    private Stats data;  
}
