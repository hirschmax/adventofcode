package de.ite.adventofcode.day03;

import java.util.Arrays;
import java.util.List;

public class ThreeElvesGroupRucksacks {

    private final Rucksack[] rucksacks = new Rucksack[3];

    public ThreeElvesGroupRucksacks(List<String> inputLines) {
        for(int i = 0; i < inputLines.size(); i++) {
            rucksacks[i] = new Rucksack(inputLines.get(i));
        }
    }

    public int getPriorityOfGroupBadge() {
        return Rucksack.getPriorityForItem(getGroupBadge());
    }

    private char getGroupBadge() {
        for (char item : rucksacks[0].getContent().toCharArray()) {
            if(Arrays.stream(rucksacks).allMatch(rucksack -> rucksack.getContent().contains(Character.toString(item)))) {
                return item;
            }
        }
        return '#';
    }

}
