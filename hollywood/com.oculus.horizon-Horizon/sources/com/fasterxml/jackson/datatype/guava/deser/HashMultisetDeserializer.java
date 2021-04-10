package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass02Z;
import X.AnonymousClass0m9;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.HashMultiset;

public final class HashMultisetDeserializer extends GuavaMultisetDeserializer<HashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AnonymousClass0m9 r3, JsonDeserializer jsonDeserializer) {
        return new HashMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    /* Return type fixed from 'X.0r9' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final HashMultiset<Object> A0R() {
        return new HashMultiset();
    }

    public HashMultisetDeserializer(AnonymousClass02Z r1, AnonymousClass0m9 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
