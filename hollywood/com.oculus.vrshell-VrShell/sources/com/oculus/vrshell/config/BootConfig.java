package com.oculus.vrshell.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.PreferencesManager;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vrshell.util.AndroidSystemProperties;
import com.oculus.vrshell.util.PreferenceStringHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class BootConfig {
    private static final String CONFIG_KEY_AUTOMATION_SESSION = "automationSession";
    private static final String CONFIG_KEY_DEFAULT_APP = "defaultApp";
    private static final String CONFIG_KEY_DEVICE_SERIAL = "deviceSerial";
    private static final String CONFIG_KEY_OVERRIDE_GK_MAP = "overrideGKs";
    private static final String CONFIG_KEY_SERVER_GK_MAP = "serverGKs";
    private static final String CONFIG_KEY_SHELL_CONFIG = "com.oculus.vrshell";
    private static final String DEVICE_CONFIG_ENABLED = "oculus_vrshell_deviceconfig_enabled";
    private static final String GK_DISABLE_IAP_IN_OVERLAY = "oculus_vrshell_iap_in_overlay_disable";
    private static final String GK_DOGFOOD_ELIGIBLE = "oculus_quest_dogfooding_update";
    private static final String GK_FORCE_APPLICATION_FOCUS_AWARENESS = "oculus_vrshell_force_focus_awareness_experiment";
    private static final String GK_SHELL_ENV = "oc_shellenv_as_default";
    private static final String PREF_OVERRIDE_GATEKEEPERS = "Override_Gatekeepers";
    private static final String PREF_SERVER_GATEKEEPERS = "Gatekeepers";
    private static final String PREF_VALUE_ENABLED = "1";
    private static final String TAG = LoggingUtil.tag(BootConfig.class);

    public static class BootConfigResult {
        public String bootConfiguration = "";
        public String deviceSerial = "";
        public boolean isAutomationEnabled = false;
        public boolean isDisableIapInOverlayEnabled = false;
        public boolean isEligibleForDogfood = false;
        public boolean isForceApplicationFocusAwarenessEnabled = false;
        public boolean isShellEnvEnabled = false;
        public int versionCode = -1;
        public String versionName = "";
    }

    public enum CachedGKsType {
        ServerGKs,
        OverrideGKs
    }

    public static BootConfigResult getBootConfig(Context context) {
        JSONObject jSONObject;
        DeviceConfigHolder.initialize(context);
        PreferencesManager preferencesManager = new PreferencesManager();
        BootConfigResult bootConfigResult = new BootConfigResult();
        String systemPropertyString = AndroidSystemProperties.getSystemPropertyString("persist.ovr.automationconfig", null);
        bootConfigResult.isAutomationEnabled = !TextUtils.isEmpty(systemPropertyString);
        if (bootConfigResult.isAutomationEnabled) {
            jSONObject = parseBootConfigFile(systemPropertyString);
        } else {
            bootConfigResult.isAutomationEnabled = PREF_VALUE_ENABLED.equals(AndroidSystemProperties.getSystemPropertyString("persist.ovr.automation", null));
            jSONObject = createSystemBootConfig(context, preferencesManager);
        }
        try {
            jSONObject.put(CONFIG_KEY_DEVICE_SERIAL, Build.SERIAL);
            JSONObject optJSONObject = jSONObject.optJSONObject("com.oculus.vrshell");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                jSONObject.put("com.oculus.vrshell", optJSONObject);
            }
            Map<String, Boolean> serverGKMap = getServerGKMap(context);
            optJSONObject.put(CONFIG_KEY_SERVER_GK_MAP, convertGKMapToJSON(serverGKMap));
            bootConfigResult.isShellEnvEnabled = serverGKMap.getOrDefault(GK_SHELL_ENV, Boolean.valueOf(bootConfigResult.isShellEnvEnabled)).booleanValue();
            bootConfigResult.isDisableIapInOverlayEnabled = serverGKMap.getOrDefault(GK_DISABLE_IAP_IN_OVERLAY, Boolean.valueOf(bootConfigResult.isDisableIapInOverlayEnabled)).booleanValue();
            bootConfigResult.isForceApplicationFocusAwarenessEnabled = serverGKMap.getOrDefault(GK_FORCE_APPLICATION_FOCUS_AWARENESS, Boolean.valueOf(bootConfigResult.isForceApplicationFocusAwarenessEnabled)).booleanValue();
            bootConfigResult.isEligibleForDogfood = serverGKMap.getOrDefault(GK_DOGFOOD_ELIGIBLE, Boolean.valueOf(bootConfigResult.isEligibleForDogfood)).booleanValue();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(CONFIG_KEY_OVERRIDE_GK_MAP);
            if (optJSONObject2 != null) {
                bootConfigResult.isShellEnvEnabled = optJSONObject2.optBoolean(GK_SHELL_ENV, bootConfigResult.isShellEnvEnabled);
                bootConfigResult.isDisableIapInOverlayEnabled = optJSONObject2.optBoolean(GK_DISABLE_IAP_IN_OVERLAY, bootConfigResult.isDisableIapInOverlayEnabled);
                bootConfigResult.isForceApplicationFocusAwarenessEnabled = optJSONObject2.optBoolean(GK_FORCE_APPLICATION_FOCUS_AWARENESS, bootConfigResult.isForceApplicationFocusAwarenessEnabled);
            }
        } catch (JSONException e) {
            Log.w(TAG, "Failure applying serverGKMap to boot configuration.", e);
        }
        getApplicationConfig(context, bootConfigResult);
        bootConfigResult.deviceSerial = Build.SERIAL;
        bootConfigResult.bootConfiguration = jSONObject.toString();
        return bootConfigResult;
    }

    private static void getApplicationConfig(Context context, BootConfigResult bootConfigResult) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            bootConfigResult.versionName = packageInfo.versionName;
            bootConfigResult.versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static JSONObject createSystemBootConfig(Context context, PreferencesManager preferencesManager) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            Pair string = preferencesManager.getString("default_shell_application");
            if (((Boolean) string.first).booleanValue() && !TextUtils.isEmpty((CharSequence) string.second)) {
                jSONObject2.put(CONFIG_KEY_DEFAULT_APP, string.second);
            }
            jSONObject2.put(CONFIG_KEY_OVERRIDE_GK_MAP, convertGKMapToJSON(getOverrideGKMap(context)));
            jSONObject.put(CONFIG_KEY_AUTOMATION_SESSION, UUID.randomUUID());
            jSONObject.put("com.oculus.vrshell", jSONObject2);
        } catch (JSONException e) {
            Log.w(TAG, "Failed to encode JSON configuration for automation.", e);
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r4 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        r3.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject parseBootConfigFile(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 133
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.config.BootConfig.parseBootConfigFile(java.lang.String):org.json.JSONObject");
    }

    public static void cacheServerGKs(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, boolean z, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            hashMap.put(next, Boolean.valueOf(jSONObject2.getBoolean("isEnabled")));
            if (jSONObject2.getBoolean("isSystemShell")) {
                hashSet.add(next);
            }
        }
        checkAndMergeValuesFromDeviceConfig(context, unifiedTelemetryLogger, z, hashSet, hashMap);
        PreferenceStringHelper.setPreferenceString(context, PREF_SERVER_GATEKEEPERS, convertGKMapToStorage(hashMap));
    }

    public static void cacheOverrideGK(Context context, String str, boolean z) {
        Log.i(TAG, String.format("Setting Override gatekeeper %s to %b", str, Boolean.valueOf(z)));
        Map<String, Boolean> overrideGKMap = getOverrideGKMap(context);
        overrideGKMap.put(str, Boolean.valueOf(z));
        PreferenceStringHelper.setPreferenceString(context, PREF_OVERRIDE_GATEKEEPERS, convertGKMapToStorage(overrideGKMap));
    }

    public static void clearOverrideGK(Context context, String str) {
        Log.i(TAG, String.format("Clearing Override gatekeeper %s", str));
        Map<String, Boolean> overrideGKMap = getOverrideGKMap(context);
        overrideGKMap.remove(str);
        PreferenceStringHelper.setPreferenceString(context, PREF_OVERRIDE_GATEKEEPERS, convertGKMapToStorage(overrideGKMap));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrshell.config.BootConfig$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$config$BootConfig$CachedGKsType = new int[CachedGKsType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.vrshell.config.BootConfig$CachedGKsType[] r0 = com.oculus.vrshell.config.BootConfig.CachedGKsType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrshell.config.BootConfig.AnonymousClass1.$SwitchMap$com$oculus$vrshell$config$BootConfig$CachedGKsType = r0
                int[] r0 = com.oculus.vrshell.config.BootConfig.AnonymousClass1.$SwitchMap$com$oculus$vrshell$config$BootConfig$CachedGKsType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrshell.config.BootConfig$CachedGKsType r1 = com.oculus.vrshell.config.BootConfig.CachedGKsType.ServerGKs     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrshell.config.BootConfig.AnonymousClass1.$SwitchMap$com$oculus$vrshell$config$BootConfig$CachedGKsType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrshell.config.BootConfig$CachedGKsType r1 = com.oculus.vrshell.config.BootConfig.CachedGKsType.OverrideGKs     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.config.BootConfig.AnonymousClass1.<clinit>():void");
        }
    }

    public static void clearCachedGKs(Context context, CachedGKsType cachedGKsType) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrshell$config$BootConfig$CachedGKsType[cachedGKsType.ordinal()];
        if (i == 1) {
            Log.i(TAG, String.format("Clear Gatekeepers cache in %s", PREF_SERVER_GATEKEEPERS));
            PreferenceStringHelper.setPreferenceString(context, PREF_SERVER_GATEKEEPERS, "");
        } else if (i == 2) {
            Log.i(TAG, String.format("Clear Gatekeepers cache in %s", PREF_OVERRIDE_GATEKEEPERS));
            PreferenceStringHelper.setPreferenceString(context, PREF_OVERRIDE_GATEKEEPERS, "");
        }
    }

    private static Map<String, Boolean> getServerGKMap(Context context) {
        return parseGKMap(PreferenceStringHelper.getPreferenceString(context, PREF_SERVER_GATEKEEPERS), PREF_SERVER_GATEKEEPERS);
    }

    private static Map<String, Boolean> getOverrideGKMap(Context context) {
        return parseGKMap(PreferenceStringHelper.getPreferenceString(context, PREF_OVERRIDE_GATEKEEPERS), PREF_OVERRIDE_GATEKEEPERS);
    }

    private static Map<String, Boolean> parseGKMap(String str, String str2) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, Boolean.valueOf(jSONObject.optBoolean(next, false)));
            }
        } catch (JSONException unused) {
            String[] split = str.split("\n");
            for (String str3 : split) {
                if (!TextUtils.isEmpty(str3)) {
                    String[] split2 = str3.split(":");
                    if (split2.length == 2) {
                        hashMap.put(split2[1], Boolean.valueOf("true".equals(split2[0])));
                    } else {
                        Log.w(TAG, String.format("Invalid GK %s found in storage %s", str3, str2));
                    }
                }
            }
        }
        return hashMap;
    }

    private static String convertGKMapToStorage(Map<String, Boolean> map) {
        try {
            return convertGKMapToJSON(map).toString();
        } catch (JSONException unused) {
            return "{}";
        }
    }

    private static JSONObject convertGKMapToJSON(Map<String, Boolean> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    private static boolean safeBooleanLookup(Map<String, Boolean> map, String str) {
        return Boolean.TRUE.equals(map.get(str));
    }

    private static void checkAndMergeValuesFromDeviceConfig(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, boolean z, Set<String> set, Map<String, Boolean> map) {
        String str;
        String str2;
        int i;
        boolean z2;
        int i2;
        int i3;
        boolean safeBooleanLookup;
        boolean booleanValue;
        int i4;
        Iterator<String> it;
        Map<String, Boolean> map2;
        int i5;
        Map<String, Boolean> map3 = map;
        Map<String, Boolean> overrideGKMap = getOverrideGKMap(context);
        Map<String, Boolean> systemShellParamConfigs = DeviceConfigHolder.getSystemShellParamConfigs();
        Iterator<String> it2 = set.iterator();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            str = "legacy_override_count";
            str2 = "failure_count";
            i = i8;
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            if (overrideGKMap.containsKey(next)) {
                i6++;
                AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent.setExtra("check_name", "overridden_gk");
                analyticsEvent.setExtra("gk_name", next);
                analyticsEvent.setExtra(str, 1);
                analyticsEvent.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                i8 = i;
            } else {
                if (systemShellParamConfigs.containsKey(next)) {
                    boolean safeBooleanLookup2 = safeBooleanLookup(map3, next);
                    map2 = overrideGKMap;
                    boolean safeBooleanLookup3 = safeBooleanLookup(systemShellParamConfigs, next);
                    if (safeBooleanLookup2 != safeBooleanLookup3) {
                        it = it2;
                        i5 = i7 + 1;
                        i4 = i6;
                        Log.e(TAG, String.format("Value mismatch between Legacy GK=%s and DeviceConfig=%s for GK '%s'", Boolean.valueOf(safeBooleanLookup2), Boolean.valueOf(safeBooleanLookup3), next));
                        AnalyticsEvent analyticsEvent2 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                        analyticsEvent2.setExtra("check_name", "value_mismatch");
                        analyticsEvent2.setExtra("gk_name", next);
                        analyticsEvent2.setExtra("expected_value", Boolean.valueOf(safeBooleanLookup2));
                        analyticsEvent2.setExtra("actual_value", Boolean.valueOf(safeBooleanLookup3));
                        analyticsEvent2.setExtra(str2, 1);
                        analyticsEvent2.setExtra("is_automation", Boolean.valueOf(z));
                        unifiedTelemetryLogger.reportEvent(analyticsEvent2, false);
                    } else {
                        it = it2;
                        i4 = i6;
                        i5 = i7;
                        i++;
                    }
                    i8 = i;
                    i7 = i5;
                } else {
                    map2 = overrideGKMap;
                    it = it2;
                    i4 = i6;
                    i7++;
                    String str3 = TAG;
                    Log.e(str3, "Legacy GK '" + next + "' is not in the DeviceConfig schema.");
                    AnalyticsEvent analyticsEvent3 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                    analyticsEvent3.setExtra("check_name", "missing_in_schema");
                    analyticsEvent3.setExtra("gk_name", next);
                    analyticsEvent3.setExtra(str2, 1);
                    analyticsEvent3.setExtra("is_automation", Boolean.valueOf(z));
                    unifiedTelemetryLogger.reportEvent(analyticsEvent3, false);
                    i8 = i;
                }
                overrideGKMap = map2;
                it2 = it;
                i6 = i4;
            }
        }
        boolean safeBooleanLookup4 = safeBooleanLookup(map3, DEVICE_CONFIG_ENABLED);
        Iterator<Map.Entry<String, Boolean>> it3 = systemShellParamConfigs.entrySet().iterator();
        int i9 = 0;
        int i10 = 0;
        while (it3.hasNext()) {
            Map.Entry<String, Boolean> next2 = it3.next();
            String key = next2.getKey();
            if (!map3.containsKey(key)) {
                boolean booleanValue2 = next2.getValue().booleanValue();
                map3.put(key, Boolean.valueOf(booleanValue2));
                String str4 = TAG;
                i2 = i7;
                StringBuilder sb = new StringBuilder();
                i3 = i10;
                sb.append("Merged '");
                sb.append(key);
                sb.append("' from DeviceConfig schema.");
                Log.d(str4, sb.toString());
                i9++;
                AnalyticsEvent analyticsEvent4 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                analyticsEvent4.setExtra("check_name", "value_merged");
                analyticsEvent4.setExtra("gk_name", key);
                analyticsEvent4.setExtra("expected_value", false);
                analyticsEvent4.setExtra("actual_value", Boolean.valueOf(booleanValue2));
                analyticsEvent4.setExtra("merge_count", 1);
                analyticsEvent4.setExtra("is_automation", Boolean.valueOf(z));
                unifiedTelemetryLogger.reportEvent(analyticsEvent4, false);
                z2 = safeBooleanLookup4;
            } else {
                i3 = i10;
                i2 = i7;
                if (!safeBooleanLookup4 || (safeBooleanLookup = safeBooleanLookup(map3, key)) == (booleanValue = next2.getValue().booleanValue())) {
                    z2 = safeBooleanLookup4;
                } else {
                    map3.put(key, Boolean.valueOf(booleanValue));
                    z2 = safeBooleanLookup4;
                    Log.d(TAG, String.format("Value from Legacy GK=%s overridden by DeviceConfig=%s for GK '%s'", Boolean.valueOf(safeBooleanLookup), Boolean.valueOf(booleanValue), key));
                    AnalyticsEvent analyticsEvent5 = new AnalyticsEvent("oculus_vrshell_device_config_check");
                    analyticsEvent5.setExtra("check_name", "value_overridden");
                    analyticsEvent5.setExtra("gk_name", key);
                    analyticsEvent5.setExtra("expected_value", Boolean.valueOf(safeBooleanLookup));
                    analyticsEvent5.setExtra("actual_value", Boolean.valueOf(booleanValue));
                    analyticsEvent5.setExtra("override_count", 1);
                    analyticsEvent5.setExtra("is_automation", Boolean.valueOf(z));
                    unifiedTelemetryLogger.reportEvent(analyticsEvent5, false);
                    i10 = i3 + 1;
                    map3 = map;
                    str = str;
                    str2 = str2;
                    i7 = i2;
                    it3 = it3;
                    safeBooleanLookup4 = z2;
                }
            }
            i10 = i3;
            map3 = map;
            str = str;
            str2 = str2;
            i7 = i2;
            it3 = it3;
            safeBooleanLookup4 = z2;
        }
        Log.println(i7 != 0 ? 6 : 3, TAG, String.format("Summary of DeviceConfig check and merge. Successes: %s - Failures: %s - Merged: %s - Overridden: %s", Integer.valueOf(i), Integer.valueOf(i7), Integer.valueOf(i9), Integer.valueOf(i10)));
        AnalyticsEvent analyticsEvent6 = new AnalyticsEvent("oculus_vrshell_device_config_check");
        analyticsEvent6.setExtra("check_name", "summary");
        analyticsEvent6.setExtra("success_count", Integer.valueOf(i));
        analyticsEvent6.setExtra(str2, Integer.valueOf(i7));
        analyticsEvent6.setExtra("merge_count", Integer.valueOf(i9));
        analyticsEvent6.setExtra("override_count", Integer.valueOf(i10));
        analyticsEvent6.setExtra(str, Integer.valueOf(i6));
        analyticsEvent6.setExtra("is_automation", Boolean.valueOf(z));
        unifiedTelemetryLogger.reportEvent(analyticsEvent6, false);
    }
}
