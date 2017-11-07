package models;

/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/6/2017
 * ChecklistModel.java
 *
 * Model/Controller for the checklist.
 */

import java.util.ArrayList;

/**
 * Model/Controller for the checklist.
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class ChecklistModel
{
    private static ArrayList<String> checklist = UtilitiesIO.readIn("checklist.txt");

    /**
     * Gets the checklist
     * @return
     */
    public ArrayList getChecklist()
    {
        return checklist;
    }

    /**
     * Adds a new item to the checklist
     * @param newItem the items to be added
     */
    public void setChecklist(String newItem)
    {
        checklist.add(newItem);

        UtilitiesIO.writeOut("checklist.txt", checklist);
    }

    /**
     * removes an item from the checklist
     * @param removeItem the item to be removed
     */
    public void removeItem(String removeItem)
    {
        for (String item: checklist)
        {
            if(item.toLowerCase().equals(removeItem.toLowerCase()))
            {
                checklist.remove(removeItem);
                break;
            }
        }
        UtilitiesIO.writeOut("checklist.txt", checklist);
        updateList();
    }

    //helper method to update the last after item removal
    private void updateList()
    {
        checklist = UtilitiesIO.readIn("checklist.txt");
    }
}