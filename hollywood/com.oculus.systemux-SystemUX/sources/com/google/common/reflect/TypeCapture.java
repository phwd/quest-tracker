package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* access modifiers changed from: package-private */
public abstract class TypeCapture<T> {
    TypeCapture() {
    }

    /* access modifiers changed from: package-private */
    public final Type capture() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Preconditions.checkArgument(genericSuperclass instanceof ParameterizedType, "%s isn't parameterized", genericSuperclass);
        return ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }
}
