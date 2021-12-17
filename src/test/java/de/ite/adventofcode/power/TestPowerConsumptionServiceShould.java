package de.ite.adventofcode.power;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPowerConsumptionServiceShould {

    private PowerConsumptionService powerConsumptionService;

    @BeforeEach
    void setUp() {
        powerConsumptionService = new PowerConsumptionService();
    }

    @Test
    @DisplayName("Compute Gamma rate")
    void computeGammaRate() {
        String diagnosticReport = "00000 11111 00000";

        String gammaRate = powerConsumptionService.computeGammaRateFor(diagnosticReport);

        String expectedResult = "00000";
        assertThat(gammaRate).isEqualTo(expectedResult);
    }
}