package de.ite.adventofcode;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.List;

public class TestInputUtils {

    @SneakyThrows
    private static List<String> readInput(String fileName) {
        return Files.readAllLines(ResourceUtils.getFile(String.format("classpath:%s", fileName)).toPath());
    }

    @SneakyThrows
    public static List<String> readInputForSonarLog() {
        return readInput("day1-sonarLog.txt");
    }

    @SneakyThrows
    public static List<String> readInputForSubmarineMoves() {
        return readInput("day2-submarineMoves.txt");
    }

    @SneakyThrows
    public static List<String> readInputForDiagnosticReport() {
        return readInput("day3-diagnosticsReport.txt");
    }

    public static int expectedResultForSonarLog() {
        return 1832;
    }

    public static int expectedResultForSubmarineMoves() {
        return 2120749;
    }

    public static int expectedResultForDiagnosticsReport() {
        return 2250414;
    }
}
