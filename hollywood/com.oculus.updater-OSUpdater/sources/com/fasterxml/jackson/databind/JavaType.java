package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.ResolvedType;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class JavaType extends ResolvedType implements Serializable, Type {
    private static final long serialVersionUID = 6774285981275451126L;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hashCode;
    protected final Object _typeHandler;
    protected final Object _valueHandler;

    /* access modifiers changed from: protected */
    public abstract JavaType _narrow(Class<?> cls);

    public JavaType containedType(int i) {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public String containedTypeName(int i) {
        return null;
    }

    public abstract boolean equals(Object obj);

    public JavaType getContentType() {
        return null;
    }

    public JavaType getKeyType() {
        return null;
    }

    public boolean isArrayType() {
        return false;
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public abstract boolean isContainerType();

    public boolean isMapLikeType() {
        return false;
    }

    public abstract JavaType narrowContentsBy(Class<?> cls);

    public abstract String toString();

    public abstract JavaType withContentValueHandler(Object obj);

    public abstract JavaType withTypeHandler(Object obj);

    public abstract JavaType withValueHandler(Object obj);

    protected JavaType(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public JavaType narrowBy(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        _assertSubclass(cls, cls2);
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        return this._typeHandler != _narrow.getTypeHandler() ? _narrow.withTypeHandler(this._typeHandler) : _narrow;
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    /* access modifiers changed from: protected */
    public void _assertSubclass(Class<?> cls, Class<?> cls2) {
        if (!this._class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not assignable to " + this._class.getName());
        }
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
