package com.oculus.fitnesstracker.database;

public class FitnessCaloriesData {
    private Double mCalories;
    private int mIsActive;
    private long mSetDate;

    public FitnessCaloriesData(Double d, long j, int i) {
        this.mCalories = d;
        this.mSetDate = j;
        this.mIsActive = i;
    }

    public Double getCalories() {
        return this.mCalories;
    }

    public long getSetDate() {
        return this.mSetDate;
    }

    public int getIsActive() {
        return this.mIsActive;
    }

    public String toString() {
        return "Calories{mCalories=" + this.mCalories + ", mSetDate=" + this.mSetDate + ", mIsActive=" + this.mIsActive + '}';
    }
}
