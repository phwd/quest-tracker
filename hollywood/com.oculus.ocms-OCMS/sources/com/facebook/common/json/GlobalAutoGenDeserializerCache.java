package com.facebook.common.json;

import android.net.Uri;
import com.facebook.graphql.modelutil.FragmentModel;
import com.facebook.graphql.modelutil.GraphQLModel;
import com.facebook.graphql.modelutil.TypeModel;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.JacksonDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.google.common.collect.Maps;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

public final class GlobalAutoGenDeserializerCache {
    private static final ConcurrentMap<Class<?>, JsonDeserializer> AUTO_GEN_DESERIALIZER_CACHE = Maps.newConcurrentMap();
    private static final FbJsonDeserializer nullDeserializer = new FbJsonDeserializer();

    static {
        AUTO_GEN_DESERIALIZER_CACHE.put(JsonNode.class, JsonNodeDeserializer.getDeserializer(JsonNode.class));
        AUTO_GEN_DESERIALIZER_CACHE.put(String.class, StringDeserializer.instance);
        AUTO_GEN_DESERIALIZER_CACHE.put(Integer.class, NumberDeserializers.find(Integer.class, Integer.class.getName()));
        AUTO_GEN_DESERIALIZER_CACHE.put(Long.class, NumberDeserializers.find(Long.class, Long.class.getName()));
        AUTO_GEN_DESERIALIZER_CACHE.put(Boolean.class, NumberDeserializers.find(Boolean.class, Boolean.class.getName()));
        AUTO_GEN_DESERIALIZER_CACHE.put(Float.class, NumberDeserializers.find(Float.class, Float.class.getName()));
        AUTO_GEN_DESERIALIZER_CACHE.put(Double.class, NumberDeserializers.find(Double.class, Double.class.getName()));
        AUTO_GEN_DESERIALIZER_CACHE.put(Uri.class, new UriDeserializer());
        AUTO_GEN_DESERIALIZER_CACHE.put(TokenBuffer.class, JacksonDeserializers.TokenBufferDeserializer.instance);
        AUTO_GEN_DESERIALIZER_CACHE.put(Object.class, UntypedObjectDeserializer.instance);
    }

    private GlobalAutoGenDeserializerCache() {
    }

    @Nullable
    public static <T> JsonDeserializer<T> getAutoGenDeserializerFromCache(Class<T> cls) {
        JsonDeserializer<T> jsonDeserializer;
        JsonDeserializer<T> jsonDeserializer2 = AUTO_GEN_DESERIALIZER_CACHE.get(cls);
        if (jsonDeserializer2 == nullDeserializer) {
            return null;
        }
        if (jsonDeserializer2 != null) {
            return jsonDeserializer2;
        }
        JsonDeserializer<T> findDeserializer = findDeserializer(cls);
        ConcurrentMap<Class<?>, JsonDeserializer> concurrentMap = AUTO_GEN_DESERIALIZER_CACHE;
        if (findDeserializer != null) {
            jsonDeserializer = findDeserializer;
        } else {
            jsonDeserializer = nullDeserializer;
        }
        concurrentMap.putIfAbsent(cls, jsonDeserializer);
        return findDeserializer;
    }

    private static JsonDeserializer findDeserializer(Class<?> cls) {
        FbJsonDeserializer fbJsonDeserializer;
        if (TypeModel.class.isAssignableFrom(cls)) {
            fbJsonDeserializer = new TypeModelBase64Deserializer();
        } else if (GraphQLModel.class.isAssignableFrom(cls) && !FragmentModel.class.isAssignableFrom(cls)) {
            return new TreeFragmentModelBase64Deserializer(cls);
        } else {
            if (JsonDeserializableFragmentModel.class.isAssignableFrom(cls)) {
                fbJsonDeserializer = new FragmentModelDeserializer();
            } else {
                fbJsonDeserializer = FBJsonDeserializeSelf.class.isAssignableFrom(cls) ? new FBJsonSelfDeserializer() : null;
            }
        }
        if (fbJsonDeserializer != null) {
            fbJsonDeserializer.init(cls);
            return fbJsonDeserializer;
        }
        JsonDeserializer findByNamingConvention = findByNamingConvention(cls.getName().replace('$', '_').concat("Deserializer"));
        return findByNamingConvention == null ? findByNamingConvention(cls.getName().concat("$Deserializer")) : findByNamingConvention;
    }

    @Nullable
    private static JsonDeserializer findByNamingConvention(String str) {
        try {
            return (JsonDeserializer) Class.forName(str).newInstance();
        } catch (ClassNotFoundException | ExceptionInInitializerError | IllegalAccessException | InstantiationException | NoClassDefFoundError unused) {
            return null;
        }
    }
}
