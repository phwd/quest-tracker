package com.oculus.appsafety;

import android.content.Context;
import android.os.PersistableBundle;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggingHelper {
    public static final String BINARY_CHECK_EVENT = "oculus_unofficial_binary_check";
    public static final String EVENT_SUBTYPE = "event_subtype";
    public static final String EVENT_SUBTYPE_PAYLOAD = "payload";
    public static final String EXCEPTION_MESSAGE = "exception_message";
    public static final String OCULUS_SAFETY_SIGNAL = "oculus_safety_signal";
    public static final String PACKAGE_NAME = "package_name";
    public static final String PACKAGE_PART_UPLOAD_EVENT = "oculus_package_part_upload";
    public static final String PACKAGE_TELEMETRY = "oculus_package_telemetry";
    public static final String STACKTRACE = "stacktrace";
    private static final String TAG = LoggingHelper.class.getSimpleName();

    public static void logBinaryCheckEvent(Context context, String packageName, String eventSubtype, Exception e) {
        PersistableBundle extras = new PersistableBundle();
        extras.putString(PACKAGE_NAME, packageName);
        extras.putString(EVENT_SUBTYPE, eventSubtype);
        if (e != null) {
            formatException(extras, e);
        }
        logEvent(context, BINARY_CHECK_EVENT, extras);
    }

    public static void logBinaryCheckEvent(Context context, String packageName, String eventSubtype) {
        logBinaryCheckEvent(context, packageName, eventSubtype, null);
    }

    public static AnalyticsEvent getSafetySignalEvent(String eventSubtype, Throwable e) {
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EVENT_SUBTYPE, eventSubtype);
        if (e != null) {
            formatException(extras, e);
        }
        return getEvent(OCULUS_SAFETY_SIGNAL, extras);
    }

    public static AnalyticsEvent getSafetySignalStatus(PersistableBundle payload) {
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EVENT_SUBTYPE, EVENT_SUBTYPE_PAYLOAD);
        extras.putPersistableBundle(EVENT_SUBTYPE_PAYLOAD, payload);
        return getEvent(OCULUS_SAFETY_SIGNAL, extras);
    }

    public static AnalyticsEvent getSafetySignalEvent(String eventSubtype) {
        return getSafetySignalEvent(eventSubtype, null);
    }

    protected static void logEvent(Context context, String eventName, PersistableBundle extras) {
        UnifiedTelemetryLogger.getInstance(context).reportEvent(new AnalyticsEvent(TAG, eventName, extras, (PersistableBundle) null), false);
    }

    protected static AnalyticsEvent getEvent(String eventName, PersistableBundle extras) {
        return new AnalyticsEvent(TAG, eventName, extras, (PersistableBundle) null);
    }

    public static void formatException(PersistableBundle extras, Throwable e) {
        extras.putString(EXCEPTION_MESSAGE, e.getMessage());
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        extras.putString(STACKTRACE, sw.toString());
    }
}
