package de.ite.adventofcode.day13;

import de.ite.adventofcode.CalculatorTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class DistressSignalCalculatorTest extends CalculatorTest<Integer, Integer, DistressSignalCalculator> {

    public DistressSignalCalculatorTest() {
        super("day13_received_packets_testdata.txt", 13, 140);
    }

    @Override
    public Class<DistressSignalCalculator> getTestClassName() {
        return DistressSignalCalculator.class;
    }

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "[1,1,3,1,1] [1,1,5,1,1]",
            "[[1],[2,3,4]] [[1],4]",
            "[[4,4],4,4] [[4,4],4,4,4]",
            "[] [3]"
    })
    void shouldReturnIsInRightOrder(String left, String right) {
        Packet leftPacket = new Packet(left);
        Packet rightPacket = new Packet(right);
        assertThat(leftPacket).isLessThan(rightPacket);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "[9] [[8,7,6]]",
            "[7,7,7,7] [7,7,7]",
            "[[[]]] [[]]",
            "[1,[2,[3,[4,[5,6,7]]]],8,9] [1,[2,[3,[4,[5,6,0]]]],8,9]"
    })
    void shouldReturnIsNotInRightOrder(String left, String right) {
        Packet leftPacket = new Packet(left);
        Packet rightPacket = new Packet(right);
        assertThat(leftPacket).isGreaterThan(rightPacket);
    }

}