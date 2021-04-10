package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>> implements ContextualSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        this._property = null;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
    }

    public EnumMapSerializer(EnumMapSerializer enumMapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super(enumMapSerializer);
        this._property = beanProperty;
        this._staticTyping = enumMapSerializer._staticTyping;
        this._valueType = enumMapSerializer._valueType;
        this._keyEnums = enumMapSerializer._keyEnums;
        this._valueTypeSerializer = enumMapSerializer._valueTypeSerializer;
        this._valueSerializer = jsonSerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public EnumMapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._valueSerializer);
    }

    public EnumMapSerializer withValueSerializer(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        if (this._property == beanProperty && jsonSerializer == this._valueSerializer) {
            return this;
        }
        return new EnumMapSerializer(this, beanProperty, jsonSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember member;
        Object findContentSerializer;
        JsonSerializer<Object> serializerInstance = (beanProperty == null || (member = beanProperty.getMember()) == null || (findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(member)) == null) ? null : serializerProvider.serializerInstance(member, findContentSerializer);
        if (serializerInstance == null) {
            serializerInstance = this._valueSerializer;
        }
        JsonSerializer<?> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
        if (findConvertingContentSerializer == null) {
            if (this._staticTyping) {
                return withValueSerializer(beanProperty, serializerProvider.findValueSerializer(this._valueType, beanProperty));
            }
        } else if (this._valueSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider, beanProperty);
        }
        return findConvertingContentSerializer != this._valueSerializer ? withValueSerializer(beanProperty, findConvertingContentSerializer) : this;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JavaType getContentType() {
        return this._valueType;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(EnumMap<? extends Enum<?>, ?> enumMap) {
        return enumMap == null || enumMap.isEmpty();
    }

    public boolean hasSingleElement(EnumMap<? extends Enum<?>, ?> enumMap) {
        return enumMap.size() == 1;
    }

    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }

    /* access modifiers changed from: protected */
    public void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, jsonSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Class<?> cls = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        for (Map.Entry<? extends Enum<?>, ?> entry : enumMap.entrySet()) {
            Object value = entry.getValue();
            if (!z || value != null) {
                Enum<?> r8 = (Enum) entry.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider.findValueSerializer(r8.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator.writeFieldName(enumValues.serializedValueFor(r8));
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls2 = value.getClass();
                    if (cls2 != cls) {
                        jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                        cls = cls2;
                    }
                    if (typeSerializer == null) {
                        try {
                            jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                        } catch (Exception e) {
                            wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                        }
                    } else {
                        jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Map.Entry<? extends Enum<?>, ?> entry : enumMap.entrySet()) {
            Object value = entry.getValue();
            if (!z || value != null) {
                Enum<?> r6 = (Enum) entry.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider.findValueSerializer(r6.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator.writeFieldName(enumValues.serializedValueFor(r6));
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    try {
                        jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                    }
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        JsonNode jsonNode;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                Enum<?>[] enumArr = (Enum[]) constructType.getRawClass().getEnumConstants();
                for (Enum<?> r5 : enumArr) {
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(constructType2.getRawClass(), this._property);
                    if (findValueSerializer instanceof SchemaAware) {
                        jsonNode = ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null);
                    } else {
                        jsonNode = JsonSchema.getDefaultSchemaNode();
                    }
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(r5), jsonNode);
                }
                createSchemaNode.put("properties", objectNode);
            }
        }
        return createSchemaNode;
    }

    @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat;
        if (!(jsonFormatVisitorWrapper == null || (expectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType)) == null)) {
            JavaType containedType = javaType.containedType(1);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null && containedType != null) {
                jsonSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(containedType, this._property);
            }
            if (containedType == null) {
                containedType = jsonFormatVisitorWrapper.getProvider().constructType(Object.class);
            }
            EnumValues enumValues = this._keyEnums;
            if (enumValues == null) {
                JavaType containedType2 = javaType.containedType(0);
                if (containedType2 != null) {
                    JsonSerializer<Object> findValueSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(containedType2, this._property);
                    if (findValueSerializer instanceof EnumSerializer) {
                        enumValues = ((EnumSerializer) findValueSerializer).getEnumValues();
                    } else {
                        throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + javaType);
                    }
                } else {
                    throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + javaType);
                }
            }
            for (Map.Entry<?, SerializedString> entry : enumValues.internalMap().entrySet()) {
                String value = entry.getValue().getValue();
                if (jsonSerializer == null) {
                    jsonSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(entry.getKey().getClass(), this._property);
                }
                expectObjectFormat.property(value, jsonSerializer, containedType);
            }
        }
    }
}
