package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC07410rk;
import X.AnonymousClass0o3;
import X.C006606d;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;

public final class ImmutableListDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableList<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0o3 r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableListDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC07410rk A0R() {
        return ImmutableList.builder();
    }

    public ImmutableListDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
