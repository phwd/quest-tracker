package com.oculus.vrapi;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.oculus.systemdriver.BuildConfig;

public class VrApi {
    public static final String TAG = "VrApi";

    public static String getSystemDriverVersion(Context context) {
        PackageManager packageMgr = context.getApplicationContext().getPackageManager();
        if (packageMgr == null) {
            return "<none>";
        }
        try {
            PackageInfo packageInfo = packageMgr.getPackageInfo(BuildConfig.APPLICATION_ID, 0);
            String versionName = packageInfo.versionName;
            int i = packageInfo.versionCode;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "<none>";
        }
    }

    public static String getApplicationName(Context context) {
        return context.getApplicationContext().getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
    }

    public static String getApplicationVersion(Context context) {
        String versionName = "<none>";
        String internalVersionName = "<none>";
        int versionCode = 0;
        PackageManager packageMgr = context.getApplicationContext().getPackageManager();
        if (packageMgr != null) {
            try {
                PackageInfo packageInfo = packageMgr.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
                versionName = packageInfo.versionName;
                versionCode = packageInfo.versionCode;
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
                if (appInfo != null) {
                    Log.d(TAG, "targetSDKVersion " + appInfo.targetSdkVersion);
                    if (appInfo.metaData != null) {
                        internalVersionName = appInfo.metaData.getString("internalVersionName", "<none>");
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                versionName = "<none>";
                versionCode = 0;
            }
        }
        return versionName + " versionCode " + versionCode + " internalVersionName " + internalVersionName;
    }

    public static String getApplicationModeType(Context context) {
        Intent mainIntent = new Intent("android.intent.action.MAIN", (Uri) null);
        mainIntent.addCategory("com.oculus.intent.category.VR");
        mainIntent.addFlags(32);
        mainIntent.setPackage(context.getPackageName());
        PackageManager packageMgr = context.getApplicationContext().getPackageManager();
        if (packageMgr == null || packageMgr.queryIntentActivities(mainIntent, 32).isEmpty()) {
            return "<none>";
        }
        return "VR";
    }

    public static String getApplicationVrType(Context context) {
        if (context.getApplicationContext().getPackageManager() == null) {
            return "<none>";
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (appInfo == null || appInfo.metaData == null) {
                return "<none>";
            }
            return appInfo.metaData.getString("com.samsung.android.vr.application.mode", "<none>");
        } catch (PackageManager.NameNotFoundException e) {
            return "<none>";
        }
    }

    public static boolean isActivityFocusAware(Context context) {
        boolean passesFocusAwarenessPushRolloutGk = GatekeeperHelper.checkVrShellFocusAwarenessPushRollout(context);
        String packageName = context.getApplicationContext().getPackageName();
        ComponentName componentName = null;
        Log.d(TAG, "isActivityFocusAware context = " + context);
        if (context instanceof Activity) {
            componentName = ((Activity) context).getComponentName();
        }
        return FocusAwarenessUtil.isActivityFocusAware(context, packageName, componentName, passesFocusAwarenessPushRolloutGk);
    }

    public static boolean doesActivitySupport120Hz(Context context) {
        Log.d(TAG, "doesActivitySupport120Hz context = " + context);
        String packageName = context.getApplicationContext().getPackageName();
        ComponentName componentName = null;
        if (context instanceof Activity) {
            componentName = ((Activity) context).getComponentName();
        }
        for (Integer refreshRate : ExtraRefreshRatesUtil.getActivityExtraRefreshRates(context, packageName, componentName)) {
            if (refreshRate.intValue() == 120) {
                return true;
            }
        }
        return false;
    }

    public static String getApplicationColorspace(Context context) {
        if (context.getApplicationContext().getPackageManager() == null) {
            return "<none>";
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (appInfo == null || appInfo.metaData == null) {
                return "<none>";
            }
            return appInfo.metaData.getString("com.oculus.application.colorspace", "<none>");
        } catch (PackageManager.NameNotFoundException e) {
            return "<none>";
        }
    }

    public static long getAvailableMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.availMem / 1048576;
    }

    public static long getThresholdMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.threshold / 1048576;
    }

    public static long getTotalMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.totalMem / 1048576;
    }

    public static Debug.MemoryInfo getForegroundAppMemoryInfo(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()})[0];
    }

    public static void setActivityWindowFullscreen(Context context) {
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Can't set FLAG_FULLSCREEN without an Activity");
            return;
        }
        final Activity act = (Activity) context;
        act.runOnUiThread(new Runnable() {
            /* class com.oculus.vrapi.VrApi.AnonymousClass1 */

            public void run() {
                Log.d(VrApi.TAG, "getWindow().addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN )");
                act.getWindow().addFlags(1024);
            }
        });
    }

    public static boolean isWifiConnected(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
        } catch (Exception e) {
            Log.d(TAG, "VrApi::isWifiConnected exception: Make sure android.permission.ACCESS_NETWORK_STATE is set in the manifest.");
            return false;
        }
    }

    public static boolean getBluetoothEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "bluetooth_on", 0) != 0;
    }

    public static boolean isAirplaneModeEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public static boolean isDeveloperModeEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) != 0;
    }

    public static String getSystemProperty(String propertyTypeName) {
        return SystemProps.getString(propertyTypeName, "");
    }

    public static boolean getHmtMounted(Context context) {
        return SystemProps.getBool("sys.hmt.mounted", true);
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static long getBatteryCurrent(Context context) {
        return Math.abs(((BatteryManager) context.getSystemService("batterymanager")).getLongProperty(2));
    }

    public static long getBatteryVoltage(Context context) {
        return (long) context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("voltage", -1);
    }

    public static int[] getDisplayDimensions(Context context, int displayId) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        return new int[]{display.getWidth(), display.getHeight()};
    }
}
