package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC07410rk;
import X.AnonymousClass0o3;
import X.C006606d;
import X.C00880Bl;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.NaturalOrdering;

public final class ImmutableSortedSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0o3 r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSortedSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC07410rk<Object> A0R() {
        return new C00880Bl(NaturalOrdering.A00);
    }

    public ImmutableSortedSetDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
