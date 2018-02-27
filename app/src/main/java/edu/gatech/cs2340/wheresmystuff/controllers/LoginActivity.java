package edu.gatech.cs2340.wheresmystuff.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Model;

public class LoginActivity extends AppCompatActivity {

    private  int loginAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button loginButton = (Button) findViewById(R.id.bt_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                checkLogin();
            }
        });
        loginAttempts = 0;
    }

    /**
     * Checks validity of username and password. If username and password are invalid, creates a
     * incorrect user/password dialog. If the user fails to login 3 times, locks the users account.
     * If account is locked, asks user to contact an admin.
     *
     * Method Contract written by Joshua Rhodes
     *
     * Preconditions: none
     *
     * Postconditions: moves to the dashboard if userid and password match the models user
     *
     * Framing Conditions: NO instance variables are altered during execution
     *
     * Invariants: model will always be the same instance
     *
     */
    private void checkLogin() {
        Model model = Model.getInstance();

        // Ensure there IS a registered user.
        if (model.getUser() == null) {
            showIncorrectAttemptDialog();
        } else {
            // Establishing necessary references between model and UI elements
            EditText usernameText = (EditText) findViewById(R.id.edtxt_userName);
            EditText passwordText = (EditText) findViewById(R.id.edtxt_password);
            String inputUserName = usernameText.getText().toString();
            String inputPassword = passwordText.getText().toString();

            // check password match
            if (model.getUser().isValidLogin(inputUserName, inputPassword) && !(model.getUser().isLocked()) && loginAttempts < 3) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                loginAttempts++;
                if (loginAttempts == 3) {
                    model.getUser().setLocked(true);
                }
                showIncorrectAttemptDialog();
            }
        }
    }

    /**
     * Dialog created for incorrect username/password input
     */
    private void showIncorrectAttemptDialog(){
        Model model = Model.getInstance();
        final AlertDialog incorrectLoginAlert = new AlertDialog.Builder(this).create();
        if (model.getUser() != null && model.getUser().isLocked()) {
            incorrectLoginAlert.setMessage("Account has been locked. Please contact a system admin.");
        } else {
            incorrectLoginAlert.setMessage("Incorrect username or password. Please try again");
        }
        incorrectLoginAlert.setButton(-3,"Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                incorrectLoginAlert.dismiss();
            }
        });
        incorrectLoginAlert.show();
    }
}
