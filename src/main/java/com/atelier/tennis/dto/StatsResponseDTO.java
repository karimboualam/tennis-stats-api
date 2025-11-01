package com.atelier.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponseDTO {
    private String countryWithBestWinRatio;
    private double averageBMI;
    private double medianHeight;
}
