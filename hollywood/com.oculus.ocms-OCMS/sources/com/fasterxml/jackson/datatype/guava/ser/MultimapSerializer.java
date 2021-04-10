package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MultimapSerializer extends JsonSerializer<Multimap<?, ?>> implements ContextualSerializer {
    private final JsonSerializer<Object> keySerializer;
    private final BeanProperty property;
    private final MapLikeType type;
    private final JsonSerializer<Object> valueSerializer;
    private final TypeSerializer valueTypeSerializer;

    public MultimapSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        this.type = mapLikeType;
        this.property = null;
        this.keySerializer = jsonSerializer;
        this.valueTypeSerializer = typeSerializer;
        this.valueSerializer = jsonSerializer2;
    }

    protected MultimapSerializer(MultimapSerializer multimapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer2) {
        this.type = multimapSerializer.type;
        this.property = beanProperty;
        this.keySerializer = jsonSerializer;
        this.valueTypeSerializer = typeSerializer;
        this.valueSerializer = jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public MultimapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer2) {
        return new MultimapSerializer(this, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer = this.valueSerializer;
        if (jsonSerializer == null) {
            JavaType contentType = this.type.getContentType();
            if (contentType.isFinal()) {
                jsonSerializer = serializerProvider.findValueSerializer(contentType, beanProperty);
            }
        } else if (jsonSerializer instanceof ContextualSerializer) {
            jsonSerializer = ((ContextualSerializer) jsonSerializer).createContextual(serializerProvider, beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = this.keySerializer;
        if (jsonSerializer2 == null) {
            jsonSerializer2 = serializerProvider.findKeySerializer(this.type.getKeyType(), beanProperty);
        } else if (jsonSerializer2 instanceof ContextualSerializer) {
            jsonSerializer2 = ((ContextualSerializer) jsonSerializer2).createContextual(serializerProvider, beanProperty);
        }
        TypeSerializer typeSerializer = this.valueTypeSerializer;
        if (typeSerializer != null) {
            typeSerializer = typeSerializer.forProperty(beanProperty);
        }
        return withResolved(beanProperty, jsonSerializer2, typeSerializer, jsonSerializer);
    }

    public void serialize(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        if (!multimap.isEmpty()) {
            serializeFields(multimap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(multimap, jsonGenerator);
        serializeFields(multimap, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForObject(multimap, jsonGenerator);
    }

    private final void serializeFields(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        for (Map.Entry<?, Collection<?>> entry : multimap.asMap().entrySet()) {
            JsonSerializer<Object> jsonSerializer = this.keySerializer;
            if (jsonSerializer != null) {
                jsonSerializer.serialize(entry.getKey(), jsonGenerator, serializerProvider);
            } else {
                serializerProvider.findKeySerializer(serializerProvider.constructType(String.class), this.property).serialize(entry.getKey(), jsonGenerator, serializerProvider);
            }
            if (this.valueSerializer != null) {
                jsonGenerator.writeStartArray();
                Iterator<?> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    this.valueSerializer.serialize(it.next(), jsonGenerator, serializerProvider);
                }
                jsonGenerator.writeEndArray();
            } else {
                serializerProvider.defaultSerializeValue(Lists.newArrayList(entry.getValue()), jsonGenerator);
            }
        }
    }
}
