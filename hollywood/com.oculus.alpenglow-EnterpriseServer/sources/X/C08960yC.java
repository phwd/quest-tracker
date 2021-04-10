package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0yC  reason: invalid class name and case insensitive filesystem */
public class C08960yC extends BroadcastReceiver {
    public final /* synthetic */ ExecutorServiceC08580xU A00;

    public C08960yC(ExecutorServiceC08580xU r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ExecutorServiceC08580xU r1 = this.A00;
        if (C07950wI.A00(action, r1.A01)) {
            ExecutorServiceC08580xU.A00(r1);
        }
    }
}
