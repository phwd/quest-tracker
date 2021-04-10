package com.oculus.fitnesstracker;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panellib.AppDetails;
import org.json.JSONObject;

public class FitnessLogger {
    private static final String TAG = "FitnessLogger";

    public enum EventType {
        active_time_goal_complete,
        calories_goal_complete,
        daily_goal_complete,
        daily_goal_half_complete,
        data_provider_create,
        overlay_instance_create,
        trim_memory
    }

    public static void log(Context context, EventType eventType) {
        log(context, eventType, null);
    }

    public static void log(Context context, EventType eventType, JSONObject jSONObject) {
        try {
            String str = TAG;
            Log.d(str, "Log event: " + eventType);
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_fitness_tracker_event");
            analyticsEvent.setExtra("fitness_tracker_event_type", eventType.toString());
            if (jSONObject != null) {
                analyticsEvent.setExtra("event_specific_data", jSONObject.toString());
            }
            analyticsEvent.setExtra("os_version_name", Build.DISPLAY);
            AppDetails appDetails = AppDetails.get(context, context.getPackageName());
            analyticsEvent.setExtra("version_code", Integer.valueOf(appDetails.versionCode));
            analyticsEvent.setExtra("version_name", appDetails.versionName);
            UnifiedTelemetryLogger.getInstance(context).reportEvent(analyticsEvent, false);
        } catch (Exception e) {
            Log.e(TAG, "Exception during logging", e);
        }
    }
}
