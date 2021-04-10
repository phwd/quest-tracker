package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.AnonymousClass3J;
import X.AnonymousClass6q;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.NaturalOrdering;

public final class ImmutableSortedSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(V4 v4, JsonDeserializer jsonDeserializer) {
        return new ImmutableSortedSetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AnonymousClass3J<Object> A0R() {
        return new AnonymousClass6q(NaturalOrdering.A00);
    }

    public ImmutableSortedSetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
