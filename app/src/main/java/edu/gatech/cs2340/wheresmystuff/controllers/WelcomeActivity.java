package edu.gatech.cs2340.wheresmystuff.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Model;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Login button press transitions to login screen
        final Button login = (Button) findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Registration button press transitions to registration screen
        final Button register = (Button) findViewById(R.id.bt_register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
            }
        });

        // Load persistent data
        Model model = Model.getInstance();
        File file = new File(this.getFilesDir(), Model.DEFAULT_FILE_NAME);
        if (model.load(file)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Data successfully loaded", Toast.LENGTH_SHORT);
            toast.show();
        }
        Log.d("Welcome", "attempt to load data");
    }
}
