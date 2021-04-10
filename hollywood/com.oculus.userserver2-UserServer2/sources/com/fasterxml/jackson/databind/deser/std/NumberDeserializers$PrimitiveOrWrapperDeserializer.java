package com.fasterxml.jackson.databind.deser.std;

public abstract class NumberDeserializers$PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;
    public final T _nullValue;

    public NumberDeserializers$PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
        super(cls);
        this._nullValue = t;
    }
}
