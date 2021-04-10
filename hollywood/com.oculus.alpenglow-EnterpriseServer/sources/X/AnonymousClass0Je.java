package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Je  reason: invalid class name */
public final class AnonymousClass0Je {
    @GuardedBy("this")
    public Map<Object, AnonymousClass0Jd> A00 = new HashMap();

    public static synchronized void A00(AnonymousClass0Je r2, Object obj) {
        synchronized (r2) {
            AnonymousClass0Jd r1 = r2.A00.get(obj);
            AnonymousClass0Q1.A00(r1);
            AnonymousClass0Jd r12 = r1;
            int i = r12.A00 - 1;
            r12.A00 = i;
            if (i == 0) {
                r2.A00.remove(obj);
            }
        }
    }

    public final synchronized void A01(Object obj) {
        AnonymousClass0Jd r0 = this.A00.get(obj);
        AnonymousClass0Q1.A00(r0);
        Semaphore semaphore = r0.A01;
        if (semaphore.availablePermits() == 0) {
            semaphore.release();
            A00(this, obj);
        } else {
            throw new AssertionError();
        }
    }
}
