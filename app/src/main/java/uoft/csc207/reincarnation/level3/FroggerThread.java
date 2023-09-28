package uoft.csc207.reincarnation.level3;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class FroggerThread extends Thread {

    /**
     * Where the frogger items are drawn.
     */
    private FroggerView froggerView;
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private String status;
    /**
     * The canvas on which to draw the game
     */
    private Canvas canvas;
    /**
     * the status of the player, wehther player pause or win or level up
     */
    private String pauseOrWin = "";
    /**
     * the number of time that PauseView drawn
     */
    private int drawPauseView = 3;
    /**
     * the number of time that WinView drawn
     */
    private int drawWin = 0;
    /**
     * the number of time that levelUpView drawn
     */
    private int drawLevelView = 0;


    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param view          where the fish tank items are drawn.
     */
    public FroggerThread(SurfaceHolder surfaceHolder, FroggerView view) {
        this.surfaceHolder = surfaceHolder;
        this.froggerView = view;
    }


    @Override
    public void run() {
        int turn = 0;
        while (status.equals("Running")) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // update and draw here.
                    if (pauseOrWin.equals("") && !froggerView.lose(canvas)) {
                        froggerView.update(turn);
                        froggerView.updateTime(turn);
                        froggerView.draw(canvas);
                    } else if (pauseOrWin.equals("Pause") && drawPauseView < 3) {
                        froggerView.draw(canvas);

                        // draw pause view
                        froggerView.drawPauseView(canvas);

                        drawPauseView += 1;
                    } else if (pauseOrWin.equals("LevelUp") && froggerView.getLevel() <= 2
                            && drawLevelView < 3) {
                        froggerView.drawLevelView(canvas);
                        drawLevelView++;
                    } else if (pauseOrWin.equals("Win") && drawWin < 3) {
                        froggerView.drawWin(canvas);
                        drawWin += 1;
                    } else if (pauseOrWin.equals("Win")) {
                        finish();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    turn++;
                }
            }

            try {
                sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the status to be "Running", to run the thread.
     */
    void setRunning() {
        this.status = "Running";
    }

    /**
     * Set the pauseOrWin status to be "Pause", which represent pause the game
     */
    void stopRunning() {
        pauseOrWin = "Pause";
        drawPauseView = 0;
    }

    /**
     * Set the pauseOrWin status to be "", which represent resume the game
     */
    void resumeRunning() {
        pauseOrWin = "";
    }

    /**
     * Set the status to be "destroy", shows that the game is over, to stop the thread
     */
    void finish() {
        this.status = "destroy";
    }

    /**
     * Set the pauseOrWin status to be "Win", which represent win the game
     */
    void win() {
        pauseOrWin = "Win";
    }

    /**
     * Set the pauseOrWin status to be "LevelUp", which represent player pass the current level
     * and will level up
     */
    void levelUp() {
        pauseOrWin = "LevelUp";
        drawLevelView = 0;
    }
}
