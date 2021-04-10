package X;

import com.google.common.collect.ImmutableCollection;

public abstract class Tx {
    public abstract Tx add(Object obj);

    public abstract ImmutableCollection build();

    public static int A00(int i, int i2) {
        if (i2 >= 0) {
            int i3 = i + (i >> 1) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                return Integer.MAX_VALUE;
            }
            return i3;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }

    public Tx add(Object... objArr) {
        for (Object obj : objArr) {
            add(obj);
        }
        return this;
    }
}
