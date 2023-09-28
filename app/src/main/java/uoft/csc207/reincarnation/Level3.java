package uoft.csc207.reincarnation;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import uoft.csc207.reincarnation.level3.FroggerGestureListener;
import uoft.csc207.reincarnation.level3.FroggerView;

public class Level3 extends Activity {
    private GestureDetector detector;
    public FroggerGestureListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FroggerView froggerView = new FroggerView(this);
        listener = new FroggerGestureListener();
        listener.froggerView = froggerView;
        detector = new GestureDetector(this, listener);
        setContentView(froggerView);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
}
