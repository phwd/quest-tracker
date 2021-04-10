package com.fasterxml.jackson.datatype.guava.deser;

import com.google.common.collect.ImmutableCollection;

public abstract class GuavaImmutableCollectionDeserializer<T extends ImmutableCollection<Object>> extends GuavaCollectionDeserializer<T> {
}
