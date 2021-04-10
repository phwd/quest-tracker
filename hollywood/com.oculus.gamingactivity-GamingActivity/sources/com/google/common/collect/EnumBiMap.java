package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Enum;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class EnumBiMap<K extends Enum<K>, V extends Enum<V>> extends AbstractBiMap<K, V> {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    private transient Class<K> keyType;
    private transient Class<V> valueType;

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap
    public /* bridge */ /* synthetic */ boolean containsValue(@NullableDecl Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.AbstractBiMap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object forcePut(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.forcePut(obj, obj2);
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
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object put(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.put(obj, obj2);
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ void putAll(Map map) {
        super.putAll(map);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, java.util.Map
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object remove(@NullableDecl Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, com.google.common.collect.AbstractBiMap, com.google.common.collect.AbstractBiMap, java.util.Map
    public /* bridge */ /* synthetic */ Set values() {
        return super.values();
    }

    public static <K extends Enum<K>, V extends Enum<V>> EnumBiMap<K, V> create(Class<K> keyType2, Class<V> valueType2) {
        return new EnumBiMap<>(keyType2, valueType2);
    }

    public static <K extends Enum<K>, V extends Enum<V>> EnumBiMap<K, V> create(Map<K, V> map) {
        EnumBiMap<K, V> bimap = create(inferKeyType(map), inferValueType(map));
        bimap.putAll(map);
        return bimap;
    }

    private EnumBiMap(Class<K> keyType2, Class<V> valueType2) {
        super(WellBehavedMap.wrap(new EnumMap(keyType2)), WellBehavedMap.wrap(new EnumMap(valueType2)));
        this.keyType = keyType2;
        this.valueType = valueType2;
    }

    static <K extends Enum<K>> Class<K> inferKeyType(Map<K, ?> map) {
        if (map instanceof EnumBiMap) {
            return ((EnumBiMap) map).keyType();
        }
        if (map instanceof EnumHashBiMap) {
            return ((EnumHashBiMap) map).keyType();
        }
        Preconditions.checkArgument(!map.isEmpty());
        return map.keySet().iterator().next().getDeclaringClass();
    }

    private static <V extends Enum<V>> Class<V> inferValueType(Map<?, V> map) {
        if (map instanceof EnumBiMap) {
            return ((EnumBiMap) map).valueType;
        }
        Preconditions.checkArgument(!map.isEmpty());
        return map.values().iterator().next().getDeclaringClass();
    }

    public Class<K> keyType() {
        return this.keyType;
    }

    public Class<V> valueType() {
        return this.valueType;
    }

    /* access modifiers changed from: package-private */
    public K checkKey(K key) {
        return (K) ((Enum) Preconditions.checkNotNull(key));
    }

    /* access modifiers changed from: package-private */
    public V checkValue(V value) {
        return (V) ((Enum) Preconditions.checkNotNull(value));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.keyType);
        stream.writeObject(this.valueType);
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyType = (Class) stream.readObject();
        this.valueType = (Class) stream.readObject();
        setDelegates(WellBehavedMap.wrap(new EnumMap(this.keyType)), WellBehavedMap.wrap(new EnumMap(this.valueType)));
        Serialization.populateMap(this, stream);
    }
}
