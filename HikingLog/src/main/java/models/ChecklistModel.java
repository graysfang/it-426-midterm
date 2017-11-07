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

    private ArrayList getChecklist()
    {
        return checklist;
    }

    private void setChecklist()
    {
        
    }


}
