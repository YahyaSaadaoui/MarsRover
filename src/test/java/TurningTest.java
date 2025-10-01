import fr.univ_amu.m1info.mars_rover.simulator.Coordinates;
import fr.univ_amu.m1info.mars_rover.simulator.Direction;
import fr.univ_amu.m1info.mars_rover.simulator.Position;
import fr.univ_amu.m1info.mars_rover.simulator.Rover;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class TurningTest {

    @Test
    void turnLeftCyclesCCW() {
        Rover r = new Rover(new Position(new Coordinates(2,2), Direction.NORTH));
        r.turnLeft();  assertEquals(Direction.WEST,  r.state().orientation());
        r.turnLeft();  assertEquals(Direction.SOUTH, r.state().orientation());
        r.turnLeft();  assertEquals(Direction.EAST,  r.state().orientation());
        r.turnLeft();  assertEquals(Direction.NORTH, r.state().orientation());
    }

    @Test
    void turnRightCyclesCW() {
        Rover r = new Rover(new Position(new Coordinates(2,2), Direction.NORTH));
        r.turnRight(); assertEquals(Direction.EAST,  r.state().orientation());
        r.turnRight(); assertEquals(Direction.SOUTH, r.state().orientation());
        r.turnRight(); assertEquals(Direction.WEST,  r.state().orientation());
        r.turnRight(); assertEquals(Direction.NORTH, r.state().orientation());
    }
}
