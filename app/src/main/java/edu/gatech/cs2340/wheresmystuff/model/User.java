package edu.gatech.cs2340.wheresmystuff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Represents a single user in model.
 *
 * Information holder/Service provider
 *
 * Implements Parcelable to be passed between intents
 * Implements Serializable to be saved to a binary file
 *
 * Created by zach on 6/14/2017
 */

public class User implements Parcelable, Serializable {
    private final String id;
    private final String password;
    private final String email;
    private boolean isLocked;

    /**
     * Constructor = Make a new User
     *
     * @param id        user's id
     * @param password  user's password
     * @param email     user's email address
     */
    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
        isLocked = false;
    }

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    /* ****************************
//     * Getters and setters
//     */
//    public String getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public String getPassword() {
//        return password;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

// --Commented out by Inspection START (7/21/2017 1:20 PM):
//    public String getEmail() {
//        return email;
//    }
// --Commented out by Inspection STOP (7/21/2017 1:20 PM)

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /* ****************************
     * Standard methods
     */
    @Override
    public String toString() {
        return id + " " + email + getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);

    }

    /* **********************************
     * Utility methods
     */

    /**
     * Determines if a login is valid
     *
     * @param user the user logging in
     * @param password the user's password
     * @return whether or not the login is valid
     */
    public boolean isValidLogin(String user, String password) {
        return id.equals(user) && this.password.equals(password);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /* ********************************
     * These methods are required by the Parcelable interface
     */

    private User(Parcel in) {
        id = in.readString();
        password = in.readString();
        email = in.readString();
        isLocked = in.readInt() != 0;  // Workaround to use boolean in Parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeInt(isLocked ? 1 : 0);  // Workaround to use boolean in Parcel
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
                public User createFromParcel(Parcel in) {
                    return new User(in);
                }

                public User[] newArray(int size) {
                    return new User[size];
                }
            };
}