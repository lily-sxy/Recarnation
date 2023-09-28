package uoft.csc207.reincarnation.maze.entity;

import uoft.csc207.reincarnation.maze.dimension.Point2;

/**
 * A 2D enity with x and y coordinates.
 */
public abstract class Entity2 implements IEntity<Point2> {
    protected double x;
    protected double y;

    /**
     * Initialize a new 2D entity with given point location.
     *
     * @param location Location of the point to set the entity.
     */
    Entity2(Point2 location) {
        this.setLocation(location);
    }

    /**
     * Get the location of the entity.
     *
     * @return A point with the location of the entity.
     */
    public Point2 getLocation() {
        return new Point2((int) x, (int) y, true);
    }

    /**
     * Set lacation of the entity to a point.
     *
     * @param p_in The point to set the location of the entity.
     */
    public void setLocation(Point2 p_in) {
        this.x = p_in.getX() + 0.5;
        this.y = p_in.getY() + 0.5;
    }

    /**
     * Get x coordinate of the entity.
     *
     * @return X coordinate of the entity.
     */
    public double getX() {
        return x;
    }

    /**
     * Get y coordinate of the entity.
     *
     * @return Y coordinate of the entity.
     */
    public double getY() {
        return y;
    }

    /**
     * Set x coordinate of the entity.
     *
     * @param x_in X coordinate to set the entity.
     */
    public void setX(double x_in) {
        x = x_in;
    }

    /**
     * Set y coordinate of the entity.
     *
     * @param y_in Y coordinate to set the entity.
     */
    public void setY(double y_in) {
        y = y_in;
    }
}
