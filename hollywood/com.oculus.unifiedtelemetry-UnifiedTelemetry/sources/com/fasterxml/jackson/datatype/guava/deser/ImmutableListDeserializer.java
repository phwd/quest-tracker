package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.AnonymousClass3J;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;

public final class ImmutableListDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableList<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(V4 v4, JsonDeserializer jsonDeserializer) {
        return new ImmutableListDeserializer(this._containerType, v4, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AnonymousClass3J A0R() {
        return ImmutableList.A02();
    }

    public ImmutableListDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
