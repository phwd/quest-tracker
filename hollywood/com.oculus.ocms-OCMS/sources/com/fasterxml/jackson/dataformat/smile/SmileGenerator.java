package com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.google.common.primitives.SignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class SmileGenerator extends GeneratorBase {
    protected static final long MAX_INT_AS_LONG = 2147483647L;
    private static final int MIN_BUFFER_LENGTH = 770;
    protected static final long MIN_INT_AS_LONG = -2147483648L;
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    protected static final byte TOKEN_BYTE_BIG_DECIMAL = 42;
    protected static final byte TOKEN_BYTE_BIG_INTEGER = 38;
    protected static final byte TOKEN_BYTE_FLOAT_32 = 40;
    protected static final byte TOKEN_BYTE_FLOAT_64 = 41;
    protected static final byte TOKEN_BYTE_INT_32 = 36;
    protected static final byte TOKEN_BYTE_INT_64 = 37;
    protected static final byte TOKEN_BYTE_LONG_STRING_ASCII = -32;
    protected static final ThreadLocal<SoftReference<SmileBufferRecycler<SharedStringNode>>> _smileRecyclerRef = new ThreadLocal<>();
    protected boolean _bufferRecyclable;
    protected int _bytesWritten;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected final IOContext _ioContext;
    protected final OutputStream _out;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected int _outputTail = 0;
    protected int _seenNameCount;
    protected SharedStringNode[] _seenNames;
    protected int _seenStringValueCount;
    protected SharedStringNode[] _seenStringValues;
    protected final SmileBufferRecycler<SharedStringNode> _smileBufferRecycler;
    protected int _smileFeatures;

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        return this;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public enum Feature {
        WRITE_HEADER(true),
        WRITE_END_MARKER(false),
        ENCODE_BINARY_AS_7BIT(true),
        CHECK_SHARED_NAMES(true),
        CHECK_SHARED_STRING_VALUES(false);
        
        protected final boolean _defaultState;
        protected final int _mask = (1 << ordinal());

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

    /* access modifiers changed from: protected */
    public static final class SharedStringNode {
        public final int index;
        public SharedStringNode next;
        public final String value;

        public SharedStringNode(String str, int i, SharedStringNode sharedStringNode) {
            this.value = str;
            this.index = i;
            this.next = sharedStringNode;
        }
    }

    public SmileGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream) {
        super(i, objectCodec);
        this._smileFeatures = i2;
        this._ioContext = iOContext;
        this._smileBufferRecycler = _smileBufferRecycler();
        this._out = outputStream;
        this._bufferRecyclable = true;
        this._outputBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (this._outputEnd >= 770) {
            if ((Feature.CHECK_SHARED_NAMES.getMask() & i2) == 0) {
                this._seenNames = null;
                this._seenNameCount = -1;
            } else {
                this._seenNames = this._smileBufferRecycler.allocSeenNamesBuffer();
                if (this._seenNames == null) {
                    this._seenNames = new SharedStringNode[64];
                }
                this._seenNameCount = 0;
            }
            if ((Feature.CHECK_SHARED_STRING_VALUES.getMask() & i2) == 0) {
                this._seenStringValues = null;
                this._seenStringValueCount = -1;
                return;
            }
            this._seenStringValues = this._smileBufferRecycler.allocSeenStringValuesBuffer();
            if (this._seenStringValues == null) {
                this._seenStringValues = new SharedStringNode[64];
            }
            this._seenStringValueCount = 0;
            return;
        }
        throw new IllegalStateException("Internal encoding buffer length (" + this._outputEnd + ") too short, must be at least " + 770);
    }

    public SmileGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i3, boolean z) {
        super(i, objectCodec);
        this._smileFeatures = i2;
        this._ioContext = iOContext;
        this._smileBufferRecycler = _smileBufferRecycler();
        this._out = outputStream;
        this._bufferRecyclable = z;
        this._outputTail = i3;
        this._outputBuffer = bArr;
        this._outputEnd = this._outputBuffer.length;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (this._outputEnd >= 770) {
            if ((Feature.CHECK_SHARED_NAMES.getMask() & i2) == 0) {
                this._seenNames = null;
                this._seenNameCount = -1;
            } else {
                this._seenNames = this._smileBufferRecycler.allocSeenNamesBuffer();
                if (this._seenNames == null) {
                    this._seenNames = new SharedStringNode[64];
                }
                this._seenNameCount = 0;
            }
            if ((Feature.CHECK_SHARED_STRING_VALUES.getMask() & i2) == 0) {
                this._seenStringValues = null;
                this._seenStringValueCount = -1;
                return;
            }
            this._seenStringValues = this._smileBufferRecycler.allocSeenStringValuesBuffer();
            if (this._seenStringValues == null) {
                this._seenStringValues = new SharedStringNode[64];
            }
            this._seenStringValueCount = 0;
            return;
        }
        throw new IllegalStateException("Internal encoding buffer length (" + this._outputEnd + ") too short, must be at least " + 770);
    }

    public void writeHeader() throws IOException {
        int i = (this._smileFeatures & Feature.CHECK_SHARED_NAMES.getMask()) != 0 ? 1 : 0;
        if ((this._smileFeatures & Feature.CHECK_SHARED_STRING_VALUES.getMask()) != 0) {
            i |= 2;
        }
        if ((this._smileFeatures & Feature.ENCODE_BINARY_AS_7BIT.getMask()) == 0) {
            i |= 4;
        }
        _writeBytes(SmileConstants.HEADER_BYTE_1, (byte) 41, (byte) 10, (byte) i);
    }

    protected static final SmileBufferRecycler<SharedStringNode> _smileBufferRecycler() {
        SmileBufferRecycler<SharedStringNode> smileBufferRecycler;
        SoftReference<SmileBufferRecycler<SharedStringNode>> softReference = _smileRecyclerRef.get();
        if (softReference == null) {
            smileBufferRecycler = null;
        } else {
            smileBufferRecycler = softReference.get();
        }
        if (smileBufferRecycler != null) {
            return smileBufferRecycler;
        }
        SmileBufferRecycler<SharedStringNode> smileBufferRecycler2 = new SmileBufferRecycler<>();
        _smileRecyclerRef.set(new SoftReference<>(smileBufferRecycler2));
        return smileBufferRecycler2;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this._out;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        if (this._writeContext.writeFieldName(str) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str);
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        if (this._writeContext.writeFieldName(serializableString.getValue()) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializableString);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStringField(String str, String str2) throws IOException, JsonGenerationException {
        if (this._writeContext.writeFieldName(str) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str);
        writeString(str2);
    }

    public SmileGenerator enable(Feature feature) {
        this._smileFeatures = feature.getMask() | this._smileFeatures;
        return this;
    }

    public SmileGenerator disable(Feature feature) {
        this._smileFeatures = (feature.getMask() ^ -1) & this._smileFeatures;
        return this;
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._smileFeatures) != 0;
    }

    public SmileGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public void writeRaw(byte b) throws IOException, JsonGenerationException {
        _writeByte((byte) -8);
    }

    public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
        _writeBytes(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        _writeByte((byte) -8);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() throws IOException, JsonGenerationException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc());
        }
        _writeByte((byte) -7);
        this._writeContext = this._writeContext.getParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        _writeByte((byte) -6);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() throws IOException, JsonGenerationException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
        }
        this._writeContext = this._writeContext.getParent();
        _writeByte((byte) -5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeFieldName(java.lang.String r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.smile.SmileGenerator._writeFieldName(java.lang.String):void");
    }

    private final void _writeNonShortFieldName(String str, int i) throws IOException, JsonGenerationException {
        _writeByte(SmileConstants.TOKEN_KEY_LONG_STRING);
        if (i > this._charBufferLength) {
            _slowUTF8Encode(str);
        } else {
            str.getChars(0, i, this._charBuffer, 0);
            int i2 = i + i + i;
            if (i2 <= this._outputBuffer.length) {
                if (this._outputTail + i2 >= this._outputEnd) {
                    _flushBuffer();
                }
                _shortUTF8Encode(this._charBuffer, 0, i);
            } else {
                _mediumUTF8Encode(this._charBuffer, 0, i);
            }
        }
        if (this._seenNameCount >= 0) {
            _addSeenName(str);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = -4;
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        int _findSeenName;
        int charLength = serializableString.charLength();
        if (charLength == 0) {
            _writeByte((byte) 32);
        } else if (this._seenNameCount < 0 || (_findSeenName = _findSeenName(serializableString.getValue())) < 0) {
            byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
            int length = asUnquotedUTF8.length;
            if (length != charLength) {
                _writeFieldNameUnicode(serializableString, asUnquotedUTF8);
                return;
            }
            if (length <= 64) {
                if (this._outputTail + length >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = (byte) (length + 127);
                System.arraycopy(asUnquotedUTF8, 0, bArr, this._outputTail, length);
                this._outputTail += length;
            } else {
                _writeLongAsciiFieldName(asUnquotedUTF8);
            }
            if (this._seenNameCount >= 0) {
                _addSeenName(serializableString.getValue());
            }
        } else {
            _writeSharedNameReference(_findSeenName);
        }
    }

    private final void _writeLongAsciiFieldName(byte[] bArr) throws IOException, JsonGenerationException {
        int length = bArr.length;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr2[i] = SmileConstants.TOKEN_KEY_LONG_STRING;
        int i2 = this._outputTail;
        if (i2 + length + 1 < this._outputEnd) {
            System.arraycopy(bArr, 0, bArr2, i2, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            if (length < 770) {
                System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
                this._outputTail += length;
            } else {
                if (this._outputTail > 0) {
                    _flushBuffer();
                }
                this._out.write(bArr, 0, length);
            }
        }
        byte[] bArr3 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr3[i3] = -4;
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldNameUnicode(SerializableString serializableString, byte[] bArr) throws IOException, JsonGenerationException {
        int length = bArr.length;
        if (length <= 56) {
            if (this._outputTail + length >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr2[i] = (byte) (length + 190);
            System.arraycopy(bArr, 0, bArr2, this._outputTail, length);
            this._outputTail += length;
            if (this._seenNameCount >= 0) {
                _addSeenName(serializableString.getValue());
                return;
            }
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr3[i2] = SmileConstants.TOKEN_KEY_LONG_STRING;
        int i3 = this._outputTail;
        if (i3 + length + 1 < this._outputEnd) {
            System.arraycopy(bArr, 0, bArr3, i3, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            if (length < 770) {
                System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
                this._outputTail += length;
            } else {
                if (this._outputTail > 0) {
                    _flushBuffer();
                }
                this._out.write(bArr, 0, length);
            }
        }
        byte[] bArr4 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr4[i4] = -4;
        if (this._seenNameCount >= 0) {
            _addSeenName(serializableString.getValue());
        }
    }

    private final void _writeSharedNameReference(int i) throws IOException, JsonGenerationException {
        if (i >= this._seenNameCount) {
            throw new IllegalArgumentException("Internal error: trying to write shared name with index " + i + "; but have only seen " + this._seenNameCount + " so far!");
        } else if (i < 64) {
            _writeByte((byte) (i + 64));
        } else {
            _writeBytes((byte) ((i >> 8) + 48), (byte) i);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException, JsonGenerationException {
        int _findSeenStringValue;
        if (str == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write String value");
        int length = str.length();
        if (length == 0) {
            _writeByte((byte) 32);
        } else if (length > 65) {
            _writeNonSharedString(str, length);
        } else if (this._seenStringValueCount < 0 || (_findSeenStringValue = _findSeenStringValue(str)) < 0) {
            if (this._outputTail + 196 >= this._outputEnd) {
                _flushBuffer();
            }
            str.getChars(0, length, this._charBuffer, 0);
            int i = this._outputTail;
            this._outputTail = i + 1;
            int _shortUTF8Encode = _shortUTF8Encode(this._charBuffer, 0, length);
            if (_shortUTF8Encode <= 64) {
                if (this._seenStringValueCount >= 0) {
                    _addSeenStringValue(str);
                }
                if (_shortUTF8Encode == length) {
                    this._outputBuffer[i] = (byte) (_shortUTF8Encode + 63);
                } else {
                    this._outputBuffer[i] = (byte) (_shortUTF8Encode + 126);
                }
            } else {
                this._outputBuffer[i] = _shortUTF8Encode == length ? -32 : SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE;
                byte[] bArr = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr[i2] = -4;
            }
        } else {
            _writeSharedStringValueReference(_findSeenStringValue);
        }
    }

    private final void _writeSharedStringValueReference(int i) throws IOException, JsonGenerationException {
        if (i >= this._seenStringValueCount) {
            throw new IllegalArgumentException("Internal error: trying to write shared String value with index " + i + "; but have only seen " + this._seenStringValueCount + " so far!");
        } else if (i < 31) {
            _writeByte((byte) (i + 1));
        } else {
            _writeBytes((byte) ((i >> 8) + 236), (byte) i);
        }
    }

    private final void _writeNonSharedString(String str, int i) throws IOException, JsonGenerationException {
        if (i > this._charBufferLength) {
            _writeByte(SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
            _slowUTF8Encode(str);
            _writeByte((byte) -4);
            return;
        }
        str.getChars(0, i, this._charBuffer, 0);
        int i2 = i + i + i + 2;
        if (i2 > this._outputBuffer.length) {
            _writeByte(SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
            _mediumUTF8Encode(this._charBuffer, 0, i);
            _writeByte((byte) -4);
            return;
        }
        if (this._outputTail + i2 >= this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        _writeByte((byte) -32);
        if (_shortUTF8Encode(this._charBuffer, 0, i) > i) {
            this._outputBuffer[i3] = SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE;
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = -4;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (i2 > 65 || this._seenStringValueCount < 0 || i2 <= 0) {
            _verifyValueWrite("write String value");
            if (i2 == 0) {
                _writeByte((byte) 32);
                return;
            }
            byte b = SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE;
            if (i2 <= 64) {
                if (this._outputTail + 196 >= this._outputEnd) {
                    _flushBuffer();
                }
                int i3 = this._outputTail;
                this._outputTail = i3 + 1;
                int _shortUTF8Encode = _shortUTF8Encode(cArr, i, i + i2);
                if (_shortUTF8Encode <= 64) {
                    b = (byte) (_shortUTF8Encode == i2 ? _shortUTF8Encode + 63 : _shortUTF8Encode + 126);
                } else {
                    byte[] bArr = this._outputBuffer;
                    int i4 = this._outputTail;
                    this._outputTail = i4 + 1;
                    bArr[i4] = -4;
                }
                this._outputBuffer[i3] = b;
                return;
            }
            int i5 = i2 + i2 + i2 + 2;
            if (i5 <= this._outputBuffer.length) {
                if (this._outputTail + i5 >= this._outputEnd) {
                    _flushBuffer();
                }
                int i6 = this._outputTail;
                _writeByte(SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
                if (_shortUTF8Encode(cArr, i, i + i2) == i2) {
                    this._outputBuffer[i6] = -32;
                }
                byte[] bArr2 = this._outputBuffer;
                int i7 = this._outputTail;
                this._outputTail = i7 + 1;
                bArr2[i7] = -4;
                return;
            }
            _writeByte(SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
            _mediumUTF8Encode(cArr, i, i2 + i);
            _writeByte((byte) -4);
            return;
        }
        writeString(new String(cArr, i, i2));
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        int _findSeenStringValue;
        _verifyValueWrite("write String value");
        String value = serializableString.getValue();
        int length = value.length();
        if (length == 0) {
            _writeByte((byte) 32);
        } else if (length > 65 || this._seenStringValueCount < 0 || (_findSeenStringValue = _findSeenStringValue(value)) < 0) {
            byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
            int length2 = asUnquotedUTF8.length;
            if (length2 <= 64) {
                if (this._outputTail + length2 + 1 >= this._outputEnd) {
                    _flushBuffer();
                }
                int i = length2 == length ? length2 + 63 : length2 + 126;
                byte[] bArr = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr[i2] = (byte) i;
                System.arraycopy(asUnquotedUTF8, 0, bArr, this._outputTail, length2);
                this._outputTail += length2;
                if (this._seenStringValueCount >= 0) {
                    _addSeenStringValue(serializableString.getValue());
                    return;
                }
                return;
            }
            _writeByte(length2 == length ? -32 : SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
            _writeBytes(asUnquotedUTF8, 0, asUnquotedUTF8.length);
            _writeByte((byte) -4);
        } else {
            _writeSharedStringValueReference(_findSeenStringValue);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write String value");
        if (i2 == 0) {
            _writeByte((byte) 32);
        } else if (this._seenStringValueCount >= 0) {
            throw new UnsupportedOperationException("Can not use direct UTF-8 write methods when 'Feature.CHECK_SHARED_STRING_VALUES' enabled");
        } else if (i2 <= 65) {
            if (this._outputTail + i2 >= this._outputEnd) {
                _flushBuffer();
            }
            if (i2 == 1) {
                byte[] bArr2 = this._outputBuffer;
                int i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr2[i3] = SignedBytes.MAX_POWER_OF_TWO;
                int i4 = this._outputTail;
                this._outputTail = i4 + 1;
                bArr2[i4] = bArr[i];
                return;
            }
            byte[] bArr3 = this._outputBuffer;
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr3[i5] = (byte) (i2 + 126);
            System.arraycopy(bArr, i, bArr3, this._outputTail, i2);
            this._outputTail += i2;
        } else {
            int i6 = i2 + i2 + i2 + 2;
            if (i6 <= this._outputBuffer.length) {
                if (this._outputTail + i6 >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr4 = this._outputBuffer;
                int i7 = this._outputTail;
                this._outputTail = i7 + 1;
                bArr4[i7] = SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE;
                System.arraycopy(bArr, i, bArr4, this._outputTail, i2);
                this._outputTail += i2;
                byte[] bArr5 = this._outputBuffer;
                int i8 = this._outputTail;
                this._outputTail = i8 + 1;
                bArr5[i8] = -4;
                return;
            }
            _writeByte(SmileConstants.TOKEN_MISC_LONG_TEXT_UNICODE);
            _writeBytes(bArr, i, i2);
            _writeByte((byte) -4);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        writeRawUTF8String(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        if (bArr == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        if (isEnabled(Feature.ENCODE_BINARY_AS_7BIT)) {
            _writeByte(SmileConstants.TOKEN_MISC_BINARY_7BIT);
            _write7BitBinaryWithLength(bArr, i, i2);
            return;
        }
        _writeByte((byte) -3);
        _writePositiveVInt(i2);
        _writeBytes(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(InputStream inputStream, int i) throws IOException, JsonGenerationException {
        int i2;
        if (i >= 0) {
            _verifyValueWrite("write Binary value");
            if (isEnabled(Feature.ENCODE_BINARY_AS_7BIT)) {
                _writeByte(SmileConstants.TOKEN_MISC_BINARY_7BIT);
                byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
                try {
                    i2 = _write7BitBinaryWithLength(inputStream, i, allocBase64Buffer);
                } finally {
                    this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                }
            } else {
                _writeByte((byte) -3);
                _writePositiveVInt(i);
                i2 = _writeBytes(inputStream, i);
            }
            if (i2 > 0) {
                _reportError("Too few bytes available: missing " + i2 + " bytes (out of " + i + ")");
            }
            return i;
        }
        throw new UnsupportedOperationException("Must pass actual length for Smile encoded data");
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException, JsonGenerationException {
        return writeBinary(inputStream, i);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        _verifyValueWrite("write boolean value");
        if (z) {
            _writeByte(SmileConstants.TOKEN_LITERAL_TRUE);
        } else {
            _writeByte(SmileConstants.TOKEN_LITERAL_FALSE);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException, JsonGenerationException {
        _verifyValueWrite("write null value");
        _writeByte((byte) 33);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        int zigzagEncode = SmileUtil.zigzagEncode(i);
        if (zigzagEncode > 63 || zigzagEncode < 0) {
            byte b = (byte) ((zigzagEncode & 63) + 128);
            int i2 = zigzagEncode >>> 6;
            if (i2 <= 127) {
                _writeBytes(TOKEN_BYTE_INT_32, (byte) i2, b);
                return;
            }
            byte b2 = (byte) (i2 & 127);
            int i3 = i2 >> 7;
            if (i3 <= 127) {
                _writeBytes(TOKEN_BYTE_INT_32, (byte) i3, b2, b);
                return;
            }
            byte b3 = (byte) (i3 & 127);
            int i4 = i3 >> 7;
            if (i4 <= 127) {
                _writeBytes(TOKEN_BYTE_INT_32, (byte) i4, b3, b2, b);
                return;
            }
            _writeBytes(TOKEN_BYTE_INT_32, (byte) (i4 >> 7), (byte) (i4 & 127), b3, b2, b);
        } else if (zigzagEncode <= 31) {
            _writeByte((byte) (zigzagEncode + 192));
        } else {
            _writeBytes(TOKEN_BYTE_INT_32, (byte) (zigzagEncode + 128));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) throws IOException, JsonGenerationException {
        if (j > MAX_INT_AS_LONG || j < -2147483648L) {
            _verifyValueWrite("write number");
            long zigzagEncode = SmileUtil.zigzagEncode(j);
            int i = (int) zigzagEncode;
            byte b = (byte) ((i & 63) + 128);
            byte b2 = (byte) ((i >> 6) & 127);
            byte b3 = (byte) ((i >> 13) & 127);
            byte b4 = (byte) ((i >> 20) & 127);
            long j2 = zigzagEncode >>> 27;
            byte b5 = (byte) (((int) j2) & 127);
            int i2 = (int) (j2 >> 7);
            if (i2 == 0) {
                _writeBytes(TOKEN_BYTE_INT_64, b5, b4, b3, b2, b);
            } else if (i2 <= 127) {
                _writeBytes(TOKEN_BYTE_INT_64, (byte) i2);
                _writeBytes(b5, b4, b3, b2, b);
            } else {
                byte b6 = (byte) (i2 & 127);
                int i3 = i2 >> 7;
                if (i3 <= 127) {
                    _writeBytes(TOKEN_BYTE_INT_64, (byte) i3);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                byte b7 = (byte) (i3 & 127);
                int i4 = i3 >> 7;
                if (i4 <= 127) {
                    _writeBytes(TOKEN_BYTE_INT_64, (byte) i4, b7);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                byte b8 = (byte) (i4 & 127);
                int i5 = i4 >> 7;
                if (i5 <= 127) {
                    _writeBytes(TOKEN_BYTE_INT_64, (byte) i5, b8, b7);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                _writeBytes(TOKEN_BYTE_INT_64, (byte) (i5 >> 7), (byte) (i5 & 127), b8, b7);
                _writeBytes(b6, b5, b4, b3, b2, b);
            }
        } else {
            writeNumber((int) j);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        if (bigInteger == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte(TOKEN_BYTE_BIG_INTEGER);
        byte[] byteArray = bigInteger.toByteArray();
        _write7BitBinaryWithLength(byteArray, 0, byteArray.length);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d) throws IOException, JsonGenerationException {
        _ensureRoomForOutput(11);
        _verifyValueWrite("write number");
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 41;
        int i2 = (int) (doubleToRawLongBits >>> 35);
        int i3 = this._outputTail;
        bArr[i3 + 4] = (byte) (i2 & 127);
        int i4 = i2 >> 7;
        bArr[i3 + 3] = (byte) (i4 & 127);
        int i5 = i4 >> 7;
        bArr[i3 + 2] = (byte) (i5 & 127);
        int i6 = i5 >> 7;
        bArr[i3 + 1] = (byte) (i6 & 127);
        bArr[i3] = (byte) (i6 >> 7);
        this._outputTail = i3 + 5;
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr[i7] = (byte) (((int) (doubleToRawLongBits >> 28)) & 127);
        int i8 = (int) doubleToRawLongBits;
        int i9 = this._outputTail;
        bArr[i9 + 3] = (byte) (i8 & 127);
        int i10 = i8 >> 7;
        bArr[i9 + 2] = (byte) (i10 & 127);
        int i11 = i10 >> 7;
        bArr[i9 + 1] = (byte) (i11 & 127);
        bArr[i9] = (byte) ((i11 >> 7) & 127);
        this._outputTail = i9 + 4;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException, JsonGenerationException {
        _ensureRoomForOutput(6);
        _verifyValueWrite("write number");
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = TOKEN_BYTE_FLOAT_32;
        int i2 = this._outputTail;
        bArr[i2 + 4] = (byte) (floatToRawIntBits & 127);
        int i3 = floatToRawIntBits >> 7;
        bArr[i2 + 3] = (byte) (i3 & 127);
        int i4 = i3 >> 7;
        bArr[i2 + 2] = (byte) (i4 & 127);
        int i5 = i4 >> 7;
        bArr[i2 + 1] = (byte) (i5 & 127);
        bArr[i2] = (byte) ((i5 >> 7) & 127);
        this._outputTail = i2 + 5;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte(TOKEN_BYTE_BIG_DECIMAL);
        _writeSignedVInt(bigDecimal.scale());
        byte[] byteArray = bigDecimal.unscaledValue().toByteArray();
        _write7BitBinaryWithLength(byteArray, 0, byteArray.length);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) throws IOException, JsonGenerationException, UnsupportedOperationException {
        throw _notSupported();
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    public final void _verifyValueWrite(String str) throws IOException, JsonGenerationException {
        if (this._writeContext.writeValue() == 5) {
            _reportError("Can not " + str + ", expecting field name");
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public final void flush() throws IOException {
        _flushBuffer();
        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._out.flush();
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonWriteContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        boolean z = this._closed;
        super.close();
        if (!z && isEnabled(Feature.WRITE_END_MARKER)) {
            _writeByte((byte) -1);
        }
        _flushBuffer();
        if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
            this._out.close();
        } else {
            this._out.flush();
        }
        _releaseBuffers();
    }

    private final int _shortUTF8Encode(char[] cArr, int i, int i2) {
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        while (true) {
            char c = cArr[i];
            if (c > 127) {
                return _shortUTF8Encode2(cArr, i, i2, i3);
            }
            int i4 = i3 + 1;
            bArr[i3] = (byte) c;
            i++;
            if (i >= i2) {
                int i5 = i4 - this._outputTail;
                this._outputTail = i4;
                return i5;
            }
            i3 = i4;
        }
    }

    private final int _shortUTF8Encode2(char[] cArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr = this._outputBuffer;
        while (i < i2) {
            int i5 = i + 1;
            char c = cArr[i];
            if (c <= 127) {
                i4 = i3 + 1;
                bArr[i3] = (byte) c;
            } else if (c < 2048) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) ((c >> 6) | 192);
                i3 = i6 + 1;
                bArr[i6] = (byte) ((c & '?') | 128);
                i = i5;
            } else if (c < SURR1_FIRST || c > SURR2_LAST) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) ((c >> '\f') | 224);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (((c >> 6) & 63) | 128);
                i4 = i8 + 1;
                bArr[i8] = (byte) ((c & '?') | 128);
            } else {
                if (c > SURR1_LAST) {
                    _throwIllegalSurrogate(c);
                }
                if (i5 >= i2) {
                    _throwIllegalSurrogate(c);
                }
                int i9 = i5 + 1;
                int _convertSurrogate = _convertSurrogate(c, cArr[i5]);
                if (_convertSurrogate > 1114111) {
                    _throwIllegalSurrogate(_convertSurrogate);
                }
                int i10 = i3 + 1;
                bArr[i3] = (byte) ((_convertSurrogate >> 18) | 240);
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                i3 = i12 + 1;
                bArr[i12] = (byte) ((_convertSurrogate & 63) | 128);
                i = i9;
            }
            i = i5;
            i3 = i4;
        }
        int i13 = i3 - this._outputTail;
        this._outputTail = i3;
        return i13;
    }

    private void _slowUTF8Encode(String str) throws IOException {
        int length = str.length();
        int i = this._outputEnd - 4;
        int i2 = 0;
        while (i2 < length) {
            if (this._outputTail >= i) {
                _flushBuffer();
            }
            int i3 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                byte[] bArr = this._outputBuffer;
                int i4 = this._outputTail;
                this._outputTail = i4 + 1;
                bArr[i4] = (byte) charAt;
                int i5 = length - i3;
                int i6 = i - this._outputTail;
                if (i5 > i6) {
                    i5 = i6;
                }
                int i7 = i5 + i3;
                while (true) {
                    i2 = i3;
                    if (i2 >= i7) {
                        break;
                    }
                    i3 = i2 + 1;
                    charAt = str.charAt(i2);
                    if (charAt > 127) {
                        break;
                    }
                    byte[] bArr2 = this._outputBuffer;
                    int i8 = this._outputTail;
                    this._outputTail = i8 + 1;
                    bArr2[i8] = (byte) charAt;
                }
            }
            if (charAt < 2048) {
                byte[] bArr3 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr3[i9] = (byte) ((charAt >> 6) | 192);
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr3[i10] = (byte) ((charAt & '?') | 128);
            } else if (charAt < SURR1_FIRST || charAt > SURR2_LAST) {
                byte[] bArr4 = this._outputBuffer;
                int i11 = this._outputTail;
                this._outputTail = i11 + 1;
                bArr4[i11] = (byte) ((charAt >> '\f') | 224);
                int i12 = this._outputTail;
                this._outputTail = i12 + 1;
                bArr4[i12] = (byte) (((charAt >> 6) & 63) | 128);
                int i13 = this._outputTail;
                this._outputTail = i13 + 1;
                bArr4[i13] = (byte) ((charAt & '?') | 128);
            } else {
                if (charAt > SURR1_LAST) {
                    _throwIllegalSurrogate(charAt);
                }
                if (i3 >= length) {
                    _throwIllegalSurrogate(charAt);
                }
                int i14 = i3 + 1;
                int _convertSurrogate = _convertSurrogate(charAt, str.charAt(i3));
                if (_convertSurrogate > 1114111) {
                    _throwIllegalSurrogate(_convertSurrogate);
                }
                byte[] bArr5 = this._outputBuffer;
                int i15 = this._outputTail;
                this._outputTail = i15 + 1;
                bArr5[i15] = (byte) ((_convertSurrogate >> 18) | 240);
                int i16 = this._outputTail;
                this._outputTail = i16 + 1;
                bArr5[i16] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                int i17 = this._outputTail;
                this._outputTail = i17 + 1;
                bArr5[i17] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                int i18 = this._outputTail;
                this._outputTail = i18 + 1;
                bArr5[i18] = (byte) ((_convertSurrogate & 63) | 128);
                i2 = i14;
            }
            i2 = i3;
        }
    }

    private void _mediumUTF8Encode(char[] cArr, int i, int i2) throws IOException {
        int i3 = this._outputEnd - 4;
        while (i < i2) {
            if (this._outputTail >= i3) {
                _flushBuffer();
            }
            int i4 = i + 1;
            char c = cArr[i];
            if (c <= 127) {
                byte[] bArr = this._outputBuffer;
                int i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) c;
                int i6 = i2 - i4;
                int i7 = i3 - this._outputTail;
                if (i6 > i7) {
                    i6 = i7;
                }
                int i8 = i6 + i4;
                while (true) {
                    i = i4;
                    if (i >= i8) {
                        break;
                    }
                    i4 = i + 1;
                    c = cArr[i];
                    if (c > 127) {
                        break;
                    }
                    byte[] bArr2 = this._outputBuffer;
                    int i9 = this._outputTail;
                    this._outputTail = i9 + 1;
                    bArr2[i9] = (byte) c;
                }
            }
            if (c < 2048) {
                byte[] bArr3 = this._outputBuffer;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr3[i10] = (byte) ((c >> 6) | 192);
                int i11 = this._outputTail;
                this._outputTail = i11 + 1;
                bArr3[i11] = (byte) ((c & '?') | 128);
            } else if (c < SURR1_FIRST || c > SURR2_LAST) {
                byte[] bArr4 = this._outputBuffer;
                int i12 = this._outputTail;
                this._outputTail = i12 + 1;
                bArr4[i12] = (byte) ((c >> '\f') | 224);
                int i13 = this._outputTail;
                this._outputTail = i13 + 1;
                bArr4[i13] = (byte) (((c >> 6) & 63) | 128);
                int i14 = this._outputTail;
                this._outputTail = i14 + 1;
                bArr4[i14] = (byte) ((c & '?') | 128);
            } else {
                if (c > SURR1_LAST) {
                    _throwIllegalSurrogate(c);
                }
                if (i4 >= i2) {
                    _throwIllegalSurrogate(c);
                }
                int i15 = i4 + 1;
                int _convertSurrogate = _convertSurrogate(c, cArr[i4]);
                if (_convertSurrogate > 1114111) {
                    _throwIllegalSurrogate(_convertSurrogate);
                }
                byte[] bArr5 = this._outputBuffer;
                int i16 = this._outputTail;
                this._outputTail = i16 + 1;
                bArr5[i16] = (byte) ((_convertSurrogate >> 18) | 240);
                int i17 = this._outputTail;
                this._outputTail = i17 + 1;
                bArr5[i17] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                int i18 = this._outputTail;
                this._outputTail = i18 + 1;
                bArr5[i18] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                int i19 = this._outputTail;
                this._outputTail = i19 + 1;
                bArr5[i19] = (byte) ((_convertSurrogate & 63) | 128);
                i = i15;
            }
            i = i4;
        }
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return ((i - SURR1_FIRST) << 10) + 65536 + (i2 - SURR2_FIRST);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < SURR1_FIRST) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= SURR1_LAST) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }

    private final void _ensureRoomForOutput(int i) throws IOException {
        if (this._outputTail + i >= this._outputEnd) {
            _flushBuffer();
        }
    }

    private final void _writeByte(byte b) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
    }

    private final void _writeBytes(byte b, byte b2) throws IOException {
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = b2;
    }

    private final void _writeBytes(byte b, byte b2, byte b3) throws IOException {
        if (this._outputTail + 2 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = b2;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = b2;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = b4;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4, byte b5) throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = b2;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = b4;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = b5;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) throws IOException {
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = b2;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = b4;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = b5;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = b6;
    }

    private final void _writeBytes(byte[] bArr, int i, int i2) throws IOException {
        if (i2 != 0) {
            int i3 = this._outputTail;
            if (i3 + i2 >= this._outputEnd) {
                _writeBytesLong(bArr, i, i2);
                return;
            }
            System.arraycopy(bArr, i, this._outputBuffer, i3, i2);
            this._outputTail += i2;
        }
    }

    private final int _writeBytes(InputStream inputStream, int i) throws IOException {
        while (i > 0) {
            int i2 = this._outputEnd - this._outputTail;
            if (i2 < 0) {
                _flushBuffer();
                i2 = this._outputEnd - this._outputTail;
            }
            int read = inputStream.read(this._outputBuffer, this._outputTail, i2);
            if (read < 0) {
                break;
            }
            this._outputTail += read;
            i -= read;
        }
        return i;
    }

    private final void _writeBytesLong(byte[] bArr, int i, int i2) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        while (true) {
            int min = Math.min(i2, this._outputEnd - this._outputTail);
            System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, min);
            this._outputTail += min;
            i2 -= min;
            if (i2 != 0) {
                i += min;
                _flushBuffer();
            } else {
                return;
            }
        }
    }

    private void _writePositiveVInt(int i) throws IOException {
        _ensureRoomForOutput(5);
        byte b = (byte) ((i & 63) + 128);
        int i2 = i >> 6;
        if (i2 <= 127) {
            if (i2 > 0) {
                byte[] bArr = this._outputBuffer;
                int i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) i2;
            }
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = b;
            return;
        }
        byte b2 = (byte) (i2 & 127);
        int i5 = i2 >> 7;
        if (i5 <= 127) {
            byte[] bArr3 = this._outputBuffer;
            int i6 = this._outputTail;
            this._outputTail = i6 + 1;
            bArr3[i6] = (byte) i5;
            int i7 = this._outputTail;
            this._outputTail = i7 + 1;
            bArr3[i7] = b2;
            int i8 = this._outputTail;
            this._outputTail = i8 + 1;
            bArr3[i8] = b;
            return;
        }
        byte b3 = (byte) (i5 & 127);
        int i9 = i5 >> 7;
        if (i9 <= 127) {
            byte[] bArr4 = this._outputBuffer;
            int i10 = this._outputTail;
            this._outputTail = i10 + 1;
            bArr4[i10] = (byte) i9;
            int i11 = this._outputTail;
            this._outputTail = i11 + 1;
            bArr4[i11] = b3;
            int i12 = this._outputTail;
            this._outputTail = i12 + 1;
            bArr4[i12] = b2;
            int i13 = this._outputTail;
            this._outputTail = i13 + 1;
            bArr4[i13] = b;
            return;
        }
        byte[] bArr5 = this._outputBuffer;
        int i14 = this._outputTail;
        this._outputTail = i14 + 1;
        bArr5[i14] = (byte) (i9 >> 7);
        int i15 = this._outputTail;
        this._outputTail = i15 + 1;
        bArr5[i15] = (byte) (i9 & 127);
        int i16 = this._outputTail;
        this._outputTail = i16 + 1;
        bArr5[i16] = b3;
        int i17 = this._outputTail;
        this._outputTail = i17 + 1;
        bArr5[i17] = b2;
        int i18 = this._outputTail;
        this._outputTail = i18 + 1;
        bArr5[i18] = b;
    }

    private void _writeSignedVInt(int i) throws IOException {
        _writePositiveVInt(SmileUtil.zigzagEncode(i));
    }

    /* access modifiers changed from: protected */
    public void _write7BitBinaryWithLength(byte[] bArr, int i, int i2) throws IOException {
        _writePositiveVInt(i2);
        while (i2 >= 7) {
            if (this._outputTail + 8 >= this._outputEnd) {
                _flushBuffer();
            }
            int i3 = i + 1;
            byte b = bArr[i];
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) ((b >> 1) & 127);
            int i5 = i3 + 1;
            int i6 = (b << 8) | (bArr[i3] & 255);
            int i7 = this._outputTail;
            this._outputTail = i7 + 1;
            bArr2[i7] = (byte) ((i6 >> 2) & 127);
            int i8 = i5 + 1;
            int i9 = (i6 << 8) | (bArr[i5] & 255);
            int i10 = this._outputTail;
            this._outputTail = i10 + 1;
            bArr2[i10] = (byte) ((i9 >> 3) & 127);
            int i11 = i8 + 1;
            int i12 = (i9 << 8) | (bArr[i8] & 255);
            int i13 = this._outputTail;
            this._outputTail = i13 + 1;
            bArr2[i13] = (byte) ((i12 >> 4) & 127);
            int i14 = i11 + 1;
            int i15 = (i12 << 8) | (bArr[i11] & 255);
            int i16 = this._outputTail;
            this._outputTail = i16 + 1;
            bArr2[i16] = (byte) ((i15 >> 5) & 127);
            int i17 = i14 + 1;
            int i18 = (i15 << 8) | (bArr[i14] & 255);
            int i19 = this._outputTail;
            this._outputTail = i19 + 1;
            bArr2[i19] = (byte) ((i18 >> 6) & 127);
            int i20 = i17 + 1;
            int i21 = (i18 << 8) | (bArr[i17] & 255);
            int i22 = this._outputTail;
            this._outputTail = i22 + 1;
            bArr2[i22] = (byte) ((i21 >> 7) & 127);
            int i23 = this._outputTail;
            this._outputTail = i23 + 1;
            bArr2[i23] = (byte) (i21 & 127);
            i2 -= 7;
            i = i20;
        }
        if (i2 > 0) {
            if (this._outputTail + 7 >= this._outputEnd) {
                _flushBuffer();
            }
            int i24 = i + 1;
            byte b2 = bArr[i];
            byte[] bArr3 = this._outputBuffer;
            int i25 = this._outputTail;
            this._outputTail = i25 + 1;
            bArr3[i25] = (byte) ((b2 >> 1) & 127);
            if (i2 > 1) {
                int i26 = i24 + 1;
                int i27 = ((b2 & 1) << 8) | (bArr[i24] & 255);
                int i28 = this._outputTail;
                this._outputTail = i28 + 1;
                bArr3[i28] = (byte) ((i27 >> 2) & 127);
                if (i2 > 2) {
                    int i29 = i26 + 1;
                    int i30 = ((i27 & 3) << 8) | (bArr[i26] & 255);
                    int i31 = this._outputTail;
                    this._outputTail = i31 + 1;
                    bArr3[i31] = (byte) ((i30 >> 3) & 127);
                    if (i2 > 3) {
                        int i32 = i29 + 1;
                        int i33 = ((i30 & 7) << 8) | (bArr[i29] & 255);
                        int i34 = this._outputTail;
                        this._outputTail = i34 + 1;
                        bArr3[i34] = (byte) ((i33 >> 4) & 127);
                        if (i2 > 4) {
                            int i35 = i32 + 1;
                            int i36 = ((i33 & 15) << 8) | (bArr[i32] & 255);
                            int i37 = this._outputTail;
                            this._outputTail = i37 + 1;
                            bArr3[i37] = (byte) ((i36 >> 5) & 127);
                            if (i2 > 5) {
                                int i38 = (bArr[i35] & 255) | ((i36 & 31) << 8);
                                int i39 = this._outputTail;
                                this._outputTail = i39 + 1;
                                bArr3[i39] = (byte) ((i38 >> 6) & 127);
                                int i40 = this._outputTail;
                                this._outputTail = i40 + 1;
                                bArr3[i40] = (byte) (i38 & 63);
                                return;
                            }
                            int i41 = this._outputTail;
                            this._outputTail = i41 + 1;
                            bArr3[i41] = (byte) (i36 & 31);
                            return;
                        }
                        int i42 = this._outputTail;
                        this._outputTail = i42 + 1;
                        bArr3[i42] = (byte) (i33 & 15);
                        return;
                    }
                    int i43 = this._outputTail;
                    this._outputTail = i43 + 1;
                    bArr3[i43] = (byte) (i30 & 7);
                    return;
                }
                int i44 = this._outputTail;
                this._outputTail = i44 + 1;
                bArr3[i44] = (byte) (i27 & 3);
                return;
            }
            int i45 = this._outputTail;
            this._outputTail = i45 + 1;
            bArr3[i45] = (byte) (b2 & 1);
        }
    }

    /* access modifiers changed from: protected */
    public int _write7BitBinaryWithLength(InputStream inputStream, int i, byte[] bArr) throws IOException {
        _writePositiveVInt(i);
        int i2 = -7;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i3 < 7) {
                break;
            }
            if (i4 > i2) {
                i5 = _readMore(inputStream, bArr, i4, i5, i3);
                if (i5 < 7) {
                    i3 -= i5;
                    i4 = 0;
                    break;
                }
                i2 = i5 - 7;
                i4 = 0;
            }
            if (this._outputTail + 8 >= this._outputEnd) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            byte b = bArr[i4];
            byte[] bArr2 = this._outputBuffer;
            int i7 = this._outputTail;
            this._outputTail = i7 + 1;
            bArr2[i7] = (byte) ((b >> 1) & 127);
            int i8 = i6 + 1;
            int i9 = (bArr[i6] & 255) | (b << 8);
            int i10 = this._outputTail;
            this._outputTail = i10 + 1;
            bArr2[i10] = (byte) ((i9 >> 2) & 127);
            int i11 = i8 + 1;
            int i12 = (i9 << 8) | (bArr[i8] & 255);
            int i13 = this._outputTail;
            this._outputTail = i13 + 1;
            bArr2[i13] = (byte) ((i12 >> 3) & 127);
            int i14 = i11 + 1;
            int i15 = (i12 << 8) | (bArr[i11] & 255);
            int i16 = this._outputTail;
            this._outputTail = i16 + 1;
            bArr2[i16] = (byte) ((i15 >> 4) & 127);
            int i17 = i14 + 1;
            int i18 = (i15 << 8) | (bArr[i14] & 255);
            int i19 = this._outputTail;
            this._outputTail = i19 + 1;
            bArr2[i19] = (byte) ((i18 >> 5) & 127);
            int i20 = i17 + 1;
            int i21 = (i18 << 8) | (bArr[i17] & 255);
            int i22 = this._outputTail;
            this._outputTail = i22 + 1;
            bArr2[i22] = (byte) ((i21 >> 6) & 127);
            int i23 = i20 + 1;
            int i24 = (i21 << 8) | (bArr[i20] & 255);
            int i25 = this._outputTail;
            this._outputTail = i25 + 1;
            bArr2[i25] = (byte) ((i24 >> 7) & 127);
            int i26 = this._outputTail;
            this._outputTail = i26 + 1;
            bArr2[i26] = (byte) (i24 & 127);
            i3 -= 7;
            i4 = i23;
        }
        if (i3 > 0) {
            if (this._outputTail + 7 >= this._outputEnd) {
                _flushBuffer();
            }
            int _readMore = _readMore(inputStream, bArr, i4, i5, i3);
            if (_readMore > 0) {
                i3 -= _readMore;
                byte b2 = bArr[0];
                byte[] bArr3 = this._outputBuffer;
                int i27 = this._outputTail;
                this._outputTail = i27 + 1;
                bArr3[i27] = (byte) ((b2 >> 1) & 127);
                if (_readMore > 1) {
                    int i28 = ((b2 & 1) << 8) | (bArr[1] & 255);
                    int i29 = this._outputTail;
                    this._outputTail = i29 + 1;
                    bArr3[i29] = (byte) ((i28 >> 2) & 127);
                    if (_readMore > 2) {
                        int i30 = ((i28 & 3) << 8) | (bArr[2] & 255);
                        int i31 = this._outputTail;
                        this._outputTail = i31 + 1;
                        bArr3[i31] = (byte) ((i30 >> 3) & 127);
                        if (_readMore > 3) {
                            int i32 = ((i30 & 7) << 8) | (bArr[3] & 255);
                            int i33 = this._outputTail;
                            this._outputTail = i33 + 1;
                            bArr3[i33] = (byte) ((i32 >> 4) & 127);
                            if (_readMore > 4) {
                                int i34 = ((i32 & 15) << 8) | (bArr[4] & 255);
                                int i35 = this._outputTail;
                                this._outputTail = i35 + 1;
                                bArr3[i35] = (byte) ((i34 >> 5) & 127);
                                if (_readMore > 5) {
                                    int i36 = ((i34 & 31) << 8) | (bArr[5] & 255);
                                    int i37 = this._outputTail;
                                    this._outputTail = i37 + 1;
                                    bArr3[i37] = (byte) ((i36 >> 6) & 127);
                                    int i38 = this._outputTail;
                                    this._outputTail = i38 + 1;
                                    bArr3[i38] = (byte) (i36 & 63);
                                } else {
                                    int i39 = this._outputTail;
                                    this._outputTail = i39 + 1;
                                    bArr3[i39] = (byte) (i34 & 31);
                                }
                            } else {
                                int i40 = this._outputTail;
                                this._outputTail = i40 + 1;
                                bArr3[i40] = (byte) (i32 & 15);
                            }
                        } else {
                            int i41 = this._outputTail;
                            this._outputTail = i41 + 1;
                            bArr3[i41] = (byte) (i30 & 7);
                        }
                    } else {
                        int i42 = this._outputTail;
                        this._outputTail = i42 + 1;
                        bArr3[i42] = (byte) (i28 & 3);
                    }
                } else {
                    int i43 = this._outputTail;
                    this._outputTail = i43 + 1;
                    bArr3[i43] = (byte) (b2 & 1);
                }
            }
        }
        return i3;
    }

    private int _readMore(InputStream inputStream, byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        while (i < i2) {
            bArr[i4] = bArr[i];
            i4++;
            i++;
        }
        int min = Math.min(i3, bArr.length);
        do {
            int i5 = min - i4;
            if (i5 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i4, i5);
            if (read < 0) {
                return i4;
            }
            i4 += read;
        } while (i4 < 7);
        return i4;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    public void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
        SharedStringNode[] sharedStringNodeArr = this._seenNames;
        if (sharedStringNodeArr != null && sharedStringNodeArr.length == 64) {
            this._seenNames = null;
            if (this._seenNameCount > 0) {
                Arrays.fill(sharedStringNodeArr, (Object) null);
            }
            this._smileBufferRecycler.releaseSeenNamesBuffer(sharedStringNodeArr);
        }
        SharedStringNode[] sharedStringNodeArr2 = this._seenStringValues;
        if (sharedStringNodeArr2 != null && sharedStringNodeArr2.length == 64) {
            this._seenStringValues = null;
            if (this._seenStringValueCount > 0) {
                Arrays.fill(sharedStringNodeArr2, (Object) null);
            }
            this._smileBufferRecycler.releaseSeenStringValuesBuffer(sharedStringNodeArr2);
        }
    }

    /* access modifiers changed from: protected */
    public final void _flushBuffer() throws IOException {
        int i = this._outputTail;
        if (i > 0) {
            this._bytesWritten += i;
            this._out.write(this._outputBuffer, 0, i);
            this._outputTail = 0;
        }
    }

    private final int _findSeenName(String str) {
        int hashCode = str.hashCode();
        SharedStringNode[] sharedStringNodeArr = this._seenNames;
        SharedStringNode sharedStringNode = sharedStringNodeArr[(sharedStringNodeArr.length - 1) & hashCode];
        if (sharedStringNode == null) {
            return -1;
        }
        if (sharedStringNode.value == str) {
            return sharedStringNode.index;
        }
        SharedStringNode sharedStringNode2 = sharedStringNode;
        do {
            sharedStringNode2 = sharedStringNode2.next;
            if (sharedStringNode2 == null) {
                do {
                    String str2 = sharedStringNode.value;
                    if (str2.hashCode() == hashCode && str2.equals(str)) {
                        return sharedStringNode.index;
                    }
                    sharedStringNode = sharedStringNode.next;
                } while (sharedStringNode != null);
                return -1;
            }
        } while (sharedStringNode2.value != str);
        return sharedStringNode2.index;
    }

    private final void _addSeenName(String str) {
        int i = this._seenNameCount;
        SharedStringNode[] sharedStringNodeArr = this._seenNames;
        if (i == sharedStringNodeArr.length) {
            if (i == 1024) {
                Arrays.fill(sharedStringNodeArr, (Object) null);
                this._seenNameCount = 0;
            } else {
                this._seenNames = new SharedStringNode[1024];
                for (SharedStringNode sharedStringNode : sharedStringNodeArr) {
                    while (sharedStringNode != null) {
                        int hashCode = sharedStringNode.value.hashCode() & 1023;
                        SharedStringNode sharedStringNode2 = sharedStringNode.next;
                        SharedStringNode[] sharedStringNodeArr2 = this._seenNames;
                        sharedStringNode.next = sharedStringNodeArr2[hashCode];
                        sharedStringNodeArr2[hashCode] = sharedStringNode;
                        sharedStringNode = sharedStringNode2;
                    }
                }
            }
        }
        int hashCode2 = str.hashCode();
        SharedStringNode[] sharedStringNodeArr3 = this._seenNames;
        int length = hashCode2 & (sharedStringNodeArr3.length - 1);
        sharedStringNodeArr3[length] = new SharedStringNode(str, this._seenNameCount, sharedStringNodeArr3[length]);
        this._seenNameCount++;
    }

    private final int _findSeenStringValue(String str) {
        int hashCode = str.hashCode();
        SharedStringNode[] sharedStringNodeArr = this._seenStringValues;
        SharedStringNode sharedStringNode = sharedStringNodeArr[(sharedStringNodeArr.length - 1) & hashCode];
        if (sharedStringNode == null) {
            return -1;
        }
        SharedStringNode sharedStringNode2 = sharedStringNode;
        while (sharedStringNode2.value != str) {
            sharedStringNode2 = sharedStringNode2.next;
            if (sharedStringNode2 == null) {
                do {
                    String str2 = sharedStringNode.value;
                    if (str2.hashCode() == hashCode && str2.equals(str)) {
                        return sharedStringNode.index;
                    }
                    sharedStringNode = sharedStringNode.next;
                } while (sharedStringNode != null);
                return -1;
            }
        }
        return sharedStringNode2.index;
    }

    private final void _addSeenStringValue(String str) {
        int i = this._seenStringValueCount;
        SharedStringNode[] sharedStringNodeArr = this._seenStringValues;
        if (i == sharedStringNodeArr.length) {
            if (i == 1024) {
                Arrays.fill(sharedStringNodeArr, (Object) null);
                this._seenStringValueCount = 0;
            } else {
                this._seenStringValues = new SharedStringNode[1024];
                for (SharedStringNode sharedStringNode : sharedStringNodeArr) {
                    while (sharedStringNode != null) {
                        int hashCode = sharedStringNode.value.hashCode() & 1023;
                        SharedStringNode sharedStringNode2 = sharedStringNode.next;
                        SharedStringNode[] sharedStringNodeArr2 = this._seenStringValues;
                        sharedStringNode.next = sharedStringNodeArr2[hashCode];
                        sharedStringNodeArr2[hashCode] = sharedStringNode;
                        sharedStringNode = sharedStringNode2;
                    }
                }
            }
        }
        int hashCode2 = str.hashCode();
        SharedStringNode[] sharedStringNodeArr3 = this._seenStringValues;
        int length = hashCode2 & (sharedStringNodeArr3.length - 1);
        sharedStringNodeArr3[length] = new SharedStringNode(str, this._seenStringValueCount, sharedStringNodeArr3[length]);
        this._seenStringValueCount++;
    }

    /* access modifiers changed from: protected */
    public long outputOffset() {
        return (long) (this._bytesWritten + this._outputTail);
    }

    /* access modifiers changed from: protected */
    public UnsupportedOperationException _notSupported() {
        return new UnsupportedOperationException();
    }
}
