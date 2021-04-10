package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AnonymousClass03E;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.LinkedHashMultiset;

public class LinkedHashMultisetDeserializer extends GuavaMultisetDeserializer<LinkedHashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<LinkedHashMultiset<Object>> A0P(AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        return new LinkedHashMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    /* Return type fixed from 'X.0vp' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final LinkedHashMultiset<Object> A0R() {
        return new LinkedHashMultiset();
    }

    public LinkedHashMultisetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
