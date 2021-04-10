package com.google.common.collect;

import java.io.Serializable;
import java.lang.Enum;
import java.util.Collection;
import java.util.EnumSet;

/* access modifiers changed from: package-private */
public final class ImmutableEnumSet<E extends Enum<E>> extends ImmutableSet<E> {
    private final transient EnumSet<E> delegate;
    private transient int hashCode;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    static <E extends Enum<E>> ImmutableSet<E> asImmutable(EnumSet<E> enumSet) {
        int size = enumSet.size();
        if (size == 0) {
            return ImmutableSet.of();
        }
        if (size != 1) {
            return new ImmutableEnumSet(enumSet);
        }
        return ImmutableSet.of(Iterables.getOnlyElement(enumSet));
    }

    private ImmutableEnumSet(EnumSet<E> enumSet) {
        this.delegate = enumSet;
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.unmodifiableIterator(this.delegate.iterator());
    }

    public int size() {
        return this.delegate.size();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        return this.delegate.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        return this.delegate.containsAll(collection);
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(Object obj) {
        return obj == this || this.delegate.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableSet
    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode2 = this.delegate.hashCode();
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        return this.delegate.toString();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }

    private static class EnumSerializedForm<E extends Enum<E>> implements Serializable {
        private static final long serialVersionUID = 0;
        final EnumSet<E> delegate;

        EnumSerializedForm(EnumSet<E> enumSet) {
            this.delegate = enumSet;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableEnumSet(this.delegate.clone());
        }
    }
}
