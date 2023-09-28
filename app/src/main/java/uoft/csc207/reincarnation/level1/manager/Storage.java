package uoft.csc207.reincarnation.level1.manager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import uoft.csc207.reincarnation.R;

/**
 * Use to keep track of what item does user own
 */
public class Storage {

    //Current activity
    private Activity activity;
    //does player own this item
    private boolean[] items = {false, false, false, false, false, false, false, false, false};
    //List of id of view of items that user could get
    private final int[] ids = {
            R.id.card_1, R.id.card_2, R.id.card_3,
            R.id.card_4, R.id.card_5, R.id.card_6,
            R.id.storage_cheese, R.id.storage_ice, R.id.storage_key};
    //If item been used
    private boolean[] used = {false, false, false, false, false, false, false, false, false};
    //Image of items
    private final int[] allPossibleCards = {R.drawable.card_0, R.drawable.card_1,
            R.drawable.card_2, R.drawable.card_3, R.drawable.card_4, R.drawable.card_5,
            R.drawable.card_6, R.drawable.card_7, R.drawable.card_8, R.drawable.card_9};

    /**
     * Player get the item num
     *
     * @param num the location of this item in items
     */
    public void getItem(int num) {
        items[num] = true;
        activity.findViewById(ids[num]).setVisibility(View.VISIBLE);
    }

    /**
     * Setter
     *
     * @param activity is current activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * check if the player own item num
     *
     * @param num the number of this item in items
     * @return true if player own the item
     */
    public boolean ownItem(int num) {
        return items[num];
    }

    /**
     * reset the storage, when first enter the game or user dead
     */
    void reset() {
        for (int i = 0; i < items.length; i++) {
            items[i] = false;
            used[i] = false;
        }
    }

    /**
     * Set the used[index] used
     *
     * @param num is the index of used
     */
    public void setUsed(int num) {
        used[num] = true;
        activity.findViewById(ids[num]).setVisibility(View.GONE);
    }

    /**
     * Check is the item is used
     *
     * @param num is the index of used
     * @return true if the item is used
     */
    public boolean checkUsed(int num) {
        return used[num];
    }

    /**
     * Set the image of the card base on the newly generated password
     *
     * @param password is the new generated password
     */
    public void setPassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            int pass = Character.getNumericValue(password.charAt(i));
            ((ImageView) activity.findViewById(ids[i])).setImageResource(allPossibleCards[pass]);
        }
    }

}
