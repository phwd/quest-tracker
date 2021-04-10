package com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

public class SmileFactory extends JsonFactory {
    static final int DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS = SmileGenerator.Feature.collectDefaults();
    static final int DEFAULT_SMILE_PARSER_FEATURE_FLAGS = SmileParser.Feature.collectDefaults();
    public static final String FORMAT_NAME_SMILE = "Smile";
    private static final long serialVersionUID = -1696783009312472365L;
    protected boolean _cfgDelegateToTextual;
    protected int _smileGeneratorFeatures;
    protected int _smileParserFeatures;

    @Override // com.fasterxml.jackson.core.JsonFactory
    public String getFormatName() {
        return FORMAT_NAME_SMILE;
    }

    public SmileFactory() {
        this(null);
    }

    public SmileFactory(ObjectCodec objectCodec) {
        super(objectCodec);
        this._smileParserFeatures = DEFAULT_SMILE_PARSER_FEATURE_FLAGS;
        this._smileGeneratorFeatures = DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS;
    }

    public SmileFactory(SmileFactory smileFactory, ObjectCodec objectCodec) {
        super(smileFactory, objectCodec);
        this._cfgDelegateToTextual = smileFactory._cfgDelegateToTextual;
        this._smileParserFeatures = smileFactory._smileParserFeatures;
        this._smileGeneratorFeatures = smileFactory._smileGeneratorFeatures;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileFactory copy() {
        _checkInvalidCopy(SmileFactory.class);
        return new SmileFactory(this, null);
    }

    public void delegateToTextual(boolean z) {
        this._cfgDelegateToTextual = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public Object readResolve() {
        return new SmileFactory(this, this._objectCodec);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        return SmileParserBootstrapper.hasSmileFormat(inputAccessor);
    }

    public final SmileFactory configure(SmileParser.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public SmileFactory enable(SmileParser.Feature feature) {
        this._smileParserFeatures = feature.getMask() | this._smileParserFeatures;
        return this;
    }

    public SmileFactory disable(SmileParser.Feature feature) {
        this._smileParserFeatures = (feature.getMask() ^ -1) & this._smileParserFeatures;
        return this;
    }

    public final boolean isEnabled(SmileParser.Feature feature) {
        return (feature.getMask() & this._smileParserFeatures) != 0;
    }

    public final SmileFactory configure(SmileGenerator.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public SmileFactory enable(SmileGenerator.Feature feature) {
        this._smileGeneratorFeatures = feature.getMask() | this._smileGeneratorFeatures;
        return this;
    }

    public SmileFactory disable(SmileGenerator.Feature feature) {
        this._smileGeneratorFeatures = (feature.getMask() ^ -1) & this._smileGeneratorFeatures;
        return this;
    }

    public final boolean isEnabled(SmileGenerator.Feature feature) {
        return (feature.getMask() & this._smileGeneratorFeatures) != 0;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser createParser(File file) throws IOException, JsonParseException {
        return _createParser((InputStream) new FileInputStream(file), _createContext(file, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser createParser(URL url) throws IOException, JsonParseException {
        return _createParser(_optimizedStreamFromURL(url), _createContext(url, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser createParser(InputStream inputStream) throws IOException, JsonParseException {
        return _createParser(inputStream, _createContext(inputStream, false));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser createParser(byte[] bArr) throws IOException, JsonParseException {
        return _createParser(bArr, 0, bArr.length, _createContext(bArr, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser createParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        return _createParser(bArr, i, i2, _createContext(bArr, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileParser createJsonParser(File file) throws IOException, JsonParseException {
        return _createParser((InputStream) new FileInputStream(file), _createContext(file, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileParser createJsonParser(URL url) throws IOException, JsonParseException {
        return _createParser(_optimizedStreamFromURL(url), _createContext(url, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileParser createJsonParser(InputStream inputStream) throws IOException, JsonParseException {
        return _createParser(inputStream, _createContext(inputStream, false));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileParser createJsonParser(byte[] bArr) throws IOException, JsonParseException {
        return _createParser(bArr, 0, bArr.length, _createContext(bArr, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileParser createJsonParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        return _createParser(bArr, i, i2, _createContext(bArr, true));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return _createGenerator(outputStream, _createContext(outputStream, false));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileGenerator createGenerator(OutputStream outputStream) throws IOException {
        return _createGenerator(outputStream, _createContext(outputStream, false));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return _createGenerator(outputStream, _createContext(outputStream, false));
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return _createGenerator(outputStream, _createContext(outputStream, false));
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    @Deprecated
    public SmileGenerator _createUTF8JsonGenerator(OutputStream outputStream, IOContext iOContext) throws IOException {
        return _createGenerator(outputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        return new SmileParserBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._smileParserFeatures, isEnabled(JsonFactory.Feature.INTERN_FIELD_NAMES), this._objectCodec, this._rootByteSymbols);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        if (this._cfgDelegateToTextual) {
            return super._createParser(reader, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public SmileParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        return new SmileParserBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._smileParserFeatures, isEnabled(JsonFactory.Feature.INTERN_FIELD_NAMES), this._objectCodec, this._rootByteSymbols);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        if (this._cfgDelegateToTextual) {
            return super._createGenerator(writer, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public JsonGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        return _createGenerator(outputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        if (this._cfgDelegateToTextual) {
            return super._createWriter(outputStream, jsonEncoding, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    /* access modifiers changed from: protected */
    public SmileGenerator _createGenerator(OutputStream outputStream, IOContext iOContext) throws IOException {
        int i = this._smileGeneratorFeatures;
        SmileGenerator smileGenerator = new SmileGenerator(iOContext, this._generatorFeatures, i, this._objectCodec, outputStream);
        if ((SmileGenerator.Feature.WRITE_HEADER.getMask() & i) != 0) {
            smileGenerator.writeHeader();
        } else if ((SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES.getMask() & i) != 0) {
            throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but CHECK_SHARED_STRING_VALUES enabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or disable CHECK_SHARED_STRING_VALUES to resolve)");
        } else if ((SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT.getMask() & i) == 0) {
            throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but ENCODE_BINARY_AS_7BIT disabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or ENCODE_BINARY_AS_7BIT to resolve)");
        }
        return smileGenerator;
    }
}
