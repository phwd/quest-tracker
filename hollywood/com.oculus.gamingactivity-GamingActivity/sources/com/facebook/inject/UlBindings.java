package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UlBindings {
    public static int getBindingId(Class clazz) {
        return getBindingId(clazz, null);
    }

    public static int getBindingId(Class clazz, @Nullable Class bindingAnnotation) {
        return RuntimeBindingIdUtils.getBindingIdFromClasses(clazz, bindingAnnotation);
    }

    public static int getBindingId(int bindingId) {
        return bindingId;
    }
}
