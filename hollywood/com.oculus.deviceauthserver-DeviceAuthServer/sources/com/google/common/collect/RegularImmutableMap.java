package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    RegularImmutableMap(ImmutableMapEntry.TerminalEntry<?, ?>... theEntries) {
        this(theEntries.length, theEntries);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: com.google.common.collect.RegularImmutableMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    RegularImmutableMap(int size, ImmutableMapEntry.TerminalEntry<?, ?>[] theEntries) {
        ImmutableMapEntry<K, V> newEntry;
        this.entries = createEntryArray(size);
        int tableSize = Hashing.closedTableSize(size, MAX_LOAD_FACTOR);
        this.table = createEntryArray(tableSize);
        this.mask = tableSize - 1;
        for (int entryIndex = 0; entryIndex < size; entryIndex++) {
            ImmutableMapEntry.TerminalEntry<?, ?> terminalEntry = theEntries[entryIndex];
            Object key = terminalEntry.getKey();
            int tableIndex = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> existing = this.table[tableIndex];
            if (existing == null) {
                newEntry = terminalEntry;
            } else {
                newEntry = new NonTerminalMapEntry<>(terminalEntry, existing);
            }
            this.table[tableIndex] = newEntry;
            this.entries[entryIndex] = newEntry;
            checkNoConflictInBucket(key, newEntry, existing);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: com.google.common.collect.RegularImmutableMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    RegularImmutableMap(Map.Entry<?, ?>[] theEntries) {
        ImmutableMapEntry<K, V> newEntry;
        int size = theEntries.length;
        this.entries = createEntryArray(size);
        int tableSize = Hashing.closedTableSize(size, MAX_LOAD_FACTOR);
        this.table = createEntryArray(tableSize);
        this.mask = tableSize - 1;
        for (int entryIndex = 0; entryIndex < size; entryIndex++) {
            Map.Entry<?, ?> entry = theEntries[entryIndex];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int tableIndex = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> existing = this.table[tableIndex];
            if (existing == null) {
                newEntry = new ImmutableMapEntry.TerminalEntry<>(key, value);
            } else {
                newEntry = new NonTerminalMapEntry<>(key, value, existing);
            }
            this.table[tableIndex] = newEntry;
            this.entries[entryIndex] = newEntry;
            checkNoConflictInBucket(key, newEntry, existing);
        }
    }

    private void checkNoConflictInBucket(K key, ImmutableMapEntry<K, V> entry, ImmutableMapEntry<K, V> bucketHead) {
        while (bucketHead != null) {
            checkNoConflict(!key.equals(bucketHead.getKey()), "key", entry, bucketHead);
            bucketHead = bucketHead.getNextInKeyBucket();
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalMapEntry(K key, V value, ImmutableMapEntry<K, V> nextInKeyBucket2) {
            super(key, value);
            this.nextInKeyBucket = nextInKeyBucket2;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> contents, ImmutableMapEntry<K, V> nextInKeyBucket2) {
            super(contents);
            this.nextInKeyBucket = nextInKeyBucket2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int size) {
        return new ImmutableMapEntry[size];
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> entry = this.table[Hashing.smear(key.hashCode()) & this.mask]; entry != null; entry = entry.getNextInKeyBucket()) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }

    private class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntrySet
        public ImmutableMap<K, V> map() {
            return RegularImmutableMap.this;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, RegularImmutableMap.this.entries);
        }
    }
}
