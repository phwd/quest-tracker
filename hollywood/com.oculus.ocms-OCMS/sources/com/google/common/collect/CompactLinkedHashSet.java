package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* access modifiers changed from: package-private */
@GwtIncompatible
public class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @MonotonicNonNullDecl
    private transient int[] predecessor;
    @MonotonicNonNullDecl
    private transient int[] successor;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    public static <E> CompactLinkedHashSet<E> create(E... eArr) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int i) {
        return new CompactLinkedHashSet<>(i);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int i) {
        super(i);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void init(int i, float f) {
        super.init(i, f);
        this.predecessor = new int[i];
        this.successor = new int[i];
        Arrays.fill(this.predecessor, -1);
        Arrays.fill(this.successor, -1);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    private void succeeds(int i, int i2) {
        if (i == -2) {
            this.firstEntry = i2;
        } else {
            this.successor[i] = i2;
        }
        if (i2 == -2) {
            this.lastEntry = i;
        } else {
            this.predecessor[i2] = i;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void insertEntry(int i, E e, int i2) {
        super.insertEntry(i, e, i2);
        succeeds(this.lastEntry, i);
        succeeds(i, -2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void moveEntry(int i) {
        int size = size() - 1;
        super.moveEntry(i);
        succeeds(this.predecessor[i], this.successor[i]);
        if (size != i) {
            succeeds(this.predecessor[size], i);
            succeeds(i, this.successor[size]);
        }
        this.predecessor[size] = -1;
        this.successor[size] = -1;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
        Arrays.fill(this.predecessor, -1);
        Arrays.fill(this.successor, -1);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void resizeEntries(int i) {
        super.resizeEntries(i);
        int[] iArr = this.predecessor;
        int length = iArr.length;
        this.predecessor = Arrays.copyOf(iArr, i);
        this.successor = Arrays.copyOf(this.successor, i);
        if (length < i) {
            Arrays.fill(this.predecessor, length, i, -1);
            Arrays.fill(this.successor, length, i, -1);
        }
    }

    @Override // com.google.common.collect.CompactHashSet
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.CompactHashSet, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public int firstEntryIndex() {
        return this.firstEntry;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public int adjustAfterRemove(int i, int i2) {
        return i == size() ? i2 : i;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public int getSuccessor(int i) {
        return this.successor[i];
    }
}
