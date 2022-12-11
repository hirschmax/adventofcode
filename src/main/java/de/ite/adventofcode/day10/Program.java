package de.ite.adventofcode.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {


    private final List<Command> commands = new ArrayList<>();
    private final Map<Integer, Integer> cycleToRegisterValueMap = new HashMap<>();

    public Program(List<String> inputLines) {
        for (String inputLine : inputLines) {
            if(inputLine.startsWith("noop")) {
                commands.add(new NoopCommand());
            } else {
                commands.add(new AddXCommand(Integer.parseInt(inputLine.split(" ")[1])));
            }
        }
    }

    public Map<Integer, Integer> getSignalStrengthForCycles(List<Integer> interestingCycles) {
        Map<Integer, Integer> cycleToSignalStrengthMap = new HashMap<>();
        for (int cycle : interestingCycles) {
            int registerValue = cycleToRegisterValueMap.getOrDefault(cycle, 0);
            cycleToSignalStrengthMap.put(cycle, cycle * registerValue);
        }
        return cycleToSignalStrengthMap;
    }

    public Map<Integer, Integer> getCycleToRegisterValueMap() {
        return cycleToRegisterValueMap;
    }

    public void execute() {
        int xValue = 1;
        int currentCycle = 1;
        cycleToRegisterValueMap.put(currentCycle, xValue);

        for (Command command : commands) {
            if(command instanceof AddXCommand addXCommand) {
                for(int i = 0; i < addXCommand.getCycles() - 1; i++) {
                    currentCycle++;
                    cycleToRegisterValueMap.put(currentCycle, xValue);
                }
                xValue += addXCommand.getValue();
            }
            currentCycle++;
            cycleToRegisterValueMap.put(currentCycle, xValue);
        }
    }

}
