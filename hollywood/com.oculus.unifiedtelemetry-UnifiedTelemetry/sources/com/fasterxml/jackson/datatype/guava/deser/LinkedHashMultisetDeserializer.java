package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.LinkedHashMultiset;

public final class LinkedHashMultisetDeserializer extends GuavaMultisetDeserializer<LinkedHashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<LinkedHashMultiset<Object>> A0P(V4 v4, JsonDeserializer<?> jsonDeserializer) {
        return new LinkedHashMultisetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    /* Return type fixed from 'X.34' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final LinkedHashMultiset<Object> A0R() {
        return new LinkedHashMultiset();
    }

    public LinkedHashMultisetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
