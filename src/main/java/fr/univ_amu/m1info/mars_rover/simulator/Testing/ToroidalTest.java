package fr.univ_amu.m1info.mars_rover.simulator.Testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.univ_amu.m1info.mars_rover.simulator.*;

public class ToroidalTest {

    @Test
    void wrapNorth() {
        Grid g = new ToroidalGrid(4,4);
        Rover r = new Rover(new Position(new Coordinates(2,3), Direction.NORTH)); // top row
        r.moveForward(g); // wraps to y=0
        assertEquals(new Coordinates(2,0), r.state().coordinates());
        assertFalse(r.isDestroyed());
    }

    @Test
    void wrapWest() {
        Grid g = new ToroidalGrid(4,4);
        Rover r = new Rover(new Position(new Coordinates(0,2), Direction.WEST));
        r.moveForward(g); // wraps to x=3
        assertEquals(new Coordinates(3,2), r.state().coordinates());
        assertFalse(r.isDestroyed());
    }
}
