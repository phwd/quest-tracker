package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* renamed from: X.0YC  reason: invalid class name */
public class AnonymousClass0YC extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YF A00;

    public AnonymousClass0YC(AnonymousClass0YF r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass0YF r5 = this.A00;
        if (AnonymousClass0W7.A00(action, r5.A0H)) {
            synchronized (r5) {
                intent.getAction();
                SystemClock.elapsedRealtime();
                long j = r5.A00;
                if (j < 900000) {
                    long elapsedRealtime = SystemClock.elapsedRealtime() + j;
                    r5.A01 = elapsedRealtime;
                    if (r5.A03) {
                        int i = r5.A04;
                        if (i >= 23 && r5.A0J) {
                            r5.A0F.A03(r5.A05, elapsedRealtime, r5.A07);
                        } else if (i >= 19) {
                            r5.A0F.A01(r5.A05, elapsedRealtime, r5.A07);
                        }
                    }
                    r5.A0N.run();
                }
            }
        }
    }
}
