package com.atelier.tennis.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PlayerCreateDTO {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String shortname;

    @NotBlank
    private String sex;

    @NotBlank
    private String picture;

    @NotBlank
    private String countryCode;

    @NotBlank
    private String countryPicture;

    @Positive
    private int rank;

    @PositiveOrZero
    private int points;

    @Positive
    private double weight;

    @Positive
    private double height;

    @Positive
    private int age;
}
