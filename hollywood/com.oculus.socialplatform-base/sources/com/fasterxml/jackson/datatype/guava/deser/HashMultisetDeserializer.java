package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AnonymousClass03E;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.HashMultiset;

public class HashMultisetDeserializer extends GuavaMultisetDeserializer<HashMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer A0P(AbstractC04520qa r3, JsonDeserializer jsonDeserializer) {
        return new HashMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    /* Return type fixed from 'X.0vp' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final HashMultiset<Object> A0R() {
        return new HashMultiset();
    }

    public HashMultisetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
