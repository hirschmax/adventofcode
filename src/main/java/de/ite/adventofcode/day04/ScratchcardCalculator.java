package de.ite.adventofcode.day04;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Slf4j
public class ScratchcardCalculator extends Calculator<BigInteger, BigInteger> {
    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day04.txt");
        ScratchcardCalculator calculator = new ScratchcardCalculator(input);
        log.info("Result for day 4 task 1: {}", calculator.solveFirstTask());
        log.info("Result for day 4 task 2: {}", calculator.solveSecondTask());
    }

    public ScratchcardCalculator(String input) {
        super(input.lines().toList());
    }
    @Override
    public BigInteger solveFirstTask() {
        List<Card> cards = new ArrayList<>();
        for (String inputLine : inputLines) {
            cards.add(parseLine(inputLine));
        }
        return cards.stream().map(Card::getMatchingNumbers).filter(set -> !set.isEmpty()).map(matchingNumbers -> BigInteger.TWO.pow(matchingNumbers.size() - 1)).reduce(BigInteger.ZERO, BigInteger::add);
    }

    @Override
    public BigInteger solveSecondTask() {
        int[] numberOfCopies = new int[inputLines.size()];
        Arrays.fill(numberOfCopies, 1);

        int index = 0;
        for (String inputLine : inputLines) {
            Card card = parseLine(inputLine);
            int matchingNumbers = card.getMatchingNumbers().size();
            int copiesOfCurrentCard = numberOfCopies[index];
            IntStream.rangeClosed(index + 1, index + matchingNumbers).forEach(i -> numberOfCopies[i] = numberOfCopies[i] + copiesOfCurrentCard);
            index++;
        }

        return Arrays.stream(numberOfCopies).mapToObj(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private Card parseLine(String inputLine) {
        String[] split = inputLine.split(":");
        String cardId = split[0];
        String[] winningAndYourCards = split[1].split("\\|");
        Set<Integer> winningNumbers = Arrays.stream(winningAndYourCards[0].trim().split(" ")).map(String::trim).filter(s -> !s.isBlank()).map(Integer::parseInt).collect(Collectors.toSet());
        Set<Integer> yourNumbers = Arrays.stream(winningAndYourCards[1].trim().split(" ")).map(String::trim).filter(s -> !s.isBlank()).map(Integer::parseInt).collect(Collectors.toSet());
        return new Card(cardId, winningNumbers, yourNumbers);
    }
}
