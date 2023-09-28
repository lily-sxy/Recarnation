package uoft.csc207.reincarnation.level1.items;

//When user is click the room
public class Room extends Item {

    /**
     * Nothing happened
     */
    @Override
    public void interact() {
        setOutput();
        setChanged();
        notifyObservers();
    }

    /**
     * output Nothing happened
     */
    @Override
    public void setOutput() {
        setMessage(getLanguage().getText("escape.nothing"));
    }
}
