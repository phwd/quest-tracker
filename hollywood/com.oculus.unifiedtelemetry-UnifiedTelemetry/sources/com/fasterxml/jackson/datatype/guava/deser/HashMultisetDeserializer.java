package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.HashMultiset;

public final class HashMultisetDeserializer extends GuavaMultisetDeserializer<HashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(V4 v4, JsonDeserializer jsonDeserializer) {
        return new HashMultisetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    /* Return type fixed from 'X.34' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final HashMultiset<Object> A0R() {
        return new HashMultiset();
    }

    public HashMultisetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
