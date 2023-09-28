package uoft.csc207.reincarnation.level3;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

import uoft.csc207.reincarnation.level3.item.Guard;
import uoft.csc207.reincarnation.level3.item.Lane;
import uoft.csc207.reincarnation.level3.item.Role;

public class FroggerManager {
    /**
     * the level of the game
     */
    private int level;
    /**
     * all the lane inside the game
     */
    private Map<Integer, Lane> lanes;
    /**
     * the grid width
     */
    private float gridwidth;
    /**
     * the grid height
     */
    private float gridheight;
    /**
     * the low speed of the lane
     */
    private static int lowspeed = 20;
    /**
     * the high speed of the lane
     */
    private static int highspeed = 15;
    /**
     * all the role of the game
     */
    private Role role;
    /**
     * all the guard of the game
     */
    private Guard guard;
    /**
     * the running time of the game
     */
    private String time = "00:00:000";
    /**
     * a collection of stats of the game, key including Score, Death, Time, Won
     */
    private Map<String, Integer> stats = new HashMap<>();
    /**
     * graph factory of the picture of the game.
     */
    private GraphFactory graphFactory;
    /**
     * a drawer of the fame
     */
    private Drawer drawer;
    /**
     * the score of the game
     */
    private double iScore;
    /**
     * a timer of the game
     */
    private Timmer timer = new Timmer();

    /**
     * Initialize a frogger manager.
     *
     * @param width        the grid width
     * @param height       the grid height
     * @param bitmaps      the collection of bitmaps of the game
     * @param level        the level of the game
     * @param screenHeight the screen height
     * @param screenWidth  the screen width
     */
    FroggerManager(float width, float height, Map<String, Bitmap> bitmaps, int level,
                   int screenHeight, int screenWidth) {
        this.gridwidth = width;
        this.gridheight = height;
        lanes = new HashMap();
        stats.put("Won", 0);
        graphFactory = new GraphFactory(bitmaps, screenHeight, screenWidth);
        this.level = level;
        this.drawer = new Drawer(graphFactory);
        lowspeed = lowspeed - (level - 1) * 5;
        highspeed = highspeed - (level - 1) * 5;
    }

    /**
     * draw all the item of the game.
     *
     * @param canvas the canvas we need to draw on
     */
    public void draw(Canvas canvas) {
        // background
        drawer.drawBackground(canvas);

        guard.draw(canvas);
        for (int i = 2; i <= 7; i++) {
            Lane item = lanes.get(i);
            item.draw(canvas);
        }
        role.draw(canvas);

        // timer
        drawer.drawTimmer(canvas, time);

        // deadtime counter
        drawer.drawDeadTime(canvas, role);

        // Pause Button
        drawer.drawPauseButton(canvas);
    }

    /**
     * update all the item of the game.
     *
     * @param turn how many 10ms passed.
     */
    public void update(int turn) {
        for (int i = 2; i <= 7; i++) {
            Lane item = lanes.get(i);
            if (turn % lowspeed == 0 && item.getSpeed() == lowspeed) {
                // update lowspeed lane
                item.interact(this.level);
            } else if (turn % highspeed == 0 && item.getSpeed() == highspeed) {
                // update highspeed lane
                item.interact(this.level);
            }
        }
        if (turn % 5 == 0) {
            // update guard
            guard.interact();
        }
        if (role.getOnThisWood() != null) {
            // move role with the wood.
            role.follow();
        }

        // use helper to get if role is dead
        if (role.getLocation()[1] <= 7 && role.getLocation()[1] >= 2 && role.getOnThisWood() == null) {
            role.dead();
        } else if (guard.found(role)) {
            role.dead();
        } else if (role.getLocation()[1] > 10 || role.getLocation()[0] < 0
                || role.getLocation()[0] > 30) {
            role.dead();
        }
    }

    /**
     * create all the item of the game.
     */
    void create() {
        Bitmap cloudMap = graphFactory.createBitmap("Cloud");
        Bitmap darkCloudMap = graphFactory.createBitmap("Darkcloud");
        Bitmap goldenCloadMap = graphFactory.createBitmap("Goldencloud");
        Bitmap guardleftMap = graphFactory.createBitmap("Guardleft");
        Bitmap guardrightMap = graphFactory.createBitmap("Guardright");
        Bitmap roleMap = graphFactory.createBitmap("Role");
        lanes.put(2, new Lane(highspeed, 2, gridwidth, gridheight, true, cloudMap, darkCloudMap, goldenCloadMap));
        lanes.put(3, new Lane(lowspeed, 3, gridwidth, gridheight, true, cloudMap, darkCloudMap, goldenCloadMap));
        lanes.put(4, new Lane(highspeed, 4, gridwidth, gridheight, false, cloudMap, darkCloudMap, goldenCloadMap));
        lanes.put(5, new Lane(lowspeed, 5, gridwidth, gridheight, false, cloudMap, darkCloudMap, goldenCloadMap));
        lanes.put(6, new Lane(highspeed, 6, gridwidth, gridheight, true, cloudMap, darkCloudMap, goldenCloadMap));
        lanes.put(7, new Lane(lowspeed, 7, gridwidth, gridheight, false, cloudMap, darkCloudMap, goldenCloadMap));
        int[] range = {0, 30};
        int[] initLocation = {15, 9};
        guard = new Guard(range, initLocation, guardrightMap, guardleftMap, gridwidth, gridheight);
        role = new Role(roleMap, gridwidth, gridheight);
    }

    /**
     * get the role of the game
     *
     * @return the role of the game
     */
    Role getRole() {
        return role;
    }

    /**
     * get all the lanes of the game
     *
     * @return collection of all lanes of the game.
     */
    Map getLanes() {
        return lanes;
    }

    /**
     * update the running time.
     */
    void updateTime(String time) {
        this.time = time;
    }

    /**
     * draw the pause view.
     *
     * @param canvas the canvas we need to draw on
     */
    void drawPauseView(Canvas canvas) {
        drawer.drawPauseView(canvas);
    }

    /**
     * clear all the pause view
     */
    void resumeView() {
        drawer.resumeView();
    }

    /**
     * draw the level up view.
     *
     * @param canvas the canvas we need to draw on
     */
    void drawLevelView(Canvas canvas) {
        drawer.drawLevelView(canvas);
    }

    /**
     * clear the level up view
     */
    void closeLevelView() {
        drawer.closeLevelView();
    }

    /**
     * calculate the score of the game.
     *
     * @param time the running time of the game
     */
    private void updateScore(long time) {
        iScore = Math.floor(200 * ((Math.pow(Math.E, -((role.getDeadTime() + 0.01) * (time / 1000))
                / 100)) / (Math.pow(Math.E, -((time / 1000) / 100000) / 100) + 1)));
        iScore = iScore / 3;
    }

    /**
     * update the stats of the game.
     *
     * @param time the running time of the game
     */
    void updateStats(long time) {
        updateScore(time);
        stats.put("Score", (int) iScore);
        stats.put("Death", role.getDeadTime());
    }

    /**
     * draw the wining view.
     *
     * @param stats  the total stats of the game
     * @param canvas the canvas we need to draw on
     */
    void drawWin(Map<String, Integer> stats, Canvas canvas) {
        int dead = stats.get("Death");
        int time = stats.get("Time");
        int score = stats.get("Score");
        drawer.drawWin(canvas, timer.formatTime(time), String.valueOf(dead), score);
        // problem: time is the last stage time
        // problem: score is the total of the first level

        /*updateScore(time);
        drawer.drawWin(time, canvas, role, this.time, this.getDeathTime(), iScore);

        stats.put("Score", (int) iScore);
        stats.put("Death", role.getDeadTime());
        stats.put("Won", 1);*/
    }

    /**
     * draw the lose view.
     *
     * @param canvas the canvas we need to draw on
     */
    void drawLoseView(Canvas canvas) {
        drawer.drawLoseView(canvas);
    }

    /**
     * get the stats of the game
     *
     * @return the stats of the game
     */
    Map<String, Integer> getStats() {
        return stats;
    }

    /**
     * clear all the broken wood
     */
    void clearBroken() {
        for (int i = 2; i <= 7; i++) {
            Lane lane = lanes.get(i);
            lane.clearBroken();
        }
    }
}
