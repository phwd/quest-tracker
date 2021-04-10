package com.fasterxml.jackson.databind.deser.std;

public abstract class StdScalarDeserializer extends StdDeserializer {
    public static final long serialVersionUID = 1;

    public StdScalarDeserializer(Class cls) {
        super(cls);
    }
}
