package uoft.csc207.reincarnation.level1.items;

import uoft.csc207.reincarnation.R;

/**
 * Pause the game or resume the game
 */
public class Pause extends Item {

    //List of all id in layout that belong to Pause
    private final int[] PAUSE = {
            R.id.pause_button, R.id.back
    };
    //true if user want to pause the game, false if user want to resume the game
    private boolean target;

    /**
     * Figure out user want to pause or resume,
     * notify Frame(Change to pause or game layout) and Stats (pause or resume the time)
     */
    @Override
    public void interact() {
        int id = getView().getId();
        for (int i = 0; i < PAUSE.length; i++) {
            if (id == PAUSE[i]) {
                target = (i == 0);
            }
        }
        setOutput();
        setChanged();
        notifyObservers(target);
    }

    /**
     * Output the user is pausing the game or resume the game
     */
    @Override
    public void setOutput() {
        if (target) {
            setMessage(getLanguage().getText("escape.pause"));
        } else {
            setMessage(getLanguage().getText("escape.restart"));
        }
    }

}
