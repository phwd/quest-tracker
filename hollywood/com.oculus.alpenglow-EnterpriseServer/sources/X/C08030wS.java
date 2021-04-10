package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0wS  reason: invalid class name and case insensitive filesystem */
public class C08030wS extends BroadcastReceiver {
    public final /* synthetic */ C08120wb A00;

    public C08030wS(C08120wb r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C08120wb r1 = this.A00;
        if (C07950wI.A00(action, r1.A09)) {
            intent.getAction();
            r1.A0D.run();
        }
    }
}
