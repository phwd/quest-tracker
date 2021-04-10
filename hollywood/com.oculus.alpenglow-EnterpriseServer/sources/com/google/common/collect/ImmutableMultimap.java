package com.google.common.collect;

import X.AnonymousClass0JM;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@GwtCompatible(emulated = true)
public abstract class ImmutableMultimap<K, V> extends AnonymousClass0JM<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    public final transient ImmutableMap<K, ? extends ImmutableCollection<V>> A00;

    /* renamed from: A05 */
    public abstract ImmutableCollection<V> A2s(K k);

    @Override // X.AnonymousClass0YL
    public final Map<K, Collection<V>> A03() {
        throw new AssertionError("should never be called");
    }

    @Override // X.AnonymousClass0YL
    public final Set<K> A04() {
        throw new AssertionError("unreachable");
    }

    @Override // X.AnonymousClass0YL, X.AbstractC07440t7
    public final Map A18() {
        return this.A00;
    }

    @Override // X.AnonymousClass0YL, X.AbstractC07440t7
    public final Set keySet() {
        ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap = this.A00;
        ImmutableSet<K> immutableSet = immutableMap.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> createKeySet = immutableMap.createKeySet();
        immutableMap.keySet = createKeySet;
        return createKeySet;
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;+Lcom/google/common/collect/ImmutableCollection<TV;>;>;I)V */
    public ImmutableMultimap(ImmutableMap immutableMap) {
        this.A00 = immutableMap;
    }
}
