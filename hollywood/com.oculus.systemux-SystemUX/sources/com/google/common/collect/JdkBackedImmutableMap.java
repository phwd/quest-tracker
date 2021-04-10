package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntrySet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class JdkBackedImmutableMap<K, V> extends ImmutableMap<K, V> {
    private final transient Map<K, V> delegateMap;
    private final transient ImmutableList<Map.Entry<K, V>> entries;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    static <K, V> ImmutableMap<K, V> create(int i, Map.Entry<K, V>[] entryArr) {
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            entryArr[i2] = RegularImmutableMap.makeImmutable(entryArr[i2]);
            Object putIfAbsent = newHashMapWithExpectedSize.putIfAbsent(entryArr[i2].getKey(), entryArr[i2].getValue());
            if (putIfAbsent != null) {
                Map.Entry<K, V> entry = entryArr[i2];
                throw conflictException("key", entry, ((Object) entryArr[i2].getKey()) + "=" + putIfAbsent);
            }
        }
        return new JdkBackedImmutableMap(newHashMapWithExpectedSize, ImmutableList.asImmutableList(entryArr, i));
    }

    JdkBackedImmutableMap(Map<K, V> map, ImmutableList<Map.Entry<K, V>> immutableList) {
        this.delegateMap = map;
        this.entries = immutableList;
    }

    public int size() {
        return this.entries.size();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        return this.delegateMap.get(obj);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        this.entries.forEach(new Consumer(biConsumer) {
            /* class com.google.common.collect.$$Lambda$JdkBackedImmutableMap$ThhB9akQzP54Zy7ZoDbOMOzR7V8 */
            private final /* synthetic */ BiConsumer f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry;
                this.f$0.accept(entry.getKey(), ((Map.Entry) obj).getValue());
            }
        });
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableCollection<V> createValues() {
        return new ImmutableMapValues(this);
    }
}
