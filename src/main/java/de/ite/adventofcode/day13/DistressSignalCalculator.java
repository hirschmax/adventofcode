package de.ite.adventofcode.day13;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Slf4j
public class DistressSignalCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day13_received_packets.txt");
        DistressSignalCalculator distressSignalCalculator = new DistressSignalCalculator(input);
        log.info("Determine which pairs of packets are already in the right order. What is the sum of the indices of those pairs?");
        log.info("Task 01: {}", distressSignalCalculator.solveFirstTask());
        log.info("Organize all of the packets into the correct order. What is the decoder key for the distress signal?");
        log.info("Task 02: {}", distressSignalCalculator.solveSecondTask());
    }

    public DistressSignalCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        List<Pair> pairs = readPairs();
        return pairs.stream().filter(pair -> pair.getLeft().compareTo(pair.getRight()) < 0).map(Pair::getIndex).mapToInt(Integer::intValue).sum();
    }

    @Override
    public Integer solveSecondTask() {
        List<Pair> pairs = readPairs();
        Packet left = new Packet("[[2]]");
        Packet right = new Packet("[[6]]");
        pairs.add(new Pair(left, right, 0));

        List<Packet> sortedPackets = pairs.stream()
                .flatMap(pair -> Stream.of(pair.getLeft(), pair.getRight()))
                .sorted(Packet::compareTo)
                .toList();

        int leftIndex = sortedPackets.indexOf(left) + 1;
        int rightIndex = sortedPackets.indexOf(right) + 1;

        return leftIndex * rightIndex;
    }

    private List<Pair> readPairs() {
        List<Pair> pairs = new ArrayList<>();
        int index = 1;
        for(int line = 0; line + 1 < inputLines.size(); line+=3) {
            Packet left = new Packet(inputLines.get(line));
            Packet right = new Packet(inputLines.get(line+1));
            pairs.add(new Pair(left, right, index++));
        }
        return pairs;
    }

    @AllArgsConstructor
    private static class Pair {
        @Getter
        Packet left;
        @Getter
        Packet right;
        @Getter
        int index;
    }

}
