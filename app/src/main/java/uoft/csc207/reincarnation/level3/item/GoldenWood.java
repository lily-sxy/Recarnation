package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;

class GoldenWood extends Wood {
    /**
     * Initialize a golden wood.
     *
     * @param x      the initial x location of the wood.
     * @param y      the initial y location of the wood.
     * @param speed  the speed of the wood.
     * @param bitmap The bitmap of the wood.
     * @param width  The grid width.
     * @param height The grid height.
     */
    GoldenWood(int x, int y, double speed, Bitmap bitmap, float width, float height) {
        super(x, y, speed, bitmap, width, height);
    }
}
