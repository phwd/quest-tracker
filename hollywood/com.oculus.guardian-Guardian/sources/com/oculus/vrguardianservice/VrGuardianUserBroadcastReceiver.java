package com.oculus.vrguardianservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.guardian.BuildConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class VrGuardianUserBroadcastReceiver extends BroadcastReceiver {
    private static final String DUMP_TABLE_ACTION = "com.oculus.vrguardianservice.DUMP_TABLE";
    private static final String[] DYNAMIC_COMPONENTS = {"com.oculus.vrguardianservice.VrGuardianService", "com.oculus.vrguardianservice.VrGuardianUserService"};
    private static Class<?> SystemPropertiesCLASS = null;
    private static final String TAG = "VrGuardianUserBroadcastReceiver";

    private static String getSystemPropString(String key, String defaultValue) {
        if (SystemPropertiesCLASS == null) {
            try {
                SystemPropertiesCLASS = Class.forName("android.os.SystemProperties");
            } catch (ClassNotFoundException e) {
            }
        }
        try {
            return (String) SystemPropertiesCLASS.getMethod(MobileConfigServiceConstants.PATH_GET, String.class, String.class).invoke(null, key, defaultValue);
        } catch (Exception e2) {
            Log.d(TAG, "getString() returning default " + defaultValue);
            return defaultValue;
        }
    }

    private void setComponentsEnabled(Context context, int state) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        for (String componentName : DYNAMIC_COMPONENTS) {
            setComponentEnabled(packageManager, new ComponentName(packageName, componentName), state);
        }
    }

    private void setComponentEnabled(PackageManager packageManager, ComponentName component, int state) {
        int componentState = packageManager.getComponentEnabledSetting(component);
        if (componentState != state) {
            if (componentState != 0 || state != 1) {
                Log.d(TAG, "Guardian set component state " + state + " for " + component);
                packageManager.setComponentEnabledSetting(component, state, 1);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive " + intent);
        if (DUMP_TABLE_ACTION.equals(intent.getAction())) {
            Log.i(TAG, "dumpReferenceTables...");
            try {
                Class cls = Class.forName("android.os.Debug");
                Method meth = cls.getDeclaredMethod("dumpReferenceTables", new Class[0]);
                Constructor con = cls.getDeclaredConstructor(new Class[0]);
                con.setAccessible(true);
                meth.invoke(con.newInstance(new Object[0]), new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, "dumpReferenceTables error: " + e);
            }
        } else {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    Log.e(TAG, "onReceive - ex: " + key + " : " + bundle.get(key));
                }
            }
            String packageName = context.getPackageName();
            Log.d(TAG, "Guardian re-enabling itself " + packageName);
            setComponentsEnabled(context, 1);
            int userId = intent.getExtras().getInt("android.intent.extra.user_handle", -1);
            Intent guardianServiceIntent = new Intent();
            guardianServiceIntent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.oculus.vrguardianservice.VrGuardianService"));
            context.startService(guardianServiceIntent);
            context.startService(CreateGuardianUserServiceIntent(userId, packageName));
        }
    }

    private Intent CreateGuardianUserServiceIntent(int userId, String packageName) {
        Intent serviceIntent = new Intent(VrGuardianUserService.BOOT);
        serviceIntent.setComponent(new ComponentName(packageName, "com.oculus.vrguardianservice.VrGuardianUserService"));
        serviceIntent.putExtra("android.intent.extra.user_handle", userId);
        return serviceIntent;
    }
}
