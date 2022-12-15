package de.ite.adventofcode.day15;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;


@Slf4j
public class BeaconExclusionCalculator extends Calculator<Integer, BigInteger> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day15_sensors_beacons.txt");
        BeaconExclusionCalculator beaconExclusionCalculator = new BeaconExclusionCalculator(input);
        beaconExclusionCalculator.setRowToCheck(2_000_000);
        beaconExclusionCalculator.setSearchSpace(0, 4_000_000);

        log.info("Consult the report from the sensors you just deployed. In the row where y=2000000, how many positions cannot contain a beacon?");
        log.info("Task 01: {}", beaconExclusionCalculator.solveFirstTask());
        log.info("Find the only possible position for the distress beacon. What is its tuning frequency?");
        log.info("Task 02: {}", beaconExclusionCalculator.solveSecondTask());
    }

    public BeaconExclusionCalculator(String input) {
        super(input.lines().toList());
    }

    private int rowToCheck = 0;
    private boolean print = false;
    private int searchMin;
    private int searchMax;
    public void setSearchSpace(int min, int max) {
        this.searchMin = min;
        this.searchMax = max;
    }
    public void setRowToCheck(int rowToCheck) {
        this.rowToCheck = rowToCheck;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    @Override
    public Integer solveFirstTask() {
        BeaconSensorMap beaconSensorMap = new BeaconSensorMap(inputLines);
        if(print) {
            beaconSensorMap.print();
        }
        return beaconSensorMap.countPositionsWhereBeaconCanNotBePresentForRow(rowToCheck);
    }

    @Override
    public BigInteger solveSecondTask() {
        BeaconSensorMap beaconSensorMap = new BeaconSensorMap(inputLines);
        return beaconSensorMap.getTuningFrequencyOfDistressBeacon(searchMin, searchMax);
    }


}
