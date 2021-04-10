package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0jm  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05530jm {
    public static volatile int A00;

    public AbstractC05530jm() {
        throw null;
    }

    public static boolean A00() {
        if (A00 != 0) {
            return false;
        }
        synchronized (AbstractC05530jm.class) {
            if (A00 == 0) {
                A00 = -1;
            }
        }
        return false;
    }
}
