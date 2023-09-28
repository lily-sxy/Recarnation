package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uoft.csc207.reincarnation.language.Language;

public class Levels extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        Language language = StartGame.getCurrentUser().getLanguage();

        Button level1 = findViewById(R.id.level1);
        level1.setOnClickListener(this);
        level1.setText(language.getText("main.levels.level1"));

        Button level2 = findViewById(R.id.level2);
        level2.setOnClickListener(this);
        level2.setText(language.getText("main.levels.level2"));

        Button level3 = findViewById(R.id.level3);
        level3.setOnClickListener(this);
        level3.setText(language.getText("main.levels.level3"));

        Button back = findViewById(R.id.back);
        back.setOnClickListener(this);
        back.setText(language.getText("main.levels.back"));

        Button end = findViewById(R.id.end);
        end.setOnClickListener(this);
        end.setText(language.getText("main.levels.ending"));

        TextView score1 = findViewById(R.id.score1);

        score1.setText(String.valueOf(StartGame.getCurrentUser().getScore("level1")));

        TextView score2 = findViewById(R.id.score2);
        score2.setText(String.valueOf(StartGame.getCurrentUser().getScore("level2")));

        TextView score3 = findViewById(R.id.score3);
        score3.setText(String.valueOf(StartGame.getCurrentUser().getScore("level3")));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.level1:
                startActivity(new Intent(Levels.this, Level1.class));
                break;
            case R.id.level2:
                startActivity(new Intent(Levels.this, Level2.class));
                break;
            case R.id.level3:
                startActivity(new Intent(Levels.this, Level3.class));
                break;
            case R.id.back:
                startActivity(new Intent(Levels.this, StartGame.class));
                break;
            case R.id.end:
                startActivity(new Intent(Levels.this, EndingActivity.class));
                finish();
                break;
        }
    }
}
