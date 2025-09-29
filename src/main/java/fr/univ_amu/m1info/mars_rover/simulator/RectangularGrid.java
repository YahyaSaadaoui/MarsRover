package fr.univ_amu.m1info.mars_rover.simulator;

public final class RectangularGrid implements Grid {
    private final int width;
    private final int height;

    public RectangularGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override public int width() { return width; }
    @Override public int height() { return height; }
    @Override public String kindName() { return "RECTANGULAR"; }

    @Override
    public Coordinates next(Coordinates from, Direction d) {
        int nx = from.x(), ny = from.y();
        switch (d) {
            case NORTH -> ny += 1;
            case SOUTH -> ny -= 1;
            case EAST  -> nx += 1;
            case WEST  -> nx -= 1;
        }
        // Caller decides destruction if outside.
        return new Coordinates(nx, ny);
    }
}
