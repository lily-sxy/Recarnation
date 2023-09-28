package uoft.csc207.reincarnation.maze.drawer;

import android.graphics.Canvas;

public interface IDrawer {

    /**
     * Draw the maze in a given canvas.
     *
     * @param canvas canvas given.
     */
    void draw(Canvas canvas);

    /**
     * Set size of block.
     *
     * @param block_size Size of block to set.
     */
    void setSize(int block_size);

    /**
     * Update screen to given width and height.
     *
     * @param w Width of screen to update.
     * @param h Height of screen to update.
     */
    void updateScreen(int w, int h);
}
