package de.ite.adventofcode.day16;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ValvePressureReleaseCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day16_valves.txt");
        ValvePressureReleaseCalculator valvePressureReleaseCalculator = new ValvePressureReleaseCalculator(input);

        log.info("Work out the steps to release the most pressure in 30 minutes. What is the most pressure you can release?");
        log.info("Task 01: {}", valvePressureReleaseCalculator.solveFirstTask());
        log.info("");
        log.info("Task 02: {}", valvePressureReleaseCalculator.solveSecondTask());
    }

    public ValvePressureReleaseCalculator(String input) {
        super(input.lines().toList());
    }

    private final Map<String, List<String>> neighborsMap = new HashMap<>();
    private final Map<String, Integer> flowRatesMap = new HashMap<>();
    private final Map<String, Integer> cache = new HashMap<>();

    @Override
    public Integer solveFirstTask() {
        cache.clear();
        readInput();

        return solve("AA", 30, new ArrayList<>());
    }

    private int solve(String position, int time, List<String> opened) {
        String hash = String.format("%s_%s_%s", position, time, String.join("x", opened));
        if(cache.get(hash) != null) {
            return cache.get(hash);
        }

        if(time == 0) {
            cache.putIfAbsent(hash, 0);
            return 0;
        }

        int score = 0;
        for (String next : neighborsMap.get(position)) {
            score = Math.max(score, solve(next, time - 1, new ArrayList<>(opened)));
        }

        if(flowRatesMap.get(position) > 0 && !opened.contains(position)) {
            List<String> newOpened = new ArrayList<>(opened);
            newOpened.add(position);
            score = Math.max(score, (time - 1) * flowRatesMap.get(position) + solve(position, time - 1, newOpened));
            cache.put(hash, score);
        }

        return score;
    }



    @Override
    public Integer solveSecondTask() {
        return -1;
    }


    private void readInput() {
        neighborsMap.clear();
        flowRatesMap.clear();

        for (String line : inputLines) {
            String[] split = line.split(";");
            String name = split[0].substring(split[0].indexOf(' ')+1, split[0].indexOf(' ')+3);
            int flowRate = Integer.parseInt(split[0].substring(split[0].indexOf('=')+1));
            List<String> neighbors = Arrays.stream(split[1].replace(" tunnels lead to valves ", "").replace(" tunnel leads to valve ", "").split(", ")).toList();
            neighborsMap.computeIfAbsent(name, n -> new ArrayList<>())
                    .addAll(neighbors);
            flowRatesMap.put(name, flowRate);
        }
    }


    /*
    public Map<String, Valve> readInputOld() {
        Map<String, Valve> valves = new HashMap<>();
        Map<String, List<String>> nodeToNeighborsMap = readLines(valves);

        for (Map.Entry<String, List<String>> nameToNeighbors : nodeToNeighborsMap.entrySet()) {
            String startNodeName = nameToNeighbors.getKey();
            Map<String, Node<String>> otherValvesToNodeMap = calculateShortestPathToEachNode(startNodeName, nodeToNeighborsMap);

            Valve valve = valves.get(startNodeName);
            for (Map.Entry<String, Node<String>> valveNodeEntry : otherValvesToNodeMap.entrySet()) {
                Valve other = valves.get(valveNodeEntry.getKey());
                if(valve != other) {
                    valve.addNeighbor(other, valveNodeEntry.getValue().getDistance());
                }
            }
        }

        return valves;
    }*/

    /*private Map<String, List<String>> readLines(Map<String, Valve> valves) {
        Map<String, List<String>> nodeToNeighborsMap = new HashMap<>();
        for (String line : inputLines) {
            String[] split = line.split(";");
            String name = split[0].substring(split[0].indexOf(' ')+1, split[0].indexOf(' ')+3);
            int flowRate = Integer.parseInt(split[0].substring(split[0].indexOf('=')+1));
            List<String> neighbors = Arrays.stream(split[1].replace(" tunnels lead to valves ", "").replace(" tunnel leads to valve ", "").split(", ")).toList();
            List<String> list = nodeToNeighborsMap.computeIfAbsent(name, n -> new ArrayList<>());
            list.addAll(neighbors);
            valves.put(name, new Valve(name, flowRate));
        }
        return nodeToNeighborsMap;
    }*/

   /* private Map<String, Node<String>> calculateShortestPathToEachNode(String startNodeName, Map<String, List<String>> nodeToNeighborsMap) {
        Map<String, Node<String>> neighborNodesMap = new HashMap<>();

        Node<String> startNode = new Node<>(startNodeName);
        startNode.setDistance(0);

        PriorityQueue<Node<String>> queue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));
        queue.add(startNode);
        while(!queue.isEmpty()) {
            Node<String> currentNode = queue.poll();
            if(currentNode != startNode) {
                neighborNodesMap.put(currentNode.getContent(), currentNode);
            }
            currentNode.setVisited(true);

            for(String neighborNodeName : nodeToNeighborsMap.get(currentNode.getContent())) {
                Node<String> neighbor = neighborNodesMap.computeIfAbsent(neighborNodeName, Node::new);
                if(!neighbor.isVisited()) {
                    int distance = currentNode.getDistance() + 1;
                    if(currentNode.getDistance() < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return neighborNodesMap;
    }*/


}
