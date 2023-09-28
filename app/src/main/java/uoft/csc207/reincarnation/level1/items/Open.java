package uoft.csc207.reincarnation.level1.items;


import android.view.View;

import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.level1.manager.Storage;

/**
 * The bookshelf, carpet, cabinet, etc. that can be open, and change the image.
 */
public class Open extends Item {

    private Storage storage;
    //List of all id in layout that belong to Open
    private final int[] OPEN = {
            R.id.cabinet,
            R.id.carpet,
            R.id.room2_mouse,
            R.id.room2_bookshelf,
            R.id.room3_frig,
            R.id.room3_stove,
    };
    //If need other item to open it
    private final boolean[] NEEDKEY = {
            false, false, true, true, false, true
    };
    //If opened, the new id of image the view will change to
    private final int[] CHANGE = {
            R.id.cabineti,
            R.id.carpeti,
            R.id.room2_mousei,
            R.id.room2_bookshelfi,
            R.id.room3_frigi,
            R.id.room3_stovei,
    };
    //The actual id of image that current view will change to
    private int target;

    /**
     * Setter
     *
     * @param storage is Storage that manage the items
     */
    void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * User try to open an object
     */
    public void interact() {
        int id = getView().getId();
        for (int i = 0; i < OPEN.length; i++) {
            if (OPEN[i] == id) {
                if (NEEDKEY[i]) { //Check does the object need to open with other item (key)
                    target = -i;//target is negative if user does not own the key
                    if (i == 2) {
                        if (storage.ownItem(6)) {
                            target = i;
                            storage.setUsed(6);
                        }
                    } else if (i == 3) {
                        if (storage.ownItem(8)) {
                            target = i;
                            storage.setUsed(8);
                        }
                    } else if (i == 5) {
                        if (storage.ownItem(7)) {
                            target = i;
                            storage.setUsed(7);
                        }
                    }
                } else {
                    //if user open the frig, the cheese and ice will be visible
                    if (i == 4) {
                        getActivity().findViewById(R.id.cheese).setVisibility(View.VISIBLE);
                        getActivity().findViewById(R.id.ice).setVisibility(View.VISIBLE);
                    }
                    target = i;
                }
            }
        }
        changeImage(target);
        setOutput();
        setChanged();
        notifyObservers();
    }

    /**
     * output to user if the character get new item
     */
    @Override
    public void setOutput() {
        if (target >= 0) {
            setMessage(getLanguage().getText("escape.open." + target));
        } else {
            int num = -target;
            setMessage(getLanguage().getText("escape.nopen." + num));
        }
    }

    /**
     * make current view invisible and show the view with id num
     *
     * @param num is the id of new image
     */
    private void changeImage(int num) {
        if (target >= 0) {
            getView().setVisibility(View.GONE);
            getActivity().findViewById(CHANGE[num]).setVisibility(View.VISIBLE);
        }

    }

}
