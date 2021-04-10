package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.0YD  reason: invalid class name */
public class AnonymousClass0YD extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YF A00;

    public AnonymousClass0YD(AnonymousClass0YF r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass0YF r8 = this.A00;
        if (AnonymousClass0W7.A00(action, r8.A0I)) {
            synchronized (r8) {
                intent.getAction();
                SystemClock.elapsedRealtime();
                boolean z = r8.A0J;
                if (!z) {
                    r8.A0F.A04(r8.A05, r8.A06);
                }
                long elapsedRealtime = SystemClock.elapsedRealtime() + r8.A02;
                if (r8.A03 && r8.A04 >= 23 && z) {
                    r8.A0F.A02(r8.A05, elapsedRealtime, r8.A08);
                }
                if (r8.A00 >= 900000) {
                    r8.A01 = elapsedRealtime;
                    if (r8.A03 && !z) {
                        AnonymousClass0YF.A01(r8, elapsedRealtime + 20000);
                    }
                    r8.A0N.run();
                }
            }
        }
    }
}
