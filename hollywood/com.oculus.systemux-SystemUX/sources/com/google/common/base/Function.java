package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
@GwtCompatible
public interface Function<F, T> extends java.util.function.Function<F, T> {
    @Override // java.util.function.Function
    @CanIgnoreReturnValue
    @NullableDecl
    T apply(@NullableDecl F f);

    boolean equals(@NullableDecl Object obj);
}
