package com.google.common.util.concurrent;

import X.AbstractC00040n;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class SettableFuture<V> extends AbstractC00040n<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }
}
