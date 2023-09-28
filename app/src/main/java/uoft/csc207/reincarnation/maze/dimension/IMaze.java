package uoft.csc207.reincarnation.maze.dimension;

import java.util.List;

import uoft.csc207.reincarnation.maze.entity.Entity2;
import uoft.csc207.reincarnation.maze.exception.PointOutOfMapException;
import uoft.csc207.reincarnation.maze.entity.IEntity;

public interface IMaze<P extends IPoint> {
    /**
     * Get points adjacent to point given.
     * Adjacent means an object is able to move to.
     *
     * @return Points adjacent, marked by isWalkWay.
     */
    List<P> getAdjacent(P point) throws PointOutOfMapException;

    /**
     * Check weather or not a point can be selected as exit.
     * By default all points on the boundary can be.
     *
     * @return true if this point can be an exit point.
     */
    boolean canEnd(P point) throws PointOutOfMapException;

    /**
     * Walk through all walkways.
     *
     * @return All points that is walkway.
     */
    public List<P> getWalkWays();

    /**
     * Get the entry of the maze.
     *
     * @return A entry point.
     */
    P getEntry();

    /**
     * Get the exit of the maze.
     *
     * @return null.
     */
    P getExit();

    /**
     * Set a point type to walk way or wall.
     *
     * @param point The point to set.
     * @param pType The type to set the given point to.
     */
    void setPoint(P point, PointType pType) throws PointOutOfMapException;

    PointType getPoint(P point);

    /**
     * Add an entity to the maze.
     *
     * @param entity_in The entity to add.
     */
    void addEntity(IEntity<P> entity_in);

    /**
     * Add a coin to the maze.
     *
     * @param point_in The location to add the coin.
     */
    void addCoin(P point_in);

    /**
     * Move the player by given x and y velocity.
     *
     * @param vx Velocity on x-axis.
     * @param vy Velocity on y-axis.
     */
    void movePlayer(double vx, double vy);

    /**
     * Get the number of coins the player got.
     *
     * @return The number of coins the player got.
     */
    double getCoin();

    /**
     * Get the distance the player walked through the game.
     *
     * @return The distance the player walked.
     */
    double getDistance();

    IEntity<P> getPlayer();

    List<Entity2> getEntities();

    /**
     * Get the seed of the maze.
     *
     * @return The seed of the maze.
     */
    long getSeed();

    /**
     * Return if the game is end.
     *
     * @return true if shouldEnd if ture.
     */
    boolean isEnd();
}
