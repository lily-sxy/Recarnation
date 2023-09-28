package uoft.csc207.reincarnation.level1.manager;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import uoft.csc207.reincarnation.level1.items.Escape;

/**
 * Use for generate password and check if user win the game
 */
public class Password extends Observable implements Observer {

    //the generated password of the game
    private int[] password;
    //BONUS, if user enter these number, user will automate win the game.
    private String[] secret = {"990529", "000410", "990317", "000506", "980818", "207207"};

    /**
     * Reset the password, if user first enter the game or user dead
     */
    void reset() {
        password = generatePassword();
    }

    /**
     * Check if user'input is correct or is BONUS
     *
     * @param pass is the user's input
     * @return true if pass == password
     */
    private boolean correct(String pass) {
        if (pass.length() != 6) {
            return false;
        }
        for (String bonus : secret) {
            if (bonus.equals(pass)) {
                return true;
            }
        }
        return pass.equals(inOrder(password));
    }

    /**
     * generate the password
     *
     * @return the escape password
     */
    private static int[] generatePassword() {
        Random rand;
        int[] password = new int[6];
        int[] range = {0, 4};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                rand = new Random();
                password[i * 3 + j] = rand.nextInt((range[1] - range[0]) + 1) + range[0];
            }
            range[0] = 5;
            range[1] = 9;
        }
        return password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return notOrder(password);
    }

    /**
     * @return the list of the password in order of rule of the escape room
     */
    private String inOrder(int[] password) {
        //02134
        //68579
        StringBuilder builder = new StringBuilder();
        int[] correctOrder = {0, 2, 1, 3, 4, 6, 8, 5, 7, 9};
        for (int j : correctOrder) {
            for (int i : password) {
                if (i == j) {
                    builder.append(i);
                }
            }
        }
        return builder.toString();
    }

    /**
     * Convert random password from int[] to String
     *
     * @param password is the generate random password in int[]
     * @return the not ordered password in String
     */
    private String notOrder(int[] password) {
        StringBuilder builder = new StringBuilder();
        for (int i : password) {
            builder.append(i);
        }
        return builder.toString();
    }

    /**
     * If observable is instanceof Escape, mean user trying to escape from the room, notify Level1 if the
     * user have the correct password and win the game.
     *
     * @param observable is instanceof Item
     * @param o          is the user'input password
     */
    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Escape) {
            setChanged();
            notifyObservers(correct((String) o));
        }
    }


}
