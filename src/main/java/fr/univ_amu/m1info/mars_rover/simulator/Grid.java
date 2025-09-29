package fr.univ_amu.m1info.mars_rover.simulator;

public interface Grid {
    int width();
    int height();

    /** Returns true if (x,y) is inside the rectangle bounds. */
    default boolean isInside(int x, int y) {
        return 0 <= x && x < width() && 0 <= y && y < height();
    }
    Coordinates next(Coordinates from, Direction orientation);
    String kindName();
}
