package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* access modifiers changed from: package-private */
@GwtIncompatible
public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    private final boolean accessOrder;
    private transient int firstEntry;
    private transient int lastEntry;
    @VisibleForTesting
    @MonotonicNonNullDecl
    transient long[] links;

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashMap<>(expectedSize);
    }

    CompactLinkedHashMap() {
        this(3);
    }

    CompactLinkedHashMap(int expectedSize) {
        this(expectedSize, 1.0f, false);
    }

    CompactLinkedHashMap(int expectedSize, float loadFactor, boolean accessOrder2) {
        super(expectedSize, loadFactor);
        this.accessOrder = accessOrder2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void init(int expectedSize, float loadFactor) {
        super.init(expectedSize, loadFactor);
        this.firstEntry = -2;
        this.lastEntry = -2;
        this.links = new long[expectedSize];
        Arrays.fill(this.links, -1L);
    }

    private int getPredecessor(int entry) {
        return (int) (this.links[entry] >>> 32);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public int getSuccessor(int entry) {
        return (int) this.links[entry];
    }

    private void setSuccessor(int entry, int succ) {
        this.links[entry] = (this.links[entry] & (-1 ^ 4294967295L)) | (((long) succ) & 4294967295L);
    }

    private void setPredecessor(int entry, int pred) {
        this.links[entry] = (this.links[entry] & (-1 ^ -4294967296L)) | (((long) pred) << 32);
    }

    private void setSucceeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            setSuccessor(pred, succ);
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            setPredecessor(succ, pred);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void insertEntry(int entryIndex, K key, V value, int hash) {
        super.insertEntry(entryIndex, key, value, hash);
        setSucceeds(this.lastEntry, entryIndex);
        setSucceeds(entryIndex, -2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void accessEntry(int index) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(index), getSuccessor(index));
            setSucceeds(this.lastEntry, index);
            setSucceeds(index, -2);
            this.modCount++;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void moveLastEntry(int dstIndex) {
        int srcIndex = size() - 1;
        setSucceeds(getPredecessor(dstIndex), getSuccessor(dstIndex));
        if (dstIndex < srcIndex) {
            setSucceeds(getPredecessor(srcIndex), dstIndex);
            setSucceeds(dstIndex, getSuccessor(srcIndex));
        }
        super.moveLastEntry(dstIndex);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        this.links = Arrays.copyOf(this.links, newCapacity);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public int firstEntryIndex() {
        return this.firstEntry;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= size() ? indexRemoved : indexBeforeRemove;
    }

    @Override // com.google.common.collect.CompactHashMap
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }
}
