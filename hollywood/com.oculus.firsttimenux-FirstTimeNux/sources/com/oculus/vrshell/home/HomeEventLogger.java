package com.oculus.vrshell.home;

import android.content.Context;
import android.text.TextUtils;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;

public class HomeEventLogger {
    private static boolean sIsInitialized;
    private static UnifiedTelemetryLogger sUnifiedTelemetryLogger;

    public static void init(Context context) {
        if (!sIsInitialized) {
            sIsInitialized = true;
            sUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        }
    }

    public static void reportEvent(String eventName, String key, String value) {
        HashMap<String, String> args = new HashMap<>();
        args.put(key, value);
        reportEvent(eventName, args);
    }

    public static void reportEvent(String eventName, String key1, String value1, String key2, String value2) {
        HashMap<String, String> args = new HashMap<>();
        args.put(key1, value1);
        args.put(key2, value2);
        reportEvent(eventName, args);
    }

    public static void reportEvent(String eventName, String key1, String value1, String key2, String value2, String key3, String value3) {
        HashMap<String, String> args = new HashMap<>();
        args.put(key1, value1);
        args.put(key2, value2);
        args.put(key3, value3);
        reportEvent(eventName, args);
    }

    public static void reportEvent(String eventName, HashMap<String, String> values) {
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
}
