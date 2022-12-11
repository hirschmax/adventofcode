package de.ite.adventofcode.day04;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SectionAssignmentCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day04_section_assignments.txt");
        SectionAssignmentCalculator sectionAssignmentCalculator = new SectionAssignmentCalculator(inputLines);
        log.info("In how many assignment pairs does one range fully contain the other?");
        log.info("Task 01: {}", sectionAssignmentCalculator.solveFirstTask());
        log.info("Task 02: {}", sectionAssignmentCalculator.solveSecondTask());
    }

    private final List<SectionsPair> sectionsPairs = new ArrayList<>();
    public SectionAssignmentCalculator(List<String> inputLines) {
        super(inputLines);
        init(inputLines);
    }

    private void init(List<String> inputLines) {
        for (String inputLine : inputLines) {
            String[] sections = inputLine.split(",");
            String[] firstSectionSplit = sections[0].split("-");
            String[] secondSectionSplit = sections[1].split("-");
            Sections firstSections = new Sections(Integer.parseInt(firstSectionSplit[0]), Integer.parseInt(firstSectionSplit[1]));
            Sections secondSections = new Sections(Integer.parseInt(secondSectionSplit[0]), Integer.parseInt(secondSectionSplit[1]));
            SectionsPair sectionsPair = new SectionsPair(firstSections, secondSections);
            sectionsPairs.add(sectionsPair);
        }
    }

    @Override
    public Integer solveFirstTask() {
        return (int) sectionsPairs.stream().filter(SectionsPair::isOneSectionsFullyContainedByTheOther).count();
    }

    @Override
    public Integer solveSecondTask() {
        return (int) sectionsPairs.stream().filter(SectionsPair::doSectionsOverlap).count();
    }

}
