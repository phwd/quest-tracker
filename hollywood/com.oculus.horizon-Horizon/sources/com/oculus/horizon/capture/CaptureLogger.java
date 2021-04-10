package com.oculus.horizon.capture;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;
import javax.annotation.Nullable;

public class CaptureLogger {
    public static final String EVENT_CAPTURE_FAILED = "oculus_mobile_capture_failed";
    public static final String EVENT_CAPTURE_FINISHED = "oculus_mobile_capture_finished";
    public static final String EVENT_CAPTURE_STARTED = "oculus_mobile_capture_started";
    public static final String EVENT_QPL_EVENT_FAILED = "oculus_mobile_capture_qpl_event_failed";
    public static final String EVENT_QPL_EVENT_FINISHED = "oculus_mobile_capture_qpl_event_finished";
    public static final String EVENT_QPL_EVENT_POINT = "oculus_mobile_capture_qpl_event_point";
    public static final String EVENT_QPL_EVENT_STARTED = "oculus_mobile_capture_qpl_event_started";
    public static final String EVENT_WATERFALL_EVENT = "oculus_mobile_capture_waterfall_event";
    public static final String EXTRA_ERROR_CLASS = "error_class";
    public static final String EXTRA_ERROR_MESSAGE = "error_message";
    public static final String EXTRA_EVENT_NAME = "event_name";
    public static final String EXTRA_MARKER_ID = "marker_id";
    public static final String EXTRA_POINT_NAME = "point_name";
    public static final String EXTRA_SEVERITY = "severity";
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public final void A00(String str) {
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent(str), false);
    }

    public final void A01(String str, int i, @Nullable String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        analyticsEvent.setExtra(EXTRA_MARKER_ID, Integer.valueOf(i));
        if (str2 != null) {
            analyticsEvent.setExtra(EXTRA_POINT_NAME, str2);
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public final void A02(String str, Throwable th, String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_CAPTURE_FAILED);
        analyticsEvent.setExtra("event_name", str);
        String message = th.getMessage();
        AnalyticsEvent extra = analyticsEvent.setExtra(EXTRA_ERROR_CLASS, th.getClass().getName());
        if (message == null) {
            message = "null";
        }
        extra.setExtra("error_message", message);
        analyticsEvent.setExtra("severity", str2);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public final void A03(Map map) {
        Object value;
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_WATERFALL_EVENT);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (entry.getValue() == null) {
                    value = "null";
                } else {
                    value = entry.getValue();
                }
                analyticsEvent.setExtra(str, value);
            }
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    public CaptureLogger(Context context) {
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance();
        this.mUnifiedTelemetryLogger = instance;
        instance.init(context);
    }
}
