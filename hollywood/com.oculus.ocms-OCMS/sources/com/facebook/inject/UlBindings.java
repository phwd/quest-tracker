package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UlBindings {
    public static int getBindingId(int i) {
        return i;
    }

    public static int getBindingId(Class cls) {
        return getBindingId(cls, null);
    }

    public static int getBindingId(Class cls, @Nullable Class cls2) {
        return RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2);
    }
}
