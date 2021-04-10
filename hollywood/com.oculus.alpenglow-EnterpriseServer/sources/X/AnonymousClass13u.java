package X;

import androidx.annotation.GuardedBy;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
/* renamed from: X.13u  reason: invalid class name */
public final class AnonymousClass13u extends AnonymousClass139 {
    @GuardedBy("sLock")
    public static AnonymousClass13u A00;
    public static final Object A01 = new Object();

    public AnonymousClass13u() {
        super(null);
    }

    public static synchronized AnonymousClass13u A00() {
        AnonymousClass13u r0;
        synchronized (AnonymousClass13u.class) {
            r0 = A00;
            if (r0 == null) {
                synchronized (A01) {
                    r0 = A00;
                    if (r0 == null) {
                        r0 = new AnonymousClass13u();
                        A00 = r0;
                    }
                }
            }
        }
        return r0;
    }
}
