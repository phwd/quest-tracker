package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.1XN  reason: invalid class name */
public class AnonymousClass1XN extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass22H A00;

    public AnonymousClass1XN(AnonymousClass22H r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && AnonymousClass1QW.A00(intent.getAction(), "android.os.action.POWER_SAVE_MODE_CHANGED")) {
            AnonymousClass22H.A02(this.A00, intent);
        }
    }
}
