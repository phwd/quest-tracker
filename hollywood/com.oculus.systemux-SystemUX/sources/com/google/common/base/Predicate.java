package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
@GwtCompatible
public interface Predicate<T> extends java.util.function.Predicate<T> {
    @CanIgnoreReturnValue
    boolean apply(@NullableDecl T t);

    boolean equals(@NullableDecl Object obj);

    @Override // java.util.function.Predicate
    default boolean test(@NullableDecl T t) {
        return apply(t);
    }
}
