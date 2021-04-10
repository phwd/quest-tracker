package com.oculus.util.lru;

import com.facebook.infer.annotation.Nullsafe;
import java.util.LinkedHashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 25;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final int maxEntries;

    public LinkedHashMapLRUCache(int i, float f, int i2) {
        super(i, f, true);
        this.maxEntries = i2;
    }

    public LinkedHashMapLRUCache(int i, int i2) {
        this(i, DEFAULT_LOAD_FACTOR, i2);
    }

    public LinkedHashMapLRUCache(int i) {
        this(25, i);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > this.maxEntries;
    }
}
