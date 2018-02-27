package edu.gatech.cs2340.wheresmystuff.model;

import java.util.GregorianCalendar;

/**
 * Information holder - represents a single lost item
 *
 * Created by zach on 6/21/2017.
 */

public class LostItem extends Item {
    private final String placeLost;
    private final int reward;

    /**
     * Constructor - make a new lost item
     *
     * @param name          lost item's name
     * @param description   lost item's description
     * @param isResolved    lost item's resolved status
     * @param category      lost item's category
     * @param dateEntered   lost item's date of entry
     * @param placeLost     where item was lost
     * @param reward        reward for returning item
     */
    public LostItem(String name, String description, boolean isResolved,
                    ItemCategory category, GregorianCalendar dateEntered, String placeLost,
                    int reward) {
        super(name, description, isResolved, category, dateEntered);
        this.placeLost = placeLost;
        this.reward = reward;
    }

    /* Getters and Setters */
    public String getPlaceLost() {
        return placeLost;
    }

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public double getReward() {
//        return reward;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

}
