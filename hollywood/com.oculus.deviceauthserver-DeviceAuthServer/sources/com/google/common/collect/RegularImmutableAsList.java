package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public class RegularImmutableAsList<E> extends ImmutableAsList<E> {
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    RegularImmutableAsList(ImmutableCollection<E> delegate2, ImmutableList<? extends E> delegateList2) {
        this.delegate = delegate2;
        this.delegateList = delegateList2;
    }

    RegularImmutableAsList(ImmutableCollection<E> delegate2, Object[] array) {
        this(delegate2, ImmutableList.asImmutableList(array));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableAsList
    public ImmutableCollection<E> delegateCollection() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<? extends E> delegateList() {
        return this.delegateList;
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.common.collect.UnmodifiableListIterator<? extends E>, com.google.common.collect.UnmodifiableListIterator<E> */
    @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
    public UnmodifiableListIterator<E> listIterator(int index) {
        return (UnmodifiableListIterator<? extends E>) this.delegateList.listIterator(index);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    @GwtIncompatible("not present in emulated superclass")
    public int copyIntoArray(Object[] dst, int offset) {
        return this.delegateList.copyIntoArray(dst, offset);
    }

    @Override // java.util.List
    public E get(int index) {
        return (E) this.delegateList.get(index);
    }
}
