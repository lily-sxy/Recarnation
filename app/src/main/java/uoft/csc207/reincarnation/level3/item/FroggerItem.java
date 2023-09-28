package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Represent all the item that will be used in the game
 */

public abstract class FroggerItem {

    /**
     * the bitmap of this item.
     */
    Bitmap bitmap;

    /**
     * the location of the item.
     */
    int[] location;

    /**
     * grid width.
     */
    float gridwidth;

    /**
     * grid height.
     */
    float gridheight;

    /**
     * the paint of this item.
     */
    Paint paint;

    /**
     * Initialize a item.
     */
    FroggerItem() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * interact with the item.
     */
    public void interact() {
    }

    /**
     * draw the item on canvas.
     *
     * @param canvas the canvas to draw.
     */
    public void draw(Canvas canvas) {
        float xlocation = (location[0]) * gridwidth;
        float ylocation = (location[1] - 1) * gridheight;
        canvas.drawBitmap(bitmap, xlocation, ylocation, paint);
    }
}
