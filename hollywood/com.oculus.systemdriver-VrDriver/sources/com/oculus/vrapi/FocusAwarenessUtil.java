package com.oculus.vrapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class FocusAwarenessUtil {
    private static final String TAG = "FocusAwarenessUtil";

    public static boolean isActivityFocusAware(Context context, String packageName, ComponentName componentName, boolean passesFocusAwarenessPushRolloutGk) {
        String str;
        String str2;
        Log.d(TAG, "isActivityFocusAware");
        Log.d(TAG, "isActivityFocusAware passesFocusAwarenessPushRolloutGk = " + passesFocusAwarenessPushRolloutGk);
        PackageManager packageMgr = context.getPackageManager();
        if (packageMgr == null) {
            Log.e(TAG, "isActivityFocusAware with unavailable getPackageManager. Returning true as default");
            return true;
        }
        boolean isSystemApp = false;
        try {
            isSystemApp = isSystemApp(packageMgr.getApplicationInfo(packageName, 0));
            Log.d(TAG, "isSystemApp = " + isSystemApp + " for " + packageName);
        } catch (Exception e) {
            Log.w(TAG, "isActivityFocusAware failed in system app check with exception: " + e);
        }
        boolean manifestEntryExists = false;
        boolean isFocusAware = false;
        if (componentName != null) {
            Log.d(TAG, "componentName non null : " + componentName.flattenToString());
            try {
                ActivityInfo activityInfo = packageMgr.getActivityInfo(componentName, 129);
                if (!(activityInfo == null || activityInfo.metaData == null)) {
                    Log.d(TAG, "Checking activity manifest entry");
                    if (activityInfo.metaData.containsKey("com.oculus.vr.focusaware")) {
                        isFocusAware = activityInfo.metaData.getBoolean("com.oculus.vr.focusaware", false);
                        manifestEntryExists = true;
                    }
                }
            } catch (Exception e2) {
                Log.w(TAG, "isActivityFocusAware failed in activity check with exception: " + e2);
                isFocusAware = false;
            }
        }
        String str3 = "ENABLED";
        if (manifestEntryExists) {
            StringBuilder sb = new StringBuilder();
            sb.append("Activity manifest entry exists and = ");
            if (isFocusAware) {
                str2 = str3;
            } else {
                str2 = "DISABLED";
            }
            sb.append(str2);
            sb.append(" for ");
            sb.append(packageName);
            Log.d(TAG, sb.toString());
        } else {
            Log.d(TAG, "No Activity manifest entry for " + packageName);
        }
        if (!isSystemApp) {
            int sysProp = SystemProps.getInt("persist.oculus.forceShellOverlay", 0);
            if (sysProp == 0) {
                sysProp = SystemProps.getInt("debug.oculus.forceShellOverlay", 0);
            }
            if (sysProp == 1) {
                Log.d(TAG, "isActivityFocusAware ENABLED due to system prop");
                return true;
            } else if (sysProp == -1) {
                Log.d(TAG, "isActivityFocusAware DISABLED due to system prop");
                return false;
            }
        }
        if (!manifestEntryExists) {
            Log.d(TAG, "No activity manifest entry, checking application manifest entry");
            try {
                ApplicationInfo applicationInfo = packageMgr.getApplicationInfo(packageName, 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("com.oculus.vr.focusaware"))) {
                    isFocusAware = applicationInfo.metaData.getBoolean("com.oculus.vr.focusaware", false);
                    manifestEntryExists = true;
                }
            } catch (Exception e3) {
                Log.w(TAG, "isActivityFocusAware failed in application check with exception: " + e3);
                isFocusAware = false;
            }
            if (manifestEntryExists) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Application manifest entry exists and = ");
                if (isFocusAware) {
                    str = str3;
                } else {
                    str = "DISABLED";
                }
                sb2.append(str);
                sb2.append(" for ");
                sb2.append(packageName);
                Log.d(TAG, sb2.toString());
            } else {
                Log.d(TAG, "No Application manifest entry for " + packageName);
            }
        }
        if (!passesFocusAwarenessPushRolloutGk) {
            return isFocusAware;
        }
        if (isSystemApp) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("isSystemApp so returning manifest value for ");
            sb3.append(packageName);
            sb3.append(" as ");
            if (!isFocusAware) {
                str3 = "DISABLED";
            }
            sb3.append(str3);
            Log.d(TAG, sb3.toString());
            return isFocusAware;
        }
        FocusAwarenessCompatibilityMode focusAwarenessCompatibility = FocusAwarenessCompatibilityMode.fromString(PublicLibraryUtil.getAppDataFromPublicLibrary(context, packageName, "system_ui_as_overlay_mode", FocusAwarenessCompatibilityMode.UNSET.toString()), FocusAwarenessCompatibilityMode.UNSET);
        if (focusAwarenessCompatibility == FocusAwarenessCompatibilityMode.UNSET) {
            Log.d(TAG, "No Store entry available. Likely sideload for " + packageName);
        } else {
            Log.d(TAG, "Store entry exists and = " + focusAwarenessCompatibility.toString() + " for " + packageName);
        }
        if (manifestEntryExists) {
            if (focusAwarenessCompatibility == FocusAwarenessCompatibilityMode.FORCE_ENABLED) {
                Log.d(TAG, "Overriding manifest entry with store entry = FORCE_ENABLED");
                isFocusAware = true;
            } else if (focusAwarenessCompatibility == FocusAwarenessCompatibilityMode.FORCE_DISABLED) {
                Log.d(TAG, "Overriding manifest entry with store entry = FORCE_DISABLED");
                isFocusAware = false;
            } else {
                Log.d(TAG, "Using Manifest value");
            }
        } else if (focusAwarenessCompatibility == FocusAwarenessCompatibilityMode.ENABLED) {
            Log.d(TAG, "No manifest entry so falling back to store entry = ENABLED");
            isFocusAware = true;
        } else if (focusAwarenessCompatibility == FocusAwarenessCompatibilityMode.DISABLED) {
            Log.d(TAG, "No manifest entry so falling back to store entry = DISABLED");
            isFocusAware = false;
        } else {
            Log.d(TAG, "No Manifest Entry and no Store Entry. Defaulting to ENABLED for " + packageName);
            isFocusAware = true;
        }
        Log.d(TAG, "isFocusAware  = " + isFocusAware + " for " + packageName);
        return isFocusAware;
    }

    private static boolean isSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 129) != 0;
    }
}
