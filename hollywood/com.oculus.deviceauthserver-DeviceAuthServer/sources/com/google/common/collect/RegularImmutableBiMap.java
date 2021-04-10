package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final double MAX_LOAD_FACTOR = 1.2d;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] valueTable;

    RegularImmutableBiMap(ImmutableMapEntry.TerminalEntry<?, ?>... entriesToAdd) {
        this(entriesToAdd.length, entriesToAdd);
    }

    /* JADX INFO: Multiple debug info for r1v4 com.google.common.collect.ImmutableMapEntry<K, V>: [D('keyEntry' com.google.common.collect.ImmutableMapEntry<K, V>), D('nextInValueBucket' com.google.common.collect.ImmutableMapEntry<K, V>)] */
    RegularImmutableBiMap(int n, ImmutableMapEntry.TerminalEntry<?, ?>[] entriesToAdd) {
        ImmutableMapEntry<K, V> newEntry;
        int i = n;
        int tableSize = Hashing.closedTableSize(i, MAX_LOAD_FACTOR);
        this.mask = tableSize - 1;
        ImmutableMapEntry<K, V>[] keyTable2 = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] valueTable2 = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] entries2 = createEntryArray(n);
        int hashCode2 = 0;
        int i2 = 0;
        while (i2 < i) {
            ImmutableMapEntry.TerminalEntry<?, ?> terminalEntry = entriesToAdd[i2];
            Object key = terminalEntry.getKey();
            Object value = terminalEntry.getValue();
            int keyHash = key.hashCode();
            int valueHash = value.hashCode();
            int keyBucket = Hashing.smear(keyHash) & this.mask;
            int valueBucket = Hashing.smear(valueHash) & this.mask;
            ImmutableMapEntry<K, V> nextInKeyBucket = keyTable2[keyBucket];
            ImmutableMapEntry<K, V> keyEntry = nextInKeyBucket;
            while (keyEntry != null) {
                checkNoConflict(!key.equals(keyEntry.getKey()), "key", terminalEntry, keyEntry);
                keyEntry = keyEntry.getNextInKeyBucket();
                tableSize = tableSize;
                key = key;
            }
            ImmutableMapEntry<K, V> nextInValueBucket = valueTable2[valueBucket];
            ImmutableMapEntry<K, V> valueEntry = nextInValueBucket;
            while (valueEntry != null) {
                checkNoConflict(!value.equals(valueEntry.getValue()), "value", terminalEntry, valueEntry);
                valueEntry = valueEntry.getNextInValueBucket();
                value = value;
            }
            if (nextInKeyBucket == null && nextInValueBucket == null) {
                newEntry = terminalEntry;
            } else {
                newEntry = new NonTerminalBiMapEntry<>(terminalEntry, nextInKeyBucket, nextInValueBucket);
            }
            keyTable2[keyBucket] = newEntry;
            valueTable2[valueBucket] = newEntry;
            entries2[i2] = newEntry;
            hashCode2 += keyHash ^ valueHash;
            i2++;
            i = n;
            tableSize = tableSize;
        }
        this.keyTable = keyTable2;
        this.valueTable = valueTable2;
        this.entries = entries2;
        this.hashCode = hashCode2;
    }

    RegularImmutableBiMap(Map.Entry<?, ?>[] entriesToAdd) {
        ImmutableMapEntry<K, V> newEntry;
        RegularImmutableBiMap<K, V> regularImmutableBiMap = this;
        Map.Entry<?, ?>[] entryArr = entriesToAdd;
        int n = entryArr.length;
        int tableSize = Hashing.closedTableSize(n, MAX_LOAD_FACTOR);
        regularImmutableBiMap.mask = tableSize - 1;
        ImmutableMapEntry<K, V>[] keyTable2 = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] valueTable2 = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] entries2 = createEntryArray(n);
        int hashCode2 = 0;
        int i = 0;
        while (i < n) {
            Map.Entry<?, ?> entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int keyHash = key.hashCode();
            int valueHash = value.hashCode();
            int keyBucket = Hashing.smear(keyHash) & regularImmutableBiMap.mask;
            int valueBucket = regularImmutableBiMap.mask & Hashing.smear(valueHash);
            ImmutableMapEntry<K, V> nextInKeyBucket = keyTable2[keyBucket];
            ImmutableMapEntry<K, V> keyEntry = nextInKeyBucket;
            while (keyEntry != null) {
                checkNoConflict(!key.equals(keyEntry.getKey()), "key", entry, keyEntry);
                keyEntry = keyEntry.getNextInKeyBucket();
                tableSize = tableSize;
            }
            ImmutableMapEntry<K, V> nextInValueBucket = valueTable2[valueBucket];
            ImmutableMapEntry<K, V> valueEntry = nextInValueBucket;
            while (valueEntry != null) {
                checkNoConflict(!value.equals(valueEntry.getValue()), "value", entry, valueEntry);
                valueEntry = valueEntry.getNextInValueBucket();
                hashCode2 = hashCode2;
            }
            if (nextInKeyBucket == null && nextInValueBucket == null) {
                newEntry = new ImmutableMapEntry.TerminalEntry<>(key, value);
            } else {
                newEntry = new NonTerminalBiMapEntry<>(key, value, nextInKeyBucket, nextInValueBucket);
            }
            keyTable2[keyBucket] = newEntry;
            valueTable2[valueBucket] = newEntry;
            entries2[i] = newEntry;
            hashCode2 += keyHash ^ valueHash;
            i++;
            regularImmutableBiMap = this;
            entryArr = entriesToAdd;
            tableSize = tableSize;
            n = n;
        }
        this.keyTable = keyTable2;
        this.valueTable = valueTable2;
        this.entries = entries2;
        this.hashCode = hashCode2;
    }

    private static final class NonTerminalBiMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        @Nullable
        private final ImmutableMapEntry<K, V> nextInKeyBucket;
        @Nullable
        private final ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalBiMapEntry(K key, V value, @Nullable ImmutableMapEntry<K, V> nextInKeyBucket2, @Nullable ImmutableMapEntry<K, V> nextInValueBucket2) {
            super(key, value);
            this.nextInKeyBucket = nextInKeyBucket2;
            this.nextInValueBucket = nextInValueBucket2;
        }

        NonTerminalBiMapEntry(ImmutableMapEntry<K, V> contents, @Nullable ImmutableMapEntry<K, V> nextInKeyBucket2, @Nullable ImmutableMapEntry<K, V> nextInValueBucket2) {
            super(contents);
            this.nextInKeyBucket = nextInKeyBucket2;
            this.nextInValueBucket = nextInValueBucket2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        @Nullable
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    private static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int length) {
        return new ImmutableMapEntry[length];
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @Nullable
    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> entry = this.keyTable[Hashing.smear(key.hashCode()) & this.mask]; entry != null; entry = entry.getNextInKeyBucket()) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet<K, V>() {
            /* class com.google.common.collect.RegularImmutableBiMap.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableMapEntrySet
            public ImmutableMap<K, V> map() {
                return RegularImmutableBiMap.this;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public ImmutableList<Map.Entry<K, V>> createAsList() {
                return new RegularImmutableAsList(this, RegularImmutableBiMap.this.entries);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableSet
            public boolean isHashCodeFast() {
                return true;
            }

            @Override // com.google.common.collect.ImmutableSet
            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }

    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> result = this.inverse;
        if (result != null) {
            return result;
        }
        Inverse inverse2 = new Inverse();
        this.inverse = inverse2;
        return inverse2;
    }

    /* access modifiers changed from: private */
    public final class Inverse extends ImmutableBiMap<V, K> {
        private Inverse() {
        }

        public int size() {
            return inverse().size();
        }

        @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public K get(@Nullable Object value) {
            if (value == null) {
                return null;
            }
            for (ImmutableMapEntry<K, V> entry = RegularImmutableBiMap.this.valueTable[Hashing.smear(value.hashCode()) & RegularImmutableBiMap.this.mask]; entry != null; entry = entry.getNextInValueBucket()) {
                if (value.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<Map.Entry<V, K>> createEntrySet() {
            return new InverseEntrySet();
        }

        /* access modifiers changed from: package-private */
        public final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {
            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableMapEntrySet
            public ImmutableMap<V, K> map() {
                return Inverse.this;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableSet
            public boolean isHashCodeFast() {
                return true;
            }

            @Override // com.google.common.collect.ImmutableSet
            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<Map.Entry<V, K>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public ImmutableList<Map.Entry<V, K>> createAsList() {
                return new ImmutableAsList<Map.Entry<V, K>>() {
                    /* class com.google.common.collect.RegularImmutableBiMap.Inverse.InverseEntrySet.AnonymousClass1 */

                    @Override // java.util.List
                    public Map.Entry<V, K> get(int index) {
                        Map.Entry<K, V> entry = RegularImmutableBiMap.this.entries[index];
                        return Maps.immutableEntry(entry.getValue(), entry.getKey());
                    }

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.collect.ImmutableAsList
                    public ImmutableCollection<Map.Entry<V, K>> delegateCollection() {
                        return InverseEntrySet.this;
                    }
                };
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.ImmutableMap
        public Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    private static class InverseSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap<K, V> forward;

        InverseSerializedForm(ImmutableBiMap<K, V> forward2) {
            this.forward = forward2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.forward.inverse();
        }
    }
}
