package edu.gatech.cs2340.wheresmystuff.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;

import edu.gatech.cs2340.wheresmystuff.model.FoundItem;
import edu.gatech.cs2340.wheresmystuff.model.ItemCategory;
import edu.gatech.cs2340.wheresmystuff.model.LostItem;
import edu.gatech.cs2340.wheresmystuff.model.Model;
import java.util.GregorianCalendar;


import edu.gatech.cs2340.wheresmystuff.R;

public class AddItemActivity extends AppCompatActivity {
    //Widgets
    private EditText nameField;
    private EditText descriptionField;
    private EditText locationField;
    private EditText rewardField;
    private static GregorianCalendar cal;
    private Spinner itemTypeSpinner;
    private Spinner listSelectSpinner;
    private final String[] lists = new String[]{"Lost", "Found", "Donation"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewlostitem);

        //Populate spinner with item types
        itemTypeSpinner = (Spinner) findViewById(R.id.spinner_itemType);
        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(adapter);

        //Populate spinner with lists
        listSelectSpinner = (Spinner) findViewById(R.id.spinner_List);
        ArrayAdapter<String> adapterList = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, lists);
        adapterList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSelectSpinner.setAdapter(adapterList);

        Button dateSelectButton = (Button) findViewById(R.id.bt_selectDate);
        locationField = (EditText) findViewById(R.id.edtxt_lostItemLocation);
        nameField = (EditText) findViewById(R.id.edtxt_lostItemName);
        descriptionField = (EditText) findViewById(R.id.edtxt_lostItemDescription);
        rewardField = (EditText) findViewById(R.id.edtxt_lostItemReward);
        dateSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        /**
         * Create a date picker dialog
         *
         * @param savedInstanceState instance state
         * @return Date picker dialog
         */
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        /**
         * Creates GregorianCalender of date when date is selected from dialog
         *
         * @param view date picker view
         * @param year int value of year
         * @param month int value of month
         * @param day int value of day
         */
        public void onDateSet(DatePicker view, int year, int month, int day) {
            cal = new GregorianCalendar(year, month, day);

        }
    }

    /**
     * Button handler for submitting an item. Adds a new item to the respective list
     * depending on the current value of the List spinner.
     *
     * @param view submit button
     */
//    protected void onSubmitItemPressed(View view) {
//        if (listSelectSpinner.getSelectedItem().toString().equals("Lost")) {
//            try {
//                LostItem item = new LostItem(nameField.getText().toString(),
//                    descriptionField.getText().toString(), false, (ItemCategory)
//                        itemTypeSpinner.getSelectedItem(), cal,
//                    locationField.getText().toString(), Integer.valueOf(
//                            rewardField.getText().toString()));
//                Log.d("Activity", item.toString());
//                Model model = Model.getInstance();
//                model.getLostItemManager().addItem(item);
//                startActivity(new Intent(AddItemActivity.this, LostItemListActivity.class));
//            } catch (NumberFormatException ex) {
//                showInvalidItemFieldsDialog();
//            }
//        }
//        if (listSelectSpinner.getSelectedItem().toString().equals("Found")) {
//            FoundItem item = new FoundItem(nameField.getText().toString(),
//                    descriptionField.getText().toString(), false, (ItemCategory)
//                    itemTypeSpinner.getSelectedItem(), cal,
//                    locationField.getText().toString());
//            Log.d("Activity", item.toString());
//            Model model = Model.getInstance();
//            model.getFoundItemManager().addItem(item);
//            startActivity(new Intent(AddItemActivity.this, FoundItemListActivity.class));
//        }
//        finish();
//    }

    protected void onSubmitItemPressed(View view) {
        String itemType = listSelectSpinner.getSelectedItem().toString();
        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();
        ItemCategory itemCategory =
                (ItemCategory)itemTypeSpinner.getSelectedItem();
        String location = locationField.getText().toString();
        String rewardEntry = rewardField.getText().toString();
        int reward = rewardEntry.isEmpty() ? 0 : Integer.parseInt(rewardEntry);

        if (name.isEmpty() || description.isEmpty() || location.isEmpty()) {
            showInvalidItemFieldsDialog();
        } else {
            Model model = Model.getInstance();

            if (itemType.equals("Lost")) {
                model.getLostItemManager().addItem(new LostItem(name,
                        description, false, itemCategory, cal, location, reward));
                startActivity(new Intent(getBaseContext(), LostItemListActivity.class));
            } else if (itemType.equals("Found")) {
                model.getFoundItemManager().addItem(new FoundItem(name,
                        description, itemCategory, cal, location));
                startActivity(new Intent(getBaseContext(), FoundItemListActivity.class));
            }
            finish();
        }

    }

    /**
     * Button handler for cancel item. Switches to DashboardActivity
     *
     * @param view cancel button
     */
    protected void onCancelItemPressed(View view) {
        startActivity(new Intent(AddItemActivity.this, DashboardActivity.class));
    }

    /**
     * Displays dialog informing user that they have not provided valid inputs for fields
     */
    private void showInvalidItemFieldsDialog(){
        final AlertDialog invalidItemFieldAlert = new AlertDialog.Builder(this).create();
        invalidItemFieldAlert.setMessage("Please complete all fields");
        invalidItemFieldAlert.setButton(-3,"Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                invalidItemFieldAlert.dismiss();
            }
        });
        invalidItemFieldAlert.show();
    }


}
