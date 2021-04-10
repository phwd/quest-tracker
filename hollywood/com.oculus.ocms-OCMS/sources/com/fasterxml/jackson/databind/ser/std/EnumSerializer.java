package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    @Deprecated
    public EnumSerializer(EnumValues enumValues) {
        this(enumValues, null);
    }

    public EnumSerializer(EnumValues enumValues, Boolean bool) {
        super(Enum.class, false);
        this._values = enumValues;
        this._serializeAsIndex = bool;
    }

    public static EnumSerializer construct(Class<Enum<?>> cls, SerializationConfig serializationConfig, BeanDescription beanDescription, JsonFormat.Value value) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        return new EnumSerializer(serializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING) ? EnumValues.constructFromToString(cls, annotationIntrospector) : EnumValues.constructFromName(cls, annotationIntrospector), _isShapeWrittenUsingIndex(cls, value, true));
    }

    @Deprecated
    public static EnumSerializer construct(Class<Enum<?>> cls, SerializationConfig serializationConfig, BeanDescription beanDescription) {
        return construct(cls, serializationConfig, beanDescription, beanDescription.findExpectedFormat(null));
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value findFormat;
        Boolean _isShapeWrittenUsingIndex;
        return (beanProperty == null || (findFormat = serializerProvider.getAnnotationIntrospector().findFormat(beanProperty.getMember())) == null || (_isShapeWrittenUsingIndex = _isShapeWrittenUsingIndex(beanProperty.getType().getRawClass(), findFormat, false)) == this._serializeAsIndex) ? this : new EnumSerializer(this._values, _isShapeWrittenUsingIndex);
    }

    public EnumValues getEnumValues() {
        return this._values;
    }

    public final void serialize(Enum<?> r1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (_serializeAsIndex(serializerProvider)) {
            jsonGenerator.writeNumber(r1.ordinal());
        } else {
            jsonGenerator.writeString(this._values.serializedValueFor(r1));
        }
    }

    @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        if (_serializeAsIndex(serializerProvider)) {
            return createSchemaNode("integer", true);
        }
        ObjectNode createSchemaNode = createSchemaNode("string", true);
        if (type != null && serializerProvider.constructType(type).isEnumType()) {
            ArrayNode putArray = createSchemaNode.putArray("enum");
            for (SerializedString serializedString : this._values.values()) {
                putArray.add(serializedString.getValue());
            }
        }
        return createSchemaNode;
    }

    @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper.getProvider().isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.INT);
                return;
            }
            return;
        }
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType);
        if (!(javaType == null || expectStringFormat == null || !javaType.isEnumType())) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (SerializedString serializedString : this._values.values()) {
                linkedHashSet.add(serializedString.getValue());
            }
            expectStringFormat.enumTypes(linkedHashSet);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean _serializeAsIndex(SerializerProvider serializerProvider) {
        Boolean bool = this._serializeAsIndex;
        if (bool != null) {
            return bool.booleanValue();
        }
        return serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> cls, JsonFormat.Value value, boolean z) {
        JsonFormat.Shape shape = value == null ? null : value.getShape();
        if (shape == null || shape == JsonFormat.Shape.ANY || shape == JsonFormat.Shape.SCALAR) {
            return null;
        }
        if (shape == JsonFormat.Shape.STRING) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric()) {
            return Boolean.TRUE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported serialization shape (");
        sb.append(shape);
        sb.append(") for Enum ");
        sb.append(cls.getName());
        sb.append(", not supported as ");
        sb.append(z ? "class" : "property");
        sb.append(" annotation");
        throw new IllegalArgumentException(sb.toString());
    }
}
