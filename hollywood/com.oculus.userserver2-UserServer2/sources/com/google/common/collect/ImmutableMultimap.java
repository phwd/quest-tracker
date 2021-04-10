package com.google.common.collect;

import X.AbstractC00209j;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.util.Collection;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public abstract class ImmutableMultimap<K, V> extends AbstractC00209j<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    public final transient ImmutableMap<K, ? extends ImmutableCollection<V>> A00;

    public ImmutableCollection<V> A04(K k) {
        ImmutableSetMultimap immutableSetMultimap = (ImmutableSetMultimap) this;
        return (ImmutableCollection) MoreObjects.firstNonNull(((ImmutableMultimap) immutableSetMultimap).A00.get(k), immutableSetMultimap.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.Qy
    public /* bridge */ /* synthetic */ Collection A1b(Object obj) {
        if (!(this instanceof ImmutableSetMultimap)) {
            return A04(obj);
        }
        ImmutableSetMultimap immutableSetMultimap = (ImmutableSetMultimap) this;
        return (ImmutableCollection) MoreObjects.firstNonNull(((ImmutableMultimap) immutableSetMultimap).A00.get(obj), immutableSetMultimap.A00);
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;+Lcom/google/common/collect/ImmutableCollection<TV;>;>;I)V */
    public ImmutableMultimap(ImmutableMap immutableMap) {
        this.A00 = immutableMap;
    }
}
