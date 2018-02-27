package edu.gatech.cs2340.wheresmystuff.model;

/**
 * Created by zach on 6/20/2017.
 *
 * Information holder/Service provider
 *
 * Represents a special case of User, an Admin.
 */

public class Admin extends User {

    /**
     * Constructor - Make a new Admin
     *
     * @param id        admin's id
     * @param password  admin's password
     * @param email     admin's email address
     */
    public Admin(String id, String password, String email) {
        super(id, password, email);
    }

    /**
     * Unlocks an account
     *
     * @param user the user whose account is to be unlocked
     */
    public void unlock(User user) {
        user.setLocked(false);
    }
}
