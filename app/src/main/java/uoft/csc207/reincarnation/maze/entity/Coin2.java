package uoft.csc207.reincarnation.maze.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import uoft.csc207.reincarnation.maze.dimension.Point2;

/**
 * A 2D coin entity.
 */
public class Coin2 extends Entity2 {
    private double value;
    private Paint paint;

    /**
     * Initialize a coin entity with given location.
     * The default value of a coin entity is 1.
     *
     * @param p_ini Location to set the coin entitiy.
     */
    public Coin2(Point2 p_ini) {
        super(p_ini);
        value = 1.0;
        paint = new Paint();
        paint.setColor(Color.rgb(255, 165, 0));
    }

    /**
     * Update a coin entity.
     */
    public void update() {
    }

    /**
     * Draw a coin entity on a given canvas and given location.
     *
     * @param canvas    Canvas to draw the coin entity.
     * @param x         X coordinate of given location.
     * @param y         Y coordinate of given lacation.
     * @param blockSize Size of block.
     */
    public void draw(Canvas canvas, float x, float y, float blockSize) {
        canvas.drawCircle(
                x + blockSize / 2,
                y + blockSize / 2,
                blockSize / 3,
                paint
        );
    }

    /**
     * Get the vaule of the coin entity.
     *
     * @return Value of value.
     */
    public double getValue() {
        return this.value;
    }
}
