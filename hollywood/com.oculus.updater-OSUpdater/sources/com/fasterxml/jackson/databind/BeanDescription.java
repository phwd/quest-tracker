package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.util.Converter;

public abstract class BeanDescription {
    public abstract Converter<Object, Object> findDeserializationConverter();

    public abstract JsonFormat.Value findExpectedFormat(JsonFormat.Value value);

    public abstract Class<?> findPOJOBuilder();

    public abstract AnnotatedClass getClassInfo();
}
