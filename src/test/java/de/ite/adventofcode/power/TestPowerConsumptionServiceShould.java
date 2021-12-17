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
        String diagnosticReport =
                "00000\n" +
                "11111\n" +
                "00000";

        int gammaRate = powerConsumptionService.computeGammaRateFor(diagnosticReport);

        int expectedResult = 0;
        assertThat(gammaRate).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Compute Epsilon rate")
    void computeEpsilonRate() {
        String diagnosticReport =
                "00000\n" +
                "11111\n" +
                "00000";

        int gammaRate = powerConsumptionService.computeEpsilonRateFor(diagnosticReport);

        int expectedResult = 31;
        assertThat(gammaRate).isEqualTo(expectedResult);
    }
}