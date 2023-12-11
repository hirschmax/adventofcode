package de.ite.adventofcode.day02;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;

@Slf4j
public class GameCalculator extends Calculator<Integer, BigInteger> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day02.txt");
        GameCalculator calculator = new GameCalculator(input);
        log.info("Result for day 2 task 1: {}", calculator.solveFirstTask());
        log.info("Result for day 2 task 2: {}", calculator.solveSecondTask());
    }
    public GameCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        List<Game> games = parseGames();
        Bag bag = new Bag(12, 13, 14);
        return games.stream()
                .filter(game -> isGamePossible(bag, game))
                .mapToInt(Game::gameId).sum();
    }

    @Override
    public BigInteger solveSecondTask() {
        List<Game> games = parseGames();
        Map<Game, BigInteger> gameToPowerOfMinimumSet = new HashMap<>();
        for (Game game : games) {
            BigInteger red = game.cubesSets().stream().map(CubesSet::red).map(BigInteger::valueOf).max(BigInteger::compareTo).orElse(BigInteger.ZERO);
            BigInteger green = game.cubesSets().stream().map(CubesSet::green).map(BigInteger::valueOf).max(BigInteger::compareTo).orElse(BigInteger.ZERO);
            BigInteger blue = game.cubesSets().stream().map(CubesSet::blue).map(BigInteger::valueOf).max(BigInteger::compareTo).orElse(BigInteger.ZERO);
            gameToPowerOfMinimumSet.put(game, red.multiply(green).multiply(blue));
        }
        return gameToPowerOfMinimumSet.values().stream().reduce(BigInteger.ZERO, BigInteger::add);
    }

    private record Game(int gameId, List<CubesSet> cubesSets) { }
    private record CubesSet(int red, int green, int blue) { }
    private record Bag(int red, int green, int blue) { }

    private boolean isGamePossible(Bag bag, Game game) {
        for (CubesSet cubesSet : game.cubesSets) {
            if(bag.red() < cubesSet.red() || bag.blue() < cubesSet.blue() || bag.green() < cubesSet.green()) {
                return false;
            }
        }
        return true;
    }

    private List<Game> parseGames() {
        List<Game> games = new ArrayList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(":");
            int gameId = Integer.parseInt(split[0].substring(5));
            List<CubesSet> cubesSets = new ArrayList<>();

            String[] sets = split[1].split(";");
            for (String set : sets) {
                Map<String, Integer> map = new HashMap<>();
                String[] cubes = set.split(",");
                for (String cube : cubes) {
                    String[] numberColor = cube.trim().split(" ");
                    int number = Integer.parseInt(numberColor[0]);
                    String color = numberColor[1];
                    map.put(color, number);
                }
                cubesSets.add(new CubesSet(map.getOrDefault("red", 0), map.getOrDefault("green", 0), map.getOrDefault("blue", 0)));
            }
            games.add(new Game(gameId, Collections.unmodifiableList(cubesSets)));
        }
        return Collections.unmodifiableList(games);
    }
}
