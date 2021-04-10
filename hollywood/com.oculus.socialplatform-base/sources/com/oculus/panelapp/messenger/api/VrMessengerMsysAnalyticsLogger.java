package com.oculus.panelapp.messenger.api;

import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import com.facebook.msys.mci.Analytics;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.List;
import java.util.Map;

public class VrMessengerMsysAnalyticsLogger implements Analytics {
    public static final String CATEGORY = "category";
    public static final String EVENT_ANNOTATIONS = "event_annotations";
    public static final String EVENT_ANNOTATION_VALUES = "eav";
    public static final String EVENT_INSTANCE_ID = "event_instance_id";
    public static final String EVENT_TYPE = "event_type";
    public static final String FEATURE = "feature";
    public static final String REALTIME = "realtime";
    public static final String TAG = "VrMessengerMsysAnalyticsLogger";
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public static void addListToBundle(PersistableBundle persistableBundle, List<Object> list) {
        PersistableBundle persistableBundle2;
        persistableBundle.putBoolean("utl_array_of_bundles", true);
        int i = 0;
        for (Object obj : list) {
            if (obj != null) {
                if (obj instanceof Map) {
                    persistableBundle2 = new PersistableBundle();
                    addMapToBundle(persistableBundle2, (Map) obj);
                } else if (obj instanceof List) {
                    persistableBundle2 = new PersistableBundle();
                    addListToBundle(persistableBundle2, (List) obj);
                } else if (obj instanceof Boolean) {
                    persistableBundle.putBoolean(String.valueOf(i), ((Boolean) obj).booleanValue());
                } else if (obj instanceof Integer) {
                    persistableBundle.putInt(String.valueOf(i), ((Number) obj).intValue());
                } else if (obj instanceof Long) {
                    persistableBundle.putLong(String.valueOf(i), ((Number) obj).longValue());
                } else if (obj instanceof String) {
                    persistableBundle.putString(String.valueOf(i), (String) obj);
                } else {
                    throw new IllegalArgumentException("Unexpected value type.");
                }
                persistableBundle.putPersistableBundle(String.valueOf(i), persistableBundle2);
            } else {
                persistableBundle.putString(String.valueOf(i), null);
            }
            i++;
        }
    }

    public VrMessengerMsysAnalyticsLogger(UnifiedTelemetryLogger unifiedTelemetryLogger) {
        this.mUnifiedTelemetryLogger = unifiedTelemetryLogger;
    }

    public static void addMapToBundle(PersistableBundle persistableBundle, Map<String, Object> map) {
        String str;
        String str2;
        PersistableBundle persistableBundle2;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof Map) {
                    persistableBundle2 = new PersistableBundle();
                    addMapToBundle(persistableBundle2, (Map) value);
                } else if (value instanceof List) {
                    persistableBundle2 = new PersistableBundle();
                    addListToBundle(persistableBundle2, (List) value);
                } else if (value instanceof Boolean) {
                    persistableBundle.putBoolean(entry.getKey(), ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    persistableBundle.putInt(entry.getKey(), ((Number) value).intValue());
                } else if (value instanceof Long) {
                    persistableBundle.putLong(entry.getKey(), ((Number) value).longValue());
                } else if (value instanceof String) {
                    str2 = entry.getKey();
                    str = (String) value;
                } else {
                    throw new IllegalArgumentException("Unexpected value type.");
                }
                persistableBundle.putPersistableBundle(entry.getKey(), persistableBundle2);
            } else {
                str2 = entry.getKey();
                str = null;
            }
            persistableBundle.putString(str2, str);
        }
    }

    @Override // com.facebook.msys.mci.Analytics
    public void log(int i, int i2, boolean z, String str, String str2, long j, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2, @Nullable List<Object> list) {
        String str3;
        Integer valueOf = Integer.valueOf(i2);
        Long valueOf2 = Long.valueOf(j);
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(Integer.toString(i));
        analyticsEvent.setExtra(EVENT_TYPE, valueOf);
        analyticsEvent.setExtra("category", str);
        analyticsEvent.setExtra(FEATURE, str2);
        analyticsEvent.setExtra(REALTIME, Boolean.valueOf(z));
        analyticsEvent.setExtra(EVENT_INSTANCE_ID, valueOf2);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue() != null) {
                    str3 = entry.getValue().toString();
                } else {
                    str3 = null;
                }
                analyticsEvent.setExtra(key, str3);
            }
        }
        if (map2 != null) {
            PersistableBundle persistableBundle = new PersistableBundle();
            addMapToBundle(persistableBundle, map2);
            analyticsEvent.setExtra(EVENT_ANNOTATIONS, persistableBundle);
        }
        if (list != null) {
            PersistableBundle persistableBundle2 = new PersistableBundle();
            addListToBundle(persistableBundle2, list);
            analyticsEvent.setExtra(EVENT_ANNOTATION_VALUES, persistableBundle2);
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }
}
