package edu.gatech.cs2340.wheresmystuff.model;

/**
 * This class represents the various categories an item might fall under.
 *
 * Created by zach on 6/20/2017.
 */

public enum ItemCategory {
    KEEPSAKES("Keepsakes"),
    HEIRLOOMS("Heirlooms"),
    MISC("Miscellaneous");

    /* String representation of item's category */
    private final String category;

    /**
     * Constructor
     *
     * @param category the item category
     */
    ItemCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return String representation of item's category
     */
    public String toString() {
        return category;
    }
}
