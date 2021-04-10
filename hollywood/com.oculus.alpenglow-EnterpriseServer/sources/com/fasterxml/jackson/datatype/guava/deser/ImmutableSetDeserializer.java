package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC07410rk;
import X.AnonymousClass0J8;
import X.AnonymousClass0o3;
import X.C006606d;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSet;

public final class ImmutableSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0o3 r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC07410rk<Object> A0R() {
        return new AnonymousClass0J8();
    }

    public ImmutableSetDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
