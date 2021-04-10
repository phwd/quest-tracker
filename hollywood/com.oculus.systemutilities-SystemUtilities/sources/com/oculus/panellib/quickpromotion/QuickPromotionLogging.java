package com.oculus.panellib.quickpromotion;

import android.content.Context;
import android.text.TextUtils;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class QuickPromotionLogging {
    private static boolean sIsInitialized;
    private static UnifiedTelemetryLogger sUnifiedTelemetryLogger;

    public static void init(Context context) {
        if (!sIsInitialized) {
            sIsInitialized = true;
            sUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        }
    }

    private static void reportEvent(String eventName, HashMap<String, String> values) {
        AnalyticsEvent event = new AnalyticsEvent(eventName);
        if (values != null) {
            for (Map.Entry<String, String> entry : values.entrySet()) {
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    event.setExtra(entry.getKey(), value);
                }
            }
        }
        sUnifiedTelemetryLogger.reportEvent(event, false);
    }

    public static void logPromotionHoldoutExposure(String promotionID, int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_holdout_exposure");
        args.put("promotion_id", promotionID);
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }

    public static void logPromotionExposure(String promotionID, int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_exposure");
        args.put("promotion_id", promotionID);
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }

    public static void logPromotionImpression(String promotionID, int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_impression");
        args.put("promotion_id", promotionID);
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }

    public static void logPromotionAction(String action, String promotionID, int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_action");
        args.put("action_name", action);
        args.put("promotion_id", promotionID);
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }

    public static void logOverallFetchStart() {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_overall_fetch_started");
        reportEvent("oculus_config", args);
    }

    public static void logOverallFetchFailure(String error, String errorDetails) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_overall_fetch_failed");
        args.put("error", error);
        args.put("error_details", errorDetails);
        reportEvent("oculus_config", args);
    }

    public static void logOverallFetchSuccess() {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_overall_fetch_completed");
        reportEvent("oculus_config", args);
    }

    public static void logPromotionFetchStart(int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_fetch_started");
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }

    public static void logPromotionFetchFailure(int surfaceID, String trigger, String error, String errorDetails) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_fetch_failed");
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        args.put("error", error);
        args.put("error_details", errorDetails);
        reportEvent("oculus_config", args);
    }

    public static void logPromotionFetchSuccess(int surfaceID, String trigger) {
        HashMap<String, String> args = new HashMap<>();
        args.put("status", "qp_fetch_completed");
        args.put("surface_id", Integer.toString(surfaceID));
        args.put("trigger", trigger);
        reportEvent("oculus_config", args);
    }
}
