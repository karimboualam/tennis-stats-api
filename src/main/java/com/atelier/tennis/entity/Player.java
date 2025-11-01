package com.atelier.tennis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    private String picture;

    @Embedded
      @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "country_code")),
            @AttributeOverride(name = "picture", column = @Column(name = "country_picture"))
    })
    private Country country;

    @Embedded
    private Stats data;  
}
