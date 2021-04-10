package com.oculus.deviceconfigclient;

import X.AnonymousClass0MD;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Locale;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigTelemetryLogger {
    public static final String ANALYTICS_EVENT_NAME = "oculus_device_config_event";
    public static final String TAG = "DeviceConfigTelemetryLogger";
    public static final boolean mDebugLog = true;
    public final Context mContext;
    public final AnalyticsEvent mEvent = new AnalyticsEvent(ANALYTICS_EVENT_NAME);

    public enum SubEvent {
        SUBSCRIBE_SUCCESS,
        SUBSCRIBE_FAILURE,
        GET_VALUE_SUCCESS,
        GET_VALUE_FAILURE,
        NOT_SUBSCRIBED,
        UNKNOWN_PARAM,
        INCORRECT_TYPE_PARAM,
        INCORRECT_DEFAULT_VALUE,
        MISSING_DEFAULT_VALUES,
        INTERNAL_ERROR
    }

    public enum ValueType {
        BOOLEAN,
        DOUBLE,
        LONG,
        STRING,
        DEVICE_BOOLEAN,
        DEVICE_DOUBLE,
        DEVICE_LONG,
        DEVICE_STRING
    }

    public static DeviceConfigTelemetryLogger create(Context context) {
        return new DeviceConfigTelemetryLogger(context);
    }

    private void log() {
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(this.mEvent, false);
    }

    public static void logGetValueFailure(Context context, ValueType valueType, String str, Exception exc) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.GET_VALUE_SUCCESS);
        deviceConfigTelemetryLogger.setParam(valueType, str);
        deviceConfigTelemetryLogger.setErrorAsException(exc);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "get%s(%s) threw an exception", valueType, str, exc);
    }

    public static void logGetValueSuccess(Context context, ValueType valueType, String str, @Nullable Object obj, String str2, long j) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.GET_VALUE_SUCCESS);
        deviceConfigTelemetryLogger.setParam(valueType, str);
        deviceConfigTelemetryLogger.setValue(obj);
        deviceConfigTelemetryLogger.setSource(str2);
        deviceConfigTelemetryLogger.setLatency(j);
        deviceConfigTelemetryLogger.log();
    }

    public static void logIncorrectDefaultValue(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INCORRECT_DEFAULT_VALUE);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.setParamName(str2);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "%s: %s", str, str2);
    }

    public static void logIncorrectTypeParam(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INCORRECT_TYPE_PARAM);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.setParamName(str2);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "%s: %s", str, str2);
    }

    public static void logInternalParamError(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INTERNAL_ERROR);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.setParamName(str2);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "%s: %s", str, str2);
    }

    public static void logMissingDefaultFile(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.MISSING_DEFAULT_VALUES);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "%s: %s", str, str2);
    }

    public static void logNotSubscribedError(Context context, String str) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.NOT_SUBSCRIBED);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A04(TAG, str);
    }

    public static void logSubscriptionSuccess(Context context, long j) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.SUBSCRIBE_SUCCESS);
        deviceConfigTelemetryLogger.setLatency(j);
        deviceConfigTelemetryLogger.log();
    }

    public static void logUnknownParam(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.UNKNOWN_PARAM);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.setParamName(str2);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "%s: %s", str, str2);
    }

    private DeviceConfigTelemetryLogger setErrorMessage(@Nullable String str) {
        if (str != null) {
            this.mEvent.setExtra("error_msg", str);
        }
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorStacktrace(String str) {
        this.mEvent.setExtra("error_stacktrace", str);
        return this;
    }

    private DeviceConfigTelemetryLogger setLatency(long j) {
        this.mEvent.setExtra("latency", Long.valueOf(j));
        return this;
    }

    private DeviceConfigTelemetryLogger setParam(ValueType valueType, String str) {
        this.mEvent.setExtra("valueType", valueType.toString().toLowerCase(Locale.US));
        this.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, str);
        return this;
    }

    private DeviceConfigTelemetryLogger setParamName(String str) {
        this.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, str);
        return this;
    }

    private DeviceConfigTelemetryLogger setSource(String str) {
        this.mEvent.setExtra("source", str);
        return this;
    }

    private DeviceConfigTelemetryLogger setSubEvent(SubEvent subEvent) {
        this.mEvent.setExtra("sub_event", subEvent.toString().toLowerCase(Locale.US));
        return this;
    }

    private DeviceConfigTelemetryLogger setValue(@Nullable Object obj) {
        if (obj != null) {
            this.mEvent.setExtra("value", obj);
        }
        return this;
    }

    public DeviceConfigTelemetryLogger(Context context) {
        this.mContext = context;
    }

    public static void logUnknownMCType(Context context, int i) {
        String format = String.format("Unknown MC type %s", Integer.valueOf(i));
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INTERNAL_ERROR);
        deviceConfigTelemetryLogger.setErrorMessage(format);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A04(TAG, format);
    }

    private DeviceConfigTelemetryLogger setErrorAsException(Exception exc) {
        setErrorMessage(exc.getMessage());
        setErrorStacktrace(exc.getStackTrace().toString());
        return this;
    }

    public static void logInternalError(Context context, Exception exc) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INTERNAL_ERROR);
        deviceConfigTelemetryLogger.setErrorAsException(exc);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A07(TAG, "threw an exception", exc);
    }

    public static void logInternalError(Context context, String str) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.INTERNAL_ERROR);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A04(TAG, str);
    }

    public static void logSubscriptionFailure(Context context, Exception exc) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.SUBSCRIBE_FAILURE);
        deviceConfigTelemetryLogger.setErrorAsException(exc);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A07(TAG, "subscribe() threw an exception", exc);
    }

    public static void logSubscriptionFailure(Context context, String str) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        deviceConfigTelemetryLogger.setSubEvent(SubEvent.SUBSCRIBE_FAILURE);
        deviceConfigTelemetryLogger.setErrorMessage(str);
        deviceConfigTelemetryLogger.log();
        AnonymousClass0MD.A09(TAG, "onFailure(%s)", str);
    }
}
