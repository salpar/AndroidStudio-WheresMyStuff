package edu.gatech.cs2340.wheresmystuff.model;

import java.util.GregorianCalendar;

/**
 * Information holder - represents a single found item
 *
 * Created by zach on 6/21/2017.
 */

public class FoundItem extends Item {
    private final String placeFound;

    /**
     * Constructor: make a new FoundItem
     *  @param name         found item's name
     * @param description  found item's description
     * @param category     found item's category
     * @param dateEntered  found item's date of entry
     * @param placeFound   place where item was found
     */
    public FoundItem(String name, String description,
                     ItemCategory category, GregorianCalendar dateEntered,
                     String placeFound) {
        super(name, description, false, category, dateEntered);
        this.placeFound = placeFound;
    }

    /* Getters and setters */
    public String getPlaceFound() {
        return placeFound;
    }

}
