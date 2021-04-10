package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MultimapDeserializer extends JsonDeserializer<Multimap<?, ?>> implements ContextualDeserializer {
    private static final List<String> METHOD_NAMES = ImmutableList.of("copyOf", "create");
    private final Method creatorMethod;
    private final JsonDeserializer<?> elementDeserializer;
    private final TypeDeserializer elementTypeDeserializer;
    private final KeyDeserializer keyDeserializer;
    private final MapLikeType type;

    public MultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer2, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        this(mapLikeType, keyDeserializer2, typeDeserializer, jsonDeserializer, findTransformer(mapLikeType.getRawClass()));
    }

    public MultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer2, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method) {
        this.type = mapLikeType;
        this.keyDeserializer = keyDeserializer2;
        this.elementTypeDeserializer = typeDeserializer;
        this.elementDeserializer = jsonDeserializer;
        this.creatorMethod = method;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializer2 = this.keyDeserializer;
        if (keyDeserializer2 == null) {
            keyDeserializer2 = deserializationContext.findKeyDeserializer(this.type.getKeyType(), beanProperty);
        }
        JsonDeserializer<?> jsonDeserializer = this.elementDeserializer;
        JsonDeserializer<?> findContextualValueDeserializer = jsonDeserializer == null ? deserializationContext.findContextualValueDeserializer(this.type.getContentType(), beanProperty) : jsonDeserializer;
        TypeDeserializer typeDeserializer = this.elementTypeDeserializer;
        if (!(typeDeserializer == null || beanProperty == null)) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return new MultimapDeserializer(this.type, keyDeserializer2, typeDeserializer, findContextualValueDeserializer, this.creatorMethod);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Multimap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object obj;
        LinkedListMultimap create = LinkedListMultimap.create();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            KeyDeserializer keyDeserializer2 = this.keyDeserializer;
            if (keyDeserializer2 != null) {
                obj = keyDeserializer2.deserializeKey(jsonParser.getCurrentName(), deserializationContext);
            } else {
                obj = jsonParser.getCurrentName();
            }
            jsonParser.nextToken();
            expect(jsonParser, JsonToken.START_ARRAY);
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                TypeDeserializer typeDeserializer = this.elementTypeDeserializer;
                if (typeDeserializer != null) {
                    create.put(obj, this.elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer));
                } else {
                    create.put(obj, this.elementDeserializer.deserialize(jsonParser, deserializationContext));
                }
            }
        }
        Method method = this.creatorMethod;
        if (method == null) {
            return create;
        }
        try {
            return (Multimap) method.invoke(null, create);
        } catch (InvocationTargetException e) {
            throw new JsonMappingException("Could not map to " + this.type, _peel(e));
        } catch (IllegalArgumentException e2) {
            throw new JsonMappingException("Could not map to " + this.type, _peel(e2));
        } catch (IllegalAccessException e3) {
            throw new JsonMappingException("Could not map to " + this.type, _peel(e3));
        }
    }

    private static Method findTransformer(Class<?> cls) {
        if (!(cls == LinkedListMultimap.class || cls == ListMultimap.class || cls == Multimap.class)) {
            for (String str : METHOD_NAMES) {
                try {
                    Method method = cls.getMethod(str, Multimap.class);
                    if (method != null) {
                        return method;
                    }
                } catch (NoSuchMethodException unused) {
                }
            }
            for (String str2 : METHOD_NAMES) {
                try {
                    Method method2 = cls.getMethod(str2, Multimap.class);
                    if (method2 != null) {
                        return method2;
                    }
                } catch (NoSuchMethodException unused2) {
                }
            }
        }
        return null;
    }

    private void expect(JsonParser jsonParser, JsonToken jsonToken) throws IOException {
        if (jsonParser.getCurrentToken() != jsonToken) {
            throw new JsonMappingException("Expecting " + jsonToken + ", found " + jsonParser.getCurrentToken(), jsonParser.getCurrentLocation());
        }
    }

    private Throwable _peel(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }
}
