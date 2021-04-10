package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.device.time_zone_monitor.TimeZoneMonitor;

/* renamed from: Ci1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0148Ci1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TimeZoneMonitor f7832a;

    public C0148Ci1(TimeZoneMonitor timeZoneMonitor) {
        this.f7832a = timeZoneMonitor;
    }

    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
            AbstractC1220Ua0.a("TimeZoneMonitor", "unexpected intent", new Object[0]);
            return;
        }
        TimeZoneMonitor timeZoneMonitor = this.f7832a;
        N.MjxIGcDd(timeZoneMonitor.c, timeZoneMonitor);
    }
}
