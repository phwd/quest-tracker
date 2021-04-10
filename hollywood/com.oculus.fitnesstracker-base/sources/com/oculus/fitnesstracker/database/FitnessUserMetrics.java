package com.oculus.fitnesstracker.database;

public class FitnessUserMetrics {
    private Double mHeight;
    private Double mWeight;

    public FitnessUserMetrics(Double d, Double d2) {
        this.mHeight = d;
        this.mWeight = d2;
    }

    public Double getHeight() {
        return this.mHeight;
    }

    public Double getWeight() {
        return this.mWeight;
    }

    public String toString() {
        return "UserMetrics{mHeight=" + this.mHeight + ", mWeight=" + this.mWeight + '}';
    }
}
