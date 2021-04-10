package com.google.common.collect;

import X.AnonymousClass0oy;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public class ImmutableEntry<K, V> extends AnonymousClass0oy<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    @NullableDecl
    public final K key;
    @NullableDecl
    public final V value;

    public ImmutableEntry(@NullableDecl K k, @NullableDecl V v) {
        this.key = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    @NullableDecl
    public final K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    @NullableDecl
    public final V getValue() {
        return this.value;
    }
}
