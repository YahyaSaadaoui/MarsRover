package fr.univ_amu.m1info.mars_rover.input;

import fr.univ_amu.m1info.mars_rover.simulator.Position;
import java.util.List;

public record RoverConfiguration(Position position, List<Command> commands) {}