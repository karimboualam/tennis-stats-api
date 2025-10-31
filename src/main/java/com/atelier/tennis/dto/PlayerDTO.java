package com.atelier.tennis.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    private String picture;
    private String countryCode;
    private String countryPicture;
    private int rank;
    private int points;
    private double weight;   
    private double height;   
    private int age;
    private double winRatio; 
}
