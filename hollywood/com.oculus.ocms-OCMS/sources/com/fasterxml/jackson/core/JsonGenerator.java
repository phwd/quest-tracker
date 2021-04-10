package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonGenerator implements Closeable, Flushable, Versioned {
    protected PrettyPrinter _cfgPrettyPrinter;

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public abstract void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException;

    public abstract void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException;

    public abstract JsonGenerator disable(Feature feature);

    public abstract JsonGenerator enable(Feature feature);

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public abstract ObjectCodec getCodec();

    public int getHighestEscapedChar() {
        return 0;
    }

    public abstract JsonStreamContext getOutputContext();

    public Object getOutputTarget() {
        return null;
    }

    public FormatSchema getSchema() {
        return null;
    }

    public abstract boolean isClosed();

    public abstract boolean isEnabled(Feature feature);

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        return this;
    }

    public abstract JsonGenerator setCodec(ObjectCodec objectCodec);

    public JsonGenerator setHighestNonEscapedChar(int i) {
        return this;
    }

    public abstract JsonGenerator useDefaultPrettyPrinter();

    @Override // com.fasterxml.jackson.core.Versioned
    public abstract Version version();

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException, JsonGenerationException;

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeBoolean(boolean z) throws IOException, JsonGenerationException;

    public abstract void writeEndArray() throws IOException, JsonGenerationException;

    public abstract void writeEndObject() throws IOException, JsonGenerationException;

    public abstract void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException;

    public abstract void writeFieldName(String str) throws IOException, JsonGenerationException;

    public abstract void writeNull() throws IOException, JsonGenerationException;

    public abstract void writeNumber(double d) throws IOException, JsonGenerationException;

    public abstract void writeNumber(float f) throws IOException, JsonGenerationException;

    public abstract void writeNumber(int i) throws IOException, JsonGenerationException;

    public abstract void writeNumber(long j) throws IOException, JsonGenerationException;

    public abstract void writeNumber(String str) throws IOException, JsonGenerationException, UnsupportedOperationException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException;

    public abstract void writeObject(Object obj) throws IOException, JsonProcessingException;

    public abstract void writeRaw(char c) throws IOException, JsonGenerationException;

    public abstract void writeRaw(String str) throws IOException, JsonGenerationException;

    public abstract void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeRawValue(String str) throws IOException, JsonGenerationException;

    public abstract void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeStartArray() throws IOException, JsonGenerationException;

    public abstract void writeStartObject() throws IOException, JsonGenerationException;

    public abstract void writeString(SerializableString serializableString) throws IOException, JsonGenerationException;

    public abstract void writeString(String str) throws IOException, JsonGenerationException;

    public abstract void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException;

    public abstract void writeTree(TreeNode treeNode) throws IOException, JsonProcessingException;

    public abstract void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException;

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false);
        
        private final boolean _defaultState;
        private final int _mask = (1 << ordinal());

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

        public int getMask() {
            return this._mask;
        }
    }

    protected JsonGenerator() {
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Generator of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    public JsonGenerator setRootValueSeparator(SerializableString serializableString) {
        throw new UnsupportedOperationException();
    }

    public final JsonGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._cfgPrettyPrinter = prettyPrinter;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public void writeRaw(SerializableString serializableString) throws IOException, JsonGenerationException {
        writeRaw(serializableString.getValue());
    }

    public void writeBinary(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, i, i2);
    }

    public void writeBinary(byte[] bArr) throws IOException, JsonGenerationException {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public int writeBinary(InputStream inputStream, int i) throws IOException, JsonGenerationException {
        return writeBinary(Base64Variants.getDefaultVariant(), inputStream, i);
    }

    public void writeNumber(short s) throws IOException, JsonGenerationException {
        writeNumber((int) s);
    }

    public void writeStringField(String str, String str2) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeString(str2);
    }

    public final void writeBooleanField(String str, boolean z) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeBoolean(z);
    }

    public final void writeNullField(String str) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNull();
    }

    public final void writeNumberField(String str, int i) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNumber(i);
    }

    public final void writeNumberField(String str, long j) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNumber(j);
    }

    public final void writeNumberField(String str, double d) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNumber(d);
    }

    public final void writeNumberField(String str, float f) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNumber(f);
    }

    public final void writeNumberField(String str, BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeNumber(bigDecimal);
    }

    public final void writeBinaryField(String str, byte[] bArr) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeBinary(bArr);
    }

    public final void writeArrayFieldStart(String str) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeStartArray();
    }

    public final void writeObjectFieldStart(String str) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeStartObject();
    }

    public final void writeObjectField(String str, Object obj) throws IOException, JsonProcessingException {
        writeFieldName(str);
        writeObject(obj);
    }
}
