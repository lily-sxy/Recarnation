package uoft.csc207.reincarnation.userdata;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uoft.csc207.reincarnation.MainActivity;

import static android.content.Context.MODE_PRIVATE;

public class JsonWriter {

    /**
     * Write file to disk.
     * Write in will fail if file not exist.
     *
     * @param context context of this app, notnull.
     * @param str     data string to write.
     */
    public void writer(Context context, String str) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("userInfo.txt", MODE_PRIVATE);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initialize output file.
     *
     * @param context the main of this app.
     */
    public void initial(MainActivity context) {
        File file = new File(context.getFilesDir(), "userInfo.txt");
        if (!file.exists()) {
            try {
                FileOutputStream fileOutputStream = context.openFileOutput("userInfo.txt", MODE_PRIVATE);
                String string = (new JsonReader().initial(context));
                fileOutputStream.write(string.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
