package com.oculus.companion.server;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.util.Log;

public final class CompanionDeviceAdmin {
    private static final String TAG = "CompanionDeviceAdmin";

    public static void setActive(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        ComponentName componentName = new ComponentName(CompanionDeviceAdminReceiver.class.getPackage().getName(), CompanionDeviceAdminReceiver.class.getName());
        if (!devicePolicyManager.isAdminActive(componentName)) {
            Log.i(TAG, "Activating admin...");
            devicePolicyManager.setActiveAdmin(componentName, false);
        }
    }

    public static void wipeData(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        try {
            ((StorageManager) context.getSystemService(StorageManager.class)).destroyUserKey(UserHandle.getCallingUserId());
            devicePolicyManager.wipeData(0);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Failed to wipe data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static class CompanionDeviceAdminReceiver extends DeviceAdminReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED") {
                abortBroadcast();
                try {
                    CompanionService.horizonLogout(context, UserHandle.SYSTEM);
                } catch (InterruptedException unused) {
                    Log.d(CompanionDeviceAdmin.TAG, "Logging out interrupted");
                }
            }
            super.onReceive(context, intent);
        }

        public CharSequence onDisableRequested(Context context, Intent intent) {
            return context.getString(R.string.admin_receiver_status_disable_warning);
        }
    }
}
