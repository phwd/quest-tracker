package com.fasterxml.jackson.core;

import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.net.URL;

public class JsonFactory implements Versioned, Serializable {
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    public static final String FORMAT_NAME_JSON = "JSON";
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal<>();
    private static final long serialVersionUID = 8726401676402117450L;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final transient BytesToNameCanonicalizer _rootByteSymbols;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    public boolean requiresCustomCodec() {
        return false;
    }

    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true);
        
        private final boolean _defaultState;

        public static int collectDefaults() {
            Feature[] values = values();
            int i = 0;
            for (Feature feature : values) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        private Feature(boolean z) {
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this(null);
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
    }

    protected JsonFactory(JsonFactory jsonFactory, ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = null;
        this._factoryFeatures = jsonFactory._factoryFeatures;
        this._parserFeatures = jsonFactory._parserFeatures;
        this._generatorFeatures = jsonFactory._generatorFeatures;
        this._characterEscapes = jsonFactory._characterEscapes;
        this._inputDecorator = jsonFactory._inputDecorator;
        this._outputDecorator = jsonFactory._outputDecorator;
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
    }

    public JsonFactory copy() {
        _checkInvalidCopy(JsonFactory.class);
        return new JsonFactory(this, null);
    }

    /* access modifiers changed from: protected */
    public void _checkInvalidCopy(Class<?> cls) {
        if (getClass() != cls) {
            throw new IllegalStateException("Failed copy(): " + getClass().getName() + " (version: " + version() + ") does not override copy(); it has to");
        }
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        return new JsonFactory(this, this._objectCodec);
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        String formatName = getFormatName();
        return formatName != null && formatName.equals(formatSchema.getSchemaType());
    }

    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return FORMAT_NAME_JSON;
        }
        return null;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        if (getClass() == JsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }

    @Override // com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    public final JsonFactory configure(Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public JsonFactory enable(Feature feature) {
        this._factoryFeatures = feature.getMask() | this._factoryFeatures;
        return this;
    }

    public JsonFactory disable(Feature feature) {
        this._factoryFeatures = (feature.getMask() ^ -1) & this._factoryFeatures;
        return this;
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._factoryFeatures) != 0;
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this._parserFeatures = feature.getMask() | this._parserFeatures;
        return this;
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this._parserFeatures = (feature.getMask() ^ -1) & this._parserFeatures;
        return this;
    }

    public final boolean isEnabled(JsonParser.Feature feature) {
        return (feature.getMask() & this._parserFeatures) != 0;
    }

    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }

    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this._generatorFeatures = feature.getMask() | this._generatorFeatures;
        return this;
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (feature.getMask() ^ -1) & this._generatorFeatures;
        return this;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._generatorFeatures) != 0;
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }

    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }

    public JsonFactory setRootValueSeparator(String str) {
        this._rootValueSeparator = str == null ? null : new SerializedString(str);
        return this;
    }

    public String getRootValueSeparator() {
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString == null) {
            return null;
        }
        return serializableString.getValue();
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonParser createParser(File file) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(file, true);
        InputStream fileInputStream = new FileInputStream(file);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            fileInputStream = inputDecorator.decorate(_createContext, fileInputStream);
        }
        return _createParser(fileInputStream, _createContext);
    }

    public JsonParser createParser(URL url) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(url, true);
        InputStream _optimizedStreamFromURL = _optimizedStreamFromURL(url);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            _optimizedStreamFromURL = inputDecorator.decorate(_createContext, _optimizedStreamFromURL);
        }
        return _createParser(_optimizedStreamFromURL, _createContext);
    }

    public JsonParser createParser(InputStream inputStream) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(inputStream, false);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            inputStream = inputDecorator.decorate(_createContext, inputStream);
        }
        return _createParser(inputStream, _createContext);
    }

    public JsonParser createParser(Reader reader) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(reader, false);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            reader = inputDecorator.decorate(_createContext, reader);
        }
        return _createParser(reader, _createContext);
    }

    public JsonParser createParser(byte[] bArr) throws IOException, JsonParseException {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator == null || (decorate = inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) == null) {
            return _createParser(bArr, 0, bArr.length, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonParser createParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator == null || (decorate = inputDecorator.decorate(_createContext, bArr, i, i2)) == null) {
            return _createParser(bArr, i, i2, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonParser createParser(String str) throws IOException, JsonParseException {
        Reader stringReader = new StringReader(str);
        IOContext _createContext = _createContext(stringReader, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            stringReader = inputDecorator.decorate(_createContext, stringReader);
        }
        return _createParser(stringReader, _createContext);
    }

    @Deprecated
    public JsonParser createJsonParser(File file) throws IOException, JsonParseException {
        return createParser(file);
    }

    @Deprecated
    public JsonParser createJsonParser(URL url) throws IOException, JsonParseException {
        return createParser(url);
    }

    @Deprecated
    public JsonParser createJsonParser(InputStream inputStream) throws IOException, JsonParseException {
        return createParser(inputStream);
    }

    @Deprecated
    public JsonParser createJsonParser(Reader reader) throws IOException, JsonParseException {
        return createParser(reader);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr) throws IOException, JsonParseException {
        return createParser(bArr);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        return createParser(bArr, i, i2);
    }

    @Deprecated
    public JsonParser createJsonParser(String str) throws IOException, JsonParseException {
        return createParser(str);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext _createContext = _createContext(outputStream, false);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            OutputDecorator outputDecorator = this._outputDecorator;
            if (outputDecorator != null) {
                outputStream = outputDecorator.decorate(_createContext, outputStream);
            }
            return _createUTF8Generator(outputStream, _createContext);
        }
        Writer _createWriter = _createWriter(outputStream, jsonEncoding, _createContext);
        OutputDecorator outputDecorator2 = this._outputDecorator;
        if (outputDecorator2 != null) {
            _createWriter = outputDecorator2.decorate(_createContext, _createWriter);
        }
        return _createGenerator(_createWriter, _createContext);
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        IOContext _createContext = _createContext(writer, false);
        OutputDecorator outputDecorator = this._outputDecorator;
        if (outputDecorator != null) {
            writer = outputDecorator.decorate(_createContext, writer);
        }
        return _createGenerator(writer, _createContext);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(file);
        IOContext _createContext = _createContext(fileOutputStream, true);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            OutputDecorator outputDecorator = this._outputDecorator;
            if (outputDecorator != null) {
                fileOutputStream = outputDecorator.decorate(_createContext, fileOutputStream);
            }
            return _createUTF8Generator(fileOutputStream, _createContext);
        }
        Writer _createWriter = _createWriter(fileOutputStream, jsonEncoding, _createContext);
        OutputDecorator outputDecorator2 = this._outputDecorator;
        if (outputDecorator2 != null) {
            _createWriter = outputDecorator2.decorate(_createContext, _createWriter);
        }
        return _createGenerator(_createWriter, _createContext);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(outputStream, jsonEncoding);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        return createGenerator(writer);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(file, jsonEncoding);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        return new ByteSourceJsonBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(inputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES)));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(reader, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        return new ByteSourceJsonBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(bArr, i, i2, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer);
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            writerBasedJsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonGenerator _createJsonGenerator(Writer writer, IOContext iOContext) throws IOException {
        return _createGenerator(writer, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        UTF8JsonGenerator uTF8JsonGenerator = new UTF8JsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, outputStream);
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            uTF8JsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            uTF8JsonGenerator.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonGenerator _createUTF8JsonGenerator(OutputStream outputStream, IOContext iOContext) throws IOException {
        return _createUTF8Generator(outputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    public Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        if (jsonEncoding == JsonEncoding.UTF8) {
            return new UTF8Writer(iOContext, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    /* access modifiers changed from: protected */
    public IOContext _createContext(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }

    public BufferRecycler _getBufferRecycler() {
        BufferRecycler bufferRecycler;
        SoftReference<BufferRecycler> softReference = _recyclerRef.get();
        if (softReference == null) {
            bufferRecycler = null;
        } else {
            bufferRecycler = softReference.get();
        }
        if (bufferRecycler != null) {
            return bufferRecycler;
        }
        BufferRecycler bufferRecycler2 = new BufferRecycler();
        _recyclerRef.set(new SoftReference<>(bufferRecycler2));
        return bufferRecycler2;
    }

    /* access modifiers changed from: protected */
    public InputStream _optimizedStreamFromURL(URL url) throws IOException {
        String host;
        if (!UriUtil.LOCAL_FILE_SCHEME.equals(url.getProtocol()) || (((host = url.getHost()) != null && host.length() != 0) || url.getPath().indexOf(37) >= 0)) {
            return url.openStream();
        }
        return new FileInputStream(url.getPath());
    }
}
