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
    @DisplayName("Compute product of horizontal position and depth after move commands")
    void computeProductOfHorizontalAndVerticalPositionAfterMoves_emptyList() {
        List<String> commands = List.of();

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Compute product of horizontal position and depth after move commands")
    void computeProductOfHorizontalAndVerticalPositionAfterMovesFor5ForwardAnd2Down() {
        List<String> commands = List.of("forward 5", "down 2");

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Compute product of horizontal position and depth after move commands")
    void computeProductOfHorizontalAndVerticalPositionAfterMoves() {
        List<String> commands = List.of("forward 5", "down 3");

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("Compute product of horizontal position and depth after move commands")
    void computeProductOfHorizontalAndVerticalPositionAfterMovesDownUp() {
        List<String> commands = List.of("forward 4", "down 3", "up 1");

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        assertThat(result).isEqualTo(8);
    }


    @Test
    @DisplayName("Final test for SubmarineMovesService")
    void finalTestForSubmarineMovesService() {
        List<String> commands = TestInputUtils.readInputForSubmarineMoves();

        int result = submarineMoveService.computeProductOfHorizontalAndVerticalPositionAfterMovesFor(commands);

        AssertionsForClassTypes.assertThat(result).isEqualTo(TestInputUtils.expectedResultForSubmarineMoves());
    }
}