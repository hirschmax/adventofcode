package de.ite.adventofcode.day01;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Elf {
    @Getter
    private final List<Integer> items = new ArrayList<>();

    public int getCaloriesCarried() {
        return items.stream().mapToInt(Integer::intValue).sum();
    }
}
