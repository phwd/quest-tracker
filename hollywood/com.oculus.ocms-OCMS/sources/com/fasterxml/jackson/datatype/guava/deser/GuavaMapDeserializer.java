package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.IOException;

public abstract class GuavaMapDeserializer<T> extends JsonDeserializer<T> implements ContextualDeserializer {
    protected KeyDeserializer _keyDeserializer;
    protected final MapType _mapType;
    protected final TypeDeserializer _typeDeserializerForValue;
    protected JsonDeserializer<?> _valueDeserializer;

    /* access modifiers changed from: protected */
    public abstract T _deserializeEntries(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract GuavaMapDeserializer<T> withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected GuavaMapDeserializer(MapType mapType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        this._mapType = mapType;
        this._keyDeserializer = keyDeserializer;
        this._typeDeserializerForValue = typeDeserializer;
        this._valueDeserializer = jsonDeserializer;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: com.fasterxml.jackson.databind.type.MapType */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.fasterxml.jackson.databind.type.MapType */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._typeDeserializerForValue;
        if (keyDeserializer != null && jsonDeserializer != null && typeDeserializer == null) {
            return this;
        }
        if (keyDeserializer == null) {
            keyDeserializer = deserializationContext.findKeyDeserializer(this._mapType.getKeyType(), beanProperty);
        }
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty);
        }
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(keyDeserializer, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            JsonToken nextToken = jsonParser.nextToken();
            if (!(nextToken == JsonToken.FIELD_NAME || nextToken == JsonToken.END_OBJECT)) {
                throw deserializationContext.mappingException(this._mapType.getRawClass());
            }
        } else if (currentToken != JsonToken.FIELD_NAME) {
            throw deserializationContext.mappingException(this._mapType.getRawClass());
        }
        return _deserializeEntries(jsonParser, deserializationContext);
    }
}
