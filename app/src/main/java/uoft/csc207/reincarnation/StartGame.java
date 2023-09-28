package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.level3.FroggerView;
import uoft.csc207.reincarnation.maze.MazeView;
import uoft.csc207.reincarnation.userdata.User;

public class StartGame extends Activity implements View.OnClickListener {
    static StartGame this_;
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Language language = getCurrentUser().getLanguage();

        Button continuous = findViewById(R.id.continuous);
        continuous.setOnClickListener(this);
        continuous.setText(language.getText("main.start.cont"));

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);
        exit.setText(language.getText("main.start.exit"));

        Button newGame = findViewById(R.id.new_game);
        newGame.setOnClickListener(this);
        newGame.setText(language.getText("main.start.start"));

        this_ = this;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_game:
                startActivity(new Intent(StartGame.this, StartActivity.class));
                break;
            case R.id.continuous:
                startActivity(new Intent(StartGame.this, Levels.class));
                break;
            case R.id.exit:
                getCurrentUser().save();
                startActivity(new Intent(StartGame.this, MainActivity.class));
                break;
        }
    }

    public void onReturn(Class<?> cls) {
        startActivity(new Intent(StartGame.this, cls));
    }

    public static void onReturn(ILevel level) {
        boolean won = false;
        Map<String, Integer> stats = level.getStats();
        try {
            won = stats.get("Won") != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = getCurrentUser();
        if (won) {
            if (level instanceof Level1) {
                user.addScore(stats.get("Score"), "level1");
                this_.onReturn(Level2.class);
            } else if (level instanceof MazeView) {
                user.addScore(stats.get("Score"), "level2");
                this_.onReturn(Level3.class);
            } else if (level instanceof FroggerView) {
                user.addScore(stats.get("Score"), "level3");
                this_.onReturn(EndingActivity.class);
            }
            user.save();
        } else {
            this_.onReturn(Levels.class);
        }
    }

    public static User getCurrentUser() {
        return user;
    }

    public static void logInUser(User user_in) {
        user = user_in;
    }

}
