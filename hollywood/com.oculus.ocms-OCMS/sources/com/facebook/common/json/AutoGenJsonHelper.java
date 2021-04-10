package com.facebook.common.json;

import android.util.Base64;
import com.facebook.debug.log.BLog;
import com.facebook.graphql.modelutil.FragmentModel;
import com.facebook.graphql.modelutil.GraphQLModel;
import com.facebook.graphql.modelutil.TypeModel;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import javax.annotation.Nullable;

public final class AutoGenJsonHelper {
    public static String FLATBUFFER_PREFIX = "fltb:";
    public static String TREE_PREFIX = "tree:";

    private AutoGenJsonHelper() {
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, @Nullable Object obj) throws IOException, JsonProcessingException {
        if (obj != null) {
            jsonGenerator.writeFieldName(str);
            writeObjectInner(jsonGenerator, serializerProvider, obj);
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Collection<?> collection) throws IOException, JsonProcessingException {
        if (collection != null) {
            jsonGenerator.writeFieldName(str);
            writeCollection(jsonGenerator, serializerProvider, collection);
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, @Nullable String str2) throws IOException, JsonProcessingException {
        if (str2 != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeString(str2);
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Integer num) throws IOException, JsonProcessingException {
        if (num != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, int i) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeNumber(i);
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Long l) throws IOException, JsonProcessingException {
        if (l != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, long j) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeNumber(j);
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Double d) throws IOException, JsonProcessingException {
        if (d != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeNumber(d.doubleValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, double d) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeNumber(d);
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Float f) throws IOException, JsonProcessingException {
        if (f != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, float f) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeNumber(f);
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Short sh) throws IOException, JsonProcessingException {
        if (sh != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeNumber(sh.shortValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, short s) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeNumber(s);
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, byte[] bArr) throws IOException, JsonProcessingException {
        if (bArr != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeBinary(bArr);
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, Boolean bool) throws IOException, JsonProcessingException {
        if (bool != null) {
            jsonGenerator.writeFieldName(str);
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    public static void write(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, String str, boolean z) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        jsonGenerator.writeBoolean(z);
    }

    public static void assertInvariants(SerializerProvider serializerProvider) {
        Preconditions.checkNotNull(serializerProvider, "Must give a non null SerializerProvider");
        SerializationConfig config = serializerProvider.getConfig();
        Preconditions.checkNotNull(serializerProvider, "SerializerProvider must have a non-null config");
        assertSerializationInclusionNonNull(config);
    }

    public static void writeCollection(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Collection<?> collection) throws IOException, JsonProcessingException {
        if (collection != null) {
            jsonGenerator.writeStartArray();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                writeObjectInner(jsonGenerator, serializerProvider, it.next());
            }
            jsonGenerator.writeEndArray();
        }
    }

    private static void writeObjectInner(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Object obj) throws IOException, JsonProcessingException {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            boolean z = true;
            boolean z2 = (serializerProvider instanceof FbSerializerProvider) && ((FbSerializerProvider) serializerProvider).isHumanReadableFormatEnabled();
            boolean z3 = obj instanceof Tree;
            if (!z3 || !((Tree) obj).isValid()) {
                z = false;
            }
            if (z2 && z) {
                jsonGenerator.writeRawValue(((Tree) obj).toExpensiveHumanReadableDebugString());
            } else if (TypeModel.class.isAssignableFrom(cls)) {
                Preconditions.checkArgument(z3);
                Tree tree = (Tree) obj;
                int typeTag = tree.getTypeTag();
                if (z) {
                    ByteBuffer serializeTreeToByteBuffer = StaticGraphServiceFactory.getTreeSerializer().serializeTreeToByteBuffer(tree);
                    byte[] bArr = new byte[serializeTreeToByteBuffer.limit()];
                    serializeTreeToByteBuffer.get(bArr);
                    jsonGenerator.writeString(TREE_PREFIX + TreeEncodingUtils.formatTypeTagPrefix(typeTag) + Base64.encodeToString(bArr, 2));
                    return;
                }
                BLog.wtf(AutoGenJsonHelper.class.getSimpleName(), "Use of deprecated flatbuffer infra");
            } else if (GraphQLModel.class.isAssignableFrom(cls) && !FragmentModel.class.isAssignableFrom(cls)) {
                ByteBuffer serializeTreeToByteBuffer2 = StaticGraphServiceFactory.getTreeSerializer().serializeTreeToByteBuffer((Tree) obj);
                byte[] bArr2 = new byte[serializeTreeToByteBuffer2.limit()];
                serializeTreeToByteBuffer2.get(bArr2);
                jsonGenerator.writeString(Base64.encodeToString(bArr2, 2));
            } else if (JsonSerializable.class.isAssignableFrom(cls)) {
                writeJsonSeralizable(jsonGenerator, serializerProvider, (JsonSerializable) obj);
            } else if (cls.isEnum()) {
                writeEnum(jsonGenerator, serializerProvider, (Enum) obj);
            } else if (Collection.class.isAssignableFrom(cls)) {
                writeCollection(jsonGenerator, serializerProvider, (Collection) obj);
            } else {
                jsonGenerator.writeObject(obj);
            }
        }
    }

    public static void writeEnum(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Enum<?> r2) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(r2.name());
    }

    public static void writeJsonSeralizable(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializable jsonSerializable) throws IOException, JsonProcessingException {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    private static void assertSerializationInclusionNonNull(SerializationConfig serializationConfig) {
        if (!JsonInclude.Include.NON_NULL.equals(serializationConfig.getSerializationInclusion())) {
            throw new IllegalArgumentException(String.format(Locale.US, "Currently, we only support serialization inclusion %s. You are using %s.", JsonInclude.Include.NON_NULL, serializationConfig.getSerializationInclusion()));
        }
    }

    @Nullable
    public static String readStringValue(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            jsonParser.skipChildren();
            return null;
        }
        String text = jsonParser.getText();
        if (text != null) {
            return text;
        }
        throw new JsonParseException("Failed to read text from Json stream", jsonParser.getCurrentLocation());
    }

    public static ImmutableList<?> readImmutableListValue(JsonParser jsonParser, DeserializationContext deserializationContext, @Nullable Class<?> cls, @Nullable TypeReference<?> typeReference) throws IOException {
        ImmutableListDeserializer immutableListDeserializer;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return ImmutableList.of();
        }
        if (cls != null) {
            immutableListDeserializer = new ImmutableListDeserializer(cls);
        } else if (typeReference != null) {
            immutableListDeserializer = new ImmutableListDeserializer(((FbObjectMapper) jsonParser.getCodec()).findDeserializer(deserializationContext, typeReference.getType()));
        } else {
            throw new IllegalArgumentException("Need to set simple or generic inner list type!");
        }
        return (ImmutableList) immutableListDeserializer.deserialize(jsonParser, deserializationContext);
    }

    @Nullable
    public static <T> T readBeanObject(Type type, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
            return (T) readBeanObject(getJavaType(type), jsonParser, deserializationContext);
        }
        jsonParser.skipChildren();
        return null;
    }

    @Nullable
    public static <T> T readBeanObject(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
            return ((FbObjectMapper) jsonParser.getCodec()).findDeserializer(deserializationContext, javaType).deserialize(jsonParser, deserializationContext);
        }
        jsonParser.skipChildren();
        return null;
    }

    private static JavaType getJavaType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = (Class) parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (ImmutableList.class.isAssignableFrom(cls)) {
                boolean z = true;
                if (actualTypeArguments.length != 1) {
                    z = false;
                }
                Preconditions.checkState(z);
                return CollectionType.construct((Class<?>) cls, TypeFactory.defaultInstance().constructType(actualTypeArguments[0]));
            }
        }
        return TypeFactory.defaultInstance().constructType(type);
    }
}
