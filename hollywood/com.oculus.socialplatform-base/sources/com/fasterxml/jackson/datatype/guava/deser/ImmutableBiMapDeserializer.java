package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AnonymousClass03D;
import X.AnonymousClass0fU;
import X.AnonymousClass0p6;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

public class ImmutableBiMapDeserializer extends GuavaImmutableMapDeserializer<ImmutableBiMap<Object, Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public final GuavaMapDeserializer<ImmutableBiMap<Object, Object>> A0F(AnonymousClass0p6 r3, AbstractC04520qa r4, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableBiMapDeserializer(this.A03, r3, r4, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer
    public final ImmutableMap.Builder<Object, Object> A0H() {
        return new AnonymousClass0fU();
    }

    public ImmutableBiMapDeserializer(AnonymousClass03D r1, AnonymousClass0p6 r2, AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, r3, jsonDeserializer);
    }
}
