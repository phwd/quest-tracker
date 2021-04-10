package com.fasterxml.jackson.core;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonParser implements Versioned, Closeable {
    protected int _features;

    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    public abstract BigInteger getBigIntegerValue() throws IOException, JsonParseException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException, JsonParseException;

    public abstract double getDoubleValue() throws IOException, JsonParseException;

    public abstract Object getEmbeddedObject() throws IOException, JsonParseException;

    public abstract float getFloatValue() throws IOException, JsonParseException;

    public abstract int getIntValue() throws IOException, JsonParseException;

    public abstract long getLongValue() throws IOException, JsonParseException;

    public abstract NumberType getNumberType() throws IOException, JsonParseException;

    public abstract Number getNumberValue() throws IOException, JsonParseException;

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        return i;
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        return j;
    }

    public abstract String getValueAsString(String str) throws IOException, JsonParseException;

    public abstract boolean hasCurrentToken();

    public abstract boolean hasTextCharacters();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract JsonToken nextValue() throws IOException, JsonParseException;

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false);
        
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

        public int getMask() {
            return 1 << ordinal();
        }
    }

    protected JsonParser() {
    }

    protected JsonParser(int i) {
        this._features = i;
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public byte getByteValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue >= -128 && intValue <= 255) {
            return (byte) intValue;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
    }

    public short getShortValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue >= -32768 && intValue <= 32767) {
            return (short) intValue;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return getValueAsInt(0);
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return getValueAsLong(0);
    }

    public String getValueAsString() throws IOException, JsonParseException {
        return getValueAsString(null);
    }

    /* access modifiers changed from: protected */
    public JsonParseException _constructError(String str) {
        return new JsonParseException(str, getCurrentLocation());
    }
}
