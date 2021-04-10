package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.Method;
import java.util.HashMap;

public class EnumResolver<T extends Enum<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    protected EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructFor(Class<ET> cls, AnnotationIntrospector annotationIntrospector) {
        ET[] enumConstants = cls.getEnumConstants();
        if (enumConstants != null) {
            HashMap hashMap = new HashMap();
            for (ET et : enumConstants) {
                hashMap.put(annotationIntrospector.findEnumValue(et), et);
            }
            return new EnumResolver<>(cls, enumConstants, hashMap);
        }
        throw new IllegalArgumentException("No enum constants for class " + cls.getName());
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingToString(Class<ET> cls) {
        ET[] enumConstants = cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver<>(cls, enumConstants, hashMap);
            }
            ET et = enumConstants[length];
            hashMap.put(et.toString(), et);
        }
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingMethod(Class<ET> cls, Method method) {
        ET[] enumConstants = cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver<>(cls, enumConstants, hashMap);
            }
            ET et = enumConstants[length];
            try {
                Object invoke = method.invoke(et, new Object[0]);
                if (invoke != null) {
                    hashMap.put(invoke.toString(), et);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + et + ": " + e.getMessage());
            }
        }
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public static EnumResolver<?> constructUnsafeUsingMethod(Class<?> cls, Method method) {
        return constructUsingMethod(cls, method);
    }

    public T findEnum(String str) {
        return this._enumsById.get(str);
    }

    public T getEnum(int i) {
        if (i < 0) {
            return null;
        }
        T[] tArr = this._enums;
        if (i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
