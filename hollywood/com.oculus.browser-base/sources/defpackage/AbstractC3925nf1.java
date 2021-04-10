package defpackage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* renamed from: nf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3925nf1 {
    public static Object a(OI1 oi1) {
        boolean z;
        SE0.h("Must not be called on the main application thread");
        SE0.i(oi1, "Task must not be null");
        synchronized (oi1.f8618a) {
            z = oi1.c;
        }
        if (z) {
            return b(oi1);
        }
        C3583lf1 lf1 = new C3583lf1(null);
        Executor executor = AbstractC0928Pe1.b;
        oi1.b.a(new RH1(executor, lf1));
        oi1.e();
        oi1.b.a(new C4723sH1(executor, lf1));
        oi1.e();
        oi1.b.a(new C5400wG1(executor, lf1));
        oi1.e();
        lf1.f10362a.await();
        return b(oi1);
    }

    public static Object b(OI1 oi1) {
        if (oi1.d()) {
            return oi1.c();
        }
        throw new ExecutionException(oi1.b());
    }
}
