package de.ite.adventofcode.day04;

public class SectionsPair {

    private final Sections firstSections;
    private final Sections secondSections;

    public SectionsPair(Sections firstSections, Sections secondSections) {
        this.firstSections = firstSections;
        this.secondSections = secondSections;
    }

    public boolean isOneSectionsFullyContainedByTheOther() {
        return sectionsFullyContainsOther(firstSections, secondSections) || sectionsFullyContainsOther(secondSections, firstSections);
    }

    public boolean doSectionsOverlap() {
        for(int section = firstSections.from; section <= firstSections.to; section++) {
            if(section >= secondSections.from && section <= secondSections.to) {
                return true;
            }
        }
        return false;
    }

    private boolean sectionsFullyContainsOther(Sections sections, Sections other) {
        return sections.from <= other.from && sections.to >= other.to;
    }
}
