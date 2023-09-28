package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;

import uoft.csc207.reincarnation.level3.item.FroggerItem;

public class Wood extends FroggerItem {

    /**
     * the speed of the wood.
     */
    private double speed;

    /**
     * Initialize a wood.
     *
     * @param x      the initial x location of the wood.
     * @param y      the initial y location of the wood.
     * @param speed  the speed of the wood.
     * @param bitmap The bitmap of the wood.
     * @param width  The grid width.
     * @param height The grid height.
     */
    Wood(int x, int y, double speed, Bitmap bitmap, float width, float height) {
        location = new int[2];
        location[0] = x;
        location[1] = y;
        this.speed = speed;
        super.bitmap = bitmap;
        super.gridheight = height;
        super.gridwidth = width;
    }

    /**
     * move the wood when this method was called.
     */
    public void interact() {
        location[0] += speed;
    }

    /**
     * get the location of the wood.
     */
    public int[] getLocation() {
        return this.location;
    }

    /**
     * get the speed of the wood.
     */
    double getSpeed() {
        return this.speed;
    }
}
