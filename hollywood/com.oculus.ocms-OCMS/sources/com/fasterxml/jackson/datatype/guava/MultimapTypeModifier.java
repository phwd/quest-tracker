package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.google.common.collect.Multimap;
import java.lang.reflect.Type;

public class MultimapTypeModifier extends TypeModifier {
    @Override // com.fasterxml.jackson.databind.type.TypeModifier
    public JavaType modifyType(JavaType javaType, Type type, TypeBindings typeBindings, TypeFactory typeFactory) {
        if (!Multimap.class.isAssignableFrom(javaType.getRawClass())) {
            return javaType;
        }
        JavaType containedType = javaType.containedType(0);
        JavaType containedType2 = javaType.containedType(1);
        if (containedType == null) {
            containedType = typeFactory.constructType(Object.class);
        }
        if (containedType2 == null) {
            containedType2 = typeFactory.constructType(Object.class);
        }
        return typeFactory.constructMapLikeType(javaType.getRawClass(), containedType, containedType2);
    }
}
