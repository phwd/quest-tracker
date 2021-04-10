package com.oculus.unifiedtelemetry.collectors;

public class SampledMetric {
    public int mMax;
    public int mMin;
    public int mTotal;
    public int mTotalSquare;

    public final void A01(int i) {
        this.mMin = Math.min(this.mMin, i);
        this.mMax = Math.max(this.mMax, i);
        this.mTotal += i;
        this.mTotalSquare += i * i;
    }

    public SampledMetric() {
        A00();
    }

    public final void A00() {
        this.mMin = Integer.MAX_VALUE;
        this.mMax = Integer.MIN_VALUE;
        this.mTotal = 0;
        this.mTotalSquare = 0;
    }
}
