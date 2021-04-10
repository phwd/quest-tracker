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
        new QuickPromotionLogEvent(context, EVENT_HOLDOUT_EXPOSURE).addExtra(KEY_PROMOTION_ID, promotionId).addExtra(KEY_TRIGGER, trigger).addExtra(KEY_INSTANCE_LOG_DATA, logData).log();
    }

    public static void logExposureEvent(Context context, String promotionId, String trigger, String logData) {
        new QuickPromotionLogEvent(context, EVENT_EXPOSURE).addExtra(KEY_PROMOTION_ID, promotionId).addExtra(KEY_TRIGGER, trigger).addExtra(KEY_INSTANCE_LOG_DATA, logData).log();
    }

    public static void logViewEvent(Context context, String promotionId, String trigger, String logData) {
        new QuickPromotionLogEvent(context, EVENT_VIEW).addExtra(KEY_PROMOTION_ID, promotionId).addExtra(KEY_TRIGGER, trigger).addExtra(KEY_INSTANCE_LOG_DATA, logData).log();
    }

    public static void logClickEvent(Context context, String promotionId, String trigger, String logData, String actionType) {
        new QuickPromotionLogEvent(context, EVENT_ACTION).addExtra(KEY_PROMOTION_ID, promotionId).addExtra(KEY_TRIGGER, trigger).addExtra(KEY_INSTANCE_LOG_DATA, logData).addExtra(KEY_OBJECT_ID, actionType).log();
    }

    private QuickPromotionLogEvent(Context context, String event) {
        this.mContext = context;
        this.mEventName = event;
        this.mPayload.put(KEY_MODULE, VALUE_MODULE);
    }

    public String toString() {
        return this.mPayload.toString();
    }
}
