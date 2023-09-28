package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * the main char of the frogger game.
 */
public class Role extends FroggerItem {

    /**
     * if the role is on a wood
     */
    private Wood onThisWood = null;

    /**
     * the relative distance between wood and role when the role is on a wood.
     */
    private int followDistance;

    /**
     * the death time of role
     */
    private int deathTime = 0;

    /**
     * Initialize a role.
     *
     * @param bitmap The bitmap of the role.
     * @param width  The grid height.
     * @param height The grid width.
     */
    public Role(Bitmap bitmap, float width, float height) {
        location = new int[2];
        location[0] = 15;
        location[1] = 10;
        super.bitmap = bitmap;
        super.gridwidth = width;
        super.gridheight = height;
    }

    /**
     * move the role to the given direction
     *
     * @param direction direction of the movement.
     */
    public void interact(String direction) {
        // move
        switch (direction) {
            case "Up":
                location[1] -= 1;
                break;
            case "Left":
                location[0] -= 1;
                break;
            case "Right":
                location[0] += 1;
                break;
            case "Down":
                location[1] += 1;
                break;
        }

    }

    /**
     * Set the location to initial and add death time when role dead.
     */
    public void dead() {
        this.location[0] = 15;
        this.location[1] = 10;
        onThisWood = null;
        deathTime += 1;
    }

    /**
     * follow the wood.
     */
    public void follow() {
        location[0] = onThisWood.getLocation()[0] + followDistance;
    }

    /**
     * stop following the wood.
     */
    public void unfollow() {
        onThisWood = null;
        followDistance = 0;
    }

    /**
     * determine if the role is on a wood.
     *
     * @param lane the lane that role inside now.
     * @return if we need to clear all the broken (on a goldenWood)
     */
    public boolean onWood(Lane lane) {
        boolean foundWood = false;
        for (Wood wood : lane.woods) {
            if (!(wood instanceof BrokenWood)) {
                if (this.location[0] == wood.getLocation()[0]) {
                    onThisWood = wood;
                    followDistance = 0;
                    foundWood = true;
                    break;
                } else if (this.location[0] == wood.getLocation()[0] + 1) {
                    onThisWood = wood;
                    followDistance = 1;
                    foundWood = true;
                    break;
                } else if (this.location[0] == wood.getLocation()[0] + 2) {
                    onThisWood = wood;
                    followDistance = 2;
                    foundWood = true;
                    break;
                } else if (this.location[0] == wood.getLocation()[0] + 3) {
                    onThisWood = wood;
                    followDistance = 3;
                    foundWood = true;
                    break;
                }
            }
        }
        if (!foundWood) {
            dead();
        }
        return onThisWood instanceof GoldenWood;
    }

    /**
     * get the location of the role.
     *
     * @return the location of the role.
     */
    public int[] getLocation() {
        return location;
    }

    /**
     * get the death time of the role.
     *
     * @return the death time of the role.
     */
    public int getDeadTime() {
        return deathTime;
    }

    /**
     * draw the role at it's location.
     *
     * @param canvas the canvas we need to draw on.
     */
    public void draw(Canvas canvas) {
        float xlocation = (location[0]) * gridwidth;
        float ylocation = (location[1] - 2) * gridheight;
        canvas.drawBitmap(bitmap, xlocation, ylocation, paint);
    }

    /**
     * get the follow distance of the role
     *
     * @return the follow distance of the role
     */
    public int getFollowDistance() {
        return followDistance;
    }

    public void changeFollowDistance(int changes) {
        this.followDistance += changes;
    }

    /**
     * get the wood that the role is on
     *
     * @return the wood that the role is on
     */
    public Wood getOnThisWood() {
        return onThisWood;
    }
}