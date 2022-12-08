package de.ite.adventofcode.day03;

public class Rucksack {

    private final String content;
    private final String firstCompartment;
    private final String secondCompartment;

    public Rucksack(String content) {
        this.firstCompartment = content.substring(0, content.length()/2);
        this.secondCompartment = content.substring(content.length()/2);
        this.content = content;
    }

    public int getPriorityOfItemThatAppearsInBothCompartments() {
        char item = getItemThatAppearsInBothCompartments();
        return getPriorityForItem(item);
    }
    public static int getPriorityForItem(char item) {
        if(item >= 'a' && item <= 'z') {
            return 1 + item - 'a';
        }
        if(item >= 'A' && item <= 'Z') {
            return 27 + item - 'A';
        }
        return 0;
    }

    private char getItemThatAppearsInBothCompartments() {
        for (char item : firstCompartment.toCharArray()) {
            if(secondCompartment.contains(Character.toString(item))) {
                return item;
            }
        }
        return '#';
    }

    public String getContent() {
        return content;
    }

}
