package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AbstractC05160uM;
import X.AnonymousClass03E;
import X.AnonymousClass0fM;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMultiset;

public class ImmutableMultisetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public final AbstractC05160uM<Object> A0R() {
        return new AnonymousClass0fM(4);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<ImmutableMultiset<Object>> A0P(AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    public ImmutableMultisetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
