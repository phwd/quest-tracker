package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0XC  reason: invalid class name */
public class AnonymousClass0XC extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass08r A00;

    public AnonymousClass0XC(AnonymousClass08r r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AnonymousClass08r r1 = this.A00;
        if (AnonymousClass0W7.A00(action, r1.A01)) {
            AnonymousClass08r.A00(r1);
        }
    }
}
