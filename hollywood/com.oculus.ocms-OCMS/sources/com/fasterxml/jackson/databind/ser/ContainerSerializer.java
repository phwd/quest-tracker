package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    /* access modifiers changed from: protected */
    public abstract ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    public abstract JsonSerializer<?> getContentSerializer();

    public abstract JavaType getContentType();

    public abstract boolean hasSingleElement(T t);

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public abstract boolean isEmpty(T t);

    protected ContainerSerializer(Class<T> cls) {
        super(cls);
    }

    protected ContainerSerializer(Class<?> cls, boolean z) {
        super(cls, z);
    }

    protected ContainerSerializer(ContainerSerializer<?> containerSerializer) {
        super(containerSerializer._handledType, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.fasterxml.jackson.databind.ser.ContainerSerializer<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public ContainerSerializer<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }

    /* access modifiers changed from: protected */
    public boolean hasContentTypeAnnotation(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector;
        return (beanProperty == null || (annotationIntrospector = serializerProvider.getAnnotationIntrospector()) == null || annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) == null) ? false : true;
    }
}
