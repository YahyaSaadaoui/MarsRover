package fr.univ_amu.m1info.mars_rover.input;

import java.util.List;

public record MarsRoverInput(
        GridConfiguration grid,
        List<RoverConfiguration> rovers
) {}