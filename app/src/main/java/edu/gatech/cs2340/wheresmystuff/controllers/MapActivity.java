package edu.gatech.cs2340.wheresmystuff.controllers;

import android.location.Address;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.support.v4.app.FragmentActivity;
import android.location.Geocoder;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import edu.gatech.cs2340.wheresmystuff.model.Model;
import edu.gatech.cs2340.wheresmystuff.model.Item;
import edu.gatech.cs2340.wheresmystuff.model.LostItem;
import edu.gatech.cs2340.wheresmystuff.model.FoundItem;
import edu.gatech.cs2340.wheresmystuff.R;

/**
 * Activity to display the map
 *
 * Created by laurenkearley on 6/28/17.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Map", "entering map");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostitemmap);
        MapFragment map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
        map.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng atlanta = new LatLng(33, -84);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, 6.0F));

        Model model = Model.getInstance();
        List<Item> lostItems = model.getLostItemManager().getItems();
        List<Item> foundItems = model.getFoundItemManager().getItems();

        //create markers for lost items
        addMarkers(lostItems, mMap);
        addMarkers(foundItems, mMap);

        mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                // Getting reference to the TextView to set latitude
                TextView tvName = (TextView) v.findViewById(R.id.tv_name);

                // Getting reference to the TextView to set longitude
                TextView tvDescr = (TextView) v.findViewById(R.id.tv_description);

                // Setting the name
                tvName.setText(arg0.getTitle());

                // Setting the description
                tvDescr.setText(arg0.getSnippet());

                // Returning the view containing InfoWindow contents
                return v;

            }
        });
    }

    private void addMarkers(List<Item> items, GoogleMap mMap) {
        Log.d("Map", "In addMarkers()");
        String address;
        String itemName;
        String itemDetails;
        List<Address> loc = null;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof  LostItem) {
                LostItem currentItem = (LostItem) items.get(i);
                address = currentItem.getPlaceLost();
                itemName = currentItem.getName();
                itemDetails = currentItem.getDescription();
            } else { // if (items.get(i) instanceof  FoundItem) {
                FoundItem currentItem = (FoundItem) items.get(i);
                address = currentItem.getPlaceFound();
                itemName = currentItem.getName();
                itemDetails = currentItem.getDescription();
            }

            if (Geocoder.isPresent()) {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    Log.d("Map", "Getting location for " + address);
                    loc = geocoder.getFromLocationName(address, 1);
                    Log.d("Map", "location: " + loc.get(0));
                } catch (Exception e) {
                    Log.d("Map", "Error getting location from geocoder");
                }
            }

            if (loc != null) {
                Log.d("Map", "adding marker");
                if (items.get(i) instanceof  LostItem) {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(loc.get(0).getLatitude(),
                                    loc.get(0).getLongitude()))
                            .title("Lost Item: " + itemName)
                            .snippet(itemDetails + "\n" + address));
                } else {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(loc.get(0).getLatitude(),
                                    loc.get(0).getLongitude()))
                            .title("Found Item: " + itemName)
                            .snippet(itemDetails + "\n" + address));
                }
            }
        }
    }
}