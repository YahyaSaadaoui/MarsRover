package fr.univ_amu.m1info.mars_rover.simulator;

import fr.univ_amu.m1info.mars_rover.input.*;
import fr.univ_amu.m1info.mars_rover.output.*;

import java.util.*;
import java.util.stream.Collectors;

public final class Simulator {

    private Simulator() {}

    public static MarsRoverOutput run(MarsRoverInput input) {
        Grid grid = GridFactory.create(input.grid());

        // Track explored cells across all rovers (include starting cells)
        Set<Coordinates> explored = new HashSet<>();

        List<MarsRoverState> finalStates = new ArrayList<>();
        for (RoverConfiguration rc : input.rovers()) {
            Rover rover = new Rover(rc.position());
            // starting cell counts as explored
            if (grid.isInside(rover.state().coordinates().x(), rover.state().coordinates().y())) {
                explored.add(rover.state().coordinates());
            }

            for (Command cmd : rc.commands()) {
                if (rover.isDestroyed()) break;

                switch (cmd) {
                    case LEFT  -> rover.turnLeft();
                    case RIGHT -> rover.turnRight();
                    case MOVE  -> {
                        rover.moveForward(grid);
                        if (!rover.isDestroyed()
                                && grid.isInside(rover.state().coordinates().x(), rover.state().coordinates().y())) {
                            explored.add(rover.state().coordinates());
                        }
                    }
                }
            }

            finalStates.add(new MarsRoverState(rover.isDestroyed(), rover.state()));
        }

        double exploredPct = computeExploredPercentage(explored, grid.width(), grid.height());

        return new MarsRoverOutput(exploredPct, finalStates);
    }

    private static double computeExploredPercentage(Set<Coordinates> explored, int w, int h) {
        long total = (long) w * (long) h;
        if (total == 0) return 0.0;
        // Example output in TP shows integer with %; we keep a double (formatting the % is for the writer).
        double pct = (explored.size() * 100.0) / total;
        // Round to one decimal if you like:
        return Math.round(pct * 10.0) / 10.0;
    }
}
