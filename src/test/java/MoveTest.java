import fr.univ_amu.m1info.mars_rover.simulator.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void moveNorthInRectangular() {
        Grid g = new RectangularGrid(5,5);
        Rover r = new Rover(new Position(new Coordinates(1,1), Direction.NORTH));
        r.moveForward(g);
        assertEquals(1, r.state().coordinates().x());
        assertEquals(2, r.state().coordinates().y());
        assertFalse(r.isDestroyed());
    }

    @Test
    void destroyWhenLeavingRectangular() {
        Grid g = new RectangularGrid(2,2); // x∈{0,1}, y∈{0,1}
        Rover r = new Rover(new Position(new Coordinates(1,1), Direction.NORTH));
        r.moveForward(g); // would go to y=2 -> out of bounds -> destroyed
        assertTrue(r.isDestroyed());
    }
}