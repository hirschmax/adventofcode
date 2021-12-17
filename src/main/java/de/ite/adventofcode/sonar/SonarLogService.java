package de.ite.adventofcode.sonar;

import java.util.List;

public class SonarLogService {

    public int computeNumberOfTimesDepthIncreasesFor(List<String> sonarLog) {
        int numberOfTimesDepthIncreases = 0;

        for (int i = 1; i < sonarLog.size(); i++) {
            if (Integer.parseInt(sonarLog.get(i)) > Integer.parseInt(sonarLog.get(i -1))) {
                numberOfTimesDepthIncreases++;
            }
        }

        return numberOfTimesDepthIncreases;
    }


}
