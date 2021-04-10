package com.google.common.collect;

import X.AnonymousClass0CK;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@GwtCompatible(emulated = true)
public abstract class ImmutableMultimap<K, V> extends AnonymousClass0CK<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    public final transient ImmutableMap<K, ? extends ImmutableCollection<V>> A00;

    /* renamed from: A02 */
    public abstract ImmutableCollection<V> A2u(K k);

    @Override // X.AnonymousClass0eN
    public final Map<K, Collection<V>> A00() {
        throw new AssertionError("should never be called");
    }

    @Override // X.AnonymousClass0eN
    public final Set<K> A01() {
        throw new AssertionError("unreachable");
    }

    @Override // X.AbstractC07090r4, X.AnonymousClass0eN
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A7h(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC07090r4, X.AnonymousClass0eN
    public final Set keySet() {
        ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap = this.A00;
        ImmutableSet<K> immutableSet = immutableMap.A01;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> A07 = immutableMap.A07();
        immutableMap.A01 = A07;
        return A07;
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;+Lcom/google/common/collect/ImmutableCollection<TV;>;>;I)V */
    public ImmutableMultimap(ImmutableMap immutableMap) {
        this.A00 = immutableMap;
    }

    @Override // X.AbstractC07090r4, X.AnonymousClass0eN
    public final Map A1H() {
        return this.A00;
    }
}
