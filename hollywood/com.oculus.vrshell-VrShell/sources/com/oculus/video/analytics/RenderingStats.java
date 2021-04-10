package com.oculus.video.analytics;

import org.json.JSONException;
import org.json.JSONObject;

public class RenderingStats {
    private static final String ESTIMATED_FRAME_RATE_FPS = "estimated_frame_rate_fps";
    private static final String EXTRA_APP_RENDER_TIME_ERROR_MS = "app_render_time_error_ms";
    private static final String EXTRA_DROPPED_FRAME_CLUSTER_SIZE = "dropped_frame_cluster_size";
    private static final String EXTRA_EXCL_DISPLAY_INTERVAL_ERROR_MS = "excl_display_interval_error_ms";
    private static final String EXTRA_EXCL_DISPLAY_TIME_ERROR_MS = "excl_display_time_error_ms";
    private static final String EXTRA_INCL_DISPLAY_INTERVAL_ERROR_MS = "incl_display_interval_error_ms";
    private static final String EXTRA_INCL_DISPLAY_TIME_ERROR_MS = "incl_display_time_error_ms";
    private static final String EXTRA_THEATER_RENDERING_PERCENTAGE = "theater_rendering_percentage";
    private static final String SUFFIX_AVG = "_avg";
    private static final String SUFFIX_COUNT = "_count";
    private static final String SUFFIX_STD_DEV = "_std_dev";
    private final ErrorSamples appRenderTimeErrorMs;
    private final ErrorSamples droppedFrameClusterSize;
    final double estimatedFrameRate;
    private final ErrorSamples exclDisplayIntervalErrorMs;
    private final ErrorSamples exclDisplayTimeErrorMs;
    private final ErrorSamples inclDisplayIntervalErrorMs;
    private final ErrorSamples inclDisplayTimeErrorMs;
    private final double theaterRenderingPercentage;

    private double getJSONFloat(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    private ErrorSamples createErrorSamples(JSONObject jSONObject, String str) {
        long jSONFloat = (long) getJSONFloat(jSONObject, str + SUFFIX_COUNT);
        double jSONFloat2 = getJSONFloat(jSONObject, str + SUFFIX_AVG);
        return new ErrorSamples(str, jSONFloat, jSONFloat2, getJSONFloat(jSONObject, str + SUFFIX_STD_DEV));
    }

    RenderingStats(String str) {
        JSONObject jSONObject;
        double d;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        double d2 = 0.0d;
        try {
            d = jSONObject.getDouble(ESTIMATED_FRAME_RATE_FPS);
        } catch (JSONException unused2) {
            d = 0.0d;
        }
        try {
            d2 = jSONObject.getDouble(EXTRA_THEATER_RENDERING_PERCENTAGE);
        } catch (JSONException unused3) {
        }
        this.estimatedFrameRate = d;
        this.appRenderTimeErrorMs = createErrorSamples(jSONObject, EXTRA_APP_RENDER_TIME_ERROR_MS);
        this.inclDisplayTimeErrorMs = createErrorSamples(jSONObject, EXTRA_INCL_DISPLAY_TIME_ERROR_MS);
        this.exclDisplayTimeErrorMs = createErrorSamples(jSONObject, EXTRA_EXCL_DISPLAY_TIME_ERROR_MS);
        this.inclDisplayIntervalErrorMs = createErrorSamples(jSONObject, EXTRA_INCL_DISPLAY_INTERVAL_ERROR_MS);
        this.exclDisplayIntervalErrorMs = createErrorSamples(jSONObject, EXTRA_EXCL_DISPLAY_INTERVAL_ERROR_MS);
        this.droppedFrameClusterSize = createErrorSamples(jSONObject, EXTRA_DROPPED_FRAME_CLUSTER_SIZE);
        this.theaterRenderingPercentage = d2;
    }

    /* access modifiers changed from: package-private */
    public void appendToEvent(VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        this.appRenderTimeErrorMs.appendToEvent(videoPlayerAnalyticsLogEvent);
        this.inclDisplayTimeErrorMs.appendToEvent(videoPlayerAnalyticsLogEvent);
        this.exclDisplayTimeErrorMs.appendToEvent(videoPlayerAnalyticsLogEvent);
        this.inclDisplayIntervalErrorMs.appendToEvent(videoPlayerAnalyticsLogEvent);
        this.exclDisplayIntervalErrorMs.appendToEvent(videoPlayerAnalyticsLogEvent);
        this.droppedFrameClusterSize.appendToEvent(videoPlayerAnalyticsLogEvent);
        videoPlayerAnalyticsLogEvent.put(EXTRA_THEATER_RENDERING_PERCENTAGE, this.theaterRenderingPercentage);
    }
}
