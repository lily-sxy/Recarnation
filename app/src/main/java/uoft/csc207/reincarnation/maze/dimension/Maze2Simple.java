package uoft.csc207.reincarnation.maze.dimension;

import java.util.List;

import uoft.csc207.reincarnation.maze.exception.PointOutOfMapException;

/**
 * A 2D maze with x and y coordinates.
 * Maze2Simple ensure the player is right beside exit.
 * Just for fun.
 */
public class Maze2Simple extends Maze2 {
    /**
     * Initialize a new 2D maze.
     *
     * @param h_in The height of the maze.
     * @param w_in The width of the maze.
     * @param pIn  The entry point of the maze.
     */
    public Maze2Simple(int h_in, int w_in, Point2 pIn) {
        super(h_in, w_in, pIn);
    }

    /**
     * Check whether or not a point can be an exit.
     * By default all points on the boundry can be an exit.
     *
     * @param p_in A point to check.
     * @return true is p_in can be an exit.
     */
    public boolean canEnd(Point2 p_in) {
        try {
            List<Point2> points = getAdjacent(getEntry());
            for (Point2 p : points) {
                if (p.equals(p_in)) {
                    return true;
                }
            }
            return false;
        } catch (PointOutOfMapException e) {
            return false;
        }
    }

    /**
     * Get the number of coins the player got.
     *
     * @return In this maze coin is always 5, no matter what player did.
     */
    public double getCoin() {
        return 5;
    }
}
