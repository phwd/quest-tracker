package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Objects;

/* renamed from: gB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC2655gB1 extends ZB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2313eB1 f9985a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC2655gB1(C2313eB1 eb1, Looper looper) {
        super(looper);
        this.f9985a = eb1;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            AbstractC2143dB1 db1 = (AbstractC2143dB1) message.obj;
            C2313eB1 eb1 = this.f9985a;
            Objects.requireNonNull(db1);
            eb1.f9838a.lock();
            try {
                if (eb1.k == db1.f9755a) {
                    db1.a();
                    eb1.f9838a.unlock();
                }
            } finally {
                eb1.f9838a.unlock();
            }
        } else if (i != 2) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i);
            Log.w("GACStateManager", sb.toString());
        } else {
            throw ((RuntimeException) message.obj);
        }
    }
}
