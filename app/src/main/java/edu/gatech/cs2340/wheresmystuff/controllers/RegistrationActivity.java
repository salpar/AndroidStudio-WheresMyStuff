package edu.gatech.cs2340.wheresmystuff.controllers;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Admin;
import edu.gatech.cs2340.wheresmystuff.model.Model;
import edu.gatech.cs2340.wheresmystuff.model.User;

public class RegistrationActivity extends AppCompatActivity {

    // Widgets we will need for receiving user input
    private EditText idField;
    private EditText passwordField;
    private EditText emailField;
    private Spinner  accountTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Establish references to text fields and spinner
        idField = (EditText) findViewById(R.id.edtxt_userID);
        passwordField = (EditText) findViewById(R.id.edtxt_password);
        emailField = (EditText) findViewById(R.id.edtxt_email);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner_acountType);

        // Create spinner values
        final List<String> spinnerValues = new ArrayList<>(2);
        spinnerValues.add("User");
        spinnerValues.add("Admin");

        // Set up adapter to display allowable account types in spinner
        accountTypeSpinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerValues));
        // Default spinner selection to User
        accountTypeSpinner.setSelection(0);
    }

    /**
     * Create new user upon pressing submit button
     *
     * @param view the submit button
     */
    protected void onSubmitPressed(View view) {
        Log.d("Registration", "Submit button pressed");

        // Grab user data from text fields
        String id = idField.getText().toString();
        String password = passwordField.getText().toString();
        String email = emailField.getText().toString();

        // Check that all fields have been completed
        if (id.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showInvalidEntryDialog();
        } else {
            Model model = Model.getInstance();
            User user;
            // Create standard User
            if (accountTypeSpinner.getSelectedItemPosition() == 0) {
                user = new User(id, password, email);
            } else {  // Create Admin
                user = new Admin(id, password, email);
            }
            Log.d("Registration", "Got new user data " + user);

            // Set new user in model
            model.setUser(user);
            showSuccessDialog();
        }
    }

    /**
     * Dialog to inform user of successful account registration
     */
    private void showSuccessDialog() {
        final AlertDialog successDialog = new AlertDialog.Builder(this).create();
        successDialog.setMessage("Account successfully created. Now please login.");
        successDialog.setButton(-3, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                successDialog.dismiss();
                finish();
            }
        });
        successDialog.show();
    }

    /**
     * Dialog to inform user to complete all registration fields
     *
     */
    private void showInvalidEntryDialog() {
        final AlertDialog invalidEntryDialog = new AlertDialog.Builder(this).create();
        invalidEntryDialog.setMessage("Please complete all fields");
        invalidEntryDialog.setButton(-3, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                invalidEntryDialog.dismiss();
            }
        });
        invalidEntryDialog.show();
    }

    /**
     * Cancels user registration and returns to previous screen
     *
     * @param view the cancel button
     */
    protected void onCancelPressed(View view) {
        Log.d("Registration", "Cancel user");
        finish();
    }
}
