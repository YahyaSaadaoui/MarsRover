package fr.univ_amu.m1info.mars_rover.simulator;

import fr.univ_amu.m1info.mars_rover.input.GridConfiguration;
import fr.univ_amu.m1info.mars_rover.input.GridKind;

public final class GridFactory {
    private GridFactory() {}

    public static Grid create(GridConfiguration cfg) {
        return switch (cfg.kind()) {
            case RECTANGULAR -> new RectangularGrid(cfg.width(), cfg.height());
            case TOROIDAL    -> new ToroidalGrid(cfg.width(), cfg.height());
            case SPHERICAL   -> throw new UnsupportedOperationException(
                "Grid kind SPHERICAL is not supported yet");
        };
    }
}
