package com.oculus.fitnesstracker.database;

public class FitnessUserGoals {
    private Double mActiveTimeGoal;
    private Double mCaloriesGoal;

    public FitnessUserGoals(Double d, Double d2) {
        this.mActiveTimeGoal = d;
        this.mCaloriesGoal = d2;
    }

    public Double getCaloriesGoal() {
        return this.mCaloriesGoal;
    }

    public Double getActiveTimeGoal() {
        return this.mActiveTimeGoal;
    }

    public String toString() {
        return "Goals{mActiveTimeGoal=" + this.mActiveTimeGoal + ", mCaloriesGoal=" + this.mCaloriesGoal + '}';
    }
}
