package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AbstractC05160uM;
import X.AnonymousClass03E;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;

public class ImmutableListDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableList<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AbstractC04520qa r3, JsonDeserializer jsonDeserializer) {
        return new ImmutableListDeserializer(this._containerType, r3, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC05160uM A0R() {
        return ImmutableList.A02();
    }

    public ImmutableListDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
