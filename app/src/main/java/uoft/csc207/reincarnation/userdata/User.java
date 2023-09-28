package uoft.csc207.reincarnation.userdata;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.language.LanguageFactory;

public class User {

    private String username;
    private Language language;
    private JSONObject data;
    private Context context;

    /**
     * Initialize a user.
     *
     * @param context   notnull.
     * @param name_in   user name.
     * @param lang_code language code, please refer to language.LanguageFactory.
     * @param data      user data, notnull.
     */
    public User(Context context, String name_in, int lang_code, JSONObject data) {
        username = name_in;
        language = LanguageFactory.createLanguage(lang_code);
        this.data = data;
        this.context = context;
    }

    /**
     * Return the language object, used for multi language.
     *
     * @return a proper language, notnull.
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Return the final score of this user.
     * Final score is the sum of scores in three levels.
     *
     * @return final score, range [0,300]
     */
    public int getFinalScore() {
        int total = 0;
        try {
            JSONObject stats = data.getJSONObject(username).getJSONObject("stats");
            total += stats.getInt("level1");
            total += stats.getInt("level2");
            total += stats.getInt("level3");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * Add score to user on one of three levels.
     * The replaces the old score.
     *
     * @param more_score the new score, range[0, 100] by default.
     * @param level_name name of level, can be "level1", "level2", "level3"
     */
    public void addScore(int more_score, String level_name) {
        try {
            JSONObject stats = data.getJSONObject(username).getJSONObject("stats");
            stats.put(level_name, more_score);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save and write user data to disk.
     */
    public void save() {
        String test = data.toString();
        new JsonWriter().writer(context, test);
    }

    /**
     * Get socre of a specofoc level.
     *
     * @param level name of the level, can be "level1", "level2", "level3"
     * @return The socre previously stored.
     */
    public int getScore(String level) {
        try {
            return data.getJSONObject(username).getJSONObject("stats").getInt(level);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
