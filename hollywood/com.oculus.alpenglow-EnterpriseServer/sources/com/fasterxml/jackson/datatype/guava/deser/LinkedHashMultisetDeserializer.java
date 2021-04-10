package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass0o3;
import X.C006606d;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.LinkedHashMultiset;

public final class LinkedHashMultisetDeserializer extends GuavaMultisetDeserializer<LinkedHashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<LinkedHashMultiset<Object>> A0P(AnonymousClass0o3 r3, JsonDeserializer<?> jsonDeserializer) {
        return new LinkedHashMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    /* Return type fixed from 'X.0tC' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final LinkedHashMultiset<Object> A0R() {
        return new LinkedHashMultiset();
    }

    public LinkedHashMultisetDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
