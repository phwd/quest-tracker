package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public abstract class TypeBase extends JavaType {
    private static final long serialVersionUID = -3581199092426900829L;
    volatile transient String _canonicalName;

    /* access modifiers changed from: protected */
    public abstract String buildCanonicalName();

    protected TypeBase(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
    }

    @Override // com.fasterxml.jackson.core.type.ResolvedType
    public String toCanonical() {
        String str = this._canonicalName;
        return str == null ? buildCanonicalName() : str;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }
}
