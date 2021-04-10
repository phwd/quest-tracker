package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.datatype.guava.deser.GuavaOptionalDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.HashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableBiMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableListDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.LinkedHashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.MultimapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.TreeMultisetDeserializer;
import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import com.google.common.collect.TreeMultiset;

public class GuavaDeserializers extends Deserializers.Base {
    @Override // com.fasterxml.jackson.databind.deser.Deserializers, com.fasterxml.jackson.databind.deser.Deserializers.Base
    public JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        if (Optional.class.isAssignableFrom(javaType.getRawClass())) {
            return new GuavaOptionalDeserializer(javaType);
        }
        return super.findBeanDeserializer(javaType, deserializationConfig, beanDescription);
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers, com.fasterxml.jackson.databind.deser.Deserializers.Base
    public JsonDeserializer<?> findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = collectionType.getRawClass();
        if (ImmutableCollection.class.isAssignableFrom(rawClass)) {
            if (ImmutableList.class.isAssignableFrom(rawClass)) {
                return new ImmutableListDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            if (ImmutableMultiset.class.isAssignableFrom(rawClass)) {
                return new ImmutableMultisetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            if (!ImmutableSet.class.isAssignableFrom(rawClass)) {
                return new ImmutableListDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            if (!ImmutableSortedSet.class.isAssignableFrom(rawClass)) {
                return new ImmutableSetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            if (Comparable.class.isAssignableFrom(collectionType.getContentType().getRawClass())) {
                return new ImmutableSortedSetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            throw new IllegalArgumentException("Can not handle ImmutableSortedSet with elements that are not Comparable<?> (" + rawClass.getName() + ")");
        } else if (!Multiset.class.isAssignableFrom(rawClass)) {
            return null;
        } else {
            if (LinkedHashMultiset.class.isAssignableFrom(rawClass)) {
                return new LinkedHashMultisetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            if (HashMultiset.class.isAssignableFrom(rawClass)) {
                return new HashMultisetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            EnumMultiset.class.isAssignableFrom(rawClass);
            if (TreeMultiset.class.isAssignableFrom(rawClass)) {
                return new TreeMultisetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
            }
            return new HashMultisetDeserializer(collectionType, typeDeserializer, jsonDeserializer);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers, com.fasterxml.jackson.databind.deser.Deserializers.Base
    public JsonDeserializer<?> findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = mapType.getRawClass();
        if (ImmutableMap.class.isAssignableFrom(rawClass)) {
            if (ImmutableSortedMap.class.isAssignableFrom(rawClass)) {
                return new ImmutableSortedMapDeserializer(mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            if (ImmutableBiMap.class.isAssignableFrom(rawClass)) {
                return new ImmutableBiMapDeserializer(mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            return new ImmutableMapDeserializer(mapType, keyDeserializer, typeDeserializer, jsonDeserializer);
        } else if (!BiMap.class.isAssignableFrom(rawClass)) {
            return null;
        } else {
            EnumBiMap.class.isAssignableFrom(rawClass);
            EnumHashBiMap.class.isAssignableFrom(rawClass);
            HashBiMap.class.isAssignableFrom(rawClass);
            return null;
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers, com.fasterxml.jackson.databind.deser.Deserializers.Base
    public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = mapLikeType.getRawClass();
        if (ImmutableMultimap.class.isAssignableFrom(rawClass)) {
            ImmutableListMultimap.class.isAssignableFrom(rawClass);
            ImmutableSetMultimap.class.isAssignableFrom(rawClass);
        }
        if (Multimap.class.isAssignableFrom(rawClass)) {
            return new MultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
        }
        Table.class.isAssignableFrom(rawClass);
        return null;
    }
}
