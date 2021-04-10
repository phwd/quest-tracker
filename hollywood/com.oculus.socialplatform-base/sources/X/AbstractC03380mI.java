package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mI  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03380mI {
    public static volatile int A00;

    public static boolean A00() {
        if (A00 != 0) {
            return false;
        }
        synchronized (AbstractC03380mI.class) {
            if (A00 == 0) {
                A00 = -1;
            }
        }
        return false;
    }

    public AbstractC03380mI() {
        throw null;
    }
}
