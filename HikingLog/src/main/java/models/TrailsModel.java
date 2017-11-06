package models;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/1/2017
 * TrailsModel.java
 *
 * DESCRIPTION
 */

import java.util.ArrayList;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class TrailsModel {

    private ArrayList<String> trails = new ArrayList<>();

    public void addTrail(String trailName)
    {
        if (!trailName.equals(""))
        {
            trails.add(trailName);
        }
    }

    public ArrayList<String> getTrails()
    {
        return trails;
    }

    public void removeTrail(String trailName)
    {
        trails.remove(trailName);
    }


}
