package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC06730pi;
import X.AnonymousClass02Z;
import X.AnonymousClass0m9;
import X.C001206j;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.NaturalOrdering;

public final class ImmutableSortedSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0m9 r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSortedSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC06730pi<Object> A0R() {
        return new C001206j(NaturalOrdering.A00);
    }

    public ImmutableSortedSetDeserializer(AnonymousClass02Z r1, AnonymousClass0m9 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
