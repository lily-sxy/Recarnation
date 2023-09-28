package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class EndingActivity extends Activity implements View.OnClickListener {

    Button end_game;
    int[][] endStory = {{R.drawable.storyend_fish}, {R.drawable.end_story_baby}, {R.drawable.end_story_dream}};
    int curr = 0;
    int ending = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending);

        end_game = findViewById(R.id.end_game);
        end_game.setOnClickListener(this);

        getEnding();

        findViewById(R.id.end_story).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.end_game:
                startActivity(new Intent(EndingActivity.this, StartGame.class));
                break;
            case R.id.end_story:
                if (curr == endStory[ending].length) {
                    end_game.setVisibility(View.VISIBLE);
                } else {
                    ((ImageView) findViewById(R.id.end_story)).setImageResource(endStory[ending][curr]);
                    curr++;
                }
        }
    }

    public void getEnding() {
        if (StartGame.getCurrentUser() == null) {
            ending = 0;
        } else {
            int finalScore = StartGame.getCurrentUser().getFinalScore();
            int[] stages = {100, 200, 300};
            for (int i = 0; i < stages.length; i++) {
                if (finalScore <= stages[i]) {
                    ending = i;
                    break;
                }
            }
        }

    }
}
