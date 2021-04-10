package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.1XO  reason: invalid class name */
public class AnonymousClass1XO extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass22H A00;

    public AnonymousClass1XO(AnonymousClass22H r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            intent.getAction();
            AnonymousClass22H.A02(this.A00, intent);
        }
    }
}
