package de.ite.adventofcode.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SonarServiceShould {

    private SonarService sonarService;

    @BeforeEach
    void setUp() {
        sonarService = new SonarService();
    }

    @Test
    @DisplayName("")
    void computeChangesInDepthForMeasurements() {
        String measurements = "1 2 3 4";
        String expectedResult = "+++";

        String result = sonarService.computeChangesInDepthFor(measurements);

        assertThat(result).isEqualTo(expectedResult);
    }

}