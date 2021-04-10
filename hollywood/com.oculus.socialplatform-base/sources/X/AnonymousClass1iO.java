package X;

import android.util.SparseIntArray;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1iO  reason: invalid class name */
public final class AnonymousClass1iO {
    public static final SparseIntArray A00 = new SparseIntArray(0);

    public static AnonymousClass1i0 A00() {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            i = (min >> 2) * 3;
        } else {
            i = min >> 1;
        }
        return new AnonymousClass1i0(0, i, A00, -1);
    }
}
