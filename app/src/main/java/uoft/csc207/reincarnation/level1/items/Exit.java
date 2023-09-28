package uoft.csc207.reincarnation.level1.items;


import uoft.csc207.reincarnation.ILevel;
import uoft.csc207.reincarnation.StartGame;

/**
 * The result when user give up on the game and try to exist the game
 */
public class Exit extends Item {
    /**
     * Return to Levels
     */
    @Override
    public void interact() {
        setOutput();
        StartGame.onReturn((ILevel) getActivity());
    }

    /**
     * output user exist the game
     */
    @Override
    public void setOutput() {
        setMessage(getLanguage().getText("escape.exit"));
    }
}
