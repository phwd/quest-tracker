package com.fasterxml.jackson.databind.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HierarchicType {
    protected final Type _actualType;
    protected final ParameterizedType _genericType;
    protected final Class<?> _rawClass;
    protected HierarchicType _subType;
    protected HierarchicType _superType;

    public HierarchicType(Type type) {
        this._actualType = type;
        if (type instanceof Class) {
            this._rawClass = (Class) type;
            this._genericType = null;
        } else if (type instanceof ParameterizedType) {
            this._genericType = (ParameterizedType) type;
            this._rawClass = (Class) this._genericType.getRawType();
        } else {
            throw new IllegalArgumentException("Type " + type.getClass().getName() + " can not be used to construct HierarchicType");
        }
    }

    private HierarchicType(Type type, Class<?> cls, ParameterizedType parameterizedType, HierarchicType hierarchicType, HierarchicType hierarchicType2) {
        this._actualType = type;
        this._rawClass = cls;
        this._genericType = parameterizedType;
        this._superType = hierarchicType;
        this._subType = hierarchicType2;
    }

    public HierarchicType deepCloneWithoutSubtype() {
        HierarchicType hierarchicType = this._superType;
        HierarchicType deepCloneWithoutSubtype = hierarchicType == null ? null : hierarchicType.deepCloneWithoutSubtype();
        HierarchicType hierarchicType2 = new HierarchicType(this._actualType, this._rawClass, this._genericType, deepCloneWithoutSubtype, null);
        if (deepCloneWithoutSubtype != null) {
            deepCloneWithoutSubtype.setSubType(hierarchicType2);
        }
        return hierarchicType2;
    }

    public void setSuperType(HierarchicType hierarchicType) {
        this._superType = hierarchicType;
    }

    public final HierarchicType getSuperType() {
        return this._superType;
    }

    public void setSubType(HierarchicType hierarchicType) {
        this._subType = hierarchicType;
    }

    public final boolean isGeneric() {
        return this._genericType != null;
    }

    public final ParameterizedType asGeneric() {
        return this._genericType;
    }

    public final Class<?> getRawClass() {
        return this._rawClass;
    }

    public String toString() {
        ParameterizedType parameterizedType = this._genericType;
        if (parameterizedType != null) {
            return parameterizedType.toString();
        }
        return this._rawClass.getName();
    }
}
