package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public abstract Set<E> delegate();

    protected ForwardingSet() {
    }

    public boolean equals(@NullableDecl Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemoveAll(Collection<?> collection) {
        return Sets.removeAllImpl(this, (Collection) Preconditions.checkNotNull(collection));
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(@NullableDecl Object obj) {
        return Sets.equalsImpl(this, obj);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return Sets.hashCodeImpl(this);
    }
}
