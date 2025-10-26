package com.atelier.tennis.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Stats {
    private int rank;
    private int points;
    private double weight;
    private double height;
    private int age;
    private double winRatio;
}
