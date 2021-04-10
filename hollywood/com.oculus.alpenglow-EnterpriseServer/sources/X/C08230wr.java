package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.0wr  reason: invalid class name and case insensitive filesystem */
public class C08230wr extends BroadcastReceiver {
    public final /* synthetic */ C08200wo A00;

    public C08230wr(C08200wo r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C08200wo r4 = this.A00;
        if (C07950wI.A00(action, r4.A0G)) {
            synchronized (r4) {
                intent.getAction();
                SystemClock.elapsedRealtime();
                long j = r4.A00;
                if (j >= 900000) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime >= r4.A01) {
                        r4.A01 = elapsedRealtime + j;
                        r4.A0F.A03(r4.A05, r4.A08);
                        if (r4.A03) {
                            C08200wo.A02(r4, r4.A01, r4.A00);
                            C08200wo.A01(r4, r4.A01 + 20000);
                        }
                        r4.A0N.run();
                    }
                }
            }
        }
    }
}
