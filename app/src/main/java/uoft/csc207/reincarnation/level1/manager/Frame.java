package uoft.csc207.reincarnation.level1.manager;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.level1.items.Change;
import uoft.csc207.reincarnation.level1.items.Pause;

/**
 * Use to manager changing layout and set OnclickListener to all view
 */
public class Frame implements Observer {

    //List of all item that need onClickListener
    private final int[] items = {R.id.pause_button, R.id.back, R.id.exit, R.id.room1, R.id.room1_arrow, R.id.door, R.id.cabinet,
            R.id.cabineti, R.id.carpet, R.id.carpeti, R.id.room2, R.id.room2_arrow, R.id.room2_door,
            R.id.room2_mouse, R.id.room2_mousei, R.id.room2_bookshelf, R.id.room2_bookshelfi,
            R.id.room3, R.id.room3_arrow, R.id.room3_frig, R.id.room3_frigi, R.id.room3_stove, R.id.room3_stovei, R.id.ice, R.id.cheese
    };

    //List of layout (room1, room2, room3, pause)
    private RelativeLayout[] layout = new RelativeLayout[4];
    //List of layout ids(room1, room2, room3, pause)
    private final int[] frame = {R.id.escape1, R.id.escape2, R.id.escape3, R.id.escape_pause};
    //Current activity
    private Activity activity;

    /**
     * Setter
     *
     * @param act is the current activity
     */
    public void setActivity(Activity act) {
        activity = act;
    }

    /**
     * Initial the layout frame, make only room1 visible, set OnClickListener
     *
     * @param language is the current language
     */
    public void initial(Language language) {
        //initial frame
        for (int i = 0; i < frame.length; i++) {
            layout[i] = activity.findViewById(frame[i]);
        }
        //set OnclickListener
        for (int item : items) {
            activity.findViewById(item).setOnClickListener((View.OnClickListener) activity);
        }

        //Set output language
        ((TextView) activity.findViewById(R.id.pause_button)).setText(language.getText("escape.pause"));
        ((TextView) activity.findViewById(R.id.back)).setText(language.getText("escape.resume"));
        ((TextView) activity.findViewById(R.id.instruction)).setText(language.getText("escape.help"));
        ((TextView) activity.findViewById(R.id.exit)).setText(language.getText("escape.exit"));
    }


    /**
     * Observe from Change, and change the layout, make one visible and make other invisible
     * Observe from Pause, change to pause layout or resume game
     *
     * @param observable is in type of Change or Pause
     * @param o          tell frame which layout need to be visible and invisible
     */
    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Change) {
            int[] target = ((Change) observable).getTarget();
            layout[target[0]].setVisibility(RelativeLayout.GONE);
            layout[target[1]].setVisibility(RelativeLayout.VISIBLE);
        } else if (observable instanceof Pause) {
            if ((boolean) o) {
                layout[3].setVisibility(RelativeLayout.VISIBLE);
            } else {
                layout[3].setVisibility(RelativeLayout.GONE);
            }
        }
    }
}
