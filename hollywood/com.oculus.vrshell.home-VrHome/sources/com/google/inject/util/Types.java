package com.google.inject.util;

import com.google.inject.internal.MoreTypes;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import javax.inject.Provider;

public final class Types {
    public static ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
        return newParameterizedTypeWithOwner(null, rawType, typeArguments);
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type... typeArguments) {
        return new MoreTypes.ParameterizedTypeImpl(ownerType, rawType, typeArguments);
    }

    public static GenericArrayType arrayOf(Type componentType) {
        return new MoreTypes.GenericArrayTypeImpl(componentType);
    }

    public static WildcardType subtypeOf(Type bound) {
        return new MoreTypes.WildcardTypeImpl(new Type[]{bound}, MoreTypes.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type bound) {
        return new MoreTypes.WildcardTypeImpl(new Type[]{Object.class}, new Type[]{bound});
    }

    public static ParameterizedType providerOf(Type providedType) {
        return newParameterizedType(Provider.class, providedType);
    }
}
