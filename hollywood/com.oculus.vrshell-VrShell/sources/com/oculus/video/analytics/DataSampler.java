package com.oculus.video.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;

class DataSampler {
    private static final String SUFFIX_AVG = "_avg";
    private static final String SUFFIX_COUNT = "_count";
    private static final String SUFFIX_MAX = "_max";
    private static final String SUFFIX_MIN = "_min";
    private static final String SUFFIX_P_50 = "_p_50";
    private static final String SUFFIX_SAMPLE = "_sample";
    private static final String SUFFIX_SUM = "_sum";
    private final String eventPrefix;
    private long max = 0;
    private long min = 0;
    private final List<Long> samples = new ArrayList();
    private long sum = 0;
    private double totalWeight = 0.0d;
    private double weightedSum = 0.0d;

    DataSampler(String str) {
        this.eventPrefix = str;
    }

    /* access modifiers changed from: package-private */
    public void sample(long j) {
        sample(j, 1.0d);
    }

    /* access modifiers changed from: package-private */
    public void sample(long j, double d) {
        if (this.samples.size() == 0) {
            this.min = j;
            this.max = j;
            this.totalWeight = 0.0d;
            this.weightedSum = 0.0d;
            this.sum = 0;
        }
        this.totalWeight += d;
        this.weightedSum += ((double) j) * d;
        this.sum += j;
        if (this.max < j) {
            this.max = j;
        }
        if (this.min > j) {
            this.min = j;
        }
        this.samples.add(Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public int getCount() {
        return this.samples.size();
    }

    /* access modifiers changed from: package-private */
    public long getSum() {
        return this.sum;
    }

    /* access modifiers changed from: package-private */
    public double getAverage() {
        return this.weightedSum / Math.max(1.0E-6d, this.totalWeight);
    }

    /* access modifiers changed from: package-private */
    public long getMax() {
        return this.max;
    }

    /* access modifiers changed from: package-private */
    public long getMin() {
        return this.min;
    }

    /* access modifiers changed from: package-private */
    public void appendToEvent(VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent, boolean z, boolean z2) throws JSONException {
        long j;
        if (!z2 || getCount() != 0) {
            videoPlayerAnalyticsLogEvent.put(this.eventPrefix + SUFFIX_COUNT, getCount()).put(this.eventPrefix + SUFFIX_AVG, getAverage()).put(this.eventPrefix + SUFFIX_MIN, getMin()).put(this.eventPrefix + SUFFIX_MAX, getMax());
            long j2 = 0;
            if (this.samples.size() != 0) {
                List<Long> list = this.samples;
                j2 = list.get(list.size() / 2).longValue();
                Collections.sort(this.samples);
                List<Long> list2 = this.samples;
                j = list2.get(list2.size() / 2).longValue();
            } else {
                j = 0;
            }
            videoPlayerAnalyticsLogEvent.put(this.eventPrefix + SUFFIX_SAMPLE, j2);
            videoPlayerAnalyticsLogEvent.put(this.eventPrefix + SUFFIX_P_50, j);
            if (z) {
                videoPlayerAnalyticsLogEvent.put(this.eventPrefix + SUFFIX_SUM, getSum());
            }
        }
    }
}
