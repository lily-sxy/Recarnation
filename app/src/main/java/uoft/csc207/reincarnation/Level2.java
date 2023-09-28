package uoft.csc207.reincarnation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.MotionEvent;

import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

import uoft.csc207.reincarnation.maze.MazeView;
import uoft.csc207.reincarnation.maze.userInput.ClickInput;
import uoft.csc207.reincarnation.maze.userInput.IUserInput;
import uoft.csc207.reincarnation.maze.userInput.MoveInput;

public class Level2 extends Activity {

    private Queue<IUserInput> inputQueue;
    MazeView mazeView;

    public float lastX;
    public float lastY;
    public boolean touching;

    public boolean ended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                );

        inputQueue = new ArrayDeque<>();
        mazeView = new MazeView(this);
        setContentView(mazeView);
        mazeView.setInputQueue(inputQueue);
        ended = false;
    }

    /**
     * This method converts touch event to a simpler user input.
     * This isolates mazeView from screen touch events.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touching = true;
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!touching) {
                    touching = true;
                    lastX = event.getX();
                    lastY = event.getY();
                } else {
                    float vx = event.getX() - lastX;
                    float vy = event.getY() - lastY;
                    float factor = (float) Math.sqrt(vx * vx + vy * vy);
                    inputQueue.offer(new MoveInput(vx / factor, vy / factor));
                }
                break;
            case MotionEvent.ACTION_UP:
                touching = false;
                if ((int) event.getX() == (int) lastX && (int) event.getY() == (int) lastY) {
                    inputQueue.offer(new ClickInput(event.getX(), event.getY()));
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                touching = false;
                break;
        }

        if (mazeView.isEnd() && !ended) {
            Map<String, Integer> stats = mazeView.getStats();
            ended = true;
            this.finish();
            Integer win = stats.get("Won");
            if (win != null) {
                StartGame.onReturn(this.mazeView);
            }

        }

        return true;
    }
}
