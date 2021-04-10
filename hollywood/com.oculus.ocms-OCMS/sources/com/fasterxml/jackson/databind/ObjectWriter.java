package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class ObjectWriter implements Versioned, Serializable {
    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    private static final long serialVersionUID = -7024829992408267532L;
    protected final SerializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final JsonSerializer<Object> _rootSerializer;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;
    protected final DefaultSerializerProvider _serializerProvider;

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        javaType = javaType != null ? javaType.withStaticTyping() : javaType;
        this._rootType = javaType;
        this._prettyPrinter = prettyPrinter;
        this._schema = null;
        this._rootSerializer = _prefetchRootSerializer(serializationConfig, javaType);
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._rootSerializer = null;
        this._prettyPrinter = null;
        this._schema = null;
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._rootSerializer = null;
        this._prettyPrinter = null;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig, JavaType javaType, JsonSerializer<Object> jsonSerializer, PrettyPrinter prettyPrinter, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._serializerProvider = objectWriter._serializerProvider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._rootType = javaType;
        this._rootSerializer = jsonSerializer;
        this._prettyPrinter = prettyPrinter;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializerProvider = objectWriter._serializerProvider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._schema = objectWriter._schema;
        this._rootType = objectWriter._rootType;
        this._rootSerializer = objectWriter._rootSerializer;
        this._prettyPrinter = objectWriter._prettyPrinter;
    }

    @Override // com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectWriter with(SerializationFeature serializationFeature) {
        SerializationConfig with = this._config.with(serializationFeature);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public ObjectWriter with(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        SerializationConfig with = this._config.with(serializationFeature, serializationFeatureArr);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public ObjectWriter withFeatures(SerializationFeature... serializationFeatureArr) {
        SerializationConfig withFeatures = this._config.withFeatures(serializationFeatureArr);
        return withFeatures == this._config ? this : new ObjectWriter(this, withFeatures);
    }

    public ObjectWriter without(SerializationFeature serializationFeature) {
        SerializationConfig without = this._config.without(serializationFeature);
        return without == this._config ? this : new ObjectWriter(this, without);
    }

    public ObjectWriter without(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        SerializationConfig without = this._config.without(serializationFeature, serializationFeatureArr);
        return without == this._config ? this : new ObjectWriter(this, without);
    }

    public ObjectWriter withoutFeatures(SerializationFeature... serializationFeatureArr) {
        SerializationConfig withoutFeatures = this._config.withoutFeatures(serializationFeatureArr);
        return withoutFeatures == this._config ? this : new ObjectWriter(this, withoutFeatures);
    }

    public ObjectWriter with(DateFormat dateFormat) {
        SerializationConfig with = this._config.with(dateFormat);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        return with(new DefaultPrettyPrinter());
    }

    public ObjectWriter with(FilterProvider filterProvider) {
        if (filterProvider == this._config.getFilterProvider()) {
            return this;
        }
        return new ObjectWriter(this, this._config.withFilters(filterProvider));
    }

    public ObjectWriter with(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == this._prettyPrinter) {
            return this;
        }
        if (prettyPrinter == null) {
            prettyPrinter = NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, this._config, this._rootType, this._rootSerializer, prettyPrinter, this._schema);
    }

    public ObjectWriter withRootName(String str) {
        SerializationConfig withRootName = this._config.withRootName(str);
        return withRootName == this._config ? this : new ObjectWriter(this, withRootName);
    }

    public ObjectWriter withSchema(FormatSchema formatSchema) {
        if (this._schema == formatSchema) {
            return this;
        }
        _verifySchemaType(formatSchema);
        return new ObjectWriter(this, this._config, this._rootType, this._rootSerializer, this._prettyPrinter, formatSchema);
    }

    public ObjectWriter withType(JavaType javaType) {
        JavaType withStaticTyping = javaType.withStaticTyping();
        return new ObjectWriter(this, this._config, withStaticTyping, _prefetchRootSerializer(this._config, withStaticTyping), this._prettyPrinter, this._schema);
    }

    public ObjectWriter withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectWriter withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectWriter withView(Class<?> cls) {
        SerializationConfig withView = this._config.withView(cls);
        return withView == this._config ? this : new ObjectWriter(this, withView);
    }

    public ObjectWriter with(Locale locale) {
        SerializationConfig with = this._config.with(locale);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public ObjectWriter with(TimeZone timeZone) {
        SerializationConfig with = this._config.with(timeZone);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public ObjectWriter with(Base64Variant base64Variant) {
        SerializationConfig with = this._config.with(base64Variant);
        return with == this._config ? this : new ObjectWriter(this, with);
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public SerializationConfig getConfig() {
        return this._config;
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public boolean hasPrefetchedSerializer() {
        return this._rootSerializer != null;
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configureJsonGenerator(jsonGenerator);
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            if (this._rootType == null) {
                _serializerProvider(this._config).serializeValue(jsonGenerator, obj);
            } else {
                _serializerProvider(this._config).serializeValue(jsonGenerator, obj, this._rootType, this._rootSerializer);
            }
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
                return;
            }
            return;
        }
        _writeCloseableValue(jsonGenerator, obj, this._config);
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(writer), obj);
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator(segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public byte[] writeValueAsBytes(Object obj) throws JsonProcessingException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
            byte[] byteArray = byteArrayBuilder.toByteArray();
            byteArrayBuilder.release();
            return byteArray;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        if (javaType != null) {
            _serializerProvider(this._config).acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
            return;
        }
        throw new IllegalArgumentException("type must be provided");
    }

    public boolean canSerialize(Class<?> cls) {
        return _serializerProvider(this._config).hasSerializerFor(cls);
    }

    /* access modifiers changed from: protected */
    public DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.createInstance(serializationConfig, this._serializerFactory);
    }

    /* access modifiers changed from: protected */
    public void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema != null && !this._jsonFactory.canUseSchema(formatSchema)) {
            throw new IllegalArgumentException("Can not use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + this._jsonFactory.getFormatName());
        }
    }

    /* access modifiers changed from: protected */
    public final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configureJsonGenerator(jsonGenerator);
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            try {
                if (this._rootType == null) {
                    _serializerProvider(this._config).serializeValue(jsonGenerator, obj);
                } else {
                    _serializerProvider(this._config).serializeValue(jsonGenerator, obj, this._rootType, this._rootSerializer);
                }
                jsonGenerator.close();
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        jsonGenerator.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        } else {
            _writeCloseable(jsonGenerator, obj, this._config);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002c A[SYNTHETIC, Splitter:B:17:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0033 A[SYNTHETIC, Splitter:B:21:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeCloseable(com.fasterxml.jackson.core.JsonGenerator r5, java.lang.Object r6, com.fasterxml.jackson.databind.SerializationConfig r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException, com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r4 = this;
            r0 = r6
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            com.fasterxml.jackson.databind.JavaType r2 = r4._rootType     // Catch:{ all -> 0x0029 }
            if (r2 != 0) goto L_0x0010
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r7 = r4._serializerProvider(r7)     // Catch:{ all -> 0x0029 }
            r7.serializeValue(r5, r6)     // Catch:{ all -> 0x0029 }
            goto L_0x001b
        L_0x0010:
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r7 = r4._serializerProvider(r7)     // Catch:{ all -> 0x0029 }
            com.fasterxml.jackson.databind.JavaType r2 = r4._rootType     // Catch:{ all -> 0x0029 }
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r3 = r4._rootSerializer     // Catch:{ all -> 0x0029 }
            r7.serializeValue(r5, r6, r2, r3)     // Catch:{ all -> 0x0029 }
        L_0x001b:
            r5.close()     // Catch:{ all -> 0x0026 }
            r0.close()     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r6 = move-exception
            r5 = r1
            r0 = r5
            goto L_0x002a
        L_0x0026:
            r6 = move-exception
            r5 = r1
            goto L_0x002a
        L_0x0029:
            r6 = move-exception
        L_0x002a:
            if (r5 == 0) goto L_0x0031
            r5.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0031
        L_0x0030:
        L_0x0031:
            if (r0 == 0) goto L_0x0036
            r0.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectWriter._writeCloseable(com.fasterxml.jackson.core.JsonGenerator, java.lang.Object, com.fasterxml.jackson.databind.SerializationConfig):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A[SYNTHETIC, Splitter:B:17:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeCloseableValue(com.fasterxml.jackson.core.JsonGenerator r4, java.lang.Object r5, com.fasterxml.jackson.databind.SerializationConfig r6) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException, com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r3 = this;
            r0 = r5
            java.io.Closeable r0 = (java.io.Closeable) r0
            com.fasterxml.jackson.databind.JavaType r1 = r3._rootType     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r6 = r3._serializerProvider(r6)     // Catch:{ all -> 0x0030 }
            r6.serializeValue(r4, r5)     // Catch:{ all -> 0x0030 }
            goto L_0x001a
        L_0x000f:
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r6 = r3._serializerProvider(r6)     // Catch:{ all -> 0x0030 }
            com.fasterxml.jackson.databind.JavaType r1 = r3._rootType     // Catch:{ all -> 0x0030 }
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r2 = r3._rootSerializer     // Catch:{ all -> 0x0030 }
            r6.serializeValue(r4, r5, r1, r2)     // Catch:{ all -> 0x0030 }
        L_0x001a:
            com.fasterxml.jackson.databind.SerializationConfig r5 = r3._config     // Catch:{ all -> 0x0030 }
            com.fasterxml.jackson.databind.SerializationFeature r6 = com.fasterxml.jackson.databind.SerializationFeature.FLUSH_AFTER_WRITE_VALUE     // Catch:{ all -> 0x0030 }
            boolean r5 = r5.isEnabled(r6)     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0027
            r4.flush()     // Catch:{ all -> 0x0030 }
        L_0x0027:
            r4 = 0
            r0.close()     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x0031
        L_0x0030:
            r4 = move-exception
        L_0x0031:
            if (r0 == 0) goto L_0x0036
            r0.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectWriter._writeCloseableValue(com.fasterxml.jackson.core.JsonGenerator, java.lang.Object, com.fasterxml.jackson.databind.SerializationConfig):void");
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _prefetchRootSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        if (javaType != null && this._config.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH)) {
            try {
                return _serializerProvider(serializationConfig).findTypedValueSerializer(javaType, true, (BeanProperty) null);
            } catch (JsonProcessingException unused) {
            }
        }
        return null;
    }

    private void _configureJsonGenerator(JsonGenerator jsonGenerator) {
        PrettyPrinter prettyPrinter = this._prettyPrinter;
        if (prettyPrinter != null) {
            if (prettyPrinter == NULL_PRETTY_PRINTER) {
                jsonGenerator.setPrettyPrinter(null);
            } else {
                if (prettyPrinter instanceof Instantiatable) {
                    prettyPrinter = (PrettyPrinter) ((Instantiatable) prettyPrinter).createInstance();
                }
                jsonGenerator.setPrettyPrinter(prettyPrinter);
            }
        } else if (this._config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonGenerator.setSchema(formatSchema);
        }
    }
}
