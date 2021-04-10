package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final ImmutableMultiset<Object> EMPTY = create(ImmutableList.of());
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001d;
    @VisibleForTesting
    static final int MAX_HASH_BUCKET_LENGTH = 9;
    @VisibleForTesting
    static final double MAX_LOAD_FACTOR = 1.0d;
    @LazyInit
    private transient ImmutableSet<E> elementSet;
    private final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient int hashCode;
    @NullableDecl
    private final transient Multisets.ImmutableEntry<E>[] hashTable;
    private final transient int size;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Multisets.ImmutableEntry immutableEntry;
        int size2 = collection.size();
        Multisets.ImmutableEntry[] immutableEntryArr = new Multisets.ImmutableEntry[size2];
        if (size2 == 0) {
            return new RegularImmutableMultiset(immutableEntryArr, null, 0, 0, ImmutableSet.of());
        }
        int closedTableSize = Hashing.closedTableSize(size2, 1.0d);
        int i = closedTableSize - 1;
        Multisets.ImmutableEntry[] immutableEntryArr2 = new Multisets.ImmutableEntry[closedTableSize];
        long j = 0;
        Iterator<? extends Multiset.Entry<? extends E>> it = collection.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Multiset.Entry entry = (Multiset.Entry) it.next();
            Object checkNotNull = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int hashCode2 = checkNotNull.hashCode();
            int smear = Hashing.smear(hashCode2) & i;
            Multisets.ImmutableEntry immutableEntry2 = immutableEntryArr2[smear];
            if (immutableEntry2 == null) {
                immutableEntry = (entry instanceof Multisets.ImmutableEntry) && !(entry instanceof NonTerminalEntry) ? (Multisets.ImmutableEntry) entry : new Multisets.ImmutableEntry(checkNotNull, count);
            } else {
                immutableEntry = new NonTerminalEntry(checkNotNull, count, immutableEntry2);
            }
            i2 += hashCode2 ^ count;
            immutableEntryArr[i3] = immutableEntry;
            immutableEntryArr2[smear] = immutableEntry;
            j += (long) count;
            i3++;
        }
        if (hashFloodingDetected(immutableEntryArr2)) {
            return JdkBackedImmutableMultiset.create(ImmutableList.asImmutableList(immutableEntryArr));
        }
        return new RegularImmutableMultiset(immutableEntryArr, immutableEntryArr2, Ints.saturatedCast(j), i2, null);
    }

    private static boolean hashFloodingDetected(Multisets.ImmutableEntry<?>[] immutableEntryArr) {
        for (Multisets.ImmutableEntry<?> immutableEntry : immutableEntryArr) {
            int i = 0;
            for (; immutableEntry != null; immutableEntry = immutableEntry.nextInBucket()) {
                i++;
                if (i > 9) {
                    return true;
                }
            }
        }
        return false;
    }

    private RegularImmutableMultiset(Multisets.ImmutableEntry<E>[] immutableEntryArr, Multisets.ImmutableEntry<E>[] immutableEntryArr2, int i, int i2, ImmutableSet<E> immutableSet) {
        this.entries = immutableEntryArr;
        this.hashTable = immutableEntryArr2;
        this.size = i;
        this.hashCode = i2;
        this.elementSet = immutableSet;
    }

    /* access modifiers changed from: private */
    public static final class NonTerminalEntry<E> extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E e, int i, Multisets.ImmutableEntry<E> immutableEntry) {
            super(e, i);
            this.nextInBucket = immutableEntry;
        }

        @Override // com.google.common.collect.Multisets.ImmutableEntry
        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        Multisets.ImmutableEntry<E>[] immutableEntryArr = this.hashTable;
        if (!(obj == null || immutableEntryArr == null)) {
            for (Multisets.ImmutableEntry<E> immutableEntry = immutableEntryArr[Hashing.smearedHash(obj) & (immutableEntryArr.length - 1)]; immutableEntry != null; immutableEntry = immutableEntry.nextInBucket()) {
                if (Objects.equal(obj, immutableEntry.getElement())) {
                    return immutableEntry.getCount();
                }
            }
        }
        return 0;
    }

    @Override // com.google.common.collect.Multiset
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableMultiset.ElementSet elementSet2 = new ImmutableMultiset.ElementSet(Arrays.asList(this.entries), this);
        this.elementSet = elementSet2;
        return elementSet2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int i) {
        return this.entries[i];
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public int hashCode() {
        return this.hashCode;
    }
}
