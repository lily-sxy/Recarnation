package uoft.csc207.reincarnation.level1.manager;

import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uoft.csc207.reincarnation.StartGame;
import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.level1.items.Pause;

public class Stats extends Observable implements Observer {

    //the start time of the game
    private long startTime;

    //TextView that display the count of user's click and death
    private TextView touch;

    //in game states
    private int deathTime = 0;
    private int click = 0;
    private long playedTime = 0;

    /**
     * Setter
     *
     * @param touch is the display of the count of user's click and death
     */
    void setTouch(TextView touch) {
        this.touch = touch;
        reset();
    }

    /**
     * After each click, reset touch
     */
    private void reset() {
        Language language = StartGame.getCurrentUser().getLanguage();
        String text = language.getText("escape.touch") + click + "\n" +
                language.getText("escape.death") + deathTime;
        touch.setText(text);
    }

    /**
     * Start timing
     */
    void start() {
        startTime = System.nanoTime();
    }

    /**
     * Pause timing
     */
    public void pause() {
        playedTime = playedTime + System.nanoTime() - startTime;
    }

    /**
     * calculate the score of this game base on playTime and deathTime
     *
     * @return the score
     */
    private int getScore() {
        double playtime = getPlayedTime();
        double score = 200 * (Math.exp(-(playtime + deathTime * 30) / (300))) / (Math.exp(-playtime / 200) + 1);
        return (int) Math.round(score);
    }

    /**
     * @return the player playTime
     */
    private int getPlayedTime() {
        //in second
        return (int) (playedTime / 1000000000);
    }

    /**
     * Add to count of the number player click
     */
    public void click() {
        click++;
        reset();
        if (click == 30) {
            deathTime += 1;
            click = 0;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * return score, time, and death of the player
     *
     * @return the map of player stats after the game
     */
    public Map<String, Integer> getStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Score", getScore());
        stats.put("Time", getPlayedTime());
        stats.put("Death", deathTime);
        return stats;
    }

    /**
     * Clear the data, allow player to start a new game
     */
    public void finish() {
        playedTime = 0;
        deathTime = 0;
        click = 0;
    }

    /**
     * Getter
     *
     * @param type is identify to get time, death or score
     * @return the int in the user stats relevant to time, death, score
     */
    public int get(String type) {
        switch (type) {
            case "Time":
                return getPlayedTime();
            case "Death":
                return deathTime;
            case "Score":
                return getScore();
        }
        return 0;
    }

    /**
     * If observable is instanceof Pause, pause the game of resume the game base on o.
     * If o is true, pause the game,
     * If o is false, resume the game
     *
     * @param observable is instanceof Item
     * @param o          is boolean if observable is instanceof Pause
     */
    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Pause) {
            if ((boolean) o) {
                pause();
            } else {
                start();
            }
        }
    }
}
