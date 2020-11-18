package com.example.hellomenu;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Helper class for the HelloMenu assignment.
 * Created by Sebastian Mueller on 25.10.15.
 */
public class ListViewHelper {

    /** Logcat TAG*/
    private static final String TAG = "ListViewHelper";
    /** Simple ArrayAdapter<String>*/
    public static ArrayAdapter<String> adapter;
    /** ArrayList of Strings*/
    private static ArrayList<String> values = new ArrayList<>(Arrays.asList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"));

    /**
     * Initializes the ArrayAdapter and sets the adapter for the given list view.
     *
     * @param context - context
     * @param listView - list view for which the adapter should be set
     */
    public static void initAdapter(Context context, ListView listView) {
        Log.i(TAG, "initAdapter()");
        //Create Adapter
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setSelector(android.R.color.holo_blue_light);
    }

    /**
     * Removes all checked items from the given ListView
     * @param listView - listView
     */
    public static void removeCheckedItems(ListView listView) {
        Log.i(TAG, "removeCheckedItems");
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        Collection<Object> removeAbleItems = new ArrayList<Object>();

        //Iteratation over all checked items (checked and unchecked are in checkedItems)
        String str = "";
        for (int i = 0; i < checkedItems.size(); i++) {
            if (checkedItems.valueAt(i)) {
                str += checkedItems.keyAt(i) + ", ";
                removeAbleItems.add(listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        //Remove all checked items from list
        values.removeAll(removeAbleItems);
        //Notify
        adapter.notifyDataSetChanged();
        Log.d(TAG, "Deleted items at Position: " + str);
    }
}
