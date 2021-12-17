package de.ite.adventofcode.position;

import de.ite.adventofcode.TestInputUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestSubmarineMoveServiceShould {

    private SubmarineMoveService submarineMoveService;

    @BeforeEach
    void setUp() {
        this.submarineMoveService = new SubmarineMoveService();
    }

    @Test
    @DisplayName("Compute relative position from series of commands")
    void computeProductOfHorizontalAndVerticalPositionAfterMoves() {
        List<String> commands = List.of("forward 5");

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Final test for SubmarineMovesService")
    void finalTestForSubmarineMovesService() {
        List<String> commands = TestInputUtils.readInputForSubmarineMoves();

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        AssertionsForClassTypes.assertThat(result).isEqualTo(TestInputUtils.expectedResultForSubmarineMoves());
    }
}