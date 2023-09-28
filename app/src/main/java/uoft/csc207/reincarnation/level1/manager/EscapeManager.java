package uoft.csc207.reincarnation.level1.manager;

import android.widget.TextView;

import uoft.csc207.reincarnation.R;
import uoft.csc207.reincarnation.language.Language;

/**
 * Collection of frame, language, output, storage, password, stats
 * Use to mange the level status, output and user stats
 */
public class EscapeManager {

    private Frame frame;
    private Language language;
    private Output output;
    private Storage storage;
    private Password password;
    private Stats stats;

    /**
     * Setter
     *
     * @param frame is to manager changing layout and set OnclickListener to all view
     */
    void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * Setter
     *
     * @param language is the current using language
     */
    void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Setter
     *
     * @param output is the user output after make some interaction to the game
     */
    void setOutput(Output output) {
        this.output = output;
    }

    /**
     * Setter
     *
     * @param storage is where to keep track of what item does user own
     */
    void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Setter
     *
     * @param password is the password generator and check if user win the game
     */
    void setPassword(Password password) {
        this.password = password;
    }

    /**
     * Setter
     *
     * @param stats is use to store the user game stats, (score, death, time)
     */
    void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Getter
     *
     * @return frame
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Getter
     *
     * @return language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Getter
     *
     * @return output
     */
    public Output getOutput() {
        return output;
    }

    /**
     * Getter
     *
     * @return password
     */
    public Password getPassword() {
        return password;
    }

    /**
     * Getter
     *
     * @return stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Getter
     *
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Initialize the game when user first enter the game, or just dead once.
     *
     * @param textView is use to set the output
     */
    public void initial(TextView textView) {
        frame.initial(language);
        output.setItem(R.id.output);
        output.output(language.getText("escape.start"));
        password.reset();
        storage.setPassword(password.getPassword());
        storage.reset();
        stats.setTouch(textView);
    }

}
