package com.google.common.collect;

import java.util.Map;

/* access modifiers changed from: package-private */
public final class EmptyImmutableBiMap extends ImmutableBiMap<Object, Object> {
    static final EmptyImmutableBiMap INSTANCE = new EmptyImmutableBiMap();

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public Object get(Object obj) {
        return null;
    }

    @Override // com.google.common.collect.ImmutableBiMap
    public ImmutableBiMap<Object, Object> inverse() {
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 0;
    }

    private EmptyImmutableBiMap() {
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Map.Entry<Object, Object>> entrySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<Object, Object>> createEntrySet() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Object> keySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }
}
