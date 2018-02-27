package edu.gatech.cs2340.wheresmystuff.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Abstract Item class: represents a single item
 *
 * Information holder - represents a single item
 * Implements Parcelable to be passed between intents
 * Implements Serializable to be saved to a binary file
 *
 * Created by zach on 6/20/2017.
 */

public abstract class Item implements Serializable {
    private final String name;
    private final String description;
    private boolean isResolved;             // For Parcelable,
    private final Serializable category;    // ItemCategory and
    private final Serializable dateEntered; // Date objects declared as Serializable

    /**
     * Constructor: make a new Item
     *
     * @param name        item's name
     * @param description item's description
     * @param isResolved  item's resolved status
     * @param category    item's category
     * @param dateEntered date item was entered
     */
    Item(String name, String description, boolean isResolved,
                ItemCategory category, GregorianCalendar dateEntered) {
        this.name = name;
        this.description = description;
        this.isResolved = isResolved;
        this.category = category;
        this.dateEntered = dateEntered;
    }

    /* **********************************
     * Getters and setters
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public boolean isResolved() {
//        return isResolved;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public void setResolved(boolean resolved) {
//        isResolved = resolved;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public ItemCategory getCategory() {
//        return (ItemCategory) category;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public GregorianCalendar getDateEntered() {
//        return (GregorianCalendar) dateEntered;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

    /* **********************************
     * Standard methods
     */

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isResolved=" + isResolved +
                ", category=" + category +
                ", dateEntered=" + dateEntered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        if (isResolved != item.isResolved) {
            return false;
        }
        if (name != null ? !name.equals(item.name) : item.name != null) {
            return false;
        }
        if (description != null ? !description.equals(item.description) : item.description != null) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (category != item.category) {
            return false;
        }
        return dateEntered != null ? dateEntered.equals(item.dateEntered) : item.dateEntered == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isResolved ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (dateEntered != null ? dateEntered.hashCode() : 0);
        return result;
    }

    /* ***********************************
     * These methods are required by the Parcelable interface
     */
    // TODO: determine if parcelable is necessary
//    private Item(Parcel in) {
//        name = in.readString();
//        description = in.readString();
//        isResolved = in.readInt() != 0;  // workaround to use boolean
//        category = in.readSerializable();
//        dateEntered = in.readSerializable();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(description);
//        dest.writeInt(isResolved ? 1 : 0);  // workaround to use boolean
//        dest.writeSerializable(category);
//        dest.writeSerializable(dateEntered);
//    }
//
//
//    // TODO: add Parcelable.Creator (probably only to subclasses)
//    // Determine if Parcelable stuff should even be in an abstract class
}

