package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AbstractC05160uM;
import X.AnonymousClass03E;
import X.AnonymousClass0BY;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.NaturalOrdering;

public class ImmutableSortedSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AbstractC04520qa r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSortedSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC05160uM<Object> A0R() {
        return new AnonymousClass0BY(NaturalOrdering.A00);
    }

    public ImmutableSortedSetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
