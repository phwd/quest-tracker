package org.chromium.base;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PowerMonitor {

    /* renamed from: a  reason: collision with root package name */
    public static PowerMonitor f10593a;
    public boolean b;

    public static void a() {
        Object obj = ThreadUtils.f10596a;
        if (f10593a == null) {
            Context applicationContext = ContextUtils.getApplicationContext();
            f10593a = new PowerMonitor();
            Intent registerReceiver = applicationContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                boolean z = false;
                if (registerReceiver.getIntExtra("plugged", 0) == 0) {
                    z = true;
                }
                f10593a.b = z;
                N.MCImhGql();
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            applicationContext.registerReceiver(new OE0(), intentFilter);
        }
    }

    public static int getRemainingBatteryCapacity() {
        if (f10593a == null) {
            a();
        }
        return ((BatteryManager) ContextUtils.getApplicationContext().getSystemService("batterymanager")).getIntProperty(1);
    }

    public static boolean isBatteryPower() {
        if (f10593a == null) {
            a();
        }
        return f10593a.b;
    }
}
