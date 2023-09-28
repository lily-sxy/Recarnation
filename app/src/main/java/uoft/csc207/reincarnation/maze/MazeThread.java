package uoft.csc207.reincarnation.maze;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

// Thread of a maze game.
public class MazeThread extends Thread {

    private MazeView mazeView;
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the maze.
     */
    public static Canvas canvas;

    private int mspt;

    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param mazeV_in      A maze manager as input.
     */
    MazeThread(SurfaceHolder surfaceHolder, MazeView mazeV_in, int millisecondPerTick) {
        this.surfaceHolder = surfaceHolder;
        this.mazeView = mazeV_in;
        mspt = millisecondPerTick;
    }

    /**
     * Run the thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            long time_beg = System.nanoTime() / 1000;
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.mazeView.update();
                    this.mazeView.draw(canvas);
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
                }
            }
            long tick_length = (long) ((System.nanoTime() / 1000 - time_beg) / 1000);

            try {
                this.sleep(mspt - tick_length);
            } catch (IllegalArgumentException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set thread to a given status.
     * The status of a thread is running or not running.
     *
     * @param isRunning Given status.
     */
    void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
