package uoft.csc207.reincarnation.maze.dimension;

public interface IPoint {

    /**
     * Check weather or not the point is walk way.
     *
     * @return true if the point is walk way.
     */
    public boolean isWalkWay();

    /**
     * Check if a point is equal to this point.
     *
     * @return true if the given point is equal to this point.
     */
    public boolean equals(Object p);

    /**
     * Clone a given point.
     *
     * @return A new point that is the same as the given point.
     */
    public IPoint clone();
}
