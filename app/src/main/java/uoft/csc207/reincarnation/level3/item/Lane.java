package uoft.csc207.reincarnation.level3.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Represent the Lane item in the game that collect all the cloud in this lane
 * A lane store all the cloud in this lane
 */
public class Lane {
    /**
     * A collection of cloud in the lane
     */
    ArrayList<Wood> woods = new ArrayList<Wood>();
    /**
     * the speed of the lane
     */
    private double speed;

    /**
     * the y location of the lane
     */
    private int y;
    /**
     * The width of the grid.
     */
    private float gridwidth;
    /**
     * The height of the grid.
     */
    private float gridheight;
    /**
     * the direction of the lane. whether it is going left or going right.
     */
    private boolean goingRight;
    /**
     * the Bitmap of the cloud
     */
    private Bitmap cloud;
    /**
     * the Bitmap of the dark cloud
     */
    private Bitmap darkCloud;
    /**
     * the Bitmap of the gloden cloud
     */
    private Bitmap glodenCloud;

    /**
     * Initialize a Lane
     *
     * @param speed       the speed of the lane
     * @param y           the y position of the lane
     * @param width       the width of the grid
     * @param height      the height of the grid
     * @param goingRight  the direction of all the cloud in the lane, whether towards left or right
     * @param cloud       the bitmap of clouds in the lane
     * @param darkCloud   the bitmap of clouds in the lane
     * @param glodenCloud te bitmap of gloden cloads in the lane
     */


    public Lane(double speed, int y, float width, float height,
                boolean goingRight, Bitmap cloud, Bitmap darkCloud, Bitmap glodenCloud) {
        this.speed = speed;
        this.gridwidth = width;
        this.gridheight = height;
        this.y = y;
        this.goingRight = goingRight;
        this.cloud = cloud;
        this.darkCloud = darkCloud;
        this.glodenCloud = glodenCloud;
    }

    /**
     * A getter of y
     *
     * @return the y position of lane
     */
    public int getY() {
        return this.y;
    }

    /**
     * Draw the Lane
     *
     * @param canvas the canvas used in the game
     */
    public void draw(Canvas canvas) {

        for (Wood wood : woods) {
            wood.draw(canvas);
        }

    }

    /**
     * Randomly put all kinds of wood in the lane
     *
     * @param level the level of the game that player is currently at
     */
    private void putwood(int level) {
        int x;
        double w_speed;
        if (this.goingRight) {
            x = 0;
            w_speed = 1;
        } else {
            x = 30;
            w_speed = -1;
        }

        if (Math.random() < 0.01) {
            Wood wood = new GoldenWood(x, y, w_speed, glodenCloud, gridwidth, gridheight);
            System.out.println("Golden");
            woods.add(wood);
        } else if (Math.random() < 0.9 - level * 0.05) {
            Wood wood = new Wood(x, y, w_speed, cloud, gridwidth, gridheight);
            woods.add(wood);
        } else {
            Wood wood = new BrokenWood(x, y, w_speed, darkCloud, gridwidth, gridheight);
            woods.add(wood);
        }
    }

    /**
     * clear all the dark cloud in the lane when meeting golden cloud
     */
    public void clearBroken() {
        for (int i = 0; i < woods.size(); i++) {
            if (woods.get(i) instanceof BrokenWood) {
                int[] location = woods.get(i).getLocation();
                double speed = woods.get(i).getSpeed();
                Wood newWood = new Wood(location[0], location[1], speed, cloud, gridwidth, gridheight);
                woods.set(i, newWood);
            }
        }
    }

    /**
     * Make everything in the wood move
     *
     * @param levelInput the level of the game that player is currently at
     */
    // interact of the lane depend on the level different level have different level input
    public void interact(int levelInput) {
        for (Wood wood : woods) {
            wood.interact();
        }
        //check woods size:
        if (woods.size() == 0) {
            this.putwood(levelInput);
        }
        // update wood and clean the wood that out of boundary
        for (Wood wood : woods) {
            // check whether the wood should be destroyed
            if (wood.getLocation()[0] < 0 && wood.getLocation()[0] > 30) {
                woods.remove(wood);
            }
        }

        if (this.goingRight && woods.get(woods.size() - 1).location[0] > 6 + levelInput) {
            this.putwood(levelInput);
        } else if (!this.goingRight && woods.get(woods.size() - 1).location[0] < 26 - levelInput) {
            this.putwood(levelInput);
        }
    }

    public double getSpeed() {
        return speed;
    }
}

