package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class PB {
    public static volatile int A00;

    public static boolean A00() {
        if (A00 != 0) {
            return false;
        }
        synchronized (PB.class) {
            if (A00 == 0) {
                A00 = -1;
            }
        }
        return false;
    }

    public PB() {
        throw null;
    }
}
