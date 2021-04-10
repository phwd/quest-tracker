package defpackage;

import android.os.BatteryManager;

/* renamed from: mh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3758mh {

    /* renamed from: a  reason: collision with root package name */
    public final BatteryManager f10439a;

    public C3758mh(BatteryManager batteryManager) {
        this.f10439a = batteryManager;
    }

    public int a(int i) {
        return this.f10439a.getIntProperty(i);
    }
}
