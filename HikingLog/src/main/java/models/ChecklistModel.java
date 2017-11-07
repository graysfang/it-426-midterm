package models;/*
 * Nicholas Perez
 * 11/6/2017
 * ChecklistModel.java
 *
 * DESCRIPTION
 */

import java.util.ArrayList;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class ChecklistModel
{
    private ArrayList<String> checklist = UtilitiesIO.readIn("checklist.txt");

    public ArrayList getChecklist()
    {
        return checklist;
    }

    public void setChecklist()
    {
        
    }


}
