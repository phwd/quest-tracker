package com.google.common.collect;

import X.AnonymousClass0tY;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public class ImmutableEntry<K, V> extends AnonymousClass0tY<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    @NullableDecl
    public final K key;
    @NullableDecl
    public final V value;

    public ImmutableEntry(@NullableDecl K k, @NullableDecl V v) {
        this.key = k;
        this.value = v;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    @NullableDecl
    public final K getKey() {
        return this.key;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    @NullableDecl
    public final V getValue() {
        return this.value;
    }
}
