package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0YP  reason: invalid class name */
public class AnonymousClass0YP extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YZ A00;

    public AnonymousClass0YP(AnonymousClass0YZ r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && AnonymousClass0W7.A00(intent.getAction(), "android.os.action.POWER_SAVE_MODE_CHANGED")) {
            AnonymousClass0YZ.A01(this.A00, intent);
        }
    }
}
