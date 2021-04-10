package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.1X6  reason: invalid class name */
public class AnonymousClass1X6 extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass1X5 A00;

    public AnonymousClass1X6(AnonymousClass1X5 r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass1X5 r1 = this.A00;
        if (AnonymousClass1QW.A00(action, r1.A01)) {
            AnonymousClass1X5.A01(r1);
        }
    }
}
