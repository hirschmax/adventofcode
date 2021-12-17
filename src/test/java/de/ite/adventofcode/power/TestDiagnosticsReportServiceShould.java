package de.ite.adventofcode.power;

import de.ite.adventofcode.TestInputUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestDiagnosticsReportServiceShould {

    private DiagnosticsReportService diagnosticsReportService;

    @BeforeEach
    void setUp() {
        diagnosticsReportService = new DiagnosticsReportService();
    }

    @Test
    @DisplayName("Compute Gamma rate for diagnostics report")
    void computeGammaRate_emptyList() {
        List<String> diagnosticsReport = List.of();
        int expectedResult = 0;

        int gammaRate = diagnosticsReportService.computeGammaRateFor(diagnosticsReport);

        assertThat(gammaRate).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Compute Gamma rate for diagnostics report")
    void computeGammaRate_five() {
        List<String> diagnosticsReport = List.of("101");
        int expectedResult = 5;

        int gammaRate = diagnosticsReportService.computeGammaRateFor(diagnosticsReport);

        assertThat(gammaRate).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Compute Gamma rate for diagnostics report")
    void computeGammaRate_7() {
        List<String> diagnosticsReport = List.of("111");
        int expectedResult = 7;

        int gammaRate = diagnosticsReportService.computeGammaRateFor(diagnosticsReport);

        assertThat(gammaRate).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Compute Gamma rate for diagnostics report")
    void computeGammaRate_5() {
        List<String> diagnosticsReport = List.of(
                "111",
                "101",
                "000"
        );
        int expectedResult = Integer.parseInt("101", 2);

        int gammaRate = diagnosticsReportService.computeGammaRateFor(diagnosticsReport);

        assertThat(gammaRate).isEqualTo(expectedResult);
    }


    @Test
    @DisplayName("Compute Epsilon rate")
    void computeEpsilonRate() {
        List<String> diagnosticsReport = List.of(
                "000",
                "111",
                "000"
        );
        int expectedResult = 7;

        int epsilonRate = diagnosticsReportService.computeEpsilonRateFor(diagnosticsReport);

        assertThat(epsilonRate).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Final test for PowerConsumptionService")
    void finalTestForPowerConsumption() {
        List<String> inputFromFile = TestInputUtils.readInputForDiagnosticReport();

        int gammaRate = diagnosticsReportService.computeGammaRateFor(inputFromFile);
        int epsilonRate = diagnosticsReportService.computeEpsilonRateFor(inputFromFile);
        int powerConsumption = diagnosticsReportService.computePowerConsumptionFor(inputFromFile);

        AssertionsForClassTypes.assertThat(gammaRate).isEqualTo(TestInputUtils.expectedResultForDiagnosticsReportGamma());
        AssertionsForClassTypes.assertThat(epsilonRate).isEqualTo(TestInputUtils.expectedResultForDiagnosticsReportEpsilon());
        AssertionsForClassTypes.assertThat(powerConsumption).isEqualTo(TestInputUtils.expectedResultForDiagnosticsReport());
    }
}