package com.oculus.video.analytics;

import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class ErrorSamples {
    private static final String SUFFIX_AVG = "_avg";
    private static final String SUFFIX_COUNT = "_count";
    private static final String SUFFIX_STD_DEV = "_std_dev";
    private final double average;
    private final long count;
    private final String eventPrefix;
    private final double stdDev;

    ErrorSamples(String str, long j, double d, double d2) {
        this.eventPrefix = str;
        this.count = j;
        this.average = d;
        this.stdDev = d2;
    }

    /* access modifiers changed from: package-private */
    public void appendToEvent(VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        JSONObject put = videoPlayerAnalyticsLogEvent.put(this.eventPrefix + SUFFIX_COUNT, this.count);
        JSONObject put2 = put.put(this.eventPrefix + SUFFIX_AVG, this.average);
        put2.put(this.eventPrefix + SUFFIX_STD_DEV, this.stdDev);
    }
}
