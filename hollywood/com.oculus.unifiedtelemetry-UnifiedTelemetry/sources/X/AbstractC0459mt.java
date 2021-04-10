package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.mt  reason: case insensitive filesystem */
public abstract class AbstractC0459mt {
    public static volatile int A00;

    public static boolean A00() {
        if (A00 != 0) {
            return false;
        }
        synchronized (AbstractC0459mt.class) {
            if (A00 == 0) {
                A00 = -1;
            }
        }
        return false;
    }

    public AbstractC0459mt() {
        throw null;
    }
}
