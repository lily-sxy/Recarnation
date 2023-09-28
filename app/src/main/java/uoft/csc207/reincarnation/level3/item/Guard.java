package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;

/**
 * the guard of the frogger game.
 */
public class Guard extends FroggerItem {

    /**
     * the range of the y-axis of the guard.
     */
    private int[] range;

    /**
     * the guard is go right or not.
     */
    private boolean goRight;

    /**
     * the graph when guard going left.
     */
    private Bitmap left;

    /**
     * the graph when guard going right.
     */
    private Bitmap right;

    /**
     * Initialize a guard.
     *
     * @param range        the range of the y-axis of the guard.
     * @param initLocation the initial location of the guard.
     * @param right        the graph when guard going right.
     * @param left         the graph when guard going left.
     * @param width        the grid width.
     * @param height       the grid height.
     */
    public Guard(int[] range, int[] initLocation, Bitmap right, Bitmap left, float width, float height) {
        this.range = range;
        goRight = true;
        location = initLocation;
        super.bitmap = right;
        this.left = left;
        this.right = right;
        super.gridwidth = width;
        super.gridheight = height;
    }

    /**
     * move the guard.
     */
    public void interact() {
        // walk around
        if (goRight && range[0] < location[0] && location[0] < range[1]) {
            location[0] += 1;
        } else if (!goRight && range[0] < location[0] && location[0] < range[1]) {
            location[0] -= 1;
        } else if (location[0] == range[1]) {
            turnAround();
            location[0] -= 1;
        } else if (location[0] == range[0]) {
            turnAround();
            location[0] += 1;
        }
    }

    /**
     * turn around the guard.
     */
    private void turnAround() {
        goRight = !goRight;
        if (goRight) {
            bitmap = right;
        } else {
            bitmap = left;
        }
    }

    /**
     * found if role can be seen.
     *
     * @param role the role of the frogger game.
     */
    public boolean found(Role role) {
        if (goRight && role.location[1] == this.location[1]
                && role.location[0] >= this.location[0]) {
            return true;
        } else return !goRight && role.location[1] == this.location[1]
                && role.location[0] <= this.location[0];
    }
}
