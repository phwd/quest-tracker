package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final long serialVersionUID = 0;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.google.common.collect.RegularImmutableMap, com.google.common.collect.RegularImmutableMap<K, V>] */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    RegularImmutableMap(int r7, com.google.common.collect.ImmutableMapEntry.TerminalEntry<?, ?>[] r8) {
        /*
            r6 = this;
            r6.<init>()
            com.google.common.collect.ImmutableMapEntry[] r0 = r6.createEntryArray(r7)
            r6.entries = r0
            r0 = 4608083138725491507(0x3ff3333333333333, double:1.2)
            int r0 = com.google.common.collect.Hashing.closedTableSize(r7, r0)
            com.google.common.collect.ImmutableMapEntry[] r1 = r6.createEntryArray(r0)
            r6.table = r1
            int r0 = r0 + -1
            r6.mask = r0
            r0 = 0
        L_0x001d:
            if (r0 >= r7) goto L_0x004b
            r1 = r8[r0]
            java.lang.Object r2 = r1.getKey()
            int r3 = r2.hashCode()
            int r3 = com.google.common.collect.Hashing.smear(r3)
            int r4 = r6.mask
            r3 = r3 & r4
            com.google.common.collect.ImmutableMapEntry<K, V>[] r4 = r6.table
            r4 = r4[r3]
            if (r4 != 0) goto L_0x0037
            goto L_0x003d
        L_0x0037:
            com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry r5 = new com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry
            r5.<init>(r1, r4)
            r1 = r5
        L_0x003d:
            com.google.common.collect.ImmutableMapEntry<K, V>[] r5 = r6.table
            r5[r3] = r1
            com.google.common.collect.ImmutableMapEntry<K, V>[] r3 = r6.entries
            r3[r0] = r1
            r6.checkNoConflictInBucket(r2, r1, r4)
            int r0 = r0 + 1
            goto L_0x001d
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.<init>(int, com.google.common.collect.ImmutableMapEntry$TerminalEntry[]):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.google.common.collect.RegularImmutableMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    RegularImmutableMap(Map.Entry<?, ?>[] entryArr) {
        ImmutableMapEntry<K, V> immutableMapEntry;
        int length = entryArr.length;
        this.entries = createEntryArray(length);
        int closedTableSize = Hashing.closedTableSize(length, 1.2d);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i = 0; i < length; i++) {
            Map.Entry<?, ?> entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int smear = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> immutableMapEntry2 = this.table[smear];
            if (immutableMapEntry2 == null) {
                immutableMapEntry = new ImmutableMapEntry.TerminalEntry<>(key, value);
            } else {
                immutableMapEntry = new NonTerminalMapEntry<>(key, value, immutableMapEntry2);
            }
            this.table[smear] = immutableMapEntry;
            this.entries[i] = immutableMapEntry;
            checkNoConflictInBucket(key, immutableMapEntry, immutableMapEntry2);
        }
    }

    private void checkNoConflictInBucket(K k, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
        while (immutableMapEntry2 != null) {
            ImmutableMap.checkNoConflict(!k.equals(immutableMapEntry2.getKey()), "key", immutableMapEntry, immutableMapEntry2);
            immutableMapEntry2 = immutableMapEntry2.getNextInKeyBucket();
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }

        NonTerminalMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> immutableMapEntry = this.table[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
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

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
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
