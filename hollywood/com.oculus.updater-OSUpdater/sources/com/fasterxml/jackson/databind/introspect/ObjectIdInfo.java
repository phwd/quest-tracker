package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;

public class ObjectIdInfo {
    protected final boolean _alwaysAsId;
    protected final Class<? extends ObjectIdGenerator<?>> _generator;
    protected final String _propertyName;
    protected final Class<?> _scope;

    public String getPropertyName() {
        return this._propertyName;
    }

    public Class<?> getScope() {
        return this._scope;
    }

    public Class<? extends ObjectIdGenerator<?>> getGeneratorType() {
        return this._generator;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ObjectIdInfo: propName=");
        sb.append(this._propertyName);
        sb.append(", scope=");
        Class<?> cls = this._scope;
        String str = "null";
        sb.append(cls == null ? str : cls.getName());
        sb.append(", generatorType=");
        Class<? extends ObjectIdGenerator<?>> cls2 = this._generator;
        if (cls2 != null) {
            str = cls2.getName();
        }
        sb.append(str);
        sb.append(", alwaysAsId=");
        sb.append(this._alwaysAsId);
        return sb.toString();
    }
}
