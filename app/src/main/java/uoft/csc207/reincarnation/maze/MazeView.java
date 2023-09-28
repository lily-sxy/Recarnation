package uoft.csc207.reincarnation.maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import uoft.csc207.reincarnation.ILevel;
import uoft.csc207.reincarnation.maze.userInput.IUserInput;
import uoft.csc207.reincarnation.StartGame;
import uoft.csc207.reincarnation.language.Language;

public class MazeView extends SurfaceView implements SurfaceHolder.Callback, ILevel {
    private int mspt = 50;
    private Queue<IUserInput> inputQueue;
    private MazeThread thread;

    private float blockSize;
    private int numHeight = 15;

    // pause denotes a state if paused, 1 for pause and 2 for hint.
    private int paused;
    //
    private int ended;
    private int timer;

    /**
     * Screen width and height.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private MazeManager mazeManager;

    /**
     * Create a new fish tank in the context environment.
     *
     * @param context the environment.
     */
    public MazeView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        blockSize = (float) (screenHeight / 2.0 / (numHeight + 1));
        mazeManager = new MazeManager(blockSize, screenWidth, screenHeight);
        paused = 0;
        this.ended = 10;
        timer = 0;

        thread = new MazeThread(getHolder(), this, mspt);
    }

    /**
     * Set inputQueue.
     *
     * @param queue_in input queue, by default not null.
     */
    public void setInputQueue(Queue<IUserInput> queue_in) {
        inputQueue = queue_in;
    }

    /**
     * Set state to pause.
     */
    public void pause() {
        paused = 1;
    }

    /**
     * Set state to running.
     */
    public void resume() {
        paused = 0;
    }

    /**
     * Set state to show hint page.
     */
    public void hint() {
        paused = 2;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        numHeight = 15;
        updateScreenSize();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (width != screenWidth || height != screenHeight) {
            updateScreenSize();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Read and interpret input.
     */
    public void update() {
        if (this.paused == 0 && !mazeManager.isEnd())
            timer += mspt;
        if (mazeManager.isEnd()) {
            ended--;
            if (ended == 0)
                thread.setRunning(false);
        }

        while (!this.inputQueue.isEmpty()) {
            IUserInput input = inputQueue.remove();

            if (this.paused == 0 && !mazeManager.isEnd()) {
                switch (input.getType()) {
                    case 0:
                        if (input.getX() < screenWidth / 16.0 &&
                                input.getY() < screenHeight / 16.0)
                            this.pause();
                        break;
                    case 1:
                        mazeManager.update(input.getX(), input.getY());
                        break;
                    default:
                        break;
                }
            } else if (paused == 2) {
                if (input.getType() == 0)
                    this.pause();
            } else {
                if (input.getType() == 0) {
                    if (input.getX() < screenWidth / 16.0 * 7) {
                        if (input.getY() < screenHeight / 2.0) {
                            resume();
                        } else if (input.getY() < screenHeight / 8.0 * 5) {
                            mazeManager.retry(screenWidth, screenHeight);
                            resume();
                        } else if (input.getY() < screenHeight / 4.0 * 3) {
                            mazeManager.giveUp();
                        }
                    } else if (input.getX() > screenWidth / 16.0 * 15 &&
                            input.getY() < screenHeight / 16.0 * 15)
                        this.hint();
                }
            }
        }
    }

    /**
     * Draw the maze view or pause page.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);

        if (canvas != null) {
            if (this.paused == 0 && !mazeManager.isEnd()) {
                mazeManager.draw(canvas);

                canvas.drawRect(screenWidth / 64, screenHeight / 64,
                        screenWidth / 48, screenHeight / 64 * 3, paint);
                canvas.drawRect(screenWidth / 24, screenHeight / 64,
                        screenWidth / 64 * 3, screenHeight / 64 * 3, paint);
            } else if (mazeManager.isEnd()) {
                drawEnd(canvas);
            } else if (paused == 2) {
                drawHint(canvas);
            } else {
                drawPause(canvas);
            }
        }
    }

    /**
     * Draw pause page.
     */
    private void drawPause(Canvas canvas) {
        Language language = StartGame.getCurrentUser().getLanguage();

        Paint paint = new Paint();
        // Draw a pause sign
        paint.setColor(Color.GRAY);
        canvas.drawRect(screenWidth / 8, screenHeight / 8,
                screenWidth / 6, screenHeight / 8 * 3, paint);
        canvas.drawRect(screenWidth / 3, screenHeight / 8,
                screenWidth / 8 * 3, screenHeight / 8 * 3, paint);

        paint.setColor(Color.DKGRAY);
        canvas.drawRect(
                0, (float) (screenHeight / 2.0),
                (float) (screenWidth / 2.0), (float) (screenHeight / 8.0 * 4.99),
                paint
        );
        canvas.drawRect(
                0, (float) (screenHeight / 8.0 * 5.01),
                (float) (screenWidth / 2.0), (float) (screenHeight / 4.0 * 3),
                paint);

        paint.setColor(Color.GRAY);
        paint.setTextSize(screenHeight / 16);
        canvas.drawText(
                language.getText("maze.pause.seed") + mazeManager.getSeed(),
                screenWidth / 2, screenHeight / 16 * 3, paint
        );
        canvas.drawText(
                language.getText("maze.pause.time") + (this.timer / 1000) + "s",
                screenWidth / 2, screenHeight / 16 * 5, paint
        );
        canvas.drawText(
                language.getText("maze.pause.distance") + (int) mazeManager.getDistance() + "m",
                screenWidth / 2, screenHeight / 16 * 7, paint
        );
        canvas.drawText(
                language.getText("maze.pause.coin") + mazeManager.getCoin(),
                screenWidth / 2, screenHeight / 16 * 9, paint
        );
        canvas.drawText(
                language.getText("maze.pause.retry") + mazeManager.getFail(),
                screenWidth / 2, screenHeight / 16 * 11, paint
        );

        paint.setColor(Color.RED);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(
                language.getText("maze.pause.giveup"),
                screenWidth / 8, screenHeight / 16 * 9, paint
        );
        canvas.drawText(
                language.getText("maze.pause.exit"),
                screenWidth / 8, screenHeight / 16 * 11, paint
        );

        paint.setColor((Color.BLUE));
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("?", screenWidth / 16 * 15, screenWidth / 16, paint);
    }

    /**
     * Draw end page.
     */
    private void drawEnd(Canvas canvas) {
        Language language = StartGame.getCurrentUser().getLanguage();

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(screenHeight / 16);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        if (mazeManager.isWin()) {
            paint.setColor(Color.YELLOW);
            canvas.drawText(
                    language.getText("maze.end.win"),
                    screenWidth / 2, screenHeight / 16, paint
            );
        } else {
            paint.setColor(Color.RED);
            canvas.drawText(
                    language.getText("fail"),
                    screenWidth / 2, screenHeight / 16, paint
            );
        }
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(
                language.getText("maze.end.continue"),
                screenWidth / 2, screenHeight / 8 * 7, paint
        );

        paint.setColor(Color.GRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(
                language.getText("maze.end.seed") + mazeManager.getSeed(),
                screenWidth / 16, screenHeight / 16 * 3, paint
        );
        canvas.drawText(
                language.getText("maze.end.time") + (this.timer / 1000) + "s",
                screenWidth / 16, screenHeight / 16 * 5, paint
        );
        canvas.drawText(
                language.getText("maze.end.distance") + (int) mazeManager.getDistance() + "m",
                screenWidth / 16, screenHeight / 16 * 7, paint
        );
        canvas.drawText(
                language.getText("maze.end.coin") + mazeManager.getCoin(),
                screenWidth / 16, screenHeight / 16 * 9, paint
        );
        canvas.drawText(
                language.getText("maze.end.retry") + mazeManager.getFail(),
                screenWidth / 16, screenHeight / 16 * 11, paint
        );
        if (mazeManager.isWin()) {
            canvas.drawText(
                    language.getText("maze.end.score") + getScore(),
                    screenWidth / 16, screenHeight / 16 * 13, paint
            );
        }
    }

    /**
     * Draw hint page.
     */
    private void drawHint(Canvas canvas) {
        Language language = StartGame.getCurrentUser().getLanguage();

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(screenHeight / 16);
        canvas.drawText(
                language.getText("maze.hint.text0"),
                0, screenHeight / 16 * 3, paint
        );
        canvas.drawText(
                language.getText("maze.hint.text1"),
                0, screenHeight / 16 * 4, paint
        );
        canvas.drawText(
                language.getText("maze.hint.text2"),
                0, screenHeight / 16 * 5, paint
        );

        paint.setColor(Color.BLUE);
        canvas.drawText(
                language.getText("maze.hint.text3"),
                0, screenHeight / 16 * 7, paint
        );
        paint.setColor(Color.rgb(255, 165, 0));
        canvas.drawText(
                language.getText("maze.hint.text4"),
                0, screenHeight / 16 * 8, paint
        );

        paint.setColor(Color.GRAY);
        canvas.drawText(
                language.getText("maze.hint.text5"),
                0, screenHeight / 16 * 10, paint
        );
        canvas.drawText(
                language.getText("maze.hint.text6"),
                0, screenHeight / 16 * 11, paint
        );

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        canvas.drawText(
                language.getText("maze.hint.continue"),
                screenWidth / 2, screenHeight / 8 * 7, paint
        );

        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(
                language.getText("maze.hint.title"),
                screenWidth / 2, screenHeight / 16, paint
        );
    }

    /**
     * Check if the maze ended.
     * Level2 will call this method, if true, onReturn will be called.
     */
    public boolean isEnd() {
        return this.ended <= 0;
    }

    /**
     * Collect the statistic when game ended.
     */
    public Map<String, Integer> getStats() {
        Map<String, Double> maze_stat = mazeManager.getStats();
        Map<String, Integer> rtn = new HashMap<>();

        rtn.put("Time", timer / 1000);
        rtn.put("Coin", (int) (double) mazeManager.getCoin());
        rtn.put("Score", (int) this.getScore());
        try {
            rtn.put("Distance", (int) (double) maze_stat.get("distance_walked"));
            rtn.put("Won", (int) (double) maze_stat.get("maze_won"));
            rtn.put("Played", mazeManager.getFail());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * Calculate the score.
     * Notice that score is only accurate if mazeManager.isEnd()
     */
    private double getScore() {
        int played = mazeManager.getFail();
        double rtn = -(timer / 1000.0 - 90);
        // Time and coin matters for score.
        rtn = (100.0 * Math.pow(2.0, (rtn + mazeManager.getCoin() - 5) / 10) / Math.pow(2.0, rtn / 10));
        // Reduce score if retried too many times.
        rtn *= Math.pow(2.0, -played + 6.0) / (1.0 + Math.pow(2.0, -played + 6.0));
        return rtn;
    }

    /**
     * Update screen size.
     */
    private void updateScreenSize() {
        blockSize = (float) (screenHeight / 2.0 / (numHeight + 1));
        mazeManager.setDrawing(blockSize, screenWidth, screenHeight);
    }
}
