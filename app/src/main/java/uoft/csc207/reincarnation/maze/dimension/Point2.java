package uoft.csc207.reincarnation.maze.dimension;

/**
 * A 2D point with x, y coordinates and is marked if it is walk way.
 */
public class Point2 implements IPoint {
    private int x;
    private int y;
    private boolean isWalkWay;

    /**
     * Intialize a new 2D point.
     *
     * @param x_in       X coordinates of the point.
     * @param y_in       Y coordinates of the point.
     * @param walkWay_in If the point is walk way.
     */
    public Point2(int x_in, int y_in, boolean walkWay_in) {
        x = x_in;
        y = y_in;
        isWalkWay = walkWay_in;
    }

    /**
     * Get x coordinate of the point.
     *
     * @return X coordinate of the point.
     */
    public int getX() {
        return x;
    }

    /**
     * Get y coordinate of the point.
     *
     * @return Y coordinate of the point.
     */
    public int getY() {
        return y;
    }

    /**
     * Check weather or not the point is walk way.
     *
     * @return true if the point is walk way.
     */
    public boolean isWalkWay() {
        return isWalkWay;
    }

    /**
     * Clone a given point.
     *
     * @return A new point that is the same as the given point.
     */
    public Point2 clone() {
        return new Point2(x, y, isWalkWay);
    }

    /**
     * Check if a point is equal to this point.
     *
     * @param pt The point to check.
     * @return true if the given point is equal to this point.
     */
    public boolean equals(Object pt) {
        if (pt instanceof Point2)
            return this.x == ((Point2) pt).x && this.y == ((Point2) pt).y;
        else
            return false;
    }
}
