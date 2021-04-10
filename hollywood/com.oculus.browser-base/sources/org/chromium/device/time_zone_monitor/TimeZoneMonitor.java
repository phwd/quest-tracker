package org.chromium.device.time_zone_monitor;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TimeZoneMonitor {

    /* renamed from: a  reason: collision with root package name */
    public final IntentFilter f10960a;
    public final BroadcastReceiver b;
    public long c;

    public TimeZoneMonitor(long j) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
        this.f10960a = intentFilter;
        C0148Ci1 ci1 = new C0148Ci1(this);
        this.b = ci1;
        this.c = j;
        ContextUtils.getApplicationContext().registerReceiver(ci1, intentFilter);
    }

    public static TimeZoneMonitor getInstance(long j) {
        return new TimeZoneMonitor(j);
    }

    public void stop() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.b);
        this.c = 0;
    }
}
