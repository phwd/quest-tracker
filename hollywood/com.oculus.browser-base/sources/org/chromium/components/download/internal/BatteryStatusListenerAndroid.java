package org.chromium.components.download.internal;

import android.content.Intent;
import android.content.IntentFilter;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BatteryStatusListenerAndroid {
    public static int getBatteryPercentage() {
        int intExtra;
        Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null || (intExtra = registerReceiver.getIntExtra("scale", -1)) == 0) {
            return 0;
        }
        return (int) Math.round((((double) registerReceiver.getIntExtra("level", -1)) * 100.0d) / ((double) intExtra));
    }
}
