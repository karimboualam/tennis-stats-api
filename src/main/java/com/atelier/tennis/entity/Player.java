package com.atelier.tennis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Table;
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
