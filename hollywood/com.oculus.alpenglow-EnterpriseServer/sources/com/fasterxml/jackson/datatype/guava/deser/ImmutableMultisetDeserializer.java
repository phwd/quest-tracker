package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC07410rk;
import X.AnonymousClass0Y8;
import X.AnonymousClass0o3;
import X.C006606d;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMultiset;

public final class ImmutableMultisetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC07410rk<Object> A0R() {
        return new AnonymousClass0Y8(4);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<ImmutableMultiset<Object>> A0P(AnonymousClass0o3 r3, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    public ImmutableMultisetDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
