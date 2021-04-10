package X;

import java.util.concurrent.Callable;

/* renamed from: X.1nf  reason: invalid class name */
public class AnonymousClass1nf implements Callable<Void> {
    public final /* synthetic */ C10521nZ A00;

    public AnonymousClass1nf(C10521nZ r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Void call() throws Exception {
        C10521nZ r2 = this.A00;
        synchronized (r2) {
            if (r2.A02 != null) {
                C10521nZ.A00(r2);
                if (C10521nZ.A06(r2)) {
                    C10521nZ.A01(r2);
                    r2.A00 = 0;
                }
            }
        }
        return null;
    }
}
