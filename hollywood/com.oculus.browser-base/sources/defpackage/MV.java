package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: MV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MV implements Handler.Callback {
    public final LV F;
    public final ArrayList G = new ArrayList();
    public final ArrayList H = new ArrayList();
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public volatile boolean f8478J = false;
    public final AtomicInteger K = new AtomicInteger(0);
    public boolean L = false;
    public final Handler M;
    public final Object N = new Object();

    public MV(Looper looper, LV lv) {
        this.F = lv;
        this.M = new ZB1(looper, this);
    }

    public final void a() {
        this.f8478J = false;
        this.K.incrementAndGet();
    }

    public final void b(XV xv) {
        Objects.requireNonNull(xv, "null reference");
        synchronized (this.N) {
            if (this.I.contains(xv)) {
                String valueOf = String.valueOf(xv);
                StringBuilder sb = new StringBuilder(valueOf.length() + 67);
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.I.add(xv);
            }
        }
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            WV wv = (WV) message.obj;
            synchronized (this.N) {
                if (this.f8478J && this.F.a() && this.G.contains(wv)) {
                    wv.f(this.F.b());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", AbstractC2531fV.s(45, "Don't know how to handle message: ", i), new Exception());
        return false;
    }
}
