package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSortedSet;

public class ImmutableSortedSetDeserializer extends GuavaImmutableCollectionDeserializer<ImmutableSortedSet<Object>> {
    public ImmutableSortedSetDeserializer(CollectionType collectionType, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(collectionType, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public ImmutableSortedSetDeserializer withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new ImmutableSortedSetDeserializer(this._containerType, typeDeserializer, jsonDeserializer);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaImmutableCollectionDeserializer
    public ImmutableCollection.Builder<Object> createBuilder() {
        return ImmutableSortedSet.naturalOrder();
    }
}
