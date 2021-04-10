package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Locale;

class DeviceConfigTelemetryLogger {
    private static final String ANALYTICS_EVENT_NAME = "oculus_device_config_event";
    private static final String TAG = DeviceConfigTelemetryLogger.class.getSimpleName();
    private static final boolean mDebugLog = true;
    private final Context mContext;
    private final AnalyticsEvent mEvent = new AnalyticsEvent(ANALYTICS_EVENT_NAME);

    private enum SubEvent {
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

    private DeviceConfigTelemetryLogger setSource(String source) {
        this.mEvent.setExtra("source", source);
        return this;
    }

    private DeviceConfigTelemetryLogger setLatency(long latency) {
        this.mEvent.setExtra("latency", Long.valueOf(latency));
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorMessage(String errorMsg) {
        if (errorMsg != null) {
            this.mEvent.setExtra("error_msg", errorMsg);
        }
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorStacktrace(String errorStacktrace) {
        this.mEvent.setExtra("error_stacktrace", errorStacktrace);
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorAsException(Exception e) {
        return setErrorMessage(e.getMessage()).setErrorStacktrace(e.getStackTrace().toString());
    }

    private DeviceConfigTelemetryLogger setParamName(String paramName) {
        this.mEvent.setExtra("param_name", paramName);
        return this;
    }

    private DeviceConfigTelemetryLogger setParam(ValueType valueType, String paramName) {
        this.mEvent.setExtra("valueType", valueType.toString().toLowerCase(Locale.US));
        this.mEvent.setExtra("param_name", paramName);
        return this;
    }

    private DeviceConfigTelemetryLogger setValue(Object value) {
        if (value != null) {
            this.mEvent.setExtra("value", value);
        }
        return this;
    }

    private void log() {
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(this.mEvent, false);
    }

    public static void logSubscriptionSuccess(Context context, long latency) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_SUCCESS).setLatency(latency).log();
        BLog.d(TAG, "onSuccess()");
    }

    public static void logSubscriptionFailure(Context context, Exception e) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_FAILURE).setErrorAsException(e).log();
        BLog.e(TAG, "subscribe() threw an exception", e);
    }

    public static void logSubscriptionFailure(Context context, String errorMsg) {
        create(context).setSubEvent(SubEvent.SUBSCRIBE_FAILURE).setErrorMessage(errorMsg).log();
        BLog.e(TAG, "onFailure(%s)", errorMsg);
    }

    public static void logGetValueSuccess(Context context, ValueType valueType, String paramName, Object value, String source, long latency) {
        create(context).setSubEvent(SubEvent.GET_VALUE_SUCCESS).setParam(valueType, paramName).setValue(value).setSource(source).setLatency(latency).log();
        BLog.d(TAG, "Get %s %s=%s Source: %s", valueType, paramName, value, source);
    }

    public static void logGetValueFailure(Context context, ValueType valueType, String paramName, Exception e) {
        create(context).setSubEvent(SubEvent.GET_VALUE_SUCCESS).setParam(valueType, paramName).setErrorAsException(e).log();
        BLog.e(TAG, "get%s(%s) threw an exception", valueType, paramName, e);
    }

    public static void logNotSubscribedError(Context context, String errorMessage) {
        create(context).setSubEvent(SubEvent.NOT_SUBSCRIBED).setErrorMessage(errorMessage).log();
        BLog.e(TAG, errorMessage);
    }

    public static void logUnknownParam(Context context, String errorMessage, String paramNames) {
        create(context).setSubEvent(SubEvent.UNKNOWN_PARAM).setErrorMessage(errorMessage).setParamName(paramNames).log();
        BLog.e(TAG, "%s: %s", errorMessage, paramNames);
    }

    public static void logIncorrectTypeParam(Context context, String errorMessage, String paramNames) {
        create(context).setSubEvent(SubEvent.INCORRECT_TYPE_PARAM).setErrorMessage(errorMessage).setParamName(paramNames).log();
        BLog.e(TAG, "%s: %s", errorMessage, paramNames);
    }

    public static void logIncorrectDefaultValue(Context context, String errorMessage, String paramNames) {
        create(context).setSubEvent(SubEvent.INCORRECT_DEFAULT_VALUE).setErrorMessage(errorMessage).setParamName(paramNames).log();
        BLog.e(TAG, "%s: %s", errorMessage, paramNames);
    }

    public static void logMissingDefaultFile(Context context, String errorMessage, String defaultFileName) {
        create(context).setSubEvent(SubEvent.MISSING_DEFAULT_VALUES).setErrorMessage(errorMessage).log();
        BLog.e(TAG, "%s: %s", errorMessage, defaultFileName);
    }

    public static void logUnknownMCType(Context context, int paramType) {
        String errorMessage = String.format("Unknown MC type %s", Integer.valueOf(paramType));
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(errorMessage).log();
        BLog.e(TAG, errorMessage);
    }

    public static void logInternalError(Context context, String errorMessage) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(errorMessage).log();
        BLog.e(TAG, errorMessage);
    }

    public static void logInternalParamError(Context context, String errorMessage, String paramName) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorMessage(errorMessage).setParamName(paramName).log();
        BLog.e(TAG, "%s: %s", errorMessage, paramName);
    }

    public static void logInternalError(Context context, Exception e) {
        create(context).setSubEvent(SubEvent.INTERNAL_ERROR).setErrorAsException(e).log();
        BLog.e(TAG, "threw an exception", e);
    }
}
