package uoft.csc207.reincarnation.level1.items;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Observable;

import uoft.csc207.reincarnation.StartGame;
import uoft.csc207.reincarnation.language.Language;


public abstract class Item extends Observable implements ItemI {

    private Activity activity;
    private View view;
    private String message = "";
    private Language lang = StartGame.getCurrentUser().getLanguage();

    /**
     * Setter
     *
     * @param view the view that user click on
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Setter
     *
     * @param activity is the current activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Getter
     *
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * @return the String represent the Item, the output user will get when click on the view
     */
    @Override
    @NonNull
    public String toString() {
        return message;
    }


    /**
     * Build on the the String of user output
     *
     * @param output is the string that will show to user
     */
    void setMessage(String output) {
        message += output + "\n";
    }

    /**
     * Getter
     *
     * @return the current using language
     */
    public Language getLanguage() {
        return lang;
    }

    /**
     * Getter
     *
     * @return the current activity
     */
    public Activity getActivity() {
        return activity;
    }
}
