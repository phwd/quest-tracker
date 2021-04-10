package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0ys  reason: invalid class name and case insensitive filesystem */
public class C09220ys extends BroadcastReceiver {
    public final /* synthetic */ C08290wy A00;

    public C09220ys(C08290wy r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            intent.getAction();
            C08290wy.A01(this.A00, intent);
        }
    }
}
