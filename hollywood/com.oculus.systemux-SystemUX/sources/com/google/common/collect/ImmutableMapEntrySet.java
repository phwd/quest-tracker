package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<K, V> map();

    static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
        private final transient ImmutableList<Map.Entry<K, V>> entries;
        @Weak
        private final transient ImmutableMap<K, V> map;

        RegularEntrySet(ImmutableMap<K, V> immutableMap, Map.Entry<K, V>[] entryArr) {
            this(immutableMap, ImmutableList.asImmutableList(entryArr));
        }

        RegularEntrySet(ImmutableMap<K, V> immutableMap, ImmutableList<Map.Entry<K, V>> immutableList) {
            this.map = immutableMap;
            this.entries = immutableList;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntrySet
        public ImmutableMap<K, V> map() {
            return this.map;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        @GwtIncompatible("not used in GWT")
        public int copyIntoArray(Object[] objArr, int i) {
            return this.entries.copyIntoArray(objArr, i);
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.entries.iterator();
        }

        @Override // java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return this.entries.spliterator();
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
            this.entries.forEach(consumer);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, this.entries);
        }
    }

    ImmutableMapEntrySet() {
    }

    public int size() {
        return map().size();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        V v = map().get(entry.getKey());
        if (v == null || !v.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return map().isPartialView();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    @GwtIncompatible
    public boolean isHashCodeFast() {
        return map().isHashCodeFast();
    }

    @Override // com.google.common.collect.ImmutableSet
    public int hashCode() {
        return map().hashCode();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new EntrySetSerializedForm(map());
    }

    @GwtIncompatible
    private static class EntrySetSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, V> map;

        EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.entrySet();
        }
    }
}
