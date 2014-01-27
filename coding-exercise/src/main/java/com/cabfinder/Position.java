package com.cabfinder;

/**
 *
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 16:26
 */
public class Position {

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Location x of the user in Meters
     */
    public int x;

    /**
     * Location y of the user in Meters
     */
    public int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
