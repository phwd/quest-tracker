package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _keyClass;

    /* access modifiers changed from: protected */
    public abstract Object _parse(String str, DeserializationContext deserializationContext) throws Exception;

    protected StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    @Override // com.fasterxml.jackson.databind.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (str == null) {
            return null;
        }
        try {
            Object _parse = _parse(str, deserializationContext);
            if (_parse != null) {
                return _parse;
            }
            if (this._keyClass.isEnum() && deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            Class<?> cls = this._keyClass;
            throw deserializationContext.weirdKeyException(cls, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }

    /* access modifiers changed from: protected */
    public int _parseInt(String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    /* access modifiers changed from: protected */
    public long _parseLong(String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    /* access modifiers changed from: protected */
    public double _parseDouble(String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }

    @JacksonStdImpl
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sObject = new StringKD(Object.class);
        private static final StringKD sString = new StringKD(String.class);
        private static final long serialVersionUID = 1;

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public String _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return str;
        }

        private StringKD(Class<?> cls) {
            super(cls);
        }

        public static StringKD forType(Class<?> cls) {
            if (cls == String.class) {
                return sString;
            }
            if (cls == Object.class) {
                return sObject;
            }
            return new StringKD(cls);
        }
    }

    @JacksonStdImpl
    static final class BoolKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        BoolKD() {
            super(Boolean.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Boolean _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    @JacksonStdImpl
    static final class ByteKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        ByteKD() {
            super(Byte.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Byte _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -128 && _parseInt <= 255) {
                return Byte.valueOf((byte) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
        }
    }

    @JacksonStdImpl
    static final class ShortKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        ShortKD() {
            super(Integer.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Short _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -32768 && _parseInt <= 32767) {
                return Short.valueOf((short) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
        }
    }

    @JacksonStdImpl
    static final class CharKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        CharKD() {
            super(Character.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Character _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    @JacksonStdImpl
    static final class IntKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        IntKD() {
            super(Integer.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Integer _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Integer.valueOf(_parseInt(str));
        }
    }

    @JacksonStdImpl
    static final class LongKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        LongKD() {
            super(Long.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Long _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Long.valueOf(_parseLong(str));
        }
    }

    @JacksonStdImpl
    static final class DoubleKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        DoubleKD() {
            super(Double.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Double _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Double.valueOf(_parseDouble(str));
        }
    }

    @JacksonStdImpl
    static final class FloatKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        FloatKD() {
            super(Float.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Float _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    @JacksonStdImpl
    static final class LocaleKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected JdkDeserializers.LocaleDeserializer _localeDeserializer = new JdkDeserializers.LocaleDeserializer();

        LocaleKD() {
            super(Locale.class);
        }

        /* access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Locale _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            try {
                return this._localeDeserializer._deserialize(str, deserializationContext);
            } catch (IOException unused) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "unable to parse key as locale");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DelegatingKD extends KeyDeserializer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final JsonDeserializer<?> _delegate;
        protected final Class<?> _keyClass;

        protected DelegatingKD(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
            this._keyClass = cls;
            this._delegate = jsonDeserializer;
        }

        @Override // com.fasterxml.jackson.databind.KeyDeserializer
        public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (str == null) {
                return null;
            }
            try {
                Object deserialize = this._delegate.deserialize(deserializationContext.getParser(), deserializationContext);
                if (deserialize != null) {
                    return deserialize;
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
            } catch (Exception e) {
                Class<?> cls = this._keyClass;
                throw deserializationContext.weirdKeyException(cls, str, "not a valid representation: " + e.getMessage());
            }
        }

        public Class<?> getKeyClass() {
            return this._keyClass;
        }
    }

    /* access modifiers changed from: package-private */
    @JacksonStdImpl
    public static final class EnumKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final AnnotatedMethod _factory;
        protected final EnumResolver<?> _resolver;

        protected EnumKD(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
            this._factory = annotatedMethod;
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            AnnotatedMethod annotatedMethod = this._factory;
            if (annotatedMethod != null) {
                try {
                    return annotatedMethod.call1(str);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            Enum findEnum = this._resolver.findEnum(str);
            if (findEnum != null || deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return findEnum;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._ctor.newInstance(str);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._factoryMethod.invoke(null, str);
        }
    }

    @JacksonStdImpl
    static final class DateKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        protected DateKD() {
            super(Date.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return deserializationContext.parseDate(str);
        }
    }

    @JacksonStdImpl
    static final class CalendarKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        protected CalendarKD() {
            super(Calendar.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            Date parseDate = deserializationContext.parseDate(str);
            if (parseDate == null) {
                return null;
            }
            return deserializationContext.constructCalendar(parseDate);
        }
    }

    @JacksonStdImpl
    static final class UuidKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        protected UuidKD() {
            super(UUID.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return UUID.fromString(str);
        }
    }
}
