package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T t) {
        return new Present(Preconditions.checkNotNull(t));
    }

    Optional() {
    }
}
