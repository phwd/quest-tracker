package X;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20G  reason: invalid class name */
public final class AnonymousClass20G extends AtomicReference<Throwable> {
    public static final long serialVersionUID = 3949248817947090603L;

    public final Throwable A00() {
        Throwable th = (Throwable) get();
        Throwable th2 = C12301xE.A00;
        if (th != th2) {
            return (Throwable) getAndSet(th2);
        }
        return th;
    }

    public final boolean A01(Throwable th) {
        Object obj;
        Throwable r1;
        do {
            obj = get();
            if (obj == C12301xE.A00) {
                return false;
            }
            if (obj == null) {
                r1 = th;
            } else {
                r1 = new AnonymousClass1Ox(Arrays.asList((Throwable) obj, th));
            }
        } while (!compareAndSet(obj, r1));
        return true;
    }
}
