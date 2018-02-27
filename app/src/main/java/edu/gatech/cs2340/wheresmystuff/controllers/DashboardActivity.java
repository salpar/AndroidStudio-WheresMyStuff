package edu.gatech.cs2340.wheresmystuff.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Model;

/**
 * Created by laurenkearley on 6/14/17.
 *
 * Temporary dashboard for Find My Stuff.
 */

public class DashboardActivity extends AppCompatActivity {

    private EditText searchFieldLost;
    private EditText searchFieldFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        searchFieldLost = (EditText) findViewById(R.id.edtxt_searchLost);
        searchFieldFound = (EditText) findViewById(R.id.edtxt_searchFound);
    }

    /**
     * Button handler for view lost items button
     *
     * @param view the view lost items button
     */
    protected void onViewLostItemsPressed(View view) {
        Log.d("Dashboard", "View lost items");
        startActivity(new Intent(DashboardActivity.this, LostItemListActivity.class));
    }

    /**
     * Button handler for view lost items button
     *
     * @param view the view lost items button
     */
    protected void onViewFoundItemsPressed(View view) {
        Log.d("Dashboard", "View found items");
        startActivity(new Intent(DashboardActivity.this, FoundItemListActivity.class));
    }

    /**
     * Button handler for add new lost item button
     *
     * @param view the add lost item button
     */
    protected void onAddItemPressed(View view) {
        Log.d("Dashboard", "Add lost item");
        startActivity(new Intent(DashboardActivity.this, AddItemActivity.class));
    }

    /**
     * Button handler for logout
     *
     * @param view the logout button
     */
    protected void onLogoutPressed(View view) {
        Log.d("Dashboard", "Logout");
        finish();
        startActivity(new Intent(DashboardActivity.this, WelcomeActivity.class));
    }

    /**
     * Button handler for switching to map
     *
     * @param view the map button
     */
    protected void onMapViewPressed(View view) {
        startActivity(new Intent(DashboardActivity.this, MapActivity.class));
    }

    /**
     * Button handler for switching to Lost item search results
     *
     * @param view the search button
     */
    protected void onSearchLostPressed(View view) {
        String query = searchFieldLost.getText().toString();
        Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
        intent.putExtra("query", query);
        intent.putExtra("type", "lost"); // let's our results activity know what kind of item
        startActivity(intent);           // to display
    }

    /**
     * Button handler for switch to Found Item search results
     *
     * @param view the search button
     */
    protected void onSearchFoundPressed(View view) {
        String query = searchFieldFound.getText().toString();
        Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
        intent.putExtra("query", query);
        intent.putExtra("type", "found");
        startActivity(intent);
    }

    /**
     * Button handler for save button
     *
     * @param view the save button
     */
    protected void onSavePressed(View view) {
        Log.d("Dashboard", "save pressed");
        Model model = Model.getInstance();
        File file = new File(this.getFilesDir(), Model.DEFAULT_FILE_NAME);
        if (model.save(file)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Data successfully saved", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
