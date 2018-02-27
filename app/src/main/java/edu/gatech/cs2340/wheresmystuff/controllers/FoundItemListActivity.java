package edu.gatech.cs2340.wheresmystuff.controllers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Item;
import edu.gatech.cs2340.wheresmystuff.model.Model;

/**
 * Copy-pasted from code by Zach Edmands by James Rick on 06/26/2017
 */

public class FoundItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founditem_list);

        View recyclerView = findViewById(R.id.founditem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Sets up the recycler view for displaying the list of found items.
     *
     * @param recyclerView list of found items
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d("Found Item List", "recycler view init");
        Model model = Model.getInstance();
        recyclerView.setAdapter(new FoundItemListActivity.FoundItemListRVAdapter(
                model.getFoundItemManager().getItems()));

    }


    class FoundItemListRVAdapter extends
            RecyclerView.Adapter<FoundItemListActivity.FoundItemListRVAdapter.ViewHolder> {

        // Items to be shown on list
        private final List<Item> foundItems;

        /**
         * Constructor
         *
         * @param items the items to be displayed in the recycler view
         */
        FoundItemListRVAdapter(List<Item> items) {
            foundItems = items;
        }

        @Override
        public FoundItemListActivity.FoundItemListRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.founditem_list_content, parent, false);
            return new FoundItemListActivity.FoundItemListRVAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FoundItemListActivity.FoundItemListRVAdapter.ViewHolder holder, int position) {
            Log.d("Found Item List", "Binding model data");
            holder.foundItem = foundItems.get(position);
            holder.nameTextView.setText(foundItems.get(position).getName());
            holder.descriptionTextView.setText(foundItems.get(position).getDescription());
            // TODO: Set up click listener here later on in the project
        }

        @Override
        public int getItemCount() {
            return foundItems.size();
        }

        /*
         * Inner ViewHolder class used to cache information about binding between model
         * and text views.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView nameTextView;
            final TextView descriptionTextView;
            Item foundItem;

            ViewHolder(View view) {
                super(view);
                nameTextView = (TextView) view.findViewById(R.id.foundItem_name);
                descriptionTextView = (TextView) view.findViewById(R.id.foundItem_description);
            }
        }
    }
}
