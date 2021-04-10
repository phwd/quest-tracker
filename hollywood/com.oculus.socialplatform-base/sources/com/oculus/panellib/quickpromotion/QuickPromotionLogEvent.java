package com.oculus.panellib.quickpromotion;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class QuickPromotionLogEvent {
    public static final String EVENT_ACTION = "click";
    public static final String EVENT_EXPOSURE = "qp_exposure";
    public static final String EVENT_HOLDOUT_EXPOSURE = "qp_holdout_exposure";
    public static final String EVENT_VIEW = "view";
    public static final String KEY_EVENT_NAME = "name";
    public static final String KEY_INSTANCE_LOG_DATA = "instance_log_data";
    public static final String KEY_MODULE = "module";
    public static final String KEY_OBJECT_ID = "object_id";
    public static final String KEY_PROMOTION_ID = "promotion_id";
    public static final String KEY_TRIGGER = "trigger";
    public static final String VALUE_MODULE = "quick_promotion";
    public final Context mContext;
    public final String mEventName;
    public final HashMap<String, String> mPayload;

    private QuickPromotionLogEvent addExtra(String str, String str2) {
        this.mPayload.put(str, str2);
        return this;
    }

    private void log() {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(this.mEventName);
        for (Map.Entry<String, String> entry : this.mPayload.entrySet()) {
            analyticsEvent.setExtra(entry.getKey(), entry.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }

    public static void logClickEvent(Context context, String str, String str2, String str3, String str4) {
        QuickPromotionLogEvent quickPromotionLogEvent = new QuickPromotionLogEvent(context, "click");
        quickPromotionLogEvent.mPayload.put("promotion_id", str);
        quickPromotionLogEvent.mPayload.put("trigger", str2);
        quickPromotionLogEvent.mPayload.put("instance_log_data", str3);
        quickPromotionLogEvent.mPayload.put("object_id", str4);
        quickPromotionLogEvent.log();
    }

    public static void logExposureEvent(Context context, String str, String str2, String str3) {
        QuickPromotionLogEvent quickPromotionLogEvent = new QuickPromotionLogEvent(context, EVENT_EXPOSURE);
        quickPromotionLogEvent.mPayload.put("promotion_id", str);
        quickPromotionLogEvent.mPayload.put("trigger", str2);
        quickPromotionLogEvent.mPayload.put("instance_log_data", str3);
        quickPromotionLogEvent.log();
    }

    public static void logHoldoutExposureEvent(Context context, String str, String str2, String str3) {
        QuickPromotionLogEvent quickPromotionLogEvent = new QuickPromotionLogEvent(context, EVENT_HOLDOUT_EXPOSURE);
        quickPromotionLogEvent.mPayload.put("promotion_id", str);
        quickPromotionLogEvent.mPayload.put("trigger", str2);
        quickPromotionLogEvent.mPayload.put("instance_log_data", str3);
        quickPromotionLogEvent.log();
    }

    public static void logViewEvent(Context context, String str, String str2, String str3) {
        QuickPromotionLogEvent quickPromotionLogEvent = new QuickPromotionLogEvent(context, "view");
        quickPromotionLogEvent.mPayload.put("promotion_id", str);
        quickPromotionLogEvent.mPayload.put("trigger", str2);
        quickPromotionLogEvent.mPayload.put("instance_log_data", str3);
        quickPromotionLogEvent.log();
    }

    public String toString() {
        return this.mPayload.toString();
    }

    public QuickPromotionLogEvent(Context context, String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        this.mPayload = hashMap;
        this.mContext = context;
        this.mEventName = str;
        hashMap.put(KEY_MODULE, "quick_promotion");
    }
}
