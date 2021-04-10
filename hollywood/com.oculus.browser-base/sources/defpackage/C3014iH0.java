package defpackage;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: iH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3014iH0 implements AbstractC5481wn0 {
    public final AbstractC2672gH0 F;
    public final AtomicInteger G = new AtomicInteger(0);
    public final AtomicBoolean H = new AtomicBoolean(false);

    public C3014iH0(AbstractC2672gH0 gh0) {
        this.F = gh0;
    }

    @Override // defpackage.AbstractC5481wn0
    public void a(int i) {
        if (NetworkChangeNotifier.c()) {
            NetworkChangeNotifier.j(this);
            b();
        }
    }

    public final void b() {
        Object obj = ThreadUtils.f10596a;
        this.H.set(false);
        C2843hH0 hh0 = new C2843hH0(this);
        Executor executor = AbstractC2032cb.f9616a;
        hh0.f();
        ((ExecutorC1463Ya) executor).execute(hh0.e);
    }
}
