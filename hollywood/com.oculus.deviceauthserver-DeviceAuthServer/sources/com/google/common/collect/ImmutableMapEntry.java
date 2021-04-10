package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtIncompatible("unnecessary")
public abstract class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {
    /* access modifiers changed from: package-private */
    @Nullable
    public abstract ImmutableMapEntry<K, V> getNextInKeyBucket();

    /* access modifiers changed from: package-private */
    @Nullable
    public abstract ImmutableMapEntry<K, V> getNextInValueBucket();

    ImmutableMapEntry(K key, V value) {
        super(key, value);
        CollectPreconditions.checkEntryNotNull(key, value);
    }

    ImmutableMapEntry(ImmutableMapEntry<K, V> contents) {
        super(contents.getKey(), contents.getValue());
    }

    /* access modifiers changed from: package-private */
    public static final class TerminalEntry<K, V> extends ImmutableMapEntry<K, V> {
        TerminalEntry(ImmutableMapEntry<K, V> contents) {
            super(contents);
        }

        TerminalEntry(K key, V value) {
            super(key, value);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        @Nullable
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return null;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntry
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }
    }
}
