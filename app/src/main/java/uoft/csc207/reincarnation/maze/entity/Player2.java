package uoft.csc207.reincarnation.maze.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import uoft.csc207.reincarnation.maze.dimension.Point2;

/**
 * A player entity.
 */
public class Player2 extends Entity2 implements IPlayer {
    private int coin;
    private double speed;
    private double size;

    private Paint paint;

    private double total_x;
    private double total_y;

    /**
     * Initialize a new 2D player entity with given location and default size.
     * The default size is 0.4.
     *
     * @param point_in Point to set the player entity.
     */
    public Player2(Point2 point_in) {
        super(point_in);
        speed = 0.1;
        size = 0.4;
        x = point_in.getX() + 0.5;
        y = point_in.getY() + 0.5;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        coin = 0;
        total_x = 0;
        total_y = 0;
    }

    /**
     * Initialize a new 2D player entity with given lacation and size.
     *
     * @param point_in Point to set the  player entity.
     * @param size_in  Size of the player entity.
     */
    public Player2(Point2 point_in, double size_in) {
        this(point_in);
        this.size = size_in;
    }

    /**
     * Update a player entity.
     */
    public void update() {
    }

    /**
     * Draw a player entity in a given canvas with given location.
     *
     * @param canvas    Canvas to draw the player entity.
     * @param screen_x  X coordinate of the location on screen.
     * @param screen_y  Y coordinate of the location on screen.
     * @param blockSize Size of the block.
     */
    public void draw(Canvas canvas, float screen_x, float screen_y, float blockSize) {
        canvas.drawCircle(
                screen_x + blockSize * (float) (x - (int) x),
                screen_y + blockSize * (float) (y - (int) y),
                blockSize * (float) size,
                paint
        );
    }

    /**
     * Move the player, without checking of boundary.
     * <p>
     * By default, the velocity vector should have length less than 1.
     *
     * @param vx velocity on x-axis
     * @param vy velocity on y-axis
     */
    public void move(double vx, double vy) {
        this.x += (float) (vx * speed);
        this.y += (float) (vy * speed);
        this.total_x += Math.abs((float) (vx * speed));
        this.total_y += Math.abs((float) (vy * speed));
    }

    /**
     * Add number of coins.
     *
     * @param value Number of coins to add.
     */
    public void pickCoin(double value) {
        coin += value;
    }

    /**
     * Get the number of coins the player has collected.
     *
     * @return The value of coin.
     */
    public double getCoin() {
        return coin;
    }

    /**
     * Get the speed of the player entity.
     *
     * @return The value of speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Get the size of the player entity.
     *
     * @return The value of size.
     */
    public double getSize() {
        return size;
    }

    /**
     * Get the distance between the player entity and the entry point.
     *
     * @return Distance between the player entity an the entry.
     */
    public double getDistance() {
        return Math.sqrt(total_x * total_x + total_y * total_y);
    }
}
