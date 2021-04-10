package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Optional;
import java.io.IOException;

public final class GuavaOptionalDeserializer extends StdDeserializer<Optional<?>> {
    private final JavaType _referenceType;

    public GuavaOptionalDeserializer(JavaType javaType) {
        super(javaType);
        this._referenceType = javaType.containedType(0);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Optional<?> getNullValue() {
        return Optional.absent();
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Optional<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Optional.of(deserializationContext.findRootValueDeserializer(this._referenceType).deserialize(jsonParser, deserializationContext));
    }
}
