package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.AnonymousClass3J;
import X.BQ;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSet;

public final class ImmutableSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(V4 v4, JsonDeserializer jsonDeserializer) {
        return new ImmutableSetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AnonymousClass3J<Object> A0R() {
        return new BQ();
    }

    public ImmutableSetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
