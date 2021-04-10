package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import java.util.Collection;
import java.util.HashMap;

public class TypeNameIdResolver extends TypeIdResolverBase {
    protected final MapperConfig<?> _config;
    protected final HashMap<String, JavaType> _idToType;
    protected final HashMap<String, String> _typeToId;

    protected TypeNameIdResolver(MapperConfig<?> mapperConfig, JavaType javaType, HashMap<String, String> hashMap, HashMap<String, JavaType> hashMap2) {
        super(javaType, mapperConfig.getTypeFactory());
        this._config = mapperConfig;
        this._typeToId = hashMap;
        this._idToType = hashMap2;
    }

    public static TypeNameIdResolver construct(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        JavaType javaType2;
        if (z != z2) {
            HashMap hashMap = null;
            HashMap hashMap2 = z ? new HashMap() : null;
            if (z2) {
                hashMap = new HashMap();
            }
            if (collection != null) {
                for (NamedType namedType : collection) {
                    Class<?> type = namedType.getType();
                    String name = namedType.hasName() ? namedType.getName() : _defaultTypeId(type);
                    if (z) {
                        hashMap2.put(type.getName(), name);
                    }
                    if (z2 && ((javaType2 = (JavaType) hashMap.get(name)) == null || !type.isAssignableFrom(javaType2.getRawClass()))) {
                        hashMap.put(name, mapperConfig.constructType(type));
                    }
                }
            }
            return new TypeNameIdResolver(mapperConfig, javaType, hashMap2, hashMap);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.NAME;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String idFromValue(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        synchronized (this._typeToId) {
            str = this._typeToId.get(name);
            if (str == null) {
                if (this._config.isAnnotationProcessingEnabled()) {
                    str = this._config.getAnnotationIntrospector().findTypeName(this._config.introspectClassAnnotations(cls).getClassInfo());
                }
                if (str == null) {
                    str = _defaultTypeId(cls);
                }
                this._typeToId.put(name, str);
            }
        }
        return str;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String idFromValueAndType(Object obj, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        return idFromValue(obj);
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public JavaType typeFromId(String str) throws IllegalArgumentException {
        return this._idToType.get(str);
    }

    public String toString() {
        return '[' + getClass().getName() + "; id-to-type=" + this._idToType + ']';
    }

    protected static String _defaultTypeId(Class<?> cls) {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(lastIndexOf + 1);
    }
}
