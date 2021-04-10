package com.oculus.common.deviceconfig;

import X.AnonymousClass006;
import android.content.Context;
import android.util.Log;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceConfigHelper {
    public static final String TAG = LoggingUtil.tag(DeviceConfigHelper.class);
    public static DeviceConfigClient sDeviceConfigClient;
    public static AtomicBoolean sDidSubscribeComplete = new AtomicBoolean(false);
    public static AtomicBoolean sHasSubscribed = new AtomicBoolean(false);
    public static ConcurrentLinkedQueue<DeviceConfigHelperSubscribeCompletedCallback> sOnSubscribeCompleteCallbacks = new ConcurrentLinkedQueue<>();

    public interface DeviceConfigHelperSubscribeCompletedCallback {
        void call();
    }

    public static abstract class GetDeviceBooleanCallback {
        public abstract void onValue(boolean z);
    }

    public static boolean getDeviceBoolean(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getDeviceBoolean(str);
    }

    public static DeviceConfigClient getDeviceConfigInstance(Context context) {
        if (sDeviceConfigClient == null) {
            DeviceConfigClient deviceConfigClient = new DeviceConfigClient(context);
            sDeviceConfigClient = deviceConfigClient;
            deviceConfigClient.subscribe(new DeviceConfigCallback() {
                /* class com.oculus.common.deviceconfig.DeviceConfigHelper.AnonymousClass1 */

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onFailure(String str) {
                    Log.e(DeviceConfigHelper.TAG, String.format("Error while connecting to Mobile Config Service: %s", str));
                    DeviceConfigHelper.sDidSubscribeComplete.set(true);
                    while (DeviceConfigHelper.sOnSubscribeCompleteCallbacks.peek() != null) {
                        DeviceConfigHelper.sOnSubscribeCompleteCallbacks.remove().call();
                    }
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onSuccess() {
                    DeviceConfigHelper.sHasSubscribed.set(true);
                    DeviceConfigHelper.sDidSubscribeComplete.set(true);
                    while (DeviceConfigHelper.sOnSubscribeCompleteCallbacks.peek() != null) {
                        DeviceConfigHelper.sOnSubscribeCompleteCallbacks.remove().call();
                    }
                }
            });
        }
        return sDeviceConfigClient;
    }

    public static void runCallbackOnceSubscribeCompleted(Context context, DeviceConfigHelperSubscribeCompletedCallback deviceConfigHelperSubscribeCompletedCallback) {
        if (sDidSubscribeComplete.get()) {
            deviceConfigHelperSubscribeCompletedCallback.call();
            return;
        }
        sOnSubscribeCompleteCallbacks.add(deviceConfigHelperSubscribeCompletedCallback);
        getDeviceConfigInstance(context);
    }

    public static boolean safeBooleanLookup(Map<String, Boolean> map, String str) {
        return Boolean.TRUE.equals(map.get(str));
    }

    public DeviceConfigHelper() {
        throw new AssertionError();
    }

    public static void checkAndMergeValues(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, Map<String, Boolean> map, boolean z) {
        boolean safeBooleanLookup;
        boolean booleanValue;
        Map<String, Boolean> systemUXParamConfigs = getSystemUXParamConfigs(context);
        int i = 0;
        int i2 = 0;
        for (String str : map.keySet()) {
            if (systemUXParamConfigs.containsKey(str)) {
                boolean safeBooleanLookup2 = safeBooleanLookup(map, str);
                boolean safeBooleanLookup3 = safeBooleanLookup(systemUXParamConfigs, str);
                if (safeBooleanLookup2 != safeBooleanLookup3) {
                    i++;
                    String str2 = TAG;
                    Boolean valueOf = Boolean.valueOf(safeBooleanLookup2);
                    Boolean valueOf2 = Boolean.valueOf(safeBooleanLookup3);
                    Log.e(str2, String.format("Value mismatch between Legacy GK=%s and DeviceConfig=%s for GK '%s'", valueOf, valueOf2, str));
                    AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_device_config_check");
                    analyticsEvent.setExtra("check_name", "value_mismatch");
                    analyticsEvent.setExtra("gk_name", str);
                    analyticsEvent.setExtra("expected_value", valueOf);
                    analyticsEvent.setExtra("actual_value", valueOf2);
                    analyticsEvent.setExtra("failure_count", 1);
                    analyticsEvent.setExtra("is_automation", Boolean.valueOf(z));
                    unifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                } else {
                    i2++;
                }
            } else {
                i++;
                Log.e(TAG, AnonymousClass006.A09("Legacy GK '", str, "' is not in the DeviceConfig schema."));
                AnalyticsEvent analyticsEvent2 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent2.setExtra("check_name", "missing_in_schema");
                analyticsEvent2.setExtra("gk_name", str);
                analyticsEvent2.setExtra("failure_count", 1);
                analyticsEvent2.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent2, false);
            }
        }
        boolean safeBooleanLookup4 = safeBooleanLookup(systemUXParamConfigs, "oculus_systemux_deviceconfig_enabled");
        int i3 = 0;
        int i4 = 0;
        for (Map.Entry<String, Boolean> entry : systemUXParamConfigs.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key)) {
                Boolean valueOf3 = Boolean.valueOf(entry.getValue().booleanValue());
                map.put(key, valueOf3);
                i3++;
                AnalyticsEvent analyticsEvent3 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent3.setExtra("check_name", "value_merged");
                analyticsEvent3.setExtra("gk_name", key);
                analyticsEvent3.setExtra("expected_value", false);
                analyticsEvent3.setExtra("actual_value", valueOf3);
                analyticsEvent3.setExtra("merge_count", 1);
                analyticsEvent3.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent3, false);
            } else if (safeBooleanLookup4 && (safeBooleanLookup = safeBooleanLookup(map, key)) != (booleanValue = entry.getValue().booleanValue())) {
                Boolean valueOf4 = Boolean.valueOf(booleanValue);
                map.put(key, valueOf4);
                i4++;
                Boolean valueOf5 = Boolean.valueOf(safeBooleanLookup);
                AnalyticsEvent analyticsEvent4 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent4.setExtra("check_name", "value_overridden");
                analyticsEvent4.setExtra("gk_name", key);
                analyticsEvent4.setExtra("expected_value", valueOf5);
                analyticsEvent4.setExtra("actual_value", valueOf4);
                analyticsEvent4.setExtra("override_count", 1);
                analyticsEvent4.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent4, false);
            }
        }
        Integer valueOf6 = Integer.valueOf(i2);
        Integer valueOf7 = Integer.valueOf(i);
        Integer valueOf8 = Integer.valueOf(i3);
        Integer valueOf9 = Integer.valueOf(i4);
        String format = String.format("Summary of DeviceConfig check and merge. Successes: %s - Failures: %s - Merged: %s - Overridden: %s", valueOf6, valueOf7, valueOf8, valueOf9);
        int i5 = 3;
        if (i != 0) {
            i5 = 6;
        }
        Log.println(i5, TAG, format);
        AnalyticsEvent analyticsEvent5 = new AnalyticsEvent("oculus_vrshell_device_config_check");
        analyticsEvent5.setExtra("check_name", "summary");
        analyticsEvent5.setExtra("success_count", valueOf6);
        analyticsEvent5.setExtra("failure_count", valueOf7);
        analyticsEvent5.setExtra("merge_count", valueOf8);
        analyticsEvent5.setExtra("override_count", valueOf9);
        analyticsEvent5.setExtra("is_automation", Boolean.valueOf(z));
        unifiedTelemetryLogger.reportEvent(analyticsEvent5, false);
    }

    public static Map<String, Boolean> getSystemUXParamConfigs(Context context) {
        DeviceConfigClient deviceConfigInstance = getDeviceConfigInstance(context);
        HashMap hashMap = new HashMap();
        for (String str : deviceConfigInstance.mParamsMapEntries.keySet()) {
            if (str.startsWith("oculus_systemux:")) {
                hashMap.put(str.substring(str.indexOf(":") + 1), Boolean.valueOf(deviceConfigInstance.getBoolean(str)));
            }
        }
        return hashMap;
    }

    public static boolean getBoolean(Context context, DeviceConfigMC deviceConfigMC) {
        return getBoolean(context, deviceConfigMC.toString());
    }

    public static boolean getBoolean(Context context, Gatekeeper gatekeeper) {
        return getBoolean(context, gatekeeper.toDeviceConfigString());
    }

    public static boolean getBoolean(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getBoolean(str);
    }

    public static void getDeviceBooleanAsync(Context context, DeviceConfigMC deviceConfigMC, GetDeviceBooleanCallback getDeviceBooleanCallback) {
        getDeviceBooleanAsync(context, deviceConfigMC.toString(), getDeviceBooleanCallback);
    }

    public static void getDeviceBooleanAsync(Context context, String str, GetDeviceBooleanCallback getDeviceBooleanCallback) {
        runCallbackOnceSubscribeCompleted(context, new DeviceConfigHelperSubscribeCompletedCallback(context, str, getDeviceBooleanCallback) {
            /* class com.oculus.common.deviceconfig.$$Lambda$DeviceConfigHelper$59STaK2CGVuWKpVuDsWpZUhlrOs2 */
            public final /* synthetic */ Context f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ DeviceConfigHelper.GetDeviceBooleanCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.common.deviceconfig.DeviceConfigHelper.DeviceConfigHelperSubscribeCompletedCallback
            public final void call() {
                DeviceConfigHelper.lambda$getDeviceBooleanAsync$2(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static double getDouble(Context context, DeviceConfigMC deviceConfigMC) {
        return getDouble(context, deviceConfigMC.toString());
    }

    public static double getDouble(Context context, Gatekeeper gatekeeper) {
        return getDouble(context, gatekeeper.toDeviceConfigString());
    }

    public static double getDouble(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getDouble(str);
    }

    public static long getLong(Context context, DeviceConfigMC deviceConfigMC) {
        return getLong(context, deviceConfigMC.toString());
    }

    public static long getLong(Context context, Gatekeeper gatekeeper) {
        return getLong(context, gatekeeper.toDeviceConfigString());
    }

    public static long getLong(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getLong(str);
    }

    public static String getString(Context context, DeviceConfigMC deviceConfigMC) {
        return getString(context, deviceConfigMC.toString());
    }

    public static String getString(Context context, Gatekeeper gatekeeper) {
        return getString(context, gatekeeper.toDeviceConfigString());
    }

    public static String getString(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getString(str);
    }
}
