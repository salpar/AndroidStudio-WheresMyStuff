package edu.gatech.cs2340.wheresmystuff.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Item Manager: Structurer and Service Provider
 *
 * Manages Items and provides utility methods for processing them.
 *
 * Implements Serializable to be able to be saved to a binary file
0 *
 * Created by zach on 6/28/2017.
 */

public class ItemManager implements Serializable {
    private List<Item> items;

    /**
     * Constructor
     */
    public ItemManager() {
        items = new ArrayList<>();
    }

    /* Getters and setters */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Adds an item to the appropriate list depending on which type it is.
     *
     * @param item the item to be added
     * @return whether or not it was added.
     */
    public boolean addItem(Item item) {
        for (Item i : items) {
            if (i.equals(item)) {
                return false;
            }
        }
        items.add(item);
        return true;
    }

    /**
     * Searches for an item based on its name.
     * Method and contract written by D. Zach Edmands
     *
     * Preconditions: There are no strict preconditions per se. However, if the caller
     *                wishes to be returned more than an empty list, the following preconditions
     *                must be satisfied:
     *
     *                1. The item list must not be empty
     *                2. query is not null.
     *                3. query is not an empty string
     *
     * Postconditions: The state of the ItemManager object does not change after
     *                 the method call completes.
     *
     * Framing Conditions: No instance variables are altered during this method's
     *                     execution; however, the List of items is traversed.
     *
     * Invariant: the list of items to be searched is always not null, though it may be
     *            empty.
     *
     * @param query the search query
     * @return the list of search results
     */
    public List<Item> search(String query) {
        List<Item> searchResults = new ArrayList<>();
        if (query == null || query.isEmpty() || items.isEmpty()) {
            return searchResults;
        }
        for (Item i : items) {
            if (i.getName().toLowerCase().equals(query.toLowerCase())) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }
}
