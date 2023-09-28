package uoft.csc207.reincarnation.maze.entity;

import android.graphics.Canvas;

import uoft.csc207.reincarnation.maze.dimension.IPoint;

public interface IEntity<P extends IPoint> {
    void update();

    /**
     * Get the location of the entity.
     *
     * @return A point with the location of the enetity.
     */
    P getLocation();

    /**
     * Set lacation of the entity to a point.
     *
     * @param p_in The point to set the location of the entity.
     */
    void setLocation(P p_in);

    /**
     * Draw an entity in a canvas with given lacation and size.
     *
     * @param canvas Canvas to draw the entity.
     * @param x      X coordinate of given location.
     * @param y      Y coordinate of given location.
     * @param size   The size of each block on screen.
     */
    void draw(Canvas canvas, float x, float y, float size);
}
