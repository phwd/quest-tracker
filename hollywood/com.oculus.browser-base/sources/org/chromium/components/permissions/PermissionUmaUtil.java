package org.chromium.components.permissions;

import android.content.Intent;
import android.content.IntentFilter;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionUmaUtil {
    public static void recordWithBatteryBucket(String str) {
        Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra2 != 0) {
            AbstractC3364kK0.g(str, (int) ((((double) intExtra) * 100.0d) / ((double) intExtra2)), 101);
        }
    }
}
