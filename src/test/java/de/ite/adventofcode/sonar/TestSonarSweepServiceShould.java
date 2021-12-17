package de.ite.adventofcode.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestSonarSweepServiceShould {

    private SonarSweepService sonarSweepService;

    @BeforeEach
    void setUp() {
        sonarSweepService = new SonarSweepService();
    }

    @Test
    @DisplayName("Compute changes in depth for measurements")
    void computeChangesInDepthForMeasurements() {
        String measurements = "1 2 3 4";
        String expectedResult = "+++";

        String result = sonarSweepService.computeChangesInDepthFor(measurements);

        assertThat(result).isEqualTo(expectedResult);
    }

}