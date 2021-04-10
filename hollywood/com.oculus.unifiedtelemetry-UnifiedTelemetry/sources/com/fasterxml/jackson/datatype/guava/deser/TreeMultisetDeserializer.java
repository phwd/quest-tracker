package com.fasterxml.jackson.datatype.guava.deser;

import X.AnonymousClass2W;
import X.V4;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;

public final class TreeMultisetDeserializer extends GuavaMultisetDeserializer<TreeMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<TreeMultiset<Object>> A0P(V4 v4, JsonDeserializer<?> jsonDeserializer) {
        return new TreeMultisetDeserializer(this._containerType, v4, jsonDeserializer);
    }

    /* Return type fixed from 'X.34' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final TreeMultiset<Object> A0R() {
        return new TreeMultiset(NaturalOrdering.A00);
    }

    public TreeMultisetDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
