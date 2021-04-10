package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.0wp  reason: invalid class name and case insensitive filesystem */
public class C08210wp extends BroadcastReceiver {
    public final /* synthetic */ C08200wo A00;

    public C08210wp(C08200wo r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C08200wo r8 = this.A00;
        if (C07950wI.A00(action, r8.A0I)) {
            synchronized (r8) {
                intent.getAction();
                SystemClock.elapsedRealtime();
                boolean z = r8.A0J;
                if (!z) {
                    r8.A0F.A03(r8.A05, r8.A06);
                }
                long elapsedRealtime = SystemClock.elapsedRealtime() + r8.A02;
                if (r8.A03 && r8.A04 >= 23 && z) {
                    r8.A0F.A01(r8.A05, elapsedRealtime, r8.A08);
                }
                if (r8.A00 >= 900000) {
                    r8.A01 = elapsedRealtime;
                    if (r8.A03 && !z) {
                        C08200wo.A01(r8, elapsedRealtime + 20000);
                    }
                    r8.A0N.run();
                }
            }
        }
    }
}
