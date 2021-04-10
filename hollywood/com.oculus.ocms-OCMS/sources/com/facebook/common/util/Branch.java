package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Branch {
    public static boolean likely(boolean z) {
        return z;
    }

    public static boolean unlikely(boolean z) {
        return z;
    }
}
