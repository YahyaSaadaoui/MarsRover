package fr.univ_amu.m1info.mars_rover.simulator;

public final class ToroidalGrid implements Grid {
    private final int width;
    private final int height;

    public ToroidalGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override public int width() { return width; }
    @Override public int height() { return height; }
    @Override public String kindName() { return "TOROIDAL"; }

    @Override
    public Coordinates next(Coordinates from, Direction d) {
        int nx = from.x(), ny = from.y();
        switch (d) {
            case NORTH -> ny = (from.y() + 1) % height;
            case SOUTH -> ny = (from.y() - 1 + height) % height;
            case EAST  -> nx = (from.x() + 1) % width;
            case WEST  -> nx = (from.x() - 1 + width) % width;
        }
        return new Coordinates(nx, ny);
    }
}