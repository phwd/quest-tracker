package com.fasterxml.jackson.databind.deser.std;

public abstract class NumberDeserializers$PrimitiveOrWrapperDeserializer extends StdScalarDeserializer {
    public static final long serialVersionUID = 1;
    public final Object _nullValue;

    public NumberDeserializers$PrimitiveOrWrapperDeserializer(Class cls, Object obj) {
        super(cls);
        this._nullValue = obj;
    }
}
