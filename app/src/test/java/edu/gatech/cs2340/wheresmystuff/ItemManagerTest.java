package edu.gatech.cs2340.wheresmystuff;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.cs2340.wheresmystuff.model.Item;
import edu.gatech.cs2340.wheresmystuff.model.ItemCategory;
import edu.gatech.cs2340.wheresmystuff.model.ItemManager;
import edu.gatech.cs2340.wheresmystuff.model.LostItem;

/**
 * Junit tests for ItemManager class in Model.
 *
 * Created by D. Zach Edmands
 */

public class ItemManagerTest {
    private ItemManager im;

    @Before
    public void setUp() {
        im = new ItemManager();
        im.addItem(new LostItem("ring", "diamond on gold band", false,
                ItemCategory.KEEPSAKES, new GregorianCalendar(), "30068", 2500));
    }

    /**
     * Tests for {@link edu.gatech.cs2340.wheresmystuff.model.ItemManager#search(String)}
     *
     * Written by D. Zach Edmands
     */
    @Test
    public void testSearchWithValidEntry() {
        List<Item> searchResults = im.search("ring");
        Assert.assertTrue("Item correctly displayed in search results",
                searchResults.get(0).getName().equals("ring"));
    }

    @Test
    public void testSearchWithInvalidEntry() {
        List<Item> searchResults = im.search("rin");
        Assert.assertTrue("Search for invalid item correctly returns empty list",
                searchResults.size() == 0);
    }

    @Test
    public void testSearchNull() {
        List<Item> searchResults = im.search(null);
        Assert.assertTrue("Search successfully returns empty list when passed null arg",
                searchResults.size() == 0);
    }

    @Test
    public void testSearchEmptyString() {
        List<Item> searchResults = im.search("");
        Assert.assertTrue("Search successfully returns empty list when passed empty string",
                searchResults.size() == 0);
    }

    @Test
    public void testSearchCaseSensitivity() {
        List<Item> searchResults = im.search("rInG");
        Assert.assertTrue("Search displays correct results despite case of query letters",
                searchResults.get(0).getName().equals("ring"));
    }

    @Test
    public void testSearchEmptyItemManager() {
        im = new ItemManager();
        List<Item> searchResults = im.search("anything");
        Assert.assertTrue("Search successfully returns empty list when Item Manager is empty",
                searchResults.size() == 0);
    }

}
