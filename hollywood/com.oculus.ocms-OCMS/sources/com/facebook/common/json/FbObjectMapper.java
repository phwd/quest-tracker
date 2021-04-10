package com.facebook.common.json;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.json.JsonLogger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;

public class FbObjectMapper extends ObjectMapper {
    public static final String SOFTERROR_TAG = "JSON_FAILURE";
    private static FbObjectMapper objectMapper;
    private boolean mHumanReadableFormatEnabled;
    private final JsonLogger mJsonLogger;

    public static synchronized FbObjectMapper getInstance() {
        FbObjectMapper fbObjectMapper;
        synchronized (FbObjectMapper.class) {
            if (objectMapper == null) {
                objectMapper = new FbObjectMapper(new JsonFactory(), new JsonLoggerStub(), false);
            }
            fbObjectMapper = objectMapper;
        }
        return fbObjectMapper;
    }

    static {
        AnonymousClass1 r12 = new BasicClassIntrospector() {
            /* class com.facebook.common.json.FbObjectMapper.AnonymousClass1 */

            @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector, com.fasterxml.jackson.databind.introspect.BasicClassIntrospector, com.fasterxml.jackson.databind.introspect.BasicClassIntrospector
            public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
                BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
                if (_findCachedDesc != null) {
                    return _findCachedDesc;
                }
                JsonDeserialize jsonDeserialize = (JsonDeserialize) javaType.getRawClass().getAnnotation(JsonDeserialize.class);
                if (jsonDeserialize == null || jsonDeserialize.using() == null) {
                    return super.forDeserialization(deserializationConfig, javaType, mixInResolver);
                }
                return super.forDirectClassAnnotations((MapperConfig<?>) deserializationConfig, javaType, mixInResolver);
            }

            @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector, com.fasterxml.jackson.databind.introspect.BasicClassIntrospector, com.fasterxml.jackson.databind.introspect.BasicClassIntrospector
            public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
                BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
                if (_findCachedDesc != null) {
                    return _findCachedDesc;
                }
                JsonSerialize jsonSerialize = (JsonSerialize) javaType.getRawClass().getAnnotation(JsonSerialize.class);
                if (jsonSerialize == null || jsonSerialize.using() == null) {
                    return super.forSerialization(serializationConfig, javaType, mixInResolver);
                }
                return super.forDirectClassAnnotations((MapperConfig<?>) serializationConfig, javaType, mixInResolver);
            }
        };
        BaseSettings baseSettings = new BaseSettings(r12, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), TimeZone.getTimeZone("GMT"), Base64Variants.getDefaultVariant());
        try {
            Field declaredField = ObjectMapper.class.getDeclaredField("DEFAULT_INTROSPECTOR");
            declaredField.setAccessible(true);
            declaredField.set(null, r12);
            Field declaredField2 = ObjectMapper.class.getDeclaredField("DEFAULT_BASE");
            declaredField2.setAccessible(true);
            declaredField2.set(null, baseSettings);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    @SuppressLint({"BadMethodUse-<init>"})
    @VisibleForTesting
    public FbObjectMapper(@Nullable JsonFactory jsonFactory, @Nullable JsonLogger jsonLogger) {
        super(jsonFactory);
        this.mJsonLogger = jsonLogger;
        registerModule(new FbGuavaModule());
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public FbObjectMapper(@Nullable JsonFactory jsonFactory, @Nullable JsonLogger jsonLogger, boolean z) {
        this(jsonFactory, jsonLogger);
        this.mHumanReadableFormatEnabled = z;
    }

    public FbObjectMapper(@Nullable JsonFactory jsonFactory) {
        this(jsonFactory, null);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ObjectMapper
    public DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return new FbSerializerProvider(this._serializerProvider, serializationConfig, this._serializerFactory, this.mJsonLogger, this.mHumanReadableFormatEnabled);
    }

    public FbObjectMapper createHumanReadableObjectMapper() {
        JsonFactory jsonFactory = new JsonFactory();
        FbObjectMapper fbObjectMapper = new FbObjectMapper(jsonFactory, this.mJsonLogger, true);
        jsonFactory.setCodec(fbObjectMapper);
        return fbObjectMapper;
    }

    public <T> JsonDeserializer<T> findDeserializer(DeserializationContext deserializationContext, Type type) throws JsonMappingException {
        if (type instanceof Class) {
            return findDeserializer(deserializationContext, (Class) ((Class) type));
        }
        return findDeserializer(deserializationContext, this._typeFactory.constructType(type));
    }

    public <T> JsonDeserializer<T> findDeserializer(DeserializationContext deserializationContext, Class<T> cls) throws JsonMappingException {
        JsonDeserializer<T> autoGenDeserializerFromCache = GlobalAutoGenDeserializerCache.getAutoGenDeserializerFromCache(cls);
        if (autoGenDeserializerFromCache == null) {
            autoGenDeserializerFromCache = (JsonDeserializer<T>) super._findRootDeserializer(deserializationContext, this._typeFactory.constructType(cls));
            JsonLogger jsonLogger = this.mJsonLogger;
            if (jsonLogger != null) {
                jsonLogger.log(JsonLogger.Event.DESERIALIZATION, cls.toString(), autoGenDeserializerFromCache);
            }
        }
        return autoGenDeserializerFromCache;
    }

    public <T> JsonDeserializer<T> findDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        JsonDeserializer autoGenDeserializerFromCache = !javaType.hasGenericTypes() ? GlobalAutoGenDeserializerCache.getAutoGenDeserializerFromCache(javaType.getRawClass()) : null;
        if (autoGenDeserializerFromCache == null) {
            autoGenDeserializerFromCache = getListOrMapDeserializerFor(javaType);
        }
        if (autoGenDeserializerFromCache == null) {
            autoGenDeserializerFromCache = super._findRootDeserializer(deserializationContext, javaType);
            JsonLogger jsonLogger = this.mJsonLogger;
            if (jsonLogger != null) {
                jsonLogger.log(JsonLogger.Event.DESERIALIZATION, javaType.toString(), autoGenDeserializerFromCache);
            }
        }
        return autoGenDeserializerFromCache;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ObjectMapper
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        return findDeserializer(deserializationContext, javaType);
    }

    @Nullable
    private <T> JsonDeserializer<T> getListOrMapDeserializerFor(JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == List.class || rawClass == ArrayList.class) {
            return new ArrayListDeserializer(javaType);
        }
        if (rawClass == ImmutableList.class) {
            return new ImmutableListDeserializer(javaType);
        }
        if (!isValidMapKeyType(javaType.containedType(0))) {
            return null;
        }
        if (rawClass == Map.class || rawClass == HashMap.class || rawClass == LinkedHashMap.class) {
            return new LinkedHashMapDeserializer(javaType);
        }
        if (rawClass == ImmutableMap.class) {
            return new ImmutableMapDeserializer(javaType);
        }
        return null;
    }

    private boolean isValidMapKeyType(@Nullable JavaType javaType) {
        if (javaType == null) {
            return false;
        }
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class || Enum.class.isAssignableFrom(rawClass)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ObjectMapper
    public Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        if (jsonParser.getCodec() == null) {
            jsonParser.setCodec(this);
        }
        return super._readMapAndClose(jsonParser, javaType);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ObjectMapper
    public Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        if (jsonParser.getCodec() == null) {
            jsonParser.setCodec(this);
        }
        return super._readValue(deserializationConfig, jsonParser, javaType);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ObjectMapper
    @VisibleForTesting
    public DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return super.createDeserializationContext(jsonParser, deserializationConfig);
    }
}
