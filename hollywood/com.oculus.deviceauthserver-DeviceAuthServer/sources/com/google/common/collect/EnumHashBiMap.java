package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Enum;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class EnumHashBiMap<K extends Enum<K>, V> extends AbstractBiMap<K, V> {
    @GwtIncompatible("only needed in emulated source.")
    private static final long serialVersionUID = 0;
    private transient Class<K> keyType;

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap
    public /* bridge */ /* synthetic */ boolean containsValue(@Nullable Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.AbstractBiMap
    public /* bridge */ /* synthetic */ BiMap inverse() {
        return super.inverse();
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ void putAll(Map map) {
        super.putAll(map);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Object remove(@Nullable Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Set values() {
        return super.values();
    }

    public static <K extends Enum<K>, V> EnumHashBiMap<K, V> create(Class<K> keyType2) {
        return new EnumHashBiMap<>(keyType2);
    }

    public static <K extends Enum<K>, V> EnumHashBiMap<K, V> create(Map<K, ? extends V> map) {
        EnumHashBiMap<K, V> bimap = create(EnumBiMap.inferKeyType(map));
        bimap.putAll(map);
        return bimap;
    }

    private EnumHashBiMap(Class<K> keyType2) {
        super(WellBehavedMap.wrap(new EnumMap(keyType2)), Maps.newHashMapWithExpectedSize(keyType2.getEnumConstants().length));
        this.keyType = keyType2;
    }

    /* access modifiers changed from: package-private */
    public K checkKey(K key) {
        return (K) ((Enum) Preconditions.checkNotNull(key));
    }

    public V put(K key, @Nullable V value) {
        return (V) super.put((Object) key, (Object) value);
    }

    public V forcePut(K key, @Nullable V value) {
        return (V) super.forcePut((Object) key, (Object) value);
    }

    public Class<K> keyType() {
        return this.keyType;
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.keyType);
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible("java.io.ObjectInputStream")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyType = (Class) stream.readObject();
        setDelegates(WellBehavedMap.wrap(new EnumMap(this.keyType)), new HashMap((this.keyType.getEnumConstants().length * 3) / 2));
        Serialization.populateMap(this, stream);
    }
}
