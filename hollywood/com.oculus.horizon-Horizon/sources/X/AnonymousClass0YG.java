package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0YG  reason: invalid class name */
public class AnonymousClass0YG extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YH A00;

    public AnonymousClass0YG(AnonymousClass0YH r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass0YH r1 = this.A00;
        if (AnonymousClass0W7.A00(action, r1.A09)) {
            intent.getAction();
            r1.A0D.run();
        }
    }
}
