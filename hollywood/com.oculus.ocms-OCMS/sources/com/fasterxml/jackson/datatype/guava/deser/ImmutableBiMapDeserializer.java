package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

public class ImmutableBiMapDeserializer extends GuavaImmutableMapDeserializer<ImmutableBiMap<Object, Object>> {
    public ImmutableBiMapDeserializer(MapType mapType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer
    public ImmutableMap.Builder<Object, Object> createBuilder() {
        return ImmutableBiMap.builder();
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public GuavaMapDeserializer<ImmutableBiMap<Object, Object>> withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableBiMapDeserializer(this._mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
    }
}
