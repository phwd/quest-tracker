package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.HashMap;

public final class ReadOnlyClassToSerializerMap {
    protected SerializerCache.TypeKey _cacheKey = null;
    protected final JsonSerializerMap _map;

    private ReadOnlyClassToSerializerMap(JsonSerializerMap jsonSerializerMap) {
        this._map = jsonSerializerMap;
    }

    public ReadOnlyClassToSerializerMap instance() {
        return new ReadOnlyClassToSerializerMap(this._map);
    }

    public static ReadOnlyClassToSerializerMap from(HashMap<SerializerCache.TypeKey, JsonSerializer<Object>> hashMap) {
        return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(hashMap));
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        SerializerCache.TypeKey typeKey = this._cacheKey;
        if (typeKey == null) {
            this._cacheKey = new SerializerCache.TypeKey(javaType, true);
        } else {
            typeKey.resetTyped(javaType);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        SerializerCache.TypeKey typeKey = this._cacheKey;
        if (typeKey == null) {
            this._cacheKey = new SerializerCache.TypeKey(cls, true);
        } else {
            typeKey.resetTyped(cls);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        SerializerCache.TypeKey typeKey = this._cacheKey;
        if (typeKey == null) {
            this._cacheKey = new SerializerCache.TypeKey(javaType, false);
        } else {
            typeKey.resetUntyped(javaType);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        SerializerCache.TypeKey typeKey = this._cacheKey;
        if (typeKey == null) {
            this._cacheKey = new SerializerCache.TypeKey(cls, false);
        } else {
            typeKey.resetUntyped(cls);
        }
        return this._map.find(this._cacheKey);
    }
}
