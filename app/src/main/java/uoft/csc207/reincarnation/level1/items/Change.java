package uoft.csc207.reincarnation.level1.items;


import uoft.csc207.reincarnation.R;

/*
The object that will change the room when user click on it
 */
public class Change extends Item {
    //List of all id in layout that belong to Change
    private final int[] CHANGE = {
            R.id.room1_arrow, R.id.room2_arrow, R.id.room2_door, R.id.room3_arrow
    };
    /*
     The result in same order of clicking id in CHANGE
     example: R.id.room1_arrow will change room from 0 to 1
     */
    private final int[][] room = {
            {0, 1}, {1, 0}, {1, 2}, {2, 1}
    };
    //example: If R.id.room1_arrow being click, target = {0,1}
    private int[] target;

    /**
     * Change the room, give user output, notify Frame to change the room
     */
    @Override
    public void interact() {
        int id = getView().getId();
        for (int i = 0; i < CHANGE.length; i++) {
            if (id == CHANGE[i]) {
                target = room[i];
            }
        }
        setOutput();
        setChanged();
        notifyObservers();
    }

    /**
     * Tell where the character move to
     */
    @Override
    public void setOutput() {
        int num = target[1] + 1;
        setMessage(getLanguage().getText("escape.move") + num + " 。");
    }

    /**
     * Tell Frame what to change
     *
     * @return the target
     */
    public int[] getTarget() {
        return target;
    }

}
