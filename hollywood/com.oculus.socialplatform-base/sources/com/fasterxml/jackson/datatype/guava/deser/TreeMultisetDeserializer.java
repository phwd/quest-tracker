package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04520qa;
import X.AnonymousClass03E;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;

public class TreeMultisetDeserializer extends GuavaMultisetDeserializer<TreeMultiset<Object>> {
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final GuavaCollectionDeserializer<TreeMultiset<Object>> A0P(AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        return new TreeMultisetDeserializer(this._containerType, r3, jsonDeserializer);
    }

    /* Return type fixed from 'X.0vp' to match base method */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMultisetDeserializer
    public final TreeMultiset<Object> A0R() {
        return new TreeMultiset(NaturalOrdering.A00);
    }

    public TreeMultisetDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
