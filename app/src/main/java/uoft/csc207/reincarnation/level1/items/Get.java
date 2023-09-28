package uoft.csc207.reincarnation.level1.items;

import android.view.View;

import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.level1.manager.Storage;

/**
 * The action and result when user click on the object that can give user items
 */
public class Get extends Item {


    private Storage storage;

    //List of all id in layout that belong to Get
    private final int[] GET = {
            R.id.cabineti,
            R.id.carpeti,
            R.id.room2_mousei,
            R.id.room2_bookshelfi,
            R.id.room3_stovei,
            R.id.ice,
            R.id.cheese
    };
    //The items that user can get when click on Get objects
    private final int[][] items = {
            {0, 8},
            {1},
            {2},
            {3, 4},
            {5},
            {7},
            {6}
    };
    //The actual items user is getting
    private int[] target;
    //Check if the user already have the target or used it
    private boolean notget;
    //the index of the clicked object in GET
    private int object;

    /**
     * Setter
     *
     * @param storage is Storage that manage the items
     */
    void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Check what user will get, if the user will get anything, modify the storage.
     */
    @Override
    public void interact() {
        int id = getView().getId();
        for (int i = 0; i < GET.length; i++) {
            if (id == GET[i]) {
                target = items[i];
                object = i;
                if (i == 5 || i == 6) {
                    getView().setVisibility(View.GONE);
                }
            }
        }
        getItem();
        setOutput();
        setChanged();
        notifyObservers();
    }

    /**
     * Output what user got
     */
    @Override
    public void setOutput() {
        if (notget) {
            setMessage(getLanguage().getText("escape.nget." + object));
        } else {
            for (int i : target) {
                setMessage(getLanguage().getText("escape.get") +
                        getLanguage().getText("escape.types." + i));
            }
        }

    }

    /**
     * Access the Storage and get the item
     */
    private void getItem() {
        for (int i : target) {
            if (storage.ownItem(i) || storage.checkUsed(i)) {
                notget = true;
            } else {
                notget = false;
                storage.getItem(i);
            }
        }
    }

}
