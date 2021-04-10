package com.oculus.deviceconfigclient.shared;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Locale;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigTelemetryLogger {
    private static final String TAG = "DeviceConfigTelemetryLogger";
    private final Context mContext;
    private final AnalyticsEvent mEvent = new AnalyticsEvent("oculus_device_config_event");

    enum ClientSubEvent {
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

    private DeviceConfigTelemetryLogger setClientSubEvent(ClientSubEvent clientSubEvent) {
        this.mEvent.setExtra("sub_event", clientSubEvent.toString().toLowerCase(Locale.US));
        return this;
    }

    private DeviceConfigTelemetryLogger setLatency(long j) {
        this.mEvent.setExtra("latency", Long.valueOf(j));
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorMessage(String str) {
        if (str != null) {
            this.mEvent.setExtra("error_msg", str);
        }
        return this;
    }

    private DeviceConfigTelemetryLogger setErrorAsException(Exception exc) {
        DeviceConfigTelemetryLogger errorMessage = setErrorMessage(exc.getMessage());
        errorMessage.mEvent.setExtra("error_stacktrace", exc.getStackTrace().toString());
        return errorMessage;
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

    private void log() {
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(this.mEvent, false);
    }

    public static void logSubscriptionSuccess(Context context, long j) {
        create(context).setClientSubEvent(ClientSubEvent.SUBSCRIBE_SUCCESS).setLatency(j).log();
        BLog.d(TAG, "onSuccess()");
    }

    public static void logSubscriptionFailure(Context context, Exception exc) {
        create(context).setClientSubEvent(ClientSubEvent.SUBSCRIBE_FAILURE).setErrorAsException(exc).log();
        BLog.e(TAG, "subscribe() threw an exception", exc);
    }

    public static void logSubscriptionFailure(Context context, String str) {
        create(context).setClientSubEvent(ClientSubEvent.SUBSCRIBE_FAILURE).setErrorMessage(str).log();
        BLog.e(TAG, "onFailure(%s)", str);
    }

    public static void logGetValueSuccess(Context context, ValueType valueType, String str, Object obj, String str2, long j) {
        DeviceConfigTelemetryLogger param = create(context).setClientSubEvent(ClientSubEvent.GET_VALUE_SUCCESS).setParam(valueType, str);
        if (obj != null) {
            param.mEvent.setExtra("value", obj);
        }
        param.mEvent.setExtra("source", str2);
        param.setLatency(j).log();
        BLog.d(TAG, "Get %s %s=%s Source: %s", valueType, str, obj, str2);
    }

    public static void logGetValueFailure(Context context, ValueType valueType, String str, Exception exc) {
        create(context).setClientSubEvent(ClientSubEvent.GET_VALUE_FAILURE).setParam(valueType, str).setErrorAsException(exc).log();
        BLog.e(TAG, "get%s(%s) threw an exception", valueType, str, exc);
    }

    public static void logNotSubscribedError(Context context, String str) {
        create(context).setClientSubEvent(ClientSubEvent.NOT_SUBSCRIBED).setErrorMessage(str).log();
        BLog.e(TAG, str);
    }

    public static void logUnknownParam(Context context, String str, String str2) {
        create(context).setClientSubEvent(ClientSubEvent.UNKNOWN_PARAM).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logIncorrectTypeParam(Context context, String str, String str2) {
        create(context).setClientSubEvent(ClientSubEvent.INCORRECT_TYPE_PARAM).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logIncorrectDefaultValue(Context context, String str, String str2) {
        create(context).setClientSubEvent(ClientSubEvent.INCORRECT_DEFAULT_VALUE).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logMissingDefaultFile(Context context, String str, String str2) {
        create(context).setClientSubEvent(ClientSubEvent.MISSING_DEFAULT_VALUES).setErrorMessage(str).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logUnknownMCType(Context context, int i) {
        String format = String.format("Unknown MC type %s", Integer.valueOf(i));
        create(context).setClientSubEvent(ClientSubEvent.INTERNAL_ERROR).setErrorMessage(format).log();
        BLog.e(TAG, format);
    }

    public static void logInternalError(Context context, String str) {
        create(context).setClientSubEvent(ClientSubEvent.INTERNAL_ERROR).setErrorMessage(str).log();
        BLog.e(TAG, str);
    }

    public static void logInternalParamError(Context context, String str, String str2) {
        create(context).setClientSubEvent(ClientSubEvent.INTERNAL_ERROR).setErrorMessage(str).setParamName(str2).log();
        BLog.e(TAG, "%s: %s", str, str2);
    }

    public static void logInternalError(Context context, Exception exc) {
        create(context).setClientSubEvent(ClientSubEvent.INTERNAL_ERROR).setErrorAsException(exc).log();
        BLog.e(TAG, "threw an exception", exc);
    }
}
