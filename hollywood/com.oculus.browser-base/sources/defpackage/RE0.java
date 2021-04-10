package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.PowerManager;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* renamed from: RE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RE0 {

    /* renamed from: a  reason: collision with root package name */
    public static RE0 f8820a;
    public final C1322Vq0 b = new C1322Vq0();
    public final PowerManager c = ((PowerManager) ContextUtils.getApplicationContext().getSystemService("power"));
    public BroadcastReceiver d;
    public boolean e;

    public RE0() {
        b();
        a();
        ApplicationStatus.h.b(new PE0(this));
    }

    public final void a() {
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1 || stateForApplication == 2) {
            if (this.d == null) {
                this.d = new QE0(this);
                ContextUtils.getApplicationContext().registerReceiver(this.d, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"));
            }
            b();
        } else if (this.d != null) {
            ContextUtils.getApplicationContext().unregisterReceiver(this.d);
            this.d = null;
        }
    }

    public final void b() {
        PowerManager powerManager = this.c;
        boolean z = powerManager != null && powerManager.isPowerSaveMode();
        if (z != this.e) {
            this.e = z;
            Iterator it = this.b.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Runnable) uq0.next()).run();
                } else {
                    return;
                }
            }
        }
    }
}
