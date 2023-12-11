package de.ite.adventofcode.day04;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record Card(String id, Set<Integer> winningNumbers, Set<Integer> yourNumbers) {
    public Set<Integer> getMatchingNumbers() {
        Set<Integer> matchingNumbers = new HashSet<>();
        for (Integer winningNumber : winningNumbers) {
            if(yourNumbers.contains(winningNumber)) {
                matchingNumbers.add(winningNumber);
            }
        }
        return Collections.unmodifiableSet(matchingNumbers);
    }
}
