package fr.univ_amu.m1info.mars_rover.simulator.Testing;

import fr.univ_amu.m1info.mars_rover.input.*;
import fr.univ_amu.m1info.mars_rover.output.MarsRoverOutput;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import fr.univ_amu.m1info.mars_rover.simulator.*;
public class ExplorationTest {

    @Test
    void countsStartingCellsAndMoves() {
        // grid 2x2, one rover starts at (0,0) facing EAST and moves to (1,0)
        GridConfiguration grid = new GridConfiguration(2, 2, GridKind.RECTANGULAR);
        Position start = new Position(new Coordinates(0,0), Direction.EAST);
        RoverConfiguration rc = new RoverConfiguration(start, List.of(Command.MOVE));
        MarsRoverInput input = new MarsRoverInput(grid, List.of(rc));

        MarsRoverOutput out = Simulator.run(input);

        // Visited cells: (0,0) and (1,0) => 2 out of 4 => 50%
        assertEquals(50.0, out.percentageExplored());
        assertFalse(out.finalRoverStates().get(0).isDestroyed());
        assertEquals(1, out.finalRoverStates().get(0).position().coordinates().x());
        assertEquals(0, out.finalRoverStates().get(0).position().coordinates().y());
    }
}
