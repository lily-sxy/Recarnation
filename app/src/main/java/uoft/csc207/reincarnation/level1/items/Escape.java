package uoft.csc207.reincarnation.level1.items;

import android.widget.TextView;

import uoft.csc207.reincarnation.R;

/**
 * The action and result when user click on the door try to escape
 */
public class Escape extends Item {

    /**
     * Get the two user's input of the password,
     * notify Password to check if the password is correct.
     */
    @Override
    public void interact() {
        TextView pass1 = getActivity().findViewById(R.id.pass1);
        TextView pass2 = getActivity().findViewById(R.id.pass2);
        String pass = pass1.getText().toString() + pass2.getText().toString();
        setOutput();
        setChanged();
        notifyObservers(pass);
    }

    /**
     * output try to escape
     */
    @Override
    public void setOutput() {
        setMessage(getLanguage().getText("escape.escape"));
    }
}
