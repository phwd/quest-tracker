package com.google.common.collect;

/* access modifiers changed from: package-private */
public class RegularImmutableAsList<E> extends ImmutableAsList<E> {
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, ImmutableList<? extends E> immutableList) {
        this.delegate = immutableCollection;
        this.delegateList = immutableList;
    }

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr) {
        this(immutableCollection, ImmutableList.asImmutableList(objArr));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableAsList
    public ImmutableCollection<E> delegateCollection() {
        return this.delegate;
    }

    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: com.google.common.collect.UnmodifiableListIterator<? extends E>, com.google.common.collect.UnmodifiableListIterator<E> */
    @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
    public UnmodifiableListIterator<E> listIterator(int i) {
        return (UnmodifiableListIterator<? extends E>) this.delegateList.listIterator(i);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        return this.delegateList.copyIntoArray(objArr, i);
    }

    @Override // java.util.List
    public E get(int i) {
        return (E) this.delegateList.get(i);
    }
}
