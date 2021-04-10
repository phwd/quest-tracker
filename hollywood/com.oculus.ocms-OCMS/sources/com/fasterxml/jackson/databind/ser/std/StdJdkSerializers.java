package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class StdJdkSerializers {
    public static Collection<Map.Entry<Class<?>, Object>> all() {
        HashMap hashMap = new HashMap();
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        hashMap.put(URL.class, toStringSerializer);
        hashMap.put(URI.class, toStringSerializer);
        hashMap.put(Currency.class, toStringSerializer);
        hashMap.put(UUID.class, toStringSerializer);
        hashMap.put(Pattern.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(AtomicReference.class, AtomicReferenceSerializer.class);
        hashMap.put(AtomicBoolean.class, AtomicBooleanSerializer.class);
        hashMap.put(AtomicInteger.class, AtomicIntegerSerializer.class);
        hashMap.put(AtomicLong.class, AtomicLongSerializer.class);
        hashMap.put(File.class, FileSerializer.class);
        hashMap.put(Class.class, ClassSerializer.class);
        hashMap.put(Void.TYPE, NullSerializer.class);
        return hashMap.entrySet();
    }

    public static final class AtomicBooleanSerializer extends StdScalarSerializer<AtomicBoolean> {
        public AtomicBooleanSerializer() {
            super(AtomicBoolean.class, false);
        }

        public void serialize(AtomicBoolean atomicBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeBoolean(atomicBoolean.get());
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            jsonFormatVisitorWrapper.expectBooleanFormat(javaType);
        }
    }

    public static final class AtomicIntegerSerializer extends StdScalarSerializer<AtomicInteger> {
        public AtomicIntegerSerializer() {
            super(AtomicInteger.class, false);
        }

        public void serialize(AtomicInteger atomicInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(atomicInteger.get());
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.INT);
            }
        }
    }

    public static final class AtomicLongSerializer extends StdScalarSerializer<AtomicLong> {
        public AtomicLongSerializer() {
            super(AtomicLong.class, false);
        }

        public void serialize(AtomicLong atomicLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(atomicLong.get());
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.LONG);
            }
        }
    }

    public static final class AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> {
        public AtomicReferenceSerializer() {
            super(AtomicReference.class, false);
        }

        public void serialize(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeValue(atomicReference.get(), jsonGenerator);
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("any", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            jsonFormatVisitorWrapper.expectAnyFormat(javaType);
        }
    }

    public static final class FileSerializer extends StdScalarSerializer<File> {
        public FileSerializer() {
            super(File.class);
        }

        public void serialize(File file, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(file.getAbsolutePath());
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            jsonFormatVisitorWrapper.expectStringFormat(javaType);
        }
    }

    public static final class ClassSerializer extends StdScalarSerializer<Class<?>> {
        public ClassSerializer() {
            super(Class.class, false);
        }

        public void serialize(Class<?> cls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(cls.getName());
        }

        @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            jsonFormatVisitorWrapper.expectStringFormat(javaType);
        }
    }
}
