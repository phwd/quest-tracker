package com.oculus.alpenglow.android;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;

public class DevicePolicyManagerWrapper {
    public static void A00(DevicePolicyManager devicePolicyManager, ComponentName componentName) {
        devicePolicyManager.setActiveAdmin(componentName, true);
    }
}
