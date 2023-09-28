package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends Activity implements View.OnClickListener {

    Button start_game;
    int[] startStory = {R.drawable.story1, R.drawable.story2, R.drawable.story3,
            R.drawable.story4, R.drawable.story5, R.drawable.story6};
    int curr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.begining);

        start_game = findViewById(R.id.start_game);
        start_game.setOnClickListener(this);

        findViewById(R.id.start_story).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_game:
                startActivity(new Intent(StartActivity.this, Level1.class));
                break;
            case R.id.start_story:
                if (curr == startStory.length - 1) {
                    start_game.setVisibility(View.VISIBLE);
                } else {
                    curr++;
                    ((ImageView) findViewById(R.id.start_story)).setImageResource(startStory[curr]);
                }
        }
    }
}


