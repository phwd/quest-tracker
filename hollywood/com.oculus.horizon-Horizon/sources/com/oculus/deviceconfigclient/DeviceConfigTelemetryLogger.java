package com.oculus.deviceconfigclient;

import X.AnonymousClass0NO;
import android.content.Context;
import com.facebook.FacebookRequestError;
import com.facebook.LegacyTokenHelper;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.socialplatform.util.SocialPlatformVersionUtil;
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

    public static void A01(Context context, int i) {
        String format = String.format("Unknown MC type %s", Integer.valueOf(i));
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.INTERNAL_ERROR);
        A0C(deviceConfigTelemetryLogger, format);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A08(TAG, format);
    }

    public static void A00(Context context) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.INCORRECT_TYPE_PARAM);
        A0C(deviceConfigTelemetryLogger, "Param is not a boolean");
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0E(TAG, "%s: %s", "Param is not a boolean", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
    }

    public static void A02(Context context, long j) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.SUBSCRIBE_SUCCESS);
        deviceConfigTelemetryLogger.mEvent.setExtra("latency", Long.valueOf(j));
        A09(deviceConfigTelemetryLogger);
    }

    public static void A03(Context context, ValueType valueType, Exception exc) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.GET_VALUE_SUCCESS);
        deviceConfigTelemetryLogger.mEvent.setExtra(LegacyTokenHelper.JSON_VALUE_TYPE, valueType.toString().toLowerCase(Locale.US));
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
        A0B(deviceConfigTelemetryLogger, exc);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0E(TAG, "get%s(%s) threw an exception", valueType, SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, exc);
    }

    public static void A04(Context context, ValueType valueType, Object obj, @Nullable String str, long j) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.GET_VALUE_SUCCESS);
        deviceConfigTelemetryLogger.mEvent.setExtra(LegacyTokenHelper.JSON_VALUE_TYPE, valueType.toString().toLowerCase(Locale.US));
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
        if (obj != null) {
            deviceConfigTelemetryLogger.mEvent.setExtra("value", obj);
        }
        deviceConfigTelemetryLogger.mEvent.setExtra("source", str);
        deviceConfigTelemetryLogger.mEvent.setExtra("latency", Long.valueOf(j));
        A09(deviceConfigTelemetryLogger);
    }

    public static void A05(Context context, Exception exc) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.INTERNAL_ERROR);
        A0B(deviceConfigTelemetryLogger, exc);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0B(TAG, "threw an exception", exc);
    }

    public static void A06(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.INCORRECT_DEFAULT_VALUE);
        A0C(deviceConfigTelemetryLogger, str);
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, str2);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0E(TAG, "%s: %s", str, str2);
    }

    public static void A07(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.INTERNAL_ERROR);
        A0C(deviceConfigTelemetryLogger, str);
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, str2);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0E(TAG, "%s: %s", str, str2);
    }

    public static void A08(Context context, String str, String str2) {
        DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(context);
        A0A(deviceConfigTelemetryLogger, SubEvent.UNKNOWN_PARAM);
        A0C(deviceConfigTelemetryLogger, str);
        deviceConfigTelemetryLogger.mEvent.setExtra(ConfigStorageCache.PARAM_NAME_JSON_KEY, str2);
        A09(deviceConfigTelemetryLogger);
        AnonymousClass0NO.A0E(TAG, "%s: %s", str, str2);
    }

    public static void A09(DeviceConfigTelemetryLogger deviceConfigTelemetryLogger) {
        UnifiedTelemetryLogger.getInstance(deviceConfigTelemetryLogger.mContext).reportEvent(deviceConfigTelemetryLogger.mEvent, false);
    }

    public static void A0A(DeviceConfigTelemetryLogger deviceConfigTelemetryLogger, SubEvent subEvent) {
        deviceConfigTelemetryLogger.mEvent.setExtra("sub_event", subEvent.toString().toLowerCase(Locale.US));
    }

    public static void A0C(@Nullable DeviceConfigTelemetryLogger deviceConfigTelemetryLogger, String str) {
        if (str != null) {
            deviceConfigTelemetryLogger.mEvent.setExtra(FacebookRequestError.ERROR_MSG_KEY, str);
        }
    }

    public DeviceConfigTelemetryLogger(Context context) {
        this.mContext = context;
    }

    public static void A0B(DeviceConfigTelemetryLogger deviceConfigTelemetryLogger, Exception exc) {
        A0C(deviceConfigTelemetryLogger, exc.getMessage());
        deviceConfigTelemetryLogger.mEvent.setExtra("error_stacktrace", exc.getStackTrace().toString());
    }
}
