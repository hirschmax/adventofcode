package de.ite.adventofcode.day06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StartOfPackageMarkerCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
            "nppdvjthqldpwncqszvftbrmjlhg,6",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"
    })
    void solveFirstTask(String signal, String result) {
        int expectedResult = Integer.parseInt(result);
        StartOfPackageMarkerCalculator startOfPackageMarkerCalculator = new StartOfPackageMarkerCalculator(List.of(signal));
        int marker = startOfPackageMarkerCalculator.solveFirstTask();
        assertThat(marker).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
            "nppdvjthqldpwncqszvftbrmjlhg,23",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"
    })
    void solveSecondTask(String signal, String result) {
        int expectedResult = Integer.parseInt(result);
        StartOfPackageMarkerCalculator startOfPackageMarkerCalculator = new StartOfPackageMarkerCalculator(List.of(signal));
        int marker = startOfPackageMarkerCalculator.solveSecondTask();
        assertThat(marker).isEqualTo(expectedResult);
    }

}