package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0YQ  reason: invalid class name */
public class AnonymousClass0YQ extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0YZ A00;

    public AnonymousClass0YQ(AnonymousClass0YZ r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            intent.getAction();
            AnonymousClass0YZ.A01(this.A00, intent);
        }
    }
}
