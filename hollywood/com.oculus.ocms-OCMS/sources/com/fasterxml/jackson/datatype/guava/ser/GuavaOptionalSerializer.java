package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Optional;
import java.io.IOException;

public final class GuavaOptionalSerializer extends StdSerializer<Optional<?>> {
    public GuavaOptionalSerializer(JavaType javaType) {
        super(javaType);
    }

    public void serialize(Optional<?> optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (optional.isPresent()) {
            serializerProvider.defaultSerializeValue(optional.get(), jsonGenerator);
        } else {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }
}
