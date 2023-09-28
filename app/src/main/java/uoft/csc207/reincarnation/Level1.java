package uoft.csc207.reincarnation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.level1.OnClickManager;
import uoft.csc207.reincarnation.level1.manager.*;


public class Level1 extends Activity implements View.OnClickListener, ILevel, Observer {
    //If play win the game
    int end = 0;
    //Game manager
    EscapeManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escape);

        //Build the Escape Manager
        EscapeBuilder builder = new EscapeBuilder();
        construct(builder, StartGame.getCurrentUser().getLanguage());
        manager = builder.getProduct();
        //Initialize the game
        manager.initial(findViewById(R.id.count));

    }

    @Override
    public void onClick(View view) {
        if (end == 0) {
            OnClickManager onClick = new OnClickManager();
            construct(onClick, view);
            onClick.click();
            manager.getStats().click();
        } else {//If user win the game
            StartGame.onReturn(this);
            manager.getStats().finish();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Password) {
            boolean escape = (boolean) o;
            if (escape) {
                end = 1;
                manager.getStats().pause();
                String text = manager.getLanguage().getText("escape.end.escape") +
                        manager.getLanguage().getText("escape.end.time") +
                        manager.getStats().get("Time") +
                        manager.getLanguage().getText("escape.end.death") +
                        manager.getStats().get("Death") +
                        manager.getLanguage().getText("escape.end.score") +
                        manager.getStats().get("Score") +
                        manager.getLanguage().getText("escape.end.continue");
                manager.getOutput().output(text);

            } else {
                manager.getOutput().output(manager.getLanguage().getText("escape.incorrect"));
            }
        } else if (observable instanceof Stats) {
            setContentView(R.layout.escape);
            manager.initial(findViewById(R.id.count));
        }
    }

    /**
     * Return the user statistics.
     * This is used to obtain user data from each level before level finish.
     *
     * @return the stats in map, notnull by default.
     */
    public Map<String, Integer> getStats() {
        Map<String, Integer> statistic = manager.getStats().getStats();
        statistic.put("Won", end);
        return statistic;
    }

    /**
     * Construct the Builder
     *
     * @param builder  is the Builder of EscapeManager
     * @param language is the current using language
     */
    private void construct(EscapeBuilder builder, Language language) {
        builder.buildFrame(this);
        builder.buildLanguage(language);
        builder.buildOutput(this);
        builder.buildPassword(this);
        builder.buildStats(this);
        builder.buildStorage(this);
    }

    /**
     * Create Item in OnClickManager and add Observers to it
     *
     * @param onClick is the onClickManager
     * @param view    is the clicked view
     */
    private void construct(OnClickManager onClick, View view) {
        onClick.setStorage(manager.getStorage());
        onClick.setActivity(this);
        onClick.setItem(view);
        onClick.addObser(manager.getPassword());
        onClick.addObser(manager.getFrame());
        onClick.addObser(manager.getOutput());
        onClick.addObser(manager.getStats());
    }
}
