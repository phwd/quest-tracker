package com.google.inject.util;

import com.google.inject.internal.MoreTypes;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

public final class Types {
    private Types() {
    }

    public static ParameterizedType newParameterizedType(Type type, Type... typeArr) {
        return newParameterizedTypeWithOwner(null, type, typeArr);
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new MoreTypes.ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new MoreTypes.GenericArrayTypeImpl(type);
    }

    public static WildcardType subtypeOf(Type type) {
        return new MoreTypes.WildcardTypeImpl(new Type[]{type}, MoreTypes.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        return new MoreTypes.WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type});
    }

    public static ParameterizedType listOf(Type type) {
        return newParameterizedType(List.class, type);
    }

    public static ParameterizedType setOf(Type type) {
        return newParameterizedType(Set.class, type);
    }

    public static ParameterizedType mapOf(Type type, Type type2) {
        return newParameterizedType(Map.class, type, type2);
    }

    public static ParameterizedType providerOf(Type type) {
        return newParameterizedType(Provider.class, type);
    }
}
