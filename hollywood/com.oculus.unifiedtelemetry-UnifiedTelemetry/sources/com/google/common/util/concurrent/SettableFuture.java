package com.google.common.util.concurrent;

import X.AbstractC00030p;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class SettableFuture<V> extends AbstractC00030p<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }
}
