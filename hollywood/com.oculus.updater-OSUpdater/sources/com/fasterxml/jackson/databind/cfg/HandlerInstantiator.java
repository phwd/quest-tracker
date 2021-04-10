package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.Converter;

public abstract class HandlerInstantiator {
    public Converter<?, ?> converterInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        return null;
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls) {
        return null;
    }
}
