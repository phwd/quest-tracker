package com.oculus.panellib.quickpromotion;

import android.content.Context;
import android.text.TextUtils;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class QuickPromotionLogging {
    public static final String CONFIG_ERROR = "error";
    public static final String CONFIG_ERROR_DETAILS = "error_details";
    public static final String CONFIG_EVENT_NAME = "oculus_config";
    public static final String CONFIG_STATUS = "status";
    public static final String KEY_ACTION_NAME = "action_name";
    public static final String KEY_PROMOTION_ID = "promotion_id";
    public static final String KEY_SURFACE_ID = "surface_id";
    public static final String KEY_TRIGGER = "trigger";
    public static boolean sIsInitialized;
    public static UnifiedTelemetryLogger sUnifiedTelemetryLogger;

    public static void init(Context context) {
        if (!sIsInitialized) {
            sIsInitialized = true;
            sUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        }
    }

    public static void logOverallFetchFailure(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_overall_fetch_failed");
        hashMap.put("error", str);
        hashMap.put(CONFIG_ERROR_DETAILS, str2);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logOverallFetchStart() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_overall_fetch_started");
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logOverallFetchSuccess() {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_overall_fetch_completed");
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionAction(String str, String str2, int i, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_action");
        hashMap.put(KEY_ACTION_NAME, str);
        hashMap.put("promotion_id", str2);
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str3);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionExposure(String str, int i, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", QuickPromotionLogEvent.EVENT_EXPOSURE);
        hashMap.put("promotion_id", str);
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str2);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionFetchFailure(int i, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_fetch_failed");
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str);
        hashMap.put("error", str2);
        hashMap.put(CONFIG_ERROR_DETAILS, str3);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionFetchStart(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_fetch_started");
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionFetchSuccess(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_fetch_completed");
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionHoldoutExposure(String str, int i, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", QuickPromotionLogEvent.EVENT_HOLDOUT_EXPOSURE);
        hashMap.put("promotion_id", str);
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str2);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void logPromotionImpression(String str, int i, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", "qp_impression");
        hashMap.put("promotion_id", str);
        hashMap.put("surface_id", Integer.toString(i));
        hashMap.put("trigger", str2);
        reportEvent(CONFIG_EVENT_NAME, hashMap);
    }

    public static void reportEvent(String str, HashMap<String, String> hashMap) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    analyticsEvent.setExtra(entry.getKey(), value);
                }
            }
        }
        sUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }
}
