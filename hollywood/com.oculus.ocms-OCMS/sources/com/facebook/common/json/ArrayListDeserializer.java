package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArrayListDeserializer<T> extends JsonDeserializer<List<T>> {
    private final Class<T> mContainedClass;
    private JsonDeserializer<T> mValueDeserializer;
    private final JavaType mValueType;

    public ArrayListDeserializer(JsonDeserializer<T> jsonDeserializer) {
        this.mContainedClass = null;
        this.mValueType = null;
        this.mValueDeserializer = jsonDeserializer;
    }

    public ArrayListDeserializer(JavaType javaType) {
        this.mContainedClass = null;
        this.mValueType = javaType.containedType(0);
        this.mValueDeserializer = null;
    }

    public ArrayListDeserializer(Class<T> cls) {
        this.mContainedClass = cls;
        this.mValueType = null;
        this.mValueDeserializer = null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public List<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FbObjectMapper fbObjectMapper = (FbObjectMapper) jsonParser.getCodec();
        if (!jsonParser.hasCurrentToken() || jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            jsonParser.skipChildren();
            return Lists.newArrayList();
        } else if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            if (this.mValueDeserializer == null) {
                Type type = this.mContainedClass;
                if (type == null) {
                    type = this.mValueType;
                }
                this.mValueDeserializer = fbObjectMapper.findDeserializer(deserializationContext, type);
            }
            ArrayList newArrayList = Lists.newArrayList();
            while (FbJsonChecker.nextTokenOrThrow(jsonParser) != JsonToken.END_ARRAY) {
                T deserialize = this.mValueDeserializer.deserialize(jsonParser, deserializationContext);
                if (deserialize != null) {
                    newArrayList.add(deserialize);
                }
            }
            return newArrayList;
        } else {
            throw new JsonParseException("Failed to deserialize to a list - missing start_array token", jsonParser.getCurrentLocation());
        }
    }
}
