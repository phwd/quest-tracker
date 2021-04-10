package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class EnumValues {
    private final Class<Enum<?>> _enumClass;
    private final EnumMap<?, SerializedString> _values;

    private EnumValues(Class<Enum<?>> cls, Map<Enum<?>, SerializedString> map) {
        this._enumClass = cls;
        this._values = new EnumMap<>(map);
    }

    public static EnumValues construct(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFromName(cls, annotationIntrospector);
    }

    public static EnumValues constructFromName(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum<?>[] enumArr = (Enum[]) ClassUtil.findEnumType(cls).getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum<?> r4 : enumArr) {
                hashMap.put(r4, new SerializedString(annotationIntrospector.findEnumValue(r4)));
            }
            return new EnumValues(cls, hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public static EnumValues constructFromToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType(cls).getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r3 : enumArr) {
                hashMap.put(r3, new SerializedString(r3.toString()));
            }
            return new EnumValues(cls, hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public SerializedString serializedValueFor(Enum<?> r2) {
        return this._values.get(r2);
    }

    public Collection<SerializedString> values() {
        return this._values.values();
    }

    public EnumMap<?, SerializedString> internalMap() {
        return this._values;
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}
