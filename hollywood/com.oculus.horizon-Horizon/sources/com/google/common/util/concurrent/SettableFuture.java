package com.google.common.util.concurrent;

import X.AbstractC000300p;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class SettableFuture<V> extends AbstractC000300p<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }
}
