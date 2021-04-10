package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;

public class ImmutableMapDeserializer<K, V> extends JsonDeserializer<ImmutableMap<K, V>> {
    private JsonDeserializer<K> mKeyDeserializer;
    private boolean mKeyDeserializerResolved = false;
    private final Class mKeyType;
    private JsonDeserializer<V> mValueDeserializer;
    private final JavaType mValueType;

    public ImmutableMapDeserializer(JavaType javaType) {
        boolean z = false;
        this.mKeyType = javaType.containedType(0).getRawClass();
        Class cls = this.mKeyType;
        Preconditions.checkArgument((cls == String.class || Enum.class.isAssignableFrom(cls)) ? true : z, "Map keys must be a String or an enum.");
        this.mValueType = javaType.containedType(1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public ImmutableMap<K, V> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FbObjectMapper fbObjectMapper = (FbObjectMapper) jsonParser.getCodec();
        if (!jsonParser.hasCurrentToken() || jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            jsonParser.skipChildren();
            return ImmutableMap.of();
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
            ImmutableMap.Builder builder = ImmutableMap.builder();
            while (FbJsonChecker.nextTokenOrThrow(jsonParser) != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    V deserialize = this.mValueDeserializer.deserialize(jsonParser, deserializationContext);
                    if (deserialize != null) {
                        if (this.mKeyDeserializer != null) {
                            JsonFactory factory = fbObjectMapper.getFactory();
                            JsonParser createParser = factory.createParser("\"" + currentName + "\"");
                            createParser.nextToken();
                            try {
                                builder.put(this.mKeyDeserializer.deserialize(createParser, deserializationContext), deserialize);
                            } catch (JsonMappingException unused) {
                            }
                        } else {
                            builder.put(currentName, deserialize);
                        }
                    }
                }
            }
            return builder.build();
        } else {
            throw new JsonParseException("Failed to deserialize to a map - missing start_object token", jsonParser.getCurrentLocation());
        }
    }
}
