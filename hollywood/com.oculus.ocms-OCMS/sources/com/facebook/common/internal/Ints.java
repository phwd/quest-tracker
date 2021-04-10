package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
public class Ints {
    private Ints() {
    }

    public static int max(int... iArr) {
        Preconditions.checkArgument(Boolean.valueOf(iArr.length > 0));
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }
}
