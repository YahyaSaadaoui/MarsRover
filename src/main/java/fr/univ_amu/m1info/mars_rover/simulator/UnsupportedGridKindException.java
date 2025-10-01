package fr.univ_amu.m1info.mars_rover.simulator;

public final class UnsupportedGridKindException extends UnsupportedOperationException {
    public UnsupportedGridKindException(String kind) {
        super("Grid kind " + kind + " is not supported");
    }
}
