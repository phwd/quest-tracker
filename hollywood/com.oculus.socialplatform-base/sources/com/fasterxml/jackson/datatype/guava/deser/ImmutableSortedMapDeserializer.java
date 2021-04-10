package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AnonymousClass03D;
import X.AnonymousClass0fJ;
import X.AnonymousClass0p6;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.NaturalOrdering;

public class ImmutableSortedMapDeserializer extends GuavaImmutableMapDeserializer<ImmutableSortedMap<Object, Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public final GuavaMapDeserializer<ImmutableSortedMap<Object, Object>> A0F(AnonymousClass0p6 r3, AbstractC04520qa r4, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableSortedMapDeserializer(this.A03, r3, r4, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableMapDeserializer
    public final ImmutableMap.Builder<Object, Object> A0H() {
        return new AnonymousClass0fJ(NaturalOrdering.A00);
    }

    public ImmutableSortedMapDeserializer(AnonymousClass03D r1, AnonymousClass0p6 r2, AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, r3, jsonDeserializer);
    }
}
