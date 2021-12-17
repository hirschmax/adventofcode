package de.ite.adventofcode.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestDiveServiceShould {

    private DiveService diveService;

    @BeforeEach
    void setUp() {
        this.diveService = new DiveService();
    }

    @Test
    @DisplayName("Compute relative position from series of commands")
    void computeRelativePositionFromCommandSeries() {
        String commandSeries = "forward 5";

        DiveService.Position position = diveService.computeRelativePositionFrom(commandSeries);

        assertThat(position.getDepth()).isZero();
        assertThat(position.getHorizontal()).isEqualTo(5);
    }
}