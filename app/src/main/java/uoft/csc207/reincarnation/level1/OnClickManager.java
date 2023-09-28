package uoft.csc207.reincarnation.level1;

import android.app.Activity;
import android.view.View;

import java.util.Observer;

import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.level1.items.Item;
import uoft.csc207.reincarnation.level1.items.ItemFactory;
import uoft.csc207.reincarnation.level1.manager.Storage;

public class OnClickManager {

    //List of ids belong to Open
    private final int[] OPEN = {
            R.id.cabinet,
            R.id.carpet,
            R.id.room2_mouse,
            R.id.room2_bookshelf,
            R.id.room3_frig,
            R.id.room3_stove,
    };
    //List of ids belong to Get
    private final int[] GET = {
            R.id.cabineti,
            R.id.carpeti,
            R.id.room2_mousei,
            R.id.room2_bookshelfi,
            R.id.room3_stovei,
            R.id.ice,
            R.id.cheese
    };
    //List of ids belong to Change
    private final int[] CHANGE = {
            R.id.room1_arrow, R.id.room2_arrow, R.id.room2_door, R.id.room3_arrow
    };
    //List of ids belong to Pause
    private final int[] PAUSE = {R.id.pause_button, R.id.back};
    //List of ids belong to Room
    private final int[] ROOM = {R.id.room1, R.id.room2, R.id.room3, R.id.room3_frigi};
    //List of ids belong to Pause, Escape, Exit
    private final int[] OTHERS = {R.id.door, R.id.exit};

    //Factory use the create Item
    private ItemFactory factory = new ItemFactory();
    //The Item get from the factory
    private Item item;
    //current activity
    private Activity activity;

    /**
     * Setter
     *
     * @param activity is current activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Create and set the item base on view.
     *
     * @param view is the View that user click on
     */
    public void setItem(View view) {
        item = factory.createItem(checkType(view.getId()));
        item.setView(view);
        item.setActivity(activity);
    }

    /**
     * Setter
     *
     * @param storage is where to keep track of what item does user own
     */
    public void setStorage(Storage storage) {
        factory.setStorage(storage);
    }

    /**
     * Add Observer
     *
     * @param observer is observer of the Item
     */
    public void addObser(Observer observer) {
        item.addObserver(observer);
    }

    /**
     * User click on the view
     */
    public void click() {
        item.interact();
    }

    /**
     * Get the type from the view
     *
     * @param id is the id of view
     * @return the type of Item
     */
    private String checkType(int id) {
        if (exist(OPEN, id)) {
            return "OPEN";
        } else if (exist(GET, id)) {
            return "GET";
        } else if (exist(CHANGE, id)) {
            return "CHANGE";
        } else if (exist(ROOM, id)) {
            return "ROOM";
        } else if (exist(PAUSE, id)) {
            return "PAUSE";
        } else if (exist(OTHERS, id)) {
            if (id == OTHERS[0]) {
                return "ESCAPE";
            }
            return "OTHERS";
        }
        return null;
    }

    /**
     * Find if the id is in list
     *
     * @param list is the list of ids
     * @param id   is the id of clicked view
     * @return if the isd is in list
     */
    private boolean exist(int[] list, int id) {
        for (int i : list) {
            if (id == i) {
                return true;
            }
        }
        return false;
    }
}
