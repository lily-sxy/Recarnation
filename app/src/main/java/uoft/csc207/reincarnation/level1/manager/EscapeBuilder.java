package uoft.csc207.reincarnation.level1.manager;

import android.app.Activity;

import uoft.csc207.reincarnation.Level1;
import uoft.csc207.reincarnation.language.Language;

/**
 * Builder of EscapeManager
 */
public class EscapeBuilder {

    private Frame frame = new Frame();
    private Language language;
    private Output output = new Output();
    private Storage storage = new Storage();
    private Password password = new Password();
    private Stats stats = new Stats();

    /**
     * Build the Frame
     *
     * @param activity is the current activity
     */
    public void buildFrame(Activity activity) {
        frame.setActivity(activity);
    }

    /**
     * build the language
     *
     * @param language is the current using language
     */
    public void buildLanguage(Language language) {
        this.language = language;
    }

    /**
     * Build the Output
     *
     * @param activity is the current activity
     */
    public void buildOutput(Activity activity) {
        output.setActivity(activity);
    }

    /**
     * Build the Storage
     *
     * @param activity is the current activity
     */
    public void buildStorage(Activity activity) {
        storage.setActivity(activity);
    }

    /**
     * Build the Password
     *
     * @param level1 is the current level
     */
    public void buildPassword(Level1 level1) {
        password.addObserver(level1);
    }

    /**
     * Build the Stats
     *
     * @param level1 is the current playing level
     */
    public void buildStats(Level1 level1) {
        stats.start();
        stats.addObserver(level1);
    }

    /**
     * Get the product EscapeManager from frame, language, output, storage, password, stats
     *
     * @return EscapeManager
     */
    public EscapeManager getProduct() {
        EscapeManager product = new EscapeManager();
        product.setFrame(frame);
        product.setLanguage(language);
        product.setOutput(output);
        product.setStorage(storage);
        product.setPassword(password);
        product.setStats(stats);
        return product;
    }
}
