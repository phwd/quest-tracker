package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nullable;

@Beta
@GwtCompatible
public final class RemovalNotification<K, V> implements Map.Entry<K, V> {
    private static final long serialVersionUID = 0;
    private final RemovalCause cause;
    @Nullable
    private final K key;
    @Nullable
    private final V value;

    RemovalNotification(@Nullable K key2, @Nullable V value2, RemovalCause cause2) {
        this.key = key2;
        this.value = value2;
        this.cause = (RemovalCause) Preconditions.checkNotNull(cause2);
    }

    public RemovalCause getCause() {
        return this.cause;
    }

    public boolean wasEvicted() {
        return this.cause.wasEvicted();
    }

    @Override // java.util.Map.Entry
    @Nullable
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    @Nullable
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<?, ?> that = (Map.Entry) object;
        if (!Objects.equal(getKey(), that.getKey()) || !Objects.equal(getValue(), that.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        K k = getKey();
        V v = getValue();
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        if (v != null) {
            i = v.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        return ((Object) getKey()) + "=" + ((Object) getValue());
    }
}
