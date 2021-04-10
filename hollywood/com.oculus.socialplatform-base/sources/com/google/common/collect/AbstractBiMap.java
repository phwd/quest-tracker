package com.google.common.collect;

import X.AbstractC01630fe;
import X.AbstractC05100td;
import X.AnonymousClass0Bi;
import X.AnonymousClass0Bj;
import X.AnonymousClass0Bl;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public abstract class AbstractBiMap<K, V> extends AbstractC01630fe<K, V> implements AbstractC05100td<K, V>, Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    @RetainedWith
    @MonotonicNonNullDecl
    public final transient AbstractBiMap<V, K> A00;
    @MonotonicNonNullDecl
    public final transient Map<K, V> A01;
    @MonotonicNonNullDecl
    public transient Set<V> A02;
    @MonotonicNonNullDecl
    public transient Set<Map.Entry<K, V>> A03;
    @MonotonicNonNullDecl
    public transient Set<K> A04;

    @Override // X.AbstractC01630fe
    public final void clear() {
        this.A01.clear();
        this.A00.A01.clear();
    }

    @Override // X.AbstractC01630fe
    public final boolean containsValue(@NullableDecl Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // X.AbstractC01630fe, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A03;
        if (set != null) {
            return set;
        }
        AnonymousClass0Bl r0 = new AnonymousClass0Bl(this);
        this.A03 = r0;
        return r0;
    }

    @Override // X.AbstractC01630fe, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.A04;
        if (set != null) {
            return set;
        }
        AnonymousClass0Bj r0 = new AnonymousClass0Bj(this);
        this.A04 = r0;
        return r0;
    }

    @Override // X.AbstractC01630fe, java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        Set<V> set = this.A02;
        if (set != null) {
            return set;
        }
        AnonymousClass0Bi r0 = new AnonymousClass0Bi(this);
        this.A02 = r0;
        return r0;
    }

    @Override // X.AbstractC01630fe
    /* renamed from: A01 */
    public final Map<K, V> A00() {
        return this.A01;
    }

    @Override // X.AbstractC05100td
    public final AbstractC05100td<V, K> A5n() {
        return this.A00;
    }

    @Override // X.AbstractC01630fe, java.util.Map, X.AbstractC05100td
    @CanIgnoreReturnValue
    public final V put(@NullableDecl K k, @NullableDecl V v) {
        boolean containsKey = containsKey(k);
        if (containsKey && Objects.equal(v, get(k))) {
            return v;
        }
        Preconditions.checkArgument(!containsValue(v), "value already present: %s", v);
        V put = this.A01.put(k, v);
        if (containsKey) {
            this.A00.A01.remove(put);
        }
        this.A00.A01.put(v, k);
        return put;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.AbstractBiMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC01630fe, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // X.AbstractC01630fe, java.util.Map
    @CanIgnoreReturnValue
    public final V remove(@NullableDecl Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        V remove = this.A01.remove(obj);
        this.A00.A01.remove(remove);
        return remove;
    }
}
