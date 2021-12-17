package de.ite.adventofcode.power;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class DiagnosticsReportService {

    protected int computeGammaRateFor(List<String> diagnosticsReport) {
        if(diagnosticsReport.isEmpty()) {
            return 0;
        }
        int[] ones = countOnes(diagnosticsReport);

        float limit = diagnosticsReport.size() / 2f;

        IntFunction<String> stringIntFunction = value -> value >= limit ? "1" : "0";

        return calculateValue(ones, stringIntFunction);
    }

    private int calculateValue(int[] ones, IntFunction<String> resultMapper) {
        String binaryNumber = Arrays.stream(ones).mapToObj(resultMapper).collect(Collectors.joining());
        return Integer.parseInt(binaryNumber, 2);
    }

    private int[] countOnes(List<String> diagnosticsReport) {
        int[] ones = new int[diagnosticsReport.get(0).length()];

        for(String row : diagnosticsReport) {
            for (int i=0; i<row.length(); i++) {
                if (row.charAt(i) == '1') {
                    ones[i]++;
                }
            }
        }
        return ones;
    }

    protected int computeEpsilonRateFor(List<String> diagnosticsReport) {
        if(diagnosticsReport.isEmpty()) {
            return 0;
        }
        int[] ones = countOnes(diagnosticsReport);

        float limit = diagnosticsReport.size() / 2f;

        IntFunction<String> stringIntFunction = value -> value < limit ? "1" : "0";

        return calculateValue(ones, stringIntFunction);
    }

    protected int computePowerConsumptionFor(List<String> diagnosticsReport) {
        return computeGammaRateFor(diagnosticsReport) * computeEpsilonRateFor(diagnosticsReport);
    }

}
