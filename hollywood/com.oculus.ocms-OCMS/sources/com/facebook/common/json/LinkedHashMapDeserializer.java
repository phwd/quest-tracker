package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.LinkedHashMap;

public class LinkedHashMapDeserializer<K, V> extends JsonDeserializer<LinkedHashMap<K, V>> {
    private JsonDeserializer<K> mKeyDeserializer;
    private boolean mKeyDeserializerResolved = false;
    private final Class mKeyType;
    private JsonDeserializer<V> mValueDeserializer;
    private final JavaType mValueType;

    public LinkedHashMapDeserializer(JavaType javaType) {
        boolean z = false;
        this.mKeyType = javaType.containedType(0).getRawClass();
        Class cls = this.mKeyType;
        Preconditions.checkArgument((cls == String.class || Enum.class.isAssignableFrom(cls)) ? true : z, "Map keys must be a String or an enum.");
        this.mValueType = javaType.containedType(1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.LinkedHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LinkedHashMap<K, V> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        V v;
        FbObjectMapper fbObjectMapper = (FbObjectMapper) jsonParser.getCodec();
        LinkedHashMap<K, V> linkedHashMap = (LinkedHashMap<K, V>) Maps.newLinkedHashMap();
        if (!jsonParser.hasCurrentToken() || jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            jsonParser.skipChildren();
            return linkedHashMap;
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            if (!this.mKeyDeserializerResolved) {
                Class cls = this.mKeyType;
                if (cls != String.class) {
                    this.mKeyDeserializer = fbObjectMapper.findDeserializer(deserializationContext, cls);
                }
                this.mKeyDeserializerResolved = true;
            }
            if (this.mValueDeserializer == null) {
                this.mValueDeserializer = fbObjectMapper.findDeserializer(deserializationContext, this.mValueType);
            }
            while (FbJsonChecker.nextTokenOrThrow(jsonParser) != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                        v = this.mValueDeserializer.getNullValue();
                    } else {
                        v = this.mValueDeserializer.deserialize(jsonParser, deserializationContext);
                        if (v == null) {
                        }
                    }
                    if (this.mKeyDeserializer != null) {
                        JsonFactory factory = fbObjectMapper.getFactory();
                        JsonParser createParser = factory.createParser("\"" + currentName + "\"");
                        createParser.nextToken();
                        linkedHashMap.put(this.mKeyDeserializer.deserialize(createParser, deserializationContext), v);
                    } else {
                        linkedHashMap.put(currentName, v);
                    }
                }
            }
            return linkedHashMap;
        } else {
            throw new JsonParseException("Failed to deserialize to a map - missing start_object token", jsonParser.getCurrentLocation());
        }
    }
}
