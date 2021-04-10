package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    private static final long serialVersionUID = -4251443320039569153L;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    protected ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, null, null, null, null);
    }

    protected ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._context = objectMapper._deserializationContext;
        this._rootDeserializers = objectMapper._rootDeserializers;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootNames = objectMapper._rootNames;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        if (obj == null || !javaType.isArrayType()) {
            this._schema = formatSchema;
            this._injectableValues = injectableValues;
            this._unwrapRoot = deserializationConfig.useRootWrapping();
            this._rootDeserializer = _prefetchRootDeserializer(deserializationConfig, javaType);
            this._dataFormatReaders = null;
            return;
        }
        throw new IllegalArgumentException("Can not update an array value");
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj, FormatSchema formatSchema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        this._config = deserializationConfig;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._jsonFactory = objectReader._jsonFactory;
        this._rootNames = objectReader._rootNames;
        this._valueType = javaType;
        this._rootDeserializer = jsonDeserializer;
        this._valueToUpdate = obj;
        if (obj == null || !javaType.isArrayType()) {
            this._schema = formatSchema;
            this._injectableValues = injectableValues;
            this._unwrapRoot = deserializationConfig.useRootWrapping();
            this._dataFormatReaders = dataFormatReaders;
            return;
        }
        throw new IllegalArgumentException("Can not update an array value");
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig) {
        this._config = deserializationConfig;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._jsonFactory = objectReader._jsonFactory;
        this._rootNames = objectReader._rootNames;
        this._valueType = objectReader._valueType;
        this._rootDeserializer = objectReader._rootDeserializer;
        this._valueToUpdate = objectReader._valueToUpdate;
        this._schema = objectReader._schema;
        this._injectableValues = objectReader._injectableValues;
        this._unwrapRoot = deserializationConfig.useRootWrapping();
        this._dataFormatReaders = objectReader._dataFormatReaders;
    }

    protected ObjectReader(ObjectReader objectReader, JsonFactory jsonFactory) {
        this._config = objectReader._config;
        this._context = objectReader._context;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._jsonFactory = jsonFactory;
        this._rootNames = objectReader._rootNames;
        this._valueType = objectReader._valueType;
        this._rootDeserializer = objectReader._rootDeserializer;
        this._valueToUpdate = objectReader._valueToUpdate;
        this._schema = objectReader._schema;
        this._injectableValues = objectReader._injectableValues;
        this._unwrapRoot = objectReader._unwrapRoot;
        this._dataFormatReaders = objectReader._dataFormatReaders;
    }

    @Override // com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectReader with(DeserializationConfig deserializationConfig) {
        return _with(deserializationConfig);
    }

    public ObjectReader with(DeserializationFeature deserializationFeature) {
        return _with(this._config.with(deserializationFeature));
    }

    public ObjectReader with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.with(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withFeatures(deserializationFeatureArr));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature) {
        return _with(this._config.without(deserializationFeature));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.without(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withoutFeatures(deserializationFeatureArr));
    }

    public ObjectReader with(InjectableValues injectableValues) {
        if (this._injectableValues == injectableValues) {
            return this;
        }
        return new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues, this._dataFormatReaders);
    }

    public ObjectReader with(JsonNodeFactory jsonNodeFactory) {
        return _with(this._config.with(jsonNodeFactory));
    }

    public ObjectReader with(JsonFactory jsonFactory) {
        if (jsonFactory == this._jsonFactory) {
            return this;
        }
        ObjectReader objectReader = new ObjectReader(this, jsonFactory);
        if (jsonFactory.getCodec() == null) {
            jsonFactory.setCodec(objectReader);
        }
        return objectReader;
    }

    public ObjectReader withRootName(String str) {
        return _with(this._config.withRootName(str));
    }

    public ObjectReader with(FormatSchema formatSchema) {
        if (this._schema == formatSchema) {
            return this;
        }
        _verifySchemaType(formatSchema);
        return new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, formatSchema, this._injectableValues, this._dataFormatReaders);
    }

    public ObjectReader withType(JavaType javaType) {
        if (javaType != null && javaType.equals(this._valueType)) {
            return this;
        }
        JsonDeserializer<Object> _prefetchRootDeserializer = _prefetchRootDeserializer(this._config, javaType);
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            dataFormatReaders = dataFormatReaders.withType(javaType);
        }
        return new ObjectReader(this, this._config, javaType, _prefetchRootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
    }

    public ObjectReader withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectReader withType(Type type) {
        return withType(this._config.getTypeFactory().constructType(type));
    }

    public ObjectReader withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectReader withValueToUpdate(Object obj) {
        if (obj == this._valueToUpdate) {
            return this;
        }
        if (obj != null) {
            JavaType javaType = this._valueType;
            if (javaType == null) {
                javaType = this._config.constructType(obj.getClass());
            }
            return new ObjectReader(this, this._config, javaType, this._rootDeserializer, obj, this._schema, this._injectableValues, this._dataFormatReaders);
        }
        throw new IllegalArgumentException("cat not update null value");
    }

    public ObjectReader withView(Class<?> cls) {
        return _with(this._config.withView(cls));
    }

    public ObjectReader with(Locale locale) {
        return _with(this._config.with(locale));
    }

    public ObjectReader with(TimeZone timeZone) {
        return _with(this._config.with(timeZone));
    }

    public ObjectReader withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        return _with(this._config.withHandler(deserializationProblemHandler));
    }

    public ObjectReader with(Base64Variant base64Variant) {
        return _with(this._config.with(base64Variant));
    }

    public ObjectReader withFormatDetection(ObjectReader... objectReaderArr) {
        return withFormatDetection(new DataFormatReaders(objectReaderArr));
    }

    public ObjectReader withFormatDetection(DataFormatReaders dataFormatReaders) {
        return new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._config.isEnabled(deserializationFeature);
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    @Deprecated
    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public <T> T readValue(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return (T) _bind(jsonParser, this._valueToUpdate);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return (T) withType((Class<?>) cls).readValue(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return (T) withType(typeReference).readValue(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return (T) withType((JavaType) resolvedType).readValue(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return (T) withType(javaType).readValue(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return _bindAsTree(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return withType((Class<?>) cls).readValues(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> Iterator<T> readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return withType(typeReference).readValues(jsonParser);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> Iterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return withType(javaType).readValues(jsonParser);
    }

    public <T> T readValue(InputStream inputStream) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        return dataFormatReaders != null ? (T) _detectBindAndClose(dataFormatReaders.findFormat(inputStream), false) : (T) _bindAndClose(this._jsonFactory.createParser(inputStream), this._valueToUpdate);
    }

    public <T> T readValue(Reader reader) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        return (T) _bindAndClose(this._jsonFactory.createParser(reader), this._valueToUpdate);
    }

    public <T> T readValue(String str) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        return (T) _bindAndClose(this._jsonFactory.createParser(str), this._valueToUpdate);
    }

    public <T> T readValue(byte[] bArr) throws IOException, JsonProcessingException {
        return this._dataFormatReaders != null ? (T) _detectBindAndClose(bArr, 0, bArr.length) : (T) _bindAndClose(this._jsonFactory.createParser(bArr), this._valueToUpdate);
    }

    public <T> T readValue(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        return this._dataFormatReaders != null ? (T) _detectBindAndClose(bArr, i, i2) : (T) _bindAndClose(this._jsonFactory.createParser(bArr, i, i2), this._valueToUpdate);
    }

    public <T> T readValue(File file) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        return dataFormatReaders != null ? (T) _detectBindAndClose(dataFormatReaders.findFormat(_inputStream(file)), true) : (T) _bindAndClose(this._jsonFactory.createParser(file), this._valueToUpdate);
    }

    public <T> T readValue(URL url) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        return dataFormatReaders != null ? (T) _detectBindAndClose(dataFormatReaders.findFormat(_inputStream(url)), true) : (T) _bindAndClose(this._jsonFactory.createParser(url), this._valueToUpdate);
    }

    public <T> T readValue(JsonNode jsonNode) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(jsonNode);
        }
        return (T) _bindAndClose(treeAsTokens(jsonNode), this._valueToUpdate);
    }

    public JsonNode readTree(InputStream inputStream) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            return _detectBindAndCloseAsTree(inputStream);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(inputStream));
    }

    public JsonNode readTree(Reader reader) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(reader));
    }

    public JsonNode readTree(String str) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(str));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser) throws IOException, JsonProcessingException {
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), false, this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(InputStream inputStream) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(inputStream), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(inputStream), this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(Reader reader) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader);
        }
        JsonParser createParser = this._jsonFactory.createParser(reader);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createParser.setSchema(formatSchema);
        }
        createParser.nextToken();
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(createParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), true, this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(String str) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str);
        }
        JsonParser createParser = this._jsonFactory.createParser(str);
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            createParser.setSchema(formatSchema);
        }
        createParser.nextToken();
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(createParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, createParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), true, this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(bArr, i, i2), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(bArr), this._valueToUpdate);
    }

    public final <T> MappingIterator<T> readValues(byte[] bArr) throws IOException, JsonProcessingException {
        return readValues(bArr, 0, bArr.length);
    }

    public <T> MappingIterator<T> readValues(File file) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(_inputStream(file)), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(file), this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(URL url) throws IOException, JsonProcessingException {
        DataFormatReaders dataFormatReaders = this._dataFormatReaders;
        if (dataFormatReaders != null) {
            return _detectBindAndReadValues(dataFormatReaders.findFormat(_inputStream(url)), true);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(url), this._valueToUpdate);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonNode createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonNode createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public JsonParser treeAsTokens(TreeNode treeNode) {
        return new TreeTraversingParser((JsonNode) treeNode, this);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws JsonProcessingException {
        try {
            return (T) readValue(treeAsTokens(treeNode), cls);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    /* access modifiers changed from: protected */
    public Object _bind(JsonParser jsonParser, Object obj) throws IOException, JsonParseException, JsonMappingException {
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            if (obj == null) {
                obj = _findRootDeserializer(createDeserializationContext(jsonParser, this._config), this._valueType).getNullValue();
            }
        } else if (!(_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT)) {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, this._valueType);
            if (this._unwrapRoot) {
                obj = _unwrapAndDeserialize(jsonParser, createDeserializationContext, this._valueType, _findRootDeserializer);
            } else if (obj == null) {
                obj = _findRootDeserializer.deserialize(jsonParser, createDeserializationContext);
            } else {
                _findRootDeserializer.deserialize(jsonParser, createDeserializationContext, obj);
            }
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    /* access modifiers changed from: protected */
    public Object _bindAndClose(JsonParser jsonParser, Object obj) throws IOException, JsonParseException, JsonMappingException {
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        try {
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                if (obj == null) {
                    obj = _findRootDeserializer(createDeserializationContext(jsonParser, this._config), this._valueType).getNullValue();
                }
            } else if (_initForReading != JsonToken.END_ARRAY) {
                if (_initForReading != JsonToken.END_OBJECT) {
                    DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, this._config);
                    JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, this._valueType);
                    if (this._unwrapRoot) {
                        obj = _unwrapAndDeserialize(jsonParser, createDeserializationContext, this._valueType, _findRootDeserializer);
                    } else if (obj == null) {
                        obj = _findRootDeserializer.deserialize(jsonParser, createDeserializationContext);
                    } else {
                        _findRootDeserializer.deserialize(jsonParser, createDeserializationContext, obj);
                    }
                }
            }
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public JsonNode _bindAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonNode jsonNode;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL || _initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            jsonNode = NullNode.instance;
        } else {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, JSON_NODE_TYPE);
            jsonNode = this._unwrapRoot ? (JsonNode) _unwrapAndDeserialize(jsonParser, createDeserializationContext, JSON_NODE_TYPE, _findRootDeserializer) : (JsonNode) _findRootDeserializer.deserialize(jsonParser, createDeserializationContext);
        }
        jsonParser.clearCurrentToken();
        return jsonNode;
    }

    /* access modifiers changed from: protected */
    public JsonNode _bindAndCloseAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        try {
            return _bindAsTree(jsonParser);
        } finally {
            try {
                jsonParser.close();
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public <T> MappingIterator<T> _bindAndReadValues(JsonParser jsonParser, Object obj) throws IOException, JsonProcessingException {
        FormatSchema formatSchema = this._schema;
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        jsonParser.nextToken();
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, this._config);
        JavaType javaType = this._valueType;
        return new MappingIterator<>(javaType, jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), true, this._valueToUpdate);
    }

    protected static JsonToken _initForReading(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != null || (currentToken = jsonParser.nextToken()) != null) {
            return currentToken;
        }
        throw JsonMappingException.from(jsonParser, "No content to map due to end-of-input");
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializer;
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        if (javaType != null) {
            JsonDeserializer<Object> jsonDeserializer2 = this._rootDeserializers.get(javaType);
            if (jsonDeserializer2 != null) {
                return jsonDeserializer2;
            }
            JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
            if (findRootValueDeserializer != null) {
                this._rootDeserializers.put(javaType, findRootValueDeserializer);
                return findRootValueDeserializer;
            }
            throw new JsonMappingException("Can not find a deserializer for type " + javaType);
        }
        throw new JsonMappingException("No value type configured for ObjectReader");
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _prefetchRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        if (javaType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            try {
                jsonDeserializer = createDeserializationContext(null, this._config).findRootValueDeserializer(javaType);
                if (jsonDeserializer != null) {
                    this._rootDeserializers.put(javaType, jsonDeserializer);
                }
            } catch (JsonProcessingException unused) {
            }
        }
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        String rootName = this._config.getRootName();
        if (rootName == null) {
            rootName = this._rootNames.findRootName(javaType, this._config).getValue();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
        } else if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            if (rootName.equals(currentName)) {
                jsonParser.nextToken();
                Object obj2 = this._valueToUpdate;
                if (obj2 == null) {
                    obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    jsonDeserializer.deserialize(jsonParser, deserializationContext, obj2);
                    obj = this._valueToUpdate;
                }
                if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                    return obj;
                }
                throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
            }
            throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + rootName + "') for type " + javaType);
        } else {
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
        }
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(byte[] bArr, int i, int i2) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(bArr, i, i2);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        return findFormat.getReader()._bindAndClose(findFormat.createParserWithMatch(), this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(DataFormatReaders.Match match, boolean z) throws IOException {
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser createParserWithMatch = match.createParserWithMatch();
        if (z) {
            createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndClose(createParserWithMatch, this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match match, boolean z) throws IOException, JsonProcessingException {
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser createParserWithMatch = match.createParserWithMatch();
        if (z) {
            createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndReadValues(createParserWithMatch, this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public JsonNode _detectBindAndCloseAsTree(InputStream inputStream) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(inputStream);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        JsonParser createParserWithMatch = findFormat.createParserWithMatch();
        createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return findFormat.getReader()._bindAndCloseAsTree(createParserWithMatch);
    }

    /* access modifiers changed from: protected */
    public void _reportUnkownFormat(DataFormatReaders dataFormatReaders, DataFormatReaders.Match match) throws JsonProcessingException {
        throw new JsonParseException("Can not detect format from input, does not look like any of detectable formats " + dataFormatReaders.toString(), JsonLocation.NA);
    }

    /* access modifiers changed from: protected */
    public void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema != null && !this._jsonFactory.canUseSchema(formatSchema)) {
            throw new IllegalArgumentException("Can not use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + this._jsonFactory.getFormatName());
        }
    }

    /* access modifiers changed from: protected */
    public DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._context.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    /* access modifiers changed from: protected */
    public ObjectReader _with(DeserializationConfig deserializationConfig) {
        if (deserializationConfig == this._config) {
            return this;
        }
        if (this._dataFormatReaders != null) {
            return new ObjectReader(this, deserializationConfig).withFormatDetection(this._dataFormatReaders.with(deserializationConfig));
        }
        return new ObjectReader(this, deserializationConfig);
    }

    /* access modifiers changed from: protected */
    public void _reportUndetectableSource(Object obj) throws JsonProcessingException {
        throw new JsonParseException("Can not use source of type " + obj.getClass().getName() + " with format auto-detection: must be byte- not char-based", JsonLocation.NA);
    }

    /* access modifiers changed from: protected */
    public InputStream _inputStream(URL url) throws IOException {
        return url.openStream();
    }

    /* access modifiers changed from: protected */
    public InputStream _inputStream(File file) throws IOException {
        return new FileInputStream(file);
    }
}
