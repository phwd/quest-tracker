package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC06730pi;
import X.AnonymousClass02Z;
import X.AnonymousClass0CC;
import X.AnonymousClass0m9;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSet;

public final class ImmutableSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0m9 r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC06730pi<Object> A0R() {
        return new AnonymousClass0CC();
    }

    public ImmutableSetDeserializer(AnonymousClass02Z r1, AnonymousClass0m9 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
