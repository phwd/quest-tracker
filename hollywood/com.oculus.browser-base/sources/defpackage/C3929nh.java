package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.BatteryManager;
import org.chromium.base.ContextUtils;

/* renamed from: nh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3929nh {

    /* renamed from: a  reason: collision with root package name */
    public final C1481Yg f10508a;
    public final IntentFilter b = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    public final BroadcastReceiver c = new C3416kh(this);
    public C3758mh d;
    public boolean e;

    public C3929nh(C1481Yg yg) {
        C3758mh mhVar = new C3758mh((BatteryManager) ContextUtils.getApplicationContext().getSystemService("batterymanager"));
        this.f10508a = yg;
        this.d = mhVar;
    }
}
