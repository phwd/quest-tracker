package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;

/* renamed from: X.1XP  reason: invalid class name */
public class AnonymousClass1XP extends BroadcastReceiver {
    public final /* synthetic */ RealtimeSinceBootClock A00;
    public final /* synthetic */ AnonymousClass1XQ A01;

    public AnonymousClass1XP(AnonymousClass1XQ r1, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A01 = r1;
        this.A00 = realtimeSinceBootClock;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) || "android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            Boolean valueOf = Boolean.valueOf("android.intent.action.SCREEN_ON".equals(intent.getAction()));
            AnonymousClass1XQ r1 = this.A01;
            if (!valueOf.equals(r1.A04.getAndSet(valueOf))) {
                r1.A03.set(SystemClock.elapsedRealtime());
                throw new NullPointerException("notify");
            }
        }
    }
}
