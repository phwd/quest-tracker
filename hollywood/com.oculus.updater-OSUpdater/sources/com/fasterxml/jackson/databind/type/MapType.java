package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class MapType extends MapLikeType {
    private static final long serialVersionUID = -811146779148281500L;

    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, javaType, javaType2, obj, obj2, z);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapType(cls, javaType, javaType2, null, null, false);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType
    public JavaType _narrow(Class<?> cls) {
        return new MapType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType
    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._valueType.getRawClass()) {
            return this;
        }
        return new MapType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType
    public JavaType narrowKey(Class<?> cls) {
        if (cls == this._keyType.getRawClass()) {
            return this;
        }
        return new MapType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.type.MapLikeType
    public MapType withTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.type.MapLikeType
    public MapType withValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.type.MapLikeType
    public MapType withContentValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType
    public MapType withKeyValueHandler(Object obj) {
        return new MapType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.type.MapLikeType
    public String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }
}
