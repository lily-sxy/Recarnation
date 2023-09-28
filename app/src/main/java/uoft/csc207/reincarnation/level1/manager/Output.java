package uoft.csc207.reincarnation.level1.manager;

import android.app.Activity;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Use for set user output after make some interaction to the game
 */
public class Output implements Observer {
    //Current activity
    private Activity activity;
    //TextView that print the output
    private TextView output;

    /**
     * Setter
     *
     * @param act is the current activity
     */
    public void setActivity(Activity act) {
        activity = act;
    }

    /**
     * Setter
     *
     * @param id is use to find the View belong to the id
     */
    public void setItem(int id) {
        output = activity.findViewById(id);
    }

    /**
     * rint the result of player's interaction on screen.
     *
     * @param observable is Item
     * @param o          is null
     */
    @Override
    public void update(Observable observable, Object o) {
        output(observable.toString());
    }

    /**
     * Print the result of player's interaction on screen.
     *
     * @param output String that print on screen
     */
    public void output(String output) {
        this.output.setText(output);
    }
}
