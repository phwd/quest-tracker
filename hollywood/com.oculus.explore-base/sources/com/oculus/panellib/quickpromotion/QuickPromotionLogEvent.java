package com.oculus.panellib.quickpromotion;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class QuickPromotionLogEvent {
    private final Context mContext;
    private final String mEventName;
    private final HashMap<String, String> mPayload = new HashMap<>();

    private QuickPromotionLogEvent addExtra(String key, String value) {
        this.mPayload.put(key, value);
        return this;
    }

    private void log() {
        AnalyticsEvent event = new AnalyticsEvent(this.mEventName);
        for (Map.Entry<String, String> data : this.mPayload.entrySet()) {
            event.setExtra(data.getKey(), data.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(event, false);
    }

    public static void logHoldoutExposureEvent(Context context, String promotionId, String trigger, String logData) {
        new QuickPromotionLogEvent(context, "qp_holdout_exposure").addExtra("promotion_id", promotionId).addExtra("trigger", trigger).addExtra("instance_log_data", logData).log();
    }

    public static void logExposureEvent(Context context, String promotionId, String trigger, String logData) {
        new QuickPromotionLogEvent(context, "qp_exposure").addExtra("promotion_id", promotionId).addExtra("trigger", trigger).addExtra("instance_log_data", logData).log();
    }

    public static void logViewEvent(Context context, String promotionId, String trigger, String logData) {
        new QuickPromotionLogEvent(context, "view").addExtra("promotion_id", promotionId).addExtra("trigger", trigger).addExtra("instance_log_data", logData).log();
    }

    public static void logClickEvent(Context context, String promotionId, String trigger, String logData, String actionType) {
        new QuickPromotionLogEvent(context, "click").addExtra("promotion_id", promotionId).addExtra("trigger", trigger).addExtra("instance_log_data", logData).addExtra("object_id", actionType).log();
    }

    private QuickPromotionLogEvent(Context context, String event) {
        this.mContext = context;
        this.mEventName = event;
        this.mPayload.put("module", "quick_promotion");
    }

    public String toString() {
        return this.mPayload.toString();
    }
}
