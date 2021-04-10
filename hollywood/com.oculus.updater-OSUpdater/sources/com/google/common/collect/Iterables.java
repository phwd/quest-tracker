package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Iterables {
    private Iterables() {
    }

    @NullableDecl
    public static <T> T getFirst(Iterable<? extends T> iterable, @NullableDecl T t) {
        return (T) Iterators.getNext(iterable.iterator(), t);
    }
}
