package com.facebook.common.json;

import android.util.Base64;
import com.facebook.common.json.JsonLogger;
import com.facebook.debug.log.BLog;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattenable;
import com.facebook.graphql.modelutil.FragmentModel;
import com.facebook.graphql.modelutil.GraphQLModel;
import com.facebook.graphql.modelutil.TypeModel;
import com.facebook.graphql.modelutil.TypeTagModel;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

public class FbSerializerProvider extends DefaultSerializerProvider {
    private static final ConcurrentMap<Class, JsonSerializer> SERIALIZER_CACHE = Maps.newConcurrentMap();
    public static final Class TAG = FbSerializerProvider.class;
    private static boolean isInitDone = false;
    private static JsonSerializer mEnumSerializer = new JsonSerializer<Enum>() {
        /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass5 */

        public void serialize(Enum r1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            AutoGenJsonHelper.writeEnum(jsonGenerator, serializerProvider, r1);
        }
    };
    private static JsonSerializer mFragmentModelTreeSerializer = new JsonSerializer<GraphQLModel>() {
        /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass4 */

        public void serialize(GraphQLModel graphQLModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            ByteBuffer serializeTreeToByteBuffer = StaticGraphServiceFactory.getTreeSerializer().serializeTreeToByteBuffer((Tree) graphQLModel);
            byte[] bArr = new byte[serializeTreeToByteBuffer.limit()];
            serializeTreeToByteBuffer.get(bArr);
            jsonGenerator.writeString(Base64.encodeToString(bArr, 2));
        }
    };
    private static JsonSerializer mJsonSerializableSerializer = new JsonSerializer<JsonSerializable>() {
        /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass2 */

        public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonSerializable.serialize(jsonGenerator, serializerProvider);
        }
    };
    private static JsonSerializer mMapSerializer = new JsonSerializer<Map>() {
        /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass6 */

        public void serialize(Map map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();
            for (Object obj : map.keySet()) {
                if (obj instanceof String) {
                    jsonGenerator.writeFieldName((String) obj);
                } else if (obj instanceof Enum) {
                    JsonFactory factory = jsonGenerator.getCodec().getFactory();
                    StringWriter stringWriter = new StringWriter();
                    JsonGenerator createGenerator = factory.createGenerator(stringWriter);
                    createGenerator.setCodec(jsonGenerator.getCodec());
                    createGenerator.writeObject(obj);
                    createGenerator.flush();
                    JsonParser createParser = factory.createParser(stringWriter.toString());
                    String nextTextValue = createParser.nextTextValue();
                    if (nextTextValue == null || createParser.nextToken() != null) {
                        throw new JsonGenerationException("Tried to use json as map key, but it is not a string: " + stringWriter);
                    }
                    jsonGenerator.writeFieldName(nextTextValue);
                } else {
                    throw new JsonGenerationException("Non-string, non-enum key (" + obj.getClass() + ") found in map.");
                }
                jsonGenerator.writeObject(map.get(obj));
            }
            jsonGenerator.writeEndObject();
        }
    };
    private static JsonSerializer mTypeModelSerializer = new JsonSerializer<TypeModel>() {
        /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass3 */

        public void serialize(TypeModel typeModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            boolean z = true;
            boolean z2 = (typeModel instanceof Tree) && ((Tree) typeModel).isValid();
            if (!(serializerProvider instanceof FbSerializerProvider) || !((FbSerializerProvider) serializerProvider).isHumanReadableFormatEnabled()) {
                z = false;
            }
            if (z && z2) {
                jsonGenerator.writeRawValue(((Tree) typeModel).toExpensiveHumanReadableDebugString());
            } else if (z2) {
                ByteBuffer serializeTreeToByteBuffer = StaticGraphServiceFactory.getTreeSerializer().serializeTreeToByteBuffer((Tree) typeModel);
                byte[] bArr = new byte[serializeTreeToByteBuffer.limit()];
                serializeTreeToByteBuffer.get(bArr);
                jsonGenerator.writeString(AutoGenJsonHelper.TREE_PREFIX + Base64.encodeToString(bArr, 2));
            } else {
                Class cls = FbSerializerProvider.TAG;
                BLog.wtf(cls, "Use of deprecated flatbuffer infra: model type tag " + ((TypeTagModel) typeModel).getTypeTag());
                byte[] flatten = FlatBufferBuilder.flatten((Flattenable) typeModel);
                jsonGenerator.writeString(AutoGenJsonHelper.FLATBUFFER_PREFIX + Base64.encodeToString(flatten, 2));
            }
        }
    };
    private JsonSerializer mCollectionSerializer;
    private boolean mHumanReadableFormatEnabled;
    private final JsonLogger mJsonLogger;

    private static void init() {
        SERIALIZER_CACHE.put(String.class, new StringSerializer());
        SERIALIZER_CACHE.put(Integer.class, new NumberSerializers.IntegerSerializer());
        SERIALIZER_CACHE.put(Long.class, new NumberSerializers.LongSerializer());
        SERIALIZER_CACHE.put(Boolean.class, new BooleanSerializer(false));
        SERIALIZER_CACHE.put(Float.class, new NumberSerializers.FloatSerializer());
        SERIALIZER_CACHE.put(Double.class, new NumberSerializers.DoubleSerializer());
    }

    public FbSerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory, JsonLogger jsonLogger) {
        super(serializerProvider, serializationConfig, serializerFactory);
        this.mCollectionSerializer = new JsonSerializer<Collection>() {
            /* class com.facebook.common.json.FbSerializerProvider.AnonymousClass1 */

            public void serialize(Collection collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                AutoGenJsonHelper.writeCollection(jsonGenerator, serializerProvider, collection);
            }
        };
        this.mJsonLogger = jsonLogger;
        if (!isInitDone) {
            init();
            isInitDone = true;
        }
    }

    public FbSerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory, JsonLogger jsonLogger, boolean z) {
        this(serializerProvider, serializationConfig, serializerFactory, jsonLogger);
        this.mHumanReadableFormatEnabled = z;
    }

    public static <T> void addSerializerToCache(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        SERIALIZER_CACHE.putIfAbsent(cls, jsonSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
    public FbSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new FbSerializerProvider(this, serializationConfig, serializerFactory, this.mJsonLogger, false);
    }

    public static JsonSerializer<Object> getSerializerFromCache(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer = SERIALIZER_CACHE.get(cls);
        if (jsonSerializer != null) {
            return jsonSerializer;
        }
        forceClassLoad(cls);
        return SERIALIZER_CACHE.get(cls);
    }

    public boolean isHumanReadableFormatEnabled() {
        return this.mHumanReadableFormatEnabled;
    }

    @Override // com.fasterxml.jackson.databind.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, @Nullable BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = SERIALIZER_CACHE.get(cls);
        if (jsonSerializer != null) {
            return jsonSerializer;
        }
        if (TypeModel.class.isAssignableFrom(cls)) {
            SERIALIZER_CACHE.put(cls, mTypeModelSerializer);
            return mTypeModelSerializer;
        } else if (GraphQLModel.class.isAssignableFrom(cls) && !FragmentModel.class.isAssignableFrom(cls)) {
            return mFragmentModelTreeSerializer;
        } else {
            if (JsonSerializable.class.isAssignableFrom(cls)) {
                SERIALIZER_CACHE.put(cls, mJsonSerializableSerializer);
                return mJsonSerializableSerializer;
            } else if (cls.isEnum()) {
                SERIALIZER_CACHE.put(cls, mEnumSerializer);
                return mEnumSerializer;
            } else if (Collection.class.isAssignableFrom(cls)) {
                SERIALIZER_CACHE.put(cls, this.mCollectionSerializer);
                return this.mCollectionSerializer;
            } else if (Map.class.isAssignableFrom(cls)) {
                SERIALIZER_CACHE.put(cls, mMapSerializer);
                return mMapSerializer;
            } else {
                forceClassLoad(cls);
                JsonSerializer<Object> jsonSerializer2 = SERIALIZER_CACHE.get(cls);
                if (jsonSerializer2 != null) {
                    return jsonSerializer2;
                }
                if (Flattenable.class.isAssignableFrom(cls)) {
                    BLog.wtf(TAG, "Serializing a FlatBuffer based object to Json: %s", cls.toString());
                }
                JsonSerializer<Object> findTypedValueSerializer = super.findTypedValueSerializer(cls, z, beanProperty);
                SERIALIZER_CACHE.put(cls, findTypedValueSerializer);
                JsonLogger jsonLogger = this.mJsonLogger;
                if (jsonLogger != null) {
                    jsonLogger.log(JsonLogger.Event.SERIALIZATION, cls.toString(), findTypedValueSerializer);
                }
                return findTypedValueSerializer;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void forceClassLoad(java.lang.Class<?> r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0021 }
            r0.<init>()     // Catch:{ ClassNotFoundException -> 0x0021 }
            java.lang.String r1 = r4.getName()     // Catch:{ ClassNotFoundException -> 0x0021 }
            r2 = 36
            r3 = 95
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ ClassNotFoundException -> 0x0021 }
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0021 }
            java.lang.String r1 = "Serializer"
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0021 }
            java.lang.String r0 = r0.toString()     // Catch:{ ClassNotFoundException -> 0x0021 }
            java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0021 }
            goto L_0x002e
        L_0x0021:
            java.lang.String r4 = r4.getName()     // Catch:{ ClassNotFoundException -> 0x002e }
            java.lang.String r0 = "$Serializer"
            java.lang.String r4 = r4.concat(r0)     // Catch:{ ClassNotFoundException -> 0x002e }
            java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x002e }
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.json.FbSerializerProvider.forceClassLoad(java.lang.Class):void");
    }
}
