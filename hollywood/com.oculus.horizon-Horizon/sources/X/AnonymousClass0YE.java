package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.0YE  reason: invalid class name */
public class AnonymousClass0YE extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YF A00;

    public AnonymousClass0YE(AnonymousClass0YF r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass0YF r4 = this.A00;
        if (AnonymousClass0W7.A00(action, r4.A0G)) {
            synchronized (r4) {
                intent.getAction();
                SystemClock.elapsedRealtime();
                long j = r4.A00;
                if (j >= 900000) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime >= r4.A01) {
                        r4.A01 = elapsedRealtime + j;
                        r4.A0F.A04(r4.A05, r4.A08);
                        if (r4.A03) {
                            AnonymousClass0YF.A02(r4, r4.A01, r4.A00);
                            AnonymousClass0YF.A01(r4, r4.A01 + 20000);
                        }
                        r4.A0N.run();
                    }
                }
            }
        }
    }
}
