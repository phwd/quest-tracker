package X;

import android.util.SparseIntArray;

/* renamed from: X.1C6  reason: invalid class name */
public abstract class AnonymousClass1C6 {
    public final SparseIntArray A00 = new SparseIntArray();
    public final SparseIntArray A01 = new SparseIntArray();

    public final int A00(int i, int i2) {
        if (this instanceof AnonymousClass1CE) {
            return i % i2;
        }
        if (1 != i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                i3++;
                if (i3 == i2) {
                    i3 = 0;
                } else if (i3 > i2) {
                    i3 = 1;
                }
            }
            if (1 + i3 <= i2) {
                return i3;
            }
        }
        return 0;
    }
}
