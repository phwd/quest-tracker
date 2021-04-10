package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0hn  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04480hn {
    public static volatile int A00;

    public static boolean A00() {
        if (A00 != 0) {
            return false;
        }
        synchronized (AbstractC04480hn.class) {
            if (A00 == 0) {
                A00 = -1;
            }
        }
        return false;
    }

    public AbstractC04480hn() {
        throw null;
    }
}
