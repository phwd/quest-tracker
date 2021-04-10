package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0yf  reason: invalid class name and case insensitive filesystem */
public class C09100yf extends BroadcastReceiver {
    public final /* synthetic */ C08290wy A00;

    public C09100yf(C08290wy r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && C07950wI.A00(intent.getAction(), "android.os.action.POWER_SAVE_MODE_CHANGED")) {
            C08290wy.A01(this.A00, intent);
        }
    }
}
