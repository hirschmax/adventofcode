package de.ite.adventofcode.sonar;

import de.ite.adventofcode.TestInputUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestSonarLogServiceShould {

    private SonarLogService sonarLogService;

    @BeforeEach
    void setUp() {
        sonarLogService = new SonarLogService();
    }

    @Test
    @DisplayName("Compute number of times depth increases")
    void computeChangesInDepthForMeasurements() {
        List<String> sonarLog = List.of("1", "2", "3", "4");

        int result = sonarLogService.computeNumberOfTimesDepthIncreasesFor(sonarLog);

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Final test for SonarLogService")
    void finalTestForSonarLogService() {
        List<String> sonarLog = TestInputUtils.readInputForSonarLog();

        int result = sonarLogService.computeNumberOfTimesDepthIncreasesFor(sonarLog);

        assertThat(result).isEqualTo(TestInputUtils.expectedResultForSonarLog());
    }
}