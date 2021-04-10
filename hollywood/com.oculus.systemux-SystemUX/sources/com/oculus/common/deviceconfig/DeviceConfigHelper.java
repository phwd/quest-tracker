package com.oculus.common.deviceconfig;

import android.content.Context;
import android.util.Log;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceConfigHelper {
    private static final String TAG = LoggingUtil.tag(DeviceConfigHelper.class);
    private static DeviceConfigClient sDeviceConfigClient = null;
    private static AtomicBoolean sDidSubscribeComplete = new AtomicBoolean(false);
    private static AtomicBoolean sHasSubscribed = new AtomicBoolean(false);
    private static ConcurrentLinkedQueue<DeviceConfigHelperSubscribeCompletedCallback> sOnSubscribeCompleteCallbacks = new ConcurrentLinkedQueue<>();

    public interface DeviceConfigHelperSubscribeCompletedCallback {
        void call();
    }

    private DeviceConfigHelper() {
        throw new AssertionError();
    }

    public static DeviceConfigClient getDeviceConfigInstance(Context context) {
        if (sDeviceConfigClient == null) {
            Log.v(TAG, "Creating a new instance of DeviceConfigClient");
            sDeviceConfigClient = new DeviceConfigClient(context);
            sDeviceConfigClient.subscribe(new DeviceConfigCallback() {
                /* class com.oculus.common.deviceconfig.DeviceConfigHelper.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onSuccess() {
                    DeviceConfigHelper.sHasSubscribed.set(true);
                    DeviceConfigHelper.sDidSubscribeComplete.set(true);
                    while (DeviceConfigHelper.sOnSubscribeCompleteCallbacks.peek() != null) {
                        ((DeviceConfigHelperSubscribeCompletedCallback) DeviceConfigHelper.sOnSubscribeCompleteCallbacks.remove()).call();
                    }
                }

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onFailure(String str) {
                    Log.e(DeviceConfigHelper.TAG, String.format("Error while connecting to Mobile Config Service: %s", str));
                    DeviceConfigHelper.sDidSubscribeComplete.set(true);
                    while (DeviceConfigHelper.sOnSubscribeCompleteCallbacks.peek() != null) {
                        ((DeviceConfigHelperSubscribeCompletedCallback) DeviceConfigHelper.sOnSubscribeCompleteCallbacks.remove()).call();
                    }
                }
            });
        }
        return sDeviceConfigClient;
    }

    public static void runCallbackOnceSubscribeCompleted(DeviceConfigHelperSubscribeCompletedCallback deviceConfigHelperSubscribeCompletedCallback) {
        if (sDidSubscribeComplete.get()) {
            deviceConfigHelperSubscribeCompletedCallback.call();
        } else {
            sOnSubscribeCompleteCallbacks.add(deviceConfigHelperSubscribeCompletedCallback);
        }
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

    public static boolean getDeviceBoolean(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getDeviceBoolean(str);
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

    public static long getLong(Context context, Gatekeeper gatekeeper) {
        return getLong(context, gatekeeper.toDeviceConfigString());
    }

    public static long getLong(Context context, DeviceConfigMC deviceConfigMC) {
        return getLong(context, deviceConfigMC.toString());
    }

    public static long getLong(Context context, String str) {
        if (!sHasSubscribed.get()) {
            Log.w(TAG, String.format("Try to get config param \"%s\" before Device Config has subscribed to the service.", str));
        }
        return getDeviceConfigInstance(context).getLong(str);
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

    public static void checkAndMergeValues(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, Map<String, Boolean> map, boolean z) {
        String str;
        boolean z2;
        int i;
        int i2;
        boolean safeBooleanLookup;
        boolean booleanValue;
        Map<String, Boolean> map2;
        Iterator<String> it;
        int i3;
        Map<String, Boolean> map3 = map;
        Map<String, Boolean> systemUXParamConfigs = getSystemUXParamConfigs(context);
        Iterator<String> it2 = map.keySet().iterator();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            str = "failure_count";
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            if (systemUXParamConfigs.containsKey(next)) {
                boolean safeBooleanLookup2 = safeBooleanLookup(map3, next);
                boolean safeBooleanLookup3 = safeBooleanLookup(systemUXParamConfigs, next);
                if (safeBooleanLookup2 != safeBooleanLookup3) {
                    it = it2;
                    i3 = i4 + 1;
                    map2 = systemUXParamConfigs;
                    Log.e(TAG, String.format("Value mismatch between Legacy GK=%s and DeviceConfig=%s for GK '%s'", Boolean.valueOf(safeBooleanLookup2), Boolean.valueOf(safeBooleanLookup3), next));
                    AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_device_config_check");
                    analyticsEvent.setExtra("check_name", "value_mismatch");
                    analyticsEvent.setExtra("gk_name", next);
                    analyticsEvent.setExtra("expected_value", Boolean.valueOf(safeBooleanLookup2));
                    analyticsEvent.setExtra("actual_value", Boolean.valueOf(safeBooleanLookup3));
                    analyticsEvent.setExtra(str, 1);
                    analyticsEvent.setExtra("is_automation", Boolean.valueOf(z));
                    unifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                } else {
                    map2 = systemUXParamConfigs;
                    it = it2;
                    i5++;
                    i3 = i4;
                }
                i4 = i3;
            } else {
                map2 = systemUXParamConfigs;
                it = it2;
                i4++;
                String str2 = TAG;
                Log.e(str2, "Legacy GK '" + next + "' is not in the DeviceConfig schema.");
                AnalyticsEvent analyticsEvent2 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent2.setExtra("check_name", "missing_in_schema");
                analyticsEvent2.setExtra("gk_name", next);
                analyticsEvent2.setExtra(str, 1);
                analyticsEvent2.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent2, false);
            }
            it2 = it;
            systemUXParamConfigs = map2;
        }
        boolean safeBooleanLookup4 = safeBooleanLookup(map3, "oculus_systemux_deviceconfig_enabled");
        Iterator<Map.Entry<String, Boolean>> it3 = systemUXParamConfigs.entrySet().iterator();
        int i6 = 0;
        int i7 = 0;
        while (it3.hasNext()) {
            Map.Entry<String, Boolean> next2 = it3.next();
            String key = next2.getKey();
            if (!map3.containsKey(key)) {
                boolean booleanValue2 = next2.getValue().booleanValue();
                map3.put(key, Boolean.valueOf(booleanValue2));
                String str3 = TAG;
                i = i5;
                StringBuilder sb = new StringBuilder();
                i2 = i7;
                sb.append("Merged '");
                sb.append(key);
                sb.append("' from DeviceConfig schema.");
                Log.d(str3, sb.toString());
                i6++;
                AnalyticsEvent analyticsEvent3 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent3.setExtra("check_name", "value_merged");
                analyticsEvent3.setExtra("gk_name", key);
                analyticsEvent3.setExtra("expected_value", false);
                analyticsEvent3.setExtra("actual_value", Boolean.valueOf(booleanValue2));
                analyticsEvent3.setExtra("merge_count", 1);
                analyticsEvent3.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent3, false);
                z2 = safeBooleanLookup4;
            } else {
                i = i5;
                i2 = i7;
                if (!safeBooleanLookup4 || (safeBooleanLookup = safeBooleanLookup(map3, key)) == (booleanValue = next2.getValue().booleanValue())) {
                    z2 = safeBooleanLookup4;
                } else {
                    map3.put(key, Boolean.valueOf(booleanValue));
                    i7 = i2 + 1;
                    z2 = safeBooleanLookup4;
                    Log.d(TAG, String.format("Value from Legacy GK=%s overridden by DeviceConfig=%s for GK '%s'", Boolean.valueOf(safeBooleanLookup), Boolean.valueOf(booleanValue), key));
                    AnalyticsEvent analyticsEvent4 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                    analyticsEvent4.setExtra("check_name", "value_overridden");
                    analyticsEvent4.setExtra("gk_name", key);
                    analyticsEvent4.setExtra("expected_value", Boolean.valueOf(safeBooleanLookup));
                    analyticsEvent4.setExtra("actual_value", Boolean.valueOf(booleanValue));
                    analyticsEvent4.setExtra("override_count", 1);
                    analyticsEvent4.setExtra("is_automation", Boolean.valueOf(z));
                    unifiedTelemetryLogger.reportEvent(analyticsEvent4, false);
                    map3 = map;
                    str = str;
                    i4 = i4;
                    i5 = i;
                    it3 = it3;
                    safeBooleanLookup4 = z2;
                }
            }
            i7 = i2;
            map3 = map;
            str = str;
            i4 = i4;
            i5 = i;
            it3 = it3;
            safeBooleanLookup4 = z2;
        }
        Log.println(i4 != 0 ? 6 : 3, TAG, String.format("Summary of DeviceConfig check and merge. Successes: %s - Failures: %s - Merged: %s - Overridden: %s", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i6), Integer.valueOf(i7)));
        AnalyticsEvent analyticsEvent5 = new AnalyticsEvent("oculus_vrshell_device_config_check");
        analyticsEvent5.setExtra("check_name", "summary");
        analyticsEvent5.setExtra("success_count", Integer.valueOf(i5));
        analyticsEvent5.setExtra(str, Integer.valueOf(i4));
        analyticsEvent5.setExtra("merge_count", Integer.valueOf(i6));
        analyticsEvent5.setExtra("override_count", Integer.valueOf(i7));
        analyticsEvent5.setExtra("is_automation", Boolean.valueOf(z));
        unifiedTelemetryLogger.reportEvent(analyticsEvent5, false);
    }

    private static boolean safeBooleanLookup(Map<String, Boolean> map, String str) {
        return Boolean.TRUE.equals(map.get(str));
    }

    private static Map<String, Boolean> getSystemUXParamConfigs(Context context) {
        DeviceConfigClient deviceConfigInstance = getDeviceConfigInstance(context);
        String debugOnlyGetMemorySnapshot = deviceConfigInstance.debugOnlyGetMemorySnapshot("");
        Map<String, Object> debugOnlyGetParamsDefaults = deviceConfigInstance.debugOnlyGetParamsDefaults();
        String[] split = debugOnlyGetMemorySnapshot.split("\n");
        HashMap hashMap = new HashMap();
        for (String str : split) {
            String[] split2 = str.split(":");
            if (split2.length >= 2) {
                String str2 = split2[0];
                if (str2.equals("oculus_systemux")) {
                    String str3 = str2 + ":" + split2[1];
                    if (debugOnlyGetParamsDefaults.containsKey(str3)) {
                        hashMap.put(split2[1], Boolean.valueOf(deviceConfigInstance.getBoolean(str3)));
                    }
                }
            }
        }
        return hashMap;
    }
}
