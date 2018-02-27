package edu.gatech.cs2340.wheresmystuff.model;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

/**
 * Facade to the Model.
 *
 * Created by zach on 6/21/2017.
 */

public class Model {
    public final static String DEFAULT_FILE_NAME = "wms.bin";

    /* Singleton instance */
    private static final Model ourInstance = new Model();
    public static Model getInstance() {
        return ourInstance;
    }

    private ItemManager lostItemManager;
    private ItemManager foundItemManager;
    private User user;                 // our single user

    /* Make a new Model */
    private Model() {
        lostItemManager = new ItemManager();
        foundItemManager = new ItemManager();
        loadDummyData();
    }

    /*
     * Adds dummy data to lost item list for testing purposes.
     */
    private void loadDummyData() {
        lostItemManager.addItem(new LostItem("ring", "diamond on gold band", false,
                ItemCategory.KEEPSAKES, new GregorianCalendar(), "30068", 2500));
        lostItemManager.addItem(new LostItem("bowling ball", "blue, heavy", false,
                ItemCategory.MISC, new GregorianCalendar(), "30062", 20));
        foundItemManager.addItem(new FoundItem("Soccer Ball", "Red and blue",
                ItemCategory.MISC, new GregorianCalendar(), "Atlanta"));
        foundItemManager.addItem(new FoundItem("Baseball", "White",
                ItemCategory.MISC, new GregorianCalendar(), "Jackson"));

    }

    /* Getters and Setters */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ItemManager getLostItemManager() {
        return lostItemManager;
    }

    public ItemManager getFoundItemManager() {
        return foundItemManager;
    }

    /* Persistence Methods */

    /**
     * Load persistent Model data from file
     *
     * @param file the file to load
     * @return whether or not it worked
     */
    public boolean load(File file) {
        boolean success = true;
        try {
            // establish input stream to file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            // load from file
            user = (User) in.readObject();
            lostItemManager = (ItemManager) in.readObject();
            foundItemManager = (ItemManager) in.readObject();
            in.close();
            Log.d("Model", "data loaded successfully");
        } catch (IOException e) {
            Log.d("Model", "Error reading persistent data from file");
            success = false;
        } catch (ClassNotFoundException e) {
            Log.d("Model", "Error casting after binary read");
            success = false;
        }
        return success;
    }


    /**
     * Save persistent Model data to file
     *
     * @param file the file to save
     * @return whether or not it worked
     */
    public boolean save(File file) {
        boolean success = true;
        try {
            // establish output stream to file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

            // write our serializable objects ot it
            out.writeObject(user);
            out.writeObject(lostItemManager);
            out.writeObject(foundItemManager);
            out.close();
            Log.d("Model", "Data saved successfully");
        } catch (FileNotFoundException e) {
            Log.d("Model", "Error writing - file not found");
        } catch (IOException e) {
            Log.d("Model", "Error writing - IOException");
            success = false;
        }
        return success;
    }
}
