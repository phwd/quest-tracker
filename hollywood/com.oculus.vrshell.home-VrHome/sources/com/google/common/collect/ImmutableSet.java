package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.Arrays;
import java.util.Set;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private transient ImmutableList<E> asList;

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E element) {
        return new SingletonImmutableSet(element);
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int n, Object... elements) {
        Object[] uniqueElements;
        switch (n) {
            case 0:
                return of();
            case 1:
                return of(elements[0]);
            default:
                int tableSize = chooseTableSize(n);
                Object[] table = new Object[tableSize];
                int mask = tableSize - 1;
                int hashCode = 0;
                int uniques = 0;
                for (int i = 0; i < n; i++) {
                    Object element = ObjectArrays.checkElementNotNull(elements[i], i);
                    int hash = element.hashCode();
                    int j = Hashing.smear(hash);
                    while (true) {
                        int index = j & mask;
                        Object value = table[index];
                        if (value == null) {
                            elements[uniques] = element;
                            table[index] = element;
                            hashCode += hash;
                            uniques++;
                        } else if (!value.equals(element)) {
                            j++;
                        }
                    }
                }
                Arrays.fill(elements, uniques, n, (Object) null);
                if (uniques == 1) {
                    return new SingletonImmutableSet(elements[0], hashCode);
                }
                if (chooseTableSize(uniques) < tableSize / 2) {
                    return construct(uniques, elements);
                }
                if (shouldTrim(uniques, elements.length)) {
                    uniqueElements = Arrays.copyOf(elements, uniques);
                } else {
                    uniqueElements = elements;
                }
                return new RegularImmutableSet(uniqueElements, hashCode, table, mask, uniques);
        }
    }

    /* access modifiers changed from: private */
    public static boolean shouldTrim(int actualUnique, int expectedUnique) {
        return actualUnique < (expectedUnique >> 1) + (expectedUnique >> 2);
    }

    static int chooseTableSize(int setSize) {
        int tableSize = 1073741824;
        int setSize2 = Math.max(setSize, 2);
        if (setSize2 < 751619276) {
            tableSize = Integer.highestOneBit(setSize2 - 1) << 1;
            while (((double) tableSize) * 0.7d < ((double) setSize2)) {
                tableSize <<= 1;
            }
        } else {
            Preconditions.checkArgument(setSize2 < 1073741824, "collection too large");
        }
        return tableSize;
    }

    ImmutableSet() {
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) object).isHashCodeFast() || hashCode() == object.hashCode()) {
            return Sets.equalsImpl(this, object);
        }
        return false;
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> result = this.asList;
        if (result != null) {
            return result;
        }
        ImmutableList<E> result2 = createAsList();
        this.asList = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        private int hashCode;
        Object[] hashTable;

        public Builder() {
            super(4);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E element) {
            Preconditions.checkNotNull(element);
            if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) > this.hashTable.length) {
                this.hashTable = null;
                super.add((Object) element);
            } else {
                addDeduping(element);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... elements) {
            if (this.hashTable != null) {
                for (E e : elements) {
                    add((Object) e);
                }
            } else {
                super.add((Object[]) elements);
            }
            return this;
        }

        private void addDeduping(E element) {
            int mask = this.hashTable.length - 1;
            int hash = element.hashCode();
            int i = Hashing.smear(hash);
            while (true) {
                int i2 = i & mask;
                Object previous = this.hashTable[i2];
                if (previous == null) {
                    this.hashTable[i2] = element;
                    this.hashCode += hash;
                    super.add((Object) element);
                    return;
                } else if (!previous.equals(element)) {
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }

        public ImmutableSet<E> build() {
            ImmutableSet<E> result;
            switch (this.size) {
                case 0:
                    return ImmutableSet.of();
                case 1:
                    return ImmutableSet.of(this.contents[0]);
                default:
                    if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) != this.hashTable.length) {
                        result = ImmutableSet.construct(this.size, this.contents);
                        this.size = result.size();
                    } else {
                        result = new RegularImmutableSet<>(ImmutableSet.shouldTrim(this.size, this.contents.length) ? Arrays.copyOf(this.contents, this.size) : this.contents, this.hashCode, this.hashTable, this.hashTable.length - 1, this.size);
                    }
                    this.forceCopy = true;
                    this.hashTable = null;
                    return result;
            }
        }
    }
}
