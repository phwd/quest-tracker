package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.1WY  reason: invalid class name */
public class AnonymousClass1WY extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass1WV A00;

    public AnonymousClass1WY(AnonymousClass1WV r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass1WV r4 = this.A00;
        if (AnonymousClass1QW.A00(action, r4.A0G)) {
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
                            AnonymousClass1WV.A02(r4, r4.A01, r4.A00);
                            AnonymousClass1WV.A01(r4, r4.A01 + 20000);
                        }
                        r4.A0N.run();
                    }
                }
            }
        }
    }
}
