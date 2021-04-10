package com.facebook.common.preconditions;

import javax.annotation.Nullable;

public class Preconditions {
    private Preconditions() {
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
