package uoft.csc207.reincarnation.level3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uoft.csc207.reincarnation.ILevel;
import uoft.csc207.reincarnation.Levels;
import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.StartGame;
import uoft.csc207.reincarnation.level3.item.Role;

public class FroggerView extends SurfaceView implements SurfaceHolder.Callback, ILevel {

    public FroggerManager froggerManager;
    /**
     * The width of the grid.
     */
    public float gridWidth;
    /**
     * The height of the grid.
     */
    public float gridHeight;
    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The part of the program that manages time.
     */
    FroggerThread thread;

    private Context context;
    /**
     * the flag use to check whether the player is lose
     */
    boolean ifLose = false;

    /**
     * the bitmap used to draw graph
     */
    Map<String, Bitmap> bitmaps = new HashMap<>();

    /**
     * the level of player and also a flag to identify whether the player need to level up or win
     * or partial win.
     */
    int level = 0;

    /**
     * the timmer used in the game.
     */
    Timmer timer = new Timmer();
    /**
     * A collection of the game statistics, total win times, total play time, total score and total
     * dead time
     */
    Map<String, Integer> stats = new HashMap<>();

    /**
     * Initialize a frogger view.
     *
     * @param context
     */
    public FroggerView(Context context) {
        super(context);
        this.context = context;
        gridHeight = screenHeight / 10;
        gridWidth = screenWidth / 30;
        getHolder().addCallback(this);
        thread = new FroggerThread(getHolder(), this);
        setFocusable(true);
        setBitMap();
        stats.put("Score", 0);
        stats.put("Death", 0);
        stats.put("Won", 0);
        stats.put("Time", 0);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Use the letter size and screen height to determine the size of the fish tank.
        froggerManager =
                new FroggerManager(gridWidth, gridHeight, bitmaps, level, screenHeight, screenWidth);
        froggerManager.create();

        thread.setRunning();
        thread.start();
    }

    /**
     * get the role in the frogger manager
     *
     * @return return the role used in the frogger manager
     */

    public Role getRole() {
        return froggerManager.getRole();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.finish();
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Draw frogger manager on canvas
     *
     * @param canvas the canvas used in the game
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            froggerManager.draw(canvas);
        }
    }

    /**
     * Update frogger manager
     *
     * @param turn
     */
    public void update(int turn) {
        froggerManager.update(turn);
    }

    /**
     * Return a map of the lanes in the frogger manager
     *
     * @return a map of the lanes in the frogger manager
     */
    public Map getLanes() {
        return froggerManager.getLanes();
    }

    /**
     * Update the time of the timmer in the game of each level every turn as well as the time in
     * frogger manager
     *
     * @param turn
     */
    public void updateTime(int turn) {
        if (turn % 3 == 0) {
            int temp = timer.updateTime();
            String currTime = timer.formatTime(temp);
            froggerManager.updateTime(currTime);
        }
    }

    /**
     * Give pause order to thread and pause the game
     */
    public void pause() {
        thread.stopRunning();
        timer.startPause();
    }

    /**
     * Give resume order to thread and resume the game
     */
    public void resume() {
        thread.resumeRunning();
        timer.pauseEnd();

        // clear pause view
        froggerManager.resumeView();
    }

    /**
     * Give finish order to thread and terminate the game
     */
    public void terminate() {
        thread.finish();
        Intent intent = new Intent(context, Levels.class);
        intent.setClass(context, Levels.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * Give win order to thread and to reach the partial win by increasing level large engough to
     * let the game to know the game is over and the player is partially winning
     */
    public void partialWin() {
        level = 10;
        thread.win();
    }

    /**
     * Give win and level up order to the thread depending on the current level
     */
    public void win() {
        stats.put("Won", 1);
        if (level < 2) {
            thread.levelUp();//maybejust levelup
        } else {
            thread.win();
        }
        level++;
    }

    /**
     * End the game and turn to the end game acitcity
     */
    public void ending() {
        /*Intent intent = new Intent(context, EndingActivity.class);
        intent.setClass(context, EndingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);*/
        StartGame.onReturn(this);
    }

    /**
     * To draw Win page in the frogger manager
     *
     * @param canvas the canvas the used in the game
     */
    public void drawWin(Canvas canvas) {
        //this.updateStates();
        froggerManager.drawWin(stats, canvas);//input all the stats in the game
    }

    /**
     * To draw Pause page in the frogger manager
     *
     * @param canvas the canvas the used in the game
     */
    public void drawPauseView(Canvas canvas) {
        froggerManager.drawPauseView(canvas);
    }

    /**
     * To draw Level Up page in the frogger manager
     *
     * @param canvas the canvas the used in the game
     */
    public void drawLevelView(Canvas canvas) {
        //this.updateStates();
        froggerManager.drawLevelView(canvas);
        // collect the date in each level

    }

    /**
     * To draw restart the game and close the Level Up page
     *
     * @param level the level that player are going to
     */
    public void restart(int level) {
        froggerManager.closeLevelView();
        timer.renew();
        froggerManager = new FroggerManager(gridWidth, gridHeight, bitmaps, level,
                screenHeight, screenWidth);
        froggerManager.create();
        thread.resumeRunning();
        ifLose = false;
    }

    /**
     * To draw lose page in the frogger manager if the player loses.
     *
     * @param canvas the canvas the used in the game
     * @return return whether the player loses
     */
    public boolean lose(Canvas canvas) {
        if (timer.ifLose()) {
            froggerManager.drawLoseView(canvas);
            ifLose = true;
            return true;
        }
        return false;
    }

    /**
     * A getter of ifLose
     *
     * @return whether the player loses the game.
     */
    public boolean getIfLose() {
        return ifLose;
    }

    @Override
    public Map<String, Integer> getStats() {
        return stats;
    }

    /**
     * Update the stats
     */
    public void updateStates() {
        froggerManager.updateStats(timer.getCountTime());
        Map<String, Integer> temp = froggerManager.getStats();
        temp.put("Time", timer.getCountTime());
        System.out.println(level);
        Set<String> keys = stats.keySet();
        for (String key : keys) {
            int temp1 = stats.get(key);
            int temp2 = temp.get(key);
            stats.replace(key, temp1, temp2 + temp1);
        }
    }

    /**
     * Set Bit Map for graphing
     */
    public void setBitMap() {
        Bitmap background = BitmapFactory.decodeResource(
                getResources(), R.drawable.level3background);
        bitmaps.put("Background", background);
        Bitmap role = BitmapFactory.decodeResource(getResources(), R.drawable.role);
        bitmaps.put("Role", role);
        Bitmap cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        bitmaps.put("Cloud", cloud);
        Bitmap darkCloud = BitmapFactory.decodeResource(getResources(), R.drawable.darkcloud);
        bitmaps.put("Darkcloud", darkCloud);
        Bitmap goldenCloud = BitmapFactory.decodeResource(getResources(), R.drawable.goldcloud);
        bitmaps.put("Goldencloud", goldenCloud);
        Bitmap guardLeft = BitmapFactory.decodeResource(getResources(), R.drawable.guardleft);
        bitmaps.put("Guardleft", guardLeft);
        Bitmap guardRight = BitmapFactory.decodeResource(getResources(), R.drawable.guardright);
        bitmaps.put("Guardright", guardRight);

    }

    /**
     * a getter of the level
     *
     * @return the level that the player are currently at
     */
    public int getLevel() {
        return level;
    }

    /**
     * clear all the broken wood
     */
    void clearBroken() {
        froggerManager.clearBroken();
    }
}
