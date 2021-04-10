package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.AnonymousClass3J;
import X.C0186Un;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMultiset;

public final class ImmutableMultisetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AnonymousClass3J<Object> A0R() {
        return new C0186Un(4);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<ImmutableMultiset<Object>> A0P(V4 v4, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableMultisetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    public ImmutableMultisetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
