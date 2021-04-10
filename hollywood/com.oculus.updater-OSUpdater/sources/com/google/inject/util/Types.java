package com.google.inject.util;

import com.google.inject.internal.MoreTypes;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    public static ParameterizedType providerOf(Type type) {
        return newParameterizedType(Provider.class, type);
    }
}
