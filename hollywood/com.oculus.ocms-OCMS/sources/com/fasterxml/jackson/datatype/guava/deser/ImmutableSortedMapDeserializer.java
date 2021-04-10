package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

public class ImmutableSortedMapDeserializer extends GuavaImmutableMapDeserializer<ImmutableSortedMap<Object, Object>> {
    public ImmutableSortedMapDeserializer(MapType mapType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer
    public ImmutableMap.Builder<Object, Object> createBuilder() {
        return ImmutableSortedMap.naturalOrder();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public GuavaMapDeserializer<ImmutableSortedMap<Object, Object>> withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableSortedMapDeserializer(this._mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }
}
