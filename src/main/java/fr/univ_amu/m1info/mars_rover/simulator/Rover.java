package fr.univ_amu.m1info.mars_rover.simulator;

public final class Rover {
    private Position state;
    private boolean destroyed = false;

    public Rover(Position start) {
        this.state = start;
    }

    public Position state() { return state; }
    public boolean isDestroyed() { return destroyed; }

    public void turnLeft() {
        if (!destroyed) {
            state = new Position(state.coordinates(), state.orientation().left());
        }
    }

    public void turnRight() {
        if (!destroyed) {
            state = new Position(state.coordinates(), state.orientation().right());
        }
    }
    public void moveForward(Grid grid) {
        if (destroyed) return;

        Coordinates to = grid.next(state.coordinates(), state.orientation());

        // For rectangular grid: if out, destroyed and stop.
        if (grid instanceof RectangularGrid && !grid.isInside(to.x(), to.y())) {
            destroyed = true;
            return;
        }

        if (grid.isInside(to.x(), to.y())) {
            state = new Position(to, state.orientation());
        } else {
            destroyed = true;
        }
    }
}
