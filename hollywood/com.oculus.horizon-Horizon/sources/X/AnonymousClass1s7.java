package X;

import android.util.SparseIntArray;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1s7  reason: invalid class name */
public final class AnonymousClass1s7 {
    public static final SparseIntArray A00 = new SparseIntArray(0);

    public static C10471su A00() {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            i = (min >> 2) * 3;
        } else {
            i = min >> 1;
        }
        return new C10471su(0, i, A00, -1);
    }
}
