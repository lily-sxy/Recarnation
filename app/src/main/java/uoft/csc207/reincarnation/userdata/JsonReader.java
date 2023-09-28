package uoft.csc207.reincarnation.userdata;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class JsonReader {

    /**
     * Return a json string from userInfo.
     *
     * @param context this app.
     * @return the json string read from file.
     */
    public String initial(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("userInfo");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
        return json;
    }

    /**
     * Read file from disk and convert to a json object.
     *
     * @param context this app.
     * @return A json object, it can be empty but it is notnull.
     */
    public JSONObject reader(Context context) {
        FileInputStream fis;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = context.openFileInput("userInfo.txt");
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }


}
