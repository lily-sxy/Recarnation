package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;

/**
 * Represent a dark cloud in the game.
 * A dark cloud will make player dead.
 */

class BrokenWood extends Wood {
    /**
     * Initialize a broken wood.
     *
     * @param x      the initial x location of the wood.
     * @param y      the initial y location of the wood.
     * @param speed  the speed of the wood.
     * @param bitmap The bitmap of the wood.
     * @param width  The grid width.
     * @param height The grid height.
     */

    BrokenWood(int x, int y, double speed, Bitmap bitmap, float width, float height) {
        super(x, y, speed, bitmap, width, height);
    }
}
