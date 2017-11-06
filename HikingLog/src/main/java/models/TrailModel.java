package models;

/**
 * Created by Hillary on 11/4/2017.
 */
public class TrailModel {

    private String trailName;
    private double heartRate;
    private int steps;

    public void setSelectedTrail(String trailName)
    {
        this.trailName = trailName;
    }
    public String getSelectedTrail()
    {
        return trailName;
    }

    public void setHeartRate(double heartRate)
    {
        this.heartRate = heartRate;
    }
    public void setStepCount(int steps)
    {
        this.steps = steps;
    }
    public Double getAverageStepCount()
    {
        return null;
    }
    public Double getAverageHeartRate()
    {
        return null;
    }
    public Double[] getStepCountHistory()
    {
        return null;
    }
    public Double[] getHeartRateHistory()
    {
        return null;
    }
}
