package com.atelier.tennis.controller;

import com.atelier.tennis.dto.StatsResponseDTO;
import com.atelier.tennis.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    @Operation(summary = "Get global tennis statistics")
    public StatsResponseDTO getStats() {
        return statsService.computeStats();
    }
}
