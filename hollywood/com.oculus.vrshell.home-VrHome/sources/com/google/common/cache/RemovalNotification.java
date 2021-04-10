package com.google.common.cache;

import com.google.common.base.Preconditions;
import java.util.AbstractMap;

public final class RemovalNotification<K, V> extends AbstractMap.SimpleImmutableEntry<K, V> {
    private final RemovalCause cause;

    public static <K, V> RemovalNotification<K, V> create(K key, V value, RemovalCause cause2) {
        return new RemovalNotification<>(key, value, cause2);
    }

    private RemovalNotification(K key, V value, RemovalCause cause2) {
        super(key, value);
        this.cause = (RemovalCause) Preconditions.checkNotNull(cause2);
    }
}
