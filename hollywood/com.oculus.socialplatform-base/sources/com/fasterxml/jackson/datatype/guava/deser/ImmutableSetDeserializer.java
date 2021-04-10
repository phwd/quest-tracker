package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AbstractC05160uM;
import X.AnonymousClass03E;
import X.AnonymousClass0NC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableSet;

public class ImmutableSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSet<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AbstractC04520qa r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableSetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC05160uM<Object> A0R() {
        return new AnonymousClass0NC();
    }

    public ImmutableSetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
