package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Type;

public class ImmutableListDeserializer<T> extends JsonDeserializer<ImmutableList<T>> {
    private final Class<T> mContainedClass;
    private JsonDeserializer<T> mValueDeserializer;
    private final JavaType mValueType;

    public ImmutableListDeserializer(JsonDeserializer<T> jsonDeserializer) {
        this.mContainedClass = null;
        this.mValueType = null;
        this.mValueDeserializer = jsonDeserializer;
    }

    public ImmutableListDeserializer(JavaType javaType) {
        this.mContainedClass = null;
        this.mValueType = javaType.containedType(0);
        this.mValueDeserializer = null;
    }

    public ImmutableListDeserializer(Class<T> cls) {
        this.mContainedClass = cls;
        this.mValueType = null;
        this.mValueDeserializer = null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public ImmutableList<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FbObjectMapper fbObjectMapper = (FbObjectMapper) jsonParser.getCodec();
        if (!jsonParser.hasCurrentToken() || jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            jsonParser.skipChildren();
            return ImmutableList.of();
        } else if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            if (this.mValueDeserializer == null) {
                Type type = this.mContainedClass;
                if (type == null) {
                    type = this.mValueType;
                }
                this.mValueDeserializer = fbObjectMapper.findDeserializer(deserializationContext, type);
            }
            ImmutableList.Builder builder = ImmutableList.builder();
            while (FbJsonChecker.nextTokenOrThrow(jsonParser) != JsonToken.END_ARRAY) {
                try {
                    T deserialize = this.mValueDeserializer.deserialize(jsonParser, deserializationContext);
                    if (deserialize != null) {
                        builder.add((Object) deserialize);
                    }
                } catch (InvalidFormatException unused) {
                }
            }
            return builder.build();
        } else {
            throw new JsonParseException("Failed to deserialize to a list - missing start_array token", jsonParser.getCurrentLocation());
        }
    }
}
