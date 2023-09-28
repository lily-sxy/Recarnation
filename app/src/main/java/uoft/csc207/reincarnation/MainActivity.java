package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import uoft.csc207.reincarnation.userdata.JsonReader;
import uoft.csc207.reincarnation.userdata.JsonWriter;
import uoft.csc207.reincarnation.userdata.User;

public class MainActivity extends Activity implements View.OnClickListener {

    JSONObject data;
    String[] languages = {"English", "Chinese"};
    Spinner spinner;
    JsonWriter writer = new JsonWriter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

        spinner = findViewById(R.id.language);
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        writer.initial(this);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String user_name = ((EditText) findViewById(R.id.user)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

                try {
                    data = new JsonReader().reader(getApplicationContext());

                    if (data.has(user_name) && match(user_name, password)) {
                        int lang_code = getLanguageCode();
                        User user = new User(getApplicationContext(), user_name, lang_code, data);
                        StartGame.logInUser(user);
                        startActivity(new Intent(MainActivity.this, StartGame.class));
                    } else {
                        Toast.makeText(MainActivity.this, "username or password isn't correct",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.register:
                startActivity(new Intent(MainActivity.this, Register.class));
        }
    }

    /**
     * Check for match between user_name and password.
     *
     * @return return true is the password is correct.
     */
    private boolean match(String user_name, String password) throws JSONException {
        return data.getJSONObject(user_name).getString("password").equals(password);
    }

    /**
     * Get language code from language name.
     *
     * @return language code for language.LanguageFactory.java
     */
    private int getLanguageCode() {
        String language = spinner.getSelectedItem().toString();
        for (int i = 0; i < languages.length; i++) {
            if (language.equals(languages[i])) {
                return i;
            }
        }
        return 0;
    }
}
