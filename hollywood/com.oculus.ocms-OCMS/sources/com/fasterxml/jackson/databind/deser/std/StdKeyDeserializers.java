package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class StdKeyDeserializers implements KeyDeserializers, Serializable {
    private static final long serialVersionUID = 923268084968181479L;

    @Deprecated
    public static KeyDeserializer constructStringKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        return StdKeyDeserializer.StringKD.forType(javaType.getRawClass());
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver) {
        return new StdKeyDeserializer.EnumKD(enumResolver, null);
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
        return new StdKeyDeserializer.EnumKD(enumResolver, annotatedMethod);
    }

    public static KeyDeserializer constructDelegatingKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        return new StdKeyDeserializer.DelegatingKD(javaType.getRawClass(), jsonDeserializer);
    }

    public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        BeanDescription introspect = deserializationConfig.introspect(javaType);
        Constructor<?> findSingleArgConstructor = introspect.findSingleArgConstructor(String.class);
        if (findSingleArgConstructor != null) {
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(findSingleArgConstructor);
            }
            return new StdKeyDeserializer.StringCtorKeyDeserializer(findSingleArgConstructor);
        }
        Method findFactoryMethod = introspect.findFactoryMethod(String.class);
        if (findFactoryMethod == null) {
            return null;
        }
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findFactoryMethod);
        }
        return new StdKeyDeserializer.StringFactoryKeyDeserializer(findFactoryMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.KeyDeserializers
    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class || rawClass == Object.class) {
            return StdKeyDeserializer.StringKD.forType(rawClass);
        }
        if (rawClass == UUID.class) {
            return new StdKeyDeserializer.UuidKD();
        }
        if (rawClass.isPrimitive()) {
            rawClass = ClassUtil.wrapperType(rawClass);
        }
        if (rawClass == Integer.class) {
            return new StdKeyDeserializer.IntKD();
        }
        if (rawClass == Long.class) {
            return new StdKeyDeserializer.LongKD();
        }
        if (rawClass == Date.class) {
            return new StdKeyDeserializer.DateKD();
        }
        if (rawClass == Calendar.class) {
            return new StdKeyDeserializer.CalendarKD();
        }
        if (rawClass == Boolean.class) {
            return new StdKeyDeserializer.BoolKD();
        }
        if (rawClass == Byte.class) {
            return new StdKeyDeserializer.ByteKD();
        }
        if (rawClass == Character.class) {
            return new StdKeyDeserializer.CharKD();
        }
        if (rawClass == Short.class) {
            return new StdKeyDeserializer.ShortKD();
        }
        if (rawClass == Float.class) {
            return new StdKeyDeserializer.FloatKD();
        }
        if (rawClass == Double.class) {
            return new StdKeyDeserializer.DoubleKD();
        }
        if (rawClass == Locale.class) {
            return new StdKeyDeserializer.LocaleKD();
        }
        return null;
    }
}
