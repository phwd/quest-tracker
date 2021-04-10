package com.google.common.collect;

import X.AnonymousClass0CP;
import X.AnonymousClass0eN;
import X.AnonymousClass0eR;
import X.AnonymousClass0ow;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class AbstractMapBasedMultimap<K, V> extends AnonymousClass0eN<K, V> implements Serializable {
    public static final long serialVersionUID = 2447537837011683357L;
    public transient int A00;
    public transient Map<K, Collection<V>> A01;

    public abstract Collection<V> A02();

    @Override // X.AnonymousClass0eN
    public final Map<K, Collection<V>> A00() {
        return new AnonymousClass0eR(this, this.A01);
    }

    @Override // X.AnonymousClass0eN
    public final Set<K> A01() {
        return new AnonymousClass0CP(this, this.A01);
    }

    public Collection<V> A03(@NullableDecl K k, Collection<V> collection) {
        return new AnonymousClass0ow(this, k, collection);
    }

    @Override // X.AbstractC07090r4
    public Collection<V> A2u(@NullableDecl K k) {
        Collection<V> collection = this.A01.get(k);
        if (collection == null) {
            collection = A02();
        }
        return A03(k, collection);
    }

    @Override // X.AbstractC07090r4, X.AnonymousClass0eN
    public final boolean A7h(@NullableDecl K k, @NullableDecl V v) {
        Collection<V> collection = this.A01.get(k);
        if (collection == null) {
            Collection<V> A02 = A02();
            if (A02.add(v)) {
                this.A00++;
                this.A01.put(k, A02);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v)) {
            return false;
        } else {
            this.A00++;
            return true;
        }
    }

    public AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
        Preconditions.checkArgument(map.isEmpty());
        this.A01 = map;
    }
}
