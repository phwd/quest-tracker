package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.1R5  reason: invalid class name */
public class AnonymousClass1R5 extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass1R4 A00;

    public AnonymousClass1R5(AnonymousClass1R4 r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass1R4 r1 = this.A00;
        if (AnonymousClass1QW.A00(action, r1.A09)) {
            intent.getAction();
            r1.A0D.run();
        }
    }
}
