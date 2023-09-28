package uoft.csc207.reincarnation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uoft.csc207.reincarnation.userdata.JsonReader;
import uoft.csc207.reincarnation.userdata.JsonWriter;

public class Register extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(Register.this, MainActivity.class));
                break;
            case R.id.register:
                String user = ((EditText) findViewById(R.id.user)).getText().toString();
                String pass = ((EditText) findViewById(R.id.password)).getText().toString();
                String confirm = ((EditText) findViewById(R.id.password_check)).getText().toString();
                if (available(user, pass, confirm)) {
                    register(user, pass);
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
                break;
        }
    }

    private boolean available(String user, String pass, String confirm) {
        //check availability
        boolean exist = new JsonReader().reader(getApplicationContext()).has(user);
        String output = "Registered.";
        boolean available = false;
        if (exist) {
            output = "this user is already exist";
        } else if (user.length() == 0) {
            output = "User name should not be empty";
        } else if (pass.length() == 0) {
            output = "password should not be empty";
        } else if (!pass.equals(confirm)) {
            output = "Those Passwords didn't match.";
        } else {
            available = true;
        }
        Toast.makeText(Register.this, output,
                Toast.LENGTH_SHORT).show();
        return available;
    }

    private void register(String user, String pass) {
        String data = new JsonReader().reader(getApplicationContext()).toString().trim();
        String newUser = ",\n\"" + user + "\":{\"password\":\"" + pass + "\",\"stats\":{\"level1\": 0,\"level2\": 0,\"level3\": 0}}}";
        data = data.substring(0, data.length() - 1) + newUser;
        new JsonWriter().writer(this, data);
    }
}
