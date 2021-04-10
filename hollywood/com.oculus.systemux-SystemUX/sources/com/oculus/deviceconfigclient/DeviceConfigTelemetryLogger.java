package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Locale;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigTelemetryLogger {
    private static final String ANALYTICS_EVENT_NAME = "oculus_device_config_event";
    private static final String TAG = "DeviceConfigTelemetryLogger";
    private static final boolean mDebugLog = true;
    private final Context mContext;
    private final AnalyticsEvent mEvent = new AnalyticsEvent(ANALYTICS_EVENT_NAME);

    /* access modifiers changed from: private */
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

    private DeviceConfigTelemetryLogger(Context context) {
        this.mContext = context;
    }

    private static DeviceConfigTelemetryLogger create(Context context) {
        return new DeviceConfigTelemetryLogger(context);
    }

    private DeviceConfigTelemetryLogger setSubEvent(SubEvent subEvent) {
        this.mEvent.setExtra("sub_event", subEvent.toString().toLowerCase(Locale.US));
        return this;
    }

    private DeviceConfigTelemetryLogger setSource(String str) {
        this.mEvent.setExtra("source", str);
        return this;
    }

    private DeviceConfigTelemetryLogger setLatency(long j) {
        this.mEvent.setExtra("latency", Long.valueOf(j));
        return this;
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

    private DeviceConfigTelemetryLogger setErrorAsException(Exception exc) {
        return setErrorMessage(exc.getMessage()).setErrorStacktrace(exc.getStackTrace().toString());
    }

    private DeviceConfigTelemetryLogger setParamName(String str) {
        this.mEvent.setExtra("param_name", str);
        return this;
    }

    private DeviceConfigTelemetryLogger setParam(ValueType valueType, String str) {
        this.mEvent.setExtra("valueType", valueType.toString().toLowerCase(Locale.US));
        this.mEvent.setExtra("param_name", str);
        return this;
    }

    private DeviceConfigTelemetryLogger setValue(@Nullable Object obj) {
        if (obj != null) {
            this.mEvent.setExtra(AssistantComponentContract.Components.TextComponent.VALUE, obj);
        }
        return this;
    }

    private void log() {
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(this.mEvent, false);
    }

    public static void logSubscriptionSuccess(Context context, long j) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_SUCCESS).setLatency(j).log();
        BLog.d(TAG, "onSuccess()");
    }

    public static void logSubscriptionFailure(Context context, Exception exc) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_FAILURE).setErrorAsException(exc).log();
        BLog.e(TAG, "subscribe() threw an exception", exc);
    }

    public static void logSubscriptionFailure(Context context, String str) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_FAILURE).setErrorMessage(str).log();
        BLog.e(TAG, "onFailure(%s)", str);
    }

    public static void logGetValueSuccess(Context context, ValueType valueType, String str, @Nullable Object obj, String str2, long j) {
        create(context).setSubEvent(SubEvent.GET_VALUE_SUCCESS).setParam(valueType, str).setValue(obj).setSource(str2).setLatency(j).log();
        BLog.d(TAG, "Get %s %s=%s Source: %s", valueType, str, obj, str2);
    }

    public static void logGetValueFailure(Context context, ValueType valueType, String str, Exception exc) {
        create(context).setSubEvent(SubEvent.GET_VALUE_SUCCESS).setParam(valueType, str).setErrorAsException(exc).log();
        BLog.e(TAG, "get%s(%s) threw an exception", valueType, str, exc);
    }

    public static void logNotSubscribedError(Context context, String str) {
        create(context).setSubEvent(SubEvent.NOT_SUBSCRIBED).setErrorMessage(str).log();
        BLog.e(TAG, str);
    }

    public static void logUnknownParam(Context context, String str, String str2) {
        create(context).setSubEvent(SubEvent.UNKNOWN_PARAM).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logIncorrectTypeParam(Context context, String str, String str2) {
        create(context).setSubEvent(SubEvent.INCORRECT_TYPE_PARAM).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logIncorrectDefaultValue(Context context, String str, String str2) {
        create(context).setSubEvent(SubEvent.INCORRECT_DEFAULT_VALUE).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logMissingDefaultFile(Context context, String str, String str2) {
        create(context).setSubEvent(SubEvent.MISSING_DEFAULT_VALUES).setErrorMessage(str).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logUnknownMCType(Context context, int i) {
        String format = String.format("Unknown MC type %s", Integer.valueOf(i));
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(format).log();
        BLog.e(TAG, format);
    }

    public static void logInternalError(Context context, String str) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(str).log();
        BLog.e(TAG, str);
    }

    public static void logInternalParamError(Context context, String str, String str2) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logInternalError(Context context, Exception exc) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorAsException(exc).log();
        BLog.e(TAG, "threw an exception", exc);
    }
}
