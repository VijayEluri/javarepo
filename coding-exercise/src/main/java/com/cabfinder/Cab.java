package com.cabfinder;

/**
 *
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 16:25
 */
public interface Cab {

    /**
     * Unique identifier of a cab.
     */
    int getID();

    /**
     * Gets the current position of the cab
     */
    Position getCabPosition();

    /**
     * Returns whether or not the cab is available
     */
    boolean isAvailable();
}
