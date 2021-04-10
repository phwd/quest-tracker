package com.fasterxml.jackson.dataformat.smile;

import com.facebook.ultralight.UL;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class SmileParser extends ParserBase {
    private static final int[] NO_INTS = new int[0];
    private static final String[] NO_STRINGS = new String[0];
    protected static final ThreadLocal<SoftReference<SmileBufferRecycler<String>>> _smileRecyclerRef = new ThreadLocal<>();
    protected boolean _bufferRecyclable;
    protected boolean _got32BitFloat;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected boolean _mayContainRawBinary;
    protected ObjectCodec _objectCodec;
    protected int _quad1;
    protected int _quad2;
    protected int[] _quadBuffer = NO_INTS;
    protected int _seenNameCount = 0;
    protected String[] _seenNames = NO_STRINGS;
    protected int _seenStringValueCount = -1;
    protected String[] _seenStringValues = null;
    protected final SmileBufferRecycler<String> _smileBufferRecycler;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;
    protected int _typeByte;

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public enum Feature {
        REQUIRE_HEADER(true);
        
        final boolean _defaultState;
        final int _mask = (1 << ordinal());

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

    public SmileParser(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, InputStream inputStream, byte[] bArr, int i3, int i4, boolean z) {
        super(iOContext, i);
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputStream = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i4;
        this._bufferRecyclable = z;
        this._tokenInputRow = -1;
        this._tokenInputCol = -1;
        this._smileBufferRecycler = _smileBufferRecycler();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    /* access modifiers changed from: protected */
    public boolean handleSignature(boolean z, boolean z2) throws IOException, JsonParseException {
        if (z) {
            this._inputPtr++;
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        boolean z3 = false;
        if (this._inputBuffer[this._inputPtr] != 41) {
            if (z2) {
                _reportError("Malformed content: signature not valid, starts with 0x3a but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr]) + ", not 0x29");
            }
            return false;
        }
        int i = this._inputPtr + 1;
        this._inputPtr = i;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        if (this._inputBuffer[this._inputPtr] != 10) {
            if (z2) {
                _reportError("Malformed content: signature not valid, starts with 0x3a, 0x29, but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr]) + ", not 0xA");
            }
            return false;
        }
        int i2 = this._inputPtr + 1;
        this._inputPtr = i2;
        if (i2 >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        int i4 = (b >> 4) & 15;
        if (i4 != 0) {
            _reportError("Header version number bits (0x" + Integer.toHexString(i4) + ") indicate unrecognized version; only 0x0 handled by parser");
        }
        if ((b & 1) == 0) {
            this._seenNames = null;
            this._seenNameCount = -1;
        }
        if ((b & 2) != 0) {
            this._seenStringValues = NO_STRINGS;
            this._seenStringValueCount = 0;
        }
        if ((b & 4) != 0) {
            z3 = true;
        }
        this._mayContainRawBinary = z3;
        return true;
    }

    protected static final SmileBufferRecycler<String> _smileBufferRecycler() {
        SmileBufferRecycler<String> smileBufferRecycler;
        SoftReference<SmileBufferRecycler<String>> softReference = _smileRecyclerRef.get();
        if (softReference == null) {
            smileBufferRecycler = null;
        } else {
            smileBufferRecycler = softReference.get();
        }
        if (smileBufferRecycler != null) {
            return smileBufferRecycler;
        }
        SmileBufferRecycler<String> smileBufferRecycler2 = new SmileBufferRecycler<>();
        _smileRecyclerRef.set(new SoftReference<>(smileBufferRecycler2));
        return smileBufferRecycler2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.Versioned, com.fasterxml.jackson.core.base.ParserBase
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), this._tokenInputTotal, -1, -1, (int) this._tokenInputTotal);
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public JsonLocation getCurrentLocation() {
        long j = this._currInputProcessed + ((long) this._inputPtr);
        return new JsonLocation(this._ioContext.getSourceReference(), j, -1, -1, (int) j);
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public final boolean loadMore() throws IOException {
        this._currInputProcessed += (long) this._inputEnd;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 <= 0 || this._inputPtr <= 0) {
            this._inputEnd = 0;
        } else {
            this._currInputProcessed += (long) this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + i2 + " bytes");
            }
            this._inputEnd += read;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _finishString() throws IOException, JsonParseException {
        _throwInternal();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase, java.io.Closeable, com.fasterxml.jackson.core.base.ParserBase, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.base.ParserBase
    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.hasTextAsCharacters();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (this._bufferRecyclable && (bArr = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseReadIOBuffer(bArr);
        }
        String[] strArr = this._seenNames;
        if (strArr != null && strArr.length > 0) {
            this._seenNames = null;
            int i = this._seenNameCount;
            if (i > 0) {
                Arrays.fill(strArr, 0, i, (Object) null);
            }
            this._smileBufferRecycler.releaseSeenNamesBuffer(strArr);
        }
        String[] strArr2 = this._seenStringValues;
        if (strArr2 != null && strArr2.length > 0) {
            this._seenStringValues = null;
            int i2 = this._seenStringValueCount;
            if (i2 > 0) {
                Arrays.fill(strArr2, 0, i2, (Object) null);
            }
            this._smileBufferRecycler.releaseSeenStringValuesBuffer(strArr2);
        }
    }

    public boolean mayContainRawBinary() {
        return this._mayContainRawBinary;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public JsonToken nextToken() throws IOException, JsonParseException {
        boolean z = false;
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipIncomplete();
        }
        this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
        this._binaryValue = null;
        if (this._parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
            JsonToken _handleFieldName = _handleFieldName();
            this._currToken = _handleFieldName;
            return _handleFieldName;
        } else if (this._inputPtr < this._inputEnd || loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            this._typeByte = b;
            switch ((b >> 5) & 7) {
                case 0:
                    if (b == 0) {
                        _reportError("Invalid token byte 0x00");
                    }
                    return _handleSharedString(b - 1);
                case 1:
                    int i2 = b & Ascii.US;
                    if (i2 < 4) {
                        if (i2 == 0) {
                            this._textBuffer.resetWithEmpty();
                            JsonToken jsonToken = JsonToken.VALUE_STRING;
                            this._currToken = jsonToken;
                            return jsonToken;
                        } else if (i2 == 1) {
                            JsonToken jsonToken2 = JsonToken.VALUE_NULL;
                            this._currToken = jsonToken2;
                            return jsonToken2;
                        } else if (i2 != 2) {
                            JsonToken jsonToken3 = JsonToken.VALUE_TRUE;
                            this._currToken = jsonToken3;
                            return jsonToken3;
                        } else {
                            JsonToken jsonToken4 = JsonToken.VALUE_FALSE;
                            this._currToken = jsonToken4;
                            return jsonToken4;
                        }
                    } else if (i2 < 8) {
                        if ((i2 & 3) <= 2) {
                            this._tokenIncomplete = true;
                            this._numTypesValid = 0;
                            JsonToken jsonToken5 = JsonToken.VALUE_NUMBER_INT;
                            this._currToken = jsonToken5;
                            return jsonToken5;
                        }
                    } else if (i2 < 12) {
                        int i3 = i2 & 3;
                        if (i3 <= 2) {
                            this._tokenIncomplete = true;
                            this._numTypesValid = 0;
                            if (i3 == 0) {
                                z = true;
                            }
                            this._got32BitFloat = z;
                            JsonToken jsonToken6 = JsonToken.VALUE_NUMBER_FLOAT;
                            this._currToken = jsonToken6;
                            return jsonToken6;
                        }
                    } else if (i2 != 26 || !handleSignature(false, false)) {
                        _reportError("Unrecognized token byte 0x3A (malformed segment header?");
                        break;
                    } else if (this._currToken == null) {
                        return nextToken();
                    } else {
                        this._currToken = null;
                        return null;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    this._currToken = JsonToken.VALUE_STRING;
                    if (this._seenStringValueCount >= 0) {
                        _addSeenStringValue();
                    } else {
                        this._tokenIncomplete = true;
                    }
                    return this._currToken;
                case 6:
                    this._numberInt = SmileUtil.zigzagDecode(b & Ascii.US);
                    this._numTypesValid = 1;
                    JsonToken jsonToken7 = JsonToken.VALUE_NUMBER_INT;
                    this._currToken = jsonToken7;
                    return jsonToken7;
                case 7:
                    int i4 = b & Ascii.US;
                    if (i4 == 0 || i4 == 4) {
                        this._tokenIncomplete = true;
                        JsonToken jsonToken8 = JsonToken.VALUE_STRING;
                        this._currToken = jsonToken8;
                        return jsonToken8;
                    } else if (i4 != 8) {
                        if (i4 != 29) {
                            if (i4 != 31) {
                                switch (i4) {
                                    case 12:
                                    case 13:
                                    case 14:
                                    case 15:
                                        if (this._inputPtr >= this._inputEnd) {
                                            loadMoreGuaranteed();
                                        }
                                        byte[] bArr2 = this._inputBuffer;
                                        int i5 = this._inputPtr;
                                        this._inputPtr = i5 + 1;
                                        return _handleSharedString(((b & 3) << 8) + (bArr2[i5] & 255));
                                    default:
                                        switch (i4) {
                                            case 24:
                                                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
                                                JsonToken jsonToken9 = JsonToken.START_ARRAY;
                                                this._currToken = jsonToken9;
                                                return jsonToken9;
                                            case 25:
                                                if (!this._parsingContext.inArray()) {
                                                    _reportMismatchedEndMarker(93, '}');
                                                }
                                                this._parsingContext = this._parsingContext.getParent();
                                                JsonToken jsonToken10 = JsonToken.END_ARRAY;
                                                this._currToken = jsonToken10;
                                                return jsonToken10;
                                            case 26:
                                                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                                                JsonToken jsonToken11 = JsonToken.START_OBJECT;
                                                this._currToken = jsonToken11;
                                                return jsonToken11;
                                            case 27:
                                                _reportError("Invalid type marker byte 0xFB in value mode (would be END_OBJECT in key mode)");
                                                break;
                                        }
                                }
                            } else {
                                this._currToken = null;
                                return null;
                            }
                        }
                        this._tokenIncomplete = true;
                        JsonToken jsonToken12 = JsonToken.VALUE_EMBEDDED_OBJECT;
                        this._currToken = jsonToken12;
                        return jsonToken12;
                    } else {
                        this._tokenIncomplete = true;
                        JsonToken jsonToken13 = JsonToken.VALUE_EMBEDDED_OBJECT;
                        this._currToken = jsonToken13;
                        return jsonToken13;
                    }
            }
            _reportError("Invalid type marker byte 0x" + Integer.toHexString(b & 255) + " for expected value token");
            return null;
        } else {
            _handleEOF();
            close();
            this._currToken = null;
            return null;
        }
    }

    private final JsonToken _handleSharedString(int i) throws IOException, JsonParseException {
        if (i >= this._seenStringValueCount) {
            _reportInvalidSharedStringValue(i);
        }
        this._textBuffer.resetWithString(this._seenStringValues[i]);
        JsonToken jsonToken = JsonToken.VALUE_STRING;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final void _addSeenStringValue() throws IOException, JsonParseException {
        _finishToken();
        int i = this._seenStringValueCount;
        String[] strArr = this._seenStringValues;
        if (i < strArr.length) {
            this._seenStringValueCount = i + 1;
            strArr[i] = this._textBuffer.contentsAsString();
            return;
        }
        _expandSeenStringValues();
    }

    private final void _expandSeenStringValues() {
        String[] strArr = this._seenStringValues;
        int length = strArr.length;
        if (length == 0) {
            strArr = this._smileBufferRecycler.allocSeenStringValuesBuffer();
            if (strArr == null) {
                strArr = new String[64];
            }
        } else {
            int i = 1024;
            if (length == 1024) {
                this._seenStringValueCount = 0;
            } else {
                if (length == 64) {
                    i = 256;
                }
                String[] strArr2 = new String[i];
                System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                strArr = strArr2;
            }
        }
        this._seenStringValues = strArr;
        String[] strArr3 = this._seenStringValues;
        int i2 = this._seenStringValueCount;
        this._seenStringValueCount = i2 + 1;
        strArr3[i2] = this._textBuffer.contentsAsString();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        if (this._got32BitFloat) {
            return JsonParser.NumberType.FLOAT;
        }
        return super.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        if (this._parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
            byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
            int length = asQuotedUTF8.length;
            if (this._inputPtr + length + 1 < this._inputEnd) {
                int i = this._inputPtr;
                byte[] bArr = this._inputBuffer;
                int i2 = i + 1;
                byte b = bArr[i];
                this._typeByte = b;
                int i3 = (b >> 6) & 3;
                if (i3 != 0) {
                    if (i3 == 1) {
                        int i4 = b & 63;
                        if (i4 >= this._seenNameCount) {
                            _reportInvalidSharedName(i4);
                        }
                        this._parsingContext.setCurrentName(this._seenNames[i4]);
                        String str = this._seenNames[i4];
                        this._parsingContext.setCurrentName(str);
                        this._inputPtr = i2;
                        this._currToken = JsonToken.FIELD_NAME;
                        return str.equals(serializableString.getValue());
                    } else if (i3 == 2) {
                        int i5 = (b & 63) + 1;
                        if (i5 == length) {
                            for (int i6 = 0; i6 < i5; i6++) {
                                if (asQuotedUTF8[i6] == this._inputBuffer[i2 + i6]) {
                                }
                            }
                            this._inputPtr = i2 + i5;
                            String value = serializableString.getValue();
                            String[] strArr = this._seenNames;
                            if (strArr != null) {
                                if (this._seenNameCount >= strArr.length) {
                                    this._seenNames = _expandSeenNames(strArr);
                                }
                                String[] strArr2 = this._seenNames;
                                int i7 = this._seenNameCount;
                                this._seenNameCount = i7 + 1;
                                strArr2[i7] = value;
                            }
                            this._parsingContext.setCurrentName(value);
                            this._currToken = JsonToken.FIELD_NAME;
                            return true;
                        }
                    } else if (i3 == 3) {
                        int i8 = b & 63;
                        if (i8 <= 55) {
                            int i9 = i8 + 2;
                            if (i9 == length) {
                                for (int i10 = 0; i10 < i9; i10++) {
                                    if (asQuotedUTF8[i10] == this._inputBuffer[i2 + i10]) {
                                    }
                                }
                                this._inputPtr = i2 + i9;
                                String value2 = serializableString.getValue();
                                String[] strArr3 = this._seenNames;
                                if (strArr3 != null) {
                                    if (this._seenNameCount >= strArr3.length) {
                                        this._seenNames = _expandSeenNames(strArr3);
                                    }
                                    String[] strArr4 = this._seenNames;
                                    int i11 = this._seenNameCount;
                                    this._seenNameCount = i11 + 1;
                                    strArr4[i11] = value2;
                                }
                                this._parsingContext.setCurrentName(value2);
                                this._currToken = JsonToken.FIELD_NAME;
                                return true;
                            }
                        } else if (i8 == 59) {
                            this._currToken = JsonToken.END_OBJECT;
                            if (!this._parsingContext.inObject()) {
                                _reportMismatchedEndMarker(125, ']');
                            }
                            this._inputPtr = i2;
                            this._parsingContext = this._parsingContext.getParent();
                            return false;
                        }
                    }
                } else if (b != 32) {
                    switch (b) {
                        case 48:
                        case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID:
                        case 50:
                        case 51:
                            int i12 = i2 + 1;
                            int i13 = ((b & 3) << 8) + (bArr[i2] & 255);
                            if (i13 >= this._seenNameCount) {
                                _reportInvalidSharedName(i13);
                            }
                            String str2 = this._seenNames[i13];
                            this._parsingContext.setCurrentName(str2);
                            this._inputPtr = i12;
                            this._currToken = JsonToken.FIELD_NAME;
                            return str2.equals(serializableString.getValue());
                    }
                } else {
                    this._currToken = JsonToken.FIELD_NAME;
                    this._inputPtr = i2;
                    this._parsingContext.setCurrentName("");
                    if (length == 0) {
                        return true;
                    }
                    return false;
                }
            }
            JsonToken _handleFieldName = _handleFieldName();
            this._currToken = _handleFieldName;
            if (_handleFieldName != JsonToken.FIELD_NAME || !serializableString.getValue().equals(this._parsingContext.getCurrentName())) {
                return false;
            }
            return true;
        } else if (nextToken() != JsonToken.FIELD_NAME || !serializableString.getValue().equals(getCurrentName())) {
            return false;
        } else {
            return true;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() throws IOException, JsonParseException {
        if (!this._parsingContext.inObject() || this._currToken == JsonToken.FIELD_NAME) {
            if (this._tokenIncomplete) {
                _skipIncomplete();
            }
            int i = this._inputPtr;
            if (i >= this._inputEnd) {
                if (!loadMore()) {
                    _handleEOF();
                    close();
                    this._currToken = null;
                    return null;
                }
                i = this._inputPtr;
            }
            int i2 = i + 1;
            byte b = this._inputBuffer[i];
            this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
            this._binaryValue = null;
            this._typeByte = b;
            switch ((b >> 5) & 7) {
                case 0:
                    if (b == 0) {
                        _reportError("Invalid token byte 0x00");
                    }
                    int i3 = b - 1;
                    if (i3 >= this._seenStringValueCount) {
                        _reportInvalidSharedStringValue(i3);
                    }
                    this._inputPtr = i2;
                    String str = this._seenStringValues[i3];
                    this._textBuffer.resetWithString(str);
                    this._currToken = JsonToken.VALUE_STRING;
                    return str;
                case 1:
                    if ((b & Ascii.US) == 0) {
                        this._inputPtr = i2;
                        this._textBuffer.resetWithEmpty();
                        this._currToken = JsonToken.VALUE_STRING;
                        return "";
                    }
                    break;
                case 2:
                case 3:
                    this._currToken = JsonToken.VALUE_STRING;
                    this._inputPtr = i2;
                    _decodeShortAsciiValue((b & 63) + 1);
                    int i4 = this._seenStringValueCount;
                    if (i4 < 0) {
                        return this._textBuffer.contentsAsString();
                    }
                    if (i4 < this._seenStringValues.length) {
                        String contentsAsString = this._textBuffer.contentsAsString();
                        String[] strArr = this._seenStringValues;
                        int i5 = this._seenStringValueCount;
                        this._seenStringValueCount = i5 + 1;
                        strArr[i5] = contentsAsString;
                        return contentsAsString;
                    }
                    _expandSeenStringValues();
                    return this._textBuffer.contentsAsString();
                case 4:
                case 5:
                    this._currToken = JsonToken.VALUE_STRING;
                    this._inputPtr = i2;
                    _decodeShortUnicodeValue((b & 63) + 2);
                    int i6 = this._seenStringValueCount;
                    if (i6 < 0) {
                        return this._textBuffer.contentsAsString();
                    }
                    if (i6 < this._seenStringValues.length) {
                        String contentsAsString2 = this._textBuffer.contentsAsString();
                        String[] strArr2 = this._seenStringValues;
                        int i7 = this._seenStringValueCount;
                        this._seenStringValueCount = i7 + 1;
                        strArr2[i7] = contentsAsString2;
                        return contentsAsString2;
                    }
                    _expandSeenStringValues();
                    return this._textBuffer.contentsAsString();
            }
        }
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int nextIntValue(int i) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long nextLongValue(long j) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    /* renamed from: com.fasterxml.jackson.dataformat.smile.SmileParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.fasterxml.jackson.core.JsonToken[] r0 = com.fasterxml.jackson.core.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken = r0
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_TRUE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x001f }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_FALSE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x002a }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x004b }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.smile.SmileParser.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[nextToken().ordinal()];
        if (i == 1) {
            return Boolean.TRUE;
        }
        if (i != 2) {
            return null;
        }
        return Boolean.FALSE;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public String getText() throws IOException, JsonParseException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            int i = this._typeByte;
            int i2 = (i >> 5) & 7;
            if (i2 == 2 || i2 == 3) {
                _decodeShortAsciiValue((i & 63) + 1);
                return this._textBuffer.contentsAsString();
            } else if (i2 == 4 || i2 == 5) {
                _decodeShortUnicodeValue((i & 63) + 2);
                return this._textBuffer.contentsAsString();
            } else {
                _finishToken();
            }
        }
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return null;
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName();
        }
        if (jsonToken.isNumeric()) {
            return getNumberValue().toString();
        }
        return this._currToken.asString();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return null;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 3) {
            return this._textBuffer.getTextBuffer();
        }
        if (i == 4) {
            if (!this._nameCopied) {
                String currentName = this._parsingContext.getCurrentName();
                int length = currentName.length();
                if (this._nameCopyBuffer == null) {
                    this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                } else if (this._nameCopyBuffer.length < length) {
                    this._nameCopyBuffer = new char[length];
                }
                currentName.getChars(0, length, this._nameCopyBuffer, 0);
                this._nameCopied = true;
            }
            return this._nameCopyBuffer;
        } else if (i == 5 || i == 6) {
            return getNumberValue().toString().toCharArray();
        } else {
            return this._currToken.asCharArray();
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return 0;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 3) {
            return this._textBuffer.size();
        }
        if (i == 4) {
            return this._parsingContext.getCurrentName().length();
        }
        if (i == 5 || i == 6) {
            return getNumberValue().toString().length();
        }
        return this._currToken.asCharArray().length;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING || (this._currToken != null && this._currToken != JsonToken.VALUE_NULL && this._currToken.isScalarValue())) {
            return getText();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public String getValueAsString(String str) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING || (this._currToken != null && this._currToken != JsonToken.VALUE_NULL && this._currToken.isScalarValue())) {
            return getText();
        }
        return str;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            _reportError("Current token (" + this._currToken + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public Object getEmbeddedObject() throws IOException, JsonParseException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            _reportError("Current token (" + this._currToken + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            int i = this._typeByte;
            if (i == -3) {
                int _readUnsignedVInt = _readUnsignedVInt();
                int i2 = _readUnsignedVInt;
                while (i2 > 0) {
                    int i3 = this._inputEnd - this._inputPtr;
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                        i3 = this._inputEnd - this._inputPtr;
                    }
                    int min = Math.min(i3, i2);
                    outputStream.write(this._inputBuffer, this._inputPtr, min);
                    this._inputPtr += min;
                    i2 -= min;
                }
                this._tokenIncomplete = false;
                return _readUnsignedVInt;
            }
            if (i != -24) {
                _throwInternal();
            }
            int _readUnsignedVInt2 = _readUnsignedVInt();
            byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
            try {
                _readBinaryEncoded(outputStream, _readUnsignedVInt2, allocBase64Buffer);
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                this._tokenIncomplete = false;
                return _readUnsignedVInt2;
            } catch (Throwable th) {
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                throw th;
            }
        } else if (this._binaryValue == null) {
            return 0;
        } else {
            int length = this._binaryValue.length;
            outputStream.write(this._binaryValue, 0, length);
            return length;
        }
    }

    private void _readBinaryEncoded(OutputStream outputStream, int i, byte[] bArr) throws IOException, JsonParseException {
        int i2;
        int i3;
        int length = bArr.length - 7;
        loop0:
        while (true) {
            i2 = 0;
            while (i > 7) {
                if (this._inputEnd - this._inputPtr < 8) {
                    _loadToHaveAtLeast(8);
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                int i5 = bArr2[i4] << Ascii.EM;
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = i5 + (bArr3[i6] << Ascii.DC2);
                byte[] bArr4 = this._inputBuffer;
                int i8 = this._inputPtr;
                this._inputPtr = i8 + 1;
                int i9 = i7 + (bArr4[i8] << Ascii.VT);
                byte[] bArr5 = this._inputBuffer;
                int i10 = this._inputPtr;
                this._inputPtr = i10 + 1;
                int i11 = i9 + (bArr5[i10] << 4);
                byte[] bArr6 = this._inputBuffer;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                byte b = bArr6[i12];
                int i13 = i11 + (b >> 3);
                byte[] bArr7 = this._inputBuffer;
                int i14 = this._inputPtr;
                this._inputPtr = i14 + 1;
                int i15 = ((b & 7) << 21) + (bArr7[i14] << Ascii.SO);
                byte[] bArr8 = this._inputBuffer;
                int i16 = this._inputPtr;
                this._inputPtr = i16 + 1;
                int i17 = i15 + (bArr8[i16] << 7);
                byte[] bArr9 = this._inputBuffer;
                int i18 = this._inputPtr;
                this._inputPtr = i18 + 1;
                int i19 = i17 + bArr9[i18];
                int i20 = i2 + 1;
                bArr[i2] = (byte) (i13 >> 24);
                int i21 = i20 + 1;
                bArr[i20] = (byte) (i13 >> 16);
                int i22 = i21 + 1;
                bArr[i21] = (byte) (i13 >> 8);
                int i23 = i22 + 1;
                bArr[i22] = (byte) i13;
                int i24 = i23 + 1;
                bArr[i23] = (byte) (i19 >> 16);
                int i25 = i24 + 1;
                bArr[i24] = (byte) (i19 >> 8);
                int i26 = i25 + 1;
                bArr[i25] = (byte) i19;
                i -= 7;
                if (i26 > length) {
                    outputStream.write(bArr, 0, i26);
                } else {
                    i2 = i26;
                }
            }
            break loop0;
        }
        if (i > 0) {
            int i27 = i + 1;
            if (this._inputEnd - this._inputPtr < i27) {
                _loadToHaveAtLeast(i27);
            }
            byte[] bArr10 = this._inputBuffer;
            int i28 = this._inputPtr;
            this._inputPtr = i28 + 1;
            byte b2 = bArr10[i28];
            int i29 = 1;
            byte b3 = b2;
            while (i29 < i) {
                byte[] bArr11 = this._inputBuffer;
                int i30 = this._inputPtr;
                this._inputPtr = i30 + 1;
                int i31 = (b3 << 7) + bArr11[i30];
                bArr[i2] = (byte) (i31 >> (7 - i29));
                i29++;
                i2++;
                b3 = i31;
            }
            int i32 = (b3 == 1 ? 1 : 0) << i;
            i3 = i2 + 1;
            byte[] bArr12 = this._inputBuffer;
            int i33 = this._inputPtr;
            this._inputPtr = i33 + 1;
            bArr[i2] = (byte) (i32 + bArr12[i33]);
        } else {
            i3 = i2;
        }
        if (i3 > 0) {
            outputStream.write(bArr, 0, i3);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonToken _handleFieldName() throws IOException, JsonParseException {
        String str;
        String str2;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        this._typeByte = b;
        int i2 = (b >> 6) & 3;
        if (i2 != 0) {
            if (i2 == 1) {
                int i3 = b & 63;
                if (i3 >= this._seenNameCount) {
                    _reportInvalidSharedName(i3);
                }
                this._parsingContext.setCurrentName(this._seenNames[i3]);
                return JsonToken.FIELD_NAME;
            } else if (i2 == 2) {
                int i4 = (b & 63) + 1;
                Name _findDecodedFromSymbols = _findDecodedFromSymbols(i4);
                if (_findDecodedFromSymbols != null) {
                    str = _findDecodedFromSymbols.getName();
                    this._inputPtr += i4;
                } else {
                    str = _addDecodedToSymbols(i4, _decodeShortAsciiName(i4));
                }
                String[] strArr = this._seenNames;
                if (strArr != null) {
                    if (this._seenNameCount >= strArr.length) {
                        this._seenNames = _expandSeenNames(strArr);
                    }
                    String[] strArr2 = this._seenNames;
                    int i5 = this._seenNameCount;
                    this._seenNameCount = i5 + 1;
                    strArr2[i5] = str;
                }
                this._parsingContext.setCurrentName(str);
                return JsonToken.FIELD_NAME;
            } else if (i2 == 3) {
                int i6 = b & 63;
                if (i6 <= 55) {
                    int i7 = i6 + 2;
                    Name _findDecodedFromSymbols2 = _findDecodedFromSymbols(i7);
                    if (_findDecodedFromSymbols2 != null) {
                        str2 = _findDecodedFromSymbols2.getName();
                        this._inputPtr += i7;
                    } else {
                        str2 = _addDecodedToSymbols(i7, _decodeShortUnicodeName(i7));
                    }
                    String[] strArr3 = this._seenNames;
                    if (strArr3 != null) {
                        if (this._seenNameCount >= strArr3.length) {
                            this._seenNames = _expandSeenNames(strArr3);
                        }
                        String[] strArr4 = this._seenNames;
                        int i8 = this._seenNameCount;
                        this._seenNameCount = i8 + 1;
                        strArr4[i8] = str2;
                    }
                    this._parsingContext.setCurrentName(str2);
                    return JsonToken.FIELD_NAME;
                } else if (i6 == 59) {
                    if (!this._parsingContext.inObject()) {
                        _reportMismatchedEndMarker(125, ']');
                    }
                    this._parsingContext = this._parsingContext.getParent();
                    return JsonToken.END_OBJECT;
                }
            }
        } else if (b != 32) {
            switch (b) {
                case 48:
                case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID:
                case 50:
                case 51:
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i9 = this._inputPtr;
                    this._inputPtr = i9 + 1;
                    int i10 = ((b & 3) << 8) + (bArr2[i9] & 255);
                    if (i10 >= this._seenNameCount) {
                        _reportInvalidSharedName(i10);
                    }
                    this._parsingContext.setCurrentName(this._seenNames[i10]);
                    return JsonToken.FIELD_NAME;
                case 52:
                    _handleLongFieldName();
                    return JsonToken.FIELD_NAME;
            }
        } else {
            this._parsingContext.setCurrentName("");
            return JsonToken.FIELD_NAME;
        }
        _reportError("Invalid type marker byte 0x" + Integer.toHexString(this._typeByte) + " for expected field name (or END_OBJECT marker)");
        return null;
    }

    private final String[] _expandSeenNames(String[] strArr) {
        int length = strArr.length;
        if (length == 0) {
            String[] allocSeenNamesBuffer = this._smileBufferRecycler.allocSeenNamesBuffer();
            if (allocSeenNamesBuffer == null) {
                return new String[64];
            }
            return allocSeenNamesBuffer;
        }
        int i = 1024;
        if (length == 1024) {
            this._seenNameCount = 0;
            return strArr;
        }
        if (length == 64) {
            i = 256;
        }
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    private final String _addDecodedToSymbols(int i, String str) {
        if (i < 5) {
            return this._symbols.addName(str, this._quad1, 0).getName();
        }
        if (i < 9) {
            return this._symbols.addName(str, this._quad1, this._quad2).getName();
        }
        return this._symbols.addName(str, this._quadBuffer, (i + 3) >> 2).getName();
    }

    private final String _decodeShortAsciiName(int i) throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = (i2 + i) - 3;
        int i4 = 0;
        while (i2 < i3) {
            int i5 = i4 + 1;
            int i6 = i2 + 1;
            emptyAndGetCurrentSegment[i4] = (char) bArr[i2];
            int i7 = i5 + 1;
            int i8 = i6 + 1;
            emptyAndGetCurrentSegment[i5] = (char) bArr[i6];
            int i9 = i7 + 1;
            int i10 = i8 + 1;
            emptyAndGetCurrentSegment[i7] = (char) bArr[i8];
            i4 = i9 + 1;
            i2 = i10 + 1;
            emptyAndGetCurrentSegment[i9] = (char) bArr[i10];
        }
        int i11 = i & 3;
        if (i11 > 0) {
            int i12 = i4 + 1;
            int i13 = i2 + 1;
            emptyAndGetCurrentSegment[i4] = (char) bArr[i2];
            if (i11 > 1) {
                int i14 = i12 + 1;
                int i15 = i13 + 1;
                emptyAndGetCurrentSegment[i12] = (char) bArr[i13];
                if (i11 > 2) {
                    emptyAndGetCurrentSegment[i14] = (char) bArr[i15];
                    i2 = i15 + 1;
                } else {
                    i2 = i15;
                }
            } else {
                i2 = i13;
            }
        }
        this._inputPtr = i2;
        this._textBuffer.setCurrentLength(i);
        return this._textBuffer.contentsAsString();
    }

    private final String _decodeShortUnicodeName(int i) throws IOException, JsonParseException {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = this._inputPtr;
        this._inputPtr += i;
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i4 = i + i3;
        int i5 = 0;
        while (i3 < i4) {
            int i6 = i3 + 1;
            int i7 = bArr[i3] & 255;
            int i8 = iArr[i7];
            if (i8 != 0) {
                if (i8 == 1) {
                    i2 = i6 + 1;
                    i7 = ((i7 & 31) << 6) | (bArr[i6] & 63);
                } else if (i8 == 2) {
                    int i9 = i6 + 1;
                    int i10 = ((i7 & 15) << 12) | ((bArr[i6] & 63) << 6);
                    i6 = i9 + 1;
                    i7 = i10 | (bArr[i9] & 63);
                } else if (i8 != 3) {
                    _reportError("Invalid byte " + Integer.toHexString(i7) + " in short Unicode text block");
                } else {
                    int i11 = i6 + 1;
                    int i12 = ((i7 & 7) << 18) | ((bArr[i6] & 63) << 12);
                    int i13 = i11 + 1;
                    int i14 = i12 | ((bArr[i11] & 63) << 6);
                    i2 = i13 + 1;
                    int i15 = (i14 | (bArr[i13] & 63)) - 65536;
                    emptyAndGetCurrentSegment[i5] = (char) (55296 | (i15 >> 10));
                    i7 = (i15 & 1023) | 56320;
                    i5++;
                }
                i6 = i2;
            }
            emptyAndGetCurrentSegment[i5] = (char) i7;
            i3 = i6;
            i5++;
        }
        this._textBuffer.setCurrentLength(i5);
        return this._textBuffer.contentsAsString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.sym.Name _decodeLongUnicodeName(int[] r18, int r19, int r20) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        // Method dump skipped, instructions count: 265
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.smile.SmileParser._decodeLongUnicodeName(int[], int, int):com.fasterxml.jackson.core.sym.Name");
    }

    private final void _handleLongFieldName() throws IOException, JsonParseException {
        String str;
        byte[] bArr = this._inputBuffer;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b = bArr[i4];
            if (-4 == b) {
                break;
            }
            i3 = b & 255;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b2 = bArr[i5];
            if (-4 == b2) {
                i = 1;
                break;
            }
            i3 = (i3 << 8) | (b2 & 255);
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            byte b3 = bArr[i6];
            if (-4 == b3) {
                i = 2;
                break;
            }
            i3 = (i3 << 8) | (b3 & 255);
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            byte b4 = bArr[i7];
            if (-4 == b4) {
                i = 3;
                break;
            }
            i3 = (i3 << 8) | (b4 & 255);
            int[] iArr = this._quadBuffer;
            if (i2 >= iArr.length) {
                this._quadBuffer = _growArrayTo(iArr, iArr.length + 256);
            }
            this._quadBuffer[i2] = i3;
            i2++;
        }
        int i8 = i2 << 2;
        if (i > 0) {
            int[] iArr2 = this._quadBuffer;
            if (i2 >= iArr2.length) {
                this._quadBuffer = _growArrayTo(iArr2, iArr2.length + 256);
            }
            this._quadBuffer[i2] = i3;
            i8 += i;
            i2++;
        }
        Name findName = this._symbols.findName(this._quadBuffer, i2);
        if (findName != null) {
            str = findName.getName();
        } else {
            str = _decodeLongUnicodeName(this._quadBuffer, i8, i2).getName();
        }
        String[] strArr = this._seenNames;
        if (strArr != null) {
            if (this._seenNameCount >= strArr.length) {
                this._seenNames = _expandSeenNames(strArr);
            }
            String[] strArr2 = this._seenNames;
            int i9 = this._seenNameCount;
            this._seenNameCount = i9 + 1;
            strArr2[i9] = str;
        }
        this._parsingContext.setCurrentName(str);
    }

    private final Name _findDecodedFromSymbols(int i) throws IOException, JsonParseException {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        if (i < 5) {
            int i2 = this._inputPtr;
            byte[] bArr = this._inputBuffer;
            int i3 = bArr[i2] & 255;
            int i4 = i - 1;
            if (i4 > 0) {
                int i5 = i2 + 1;
                i3 = (i3 << 8) + (bArr[i5] & 255);
                int i6 = i4 - 1;
                if (i6 > 0) {
                    int i7 = i5 + 1;
                    i3 = (i3 << 8) + (bArr[i7] & 255);
                    if (i6 - 1 > 0) {
                        i3 = (i3 << 8) + (bArr[i7 + 1] & 255);
                    }
                }
            }
            this._quad1 = i3;
            return this._symbols.findName(i3);
        } else if (i >= 9) {
            return _findDecodedMedium(i);
        } else {
            int i8 = this._inputPtr;
            byte[] bArr2 = this._inputBuffer;
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            int i11 = i10 + 1;
            int i12 = ((((((bArr2[i8] & 255) << 8) + (bArr2[i9] & 255)) << 8) + (bArr2[i10] & 255)) << 8) + (bArr2[i11] & 255);
            int i13 = i11 + 1;
            int i14 = bArr2[i13] & 255;
            int i15 = i - 5;
            if (i15 > 0) {
                int i16 = i13 + 1;
                i14 = (i14 << 8) + (bArr2[i16] & 255);
                int i17 = i15 - 1;
                if (i17 > 0) {
                    int i18 = i16 + 1;
                    i14 = (i14 << 8) + (bArr2[i18] & 255);
                    if (i17 - 1 > 0) {
                        i14 = (i14 << 8) + (bArr2[i18 + 1] & 255);
                    }
                }
            }
            this._quad1 = i12;
            this._quad2 = i14;
            return this._symbols.findName(i12, i14);
        }
    }

    private final Name _findDecodedMedium(int i) throws IOException, JsonParseException {
        int i2;
        int i3;
        int i4;
        int i5 = (i + 3) >> 2;
        int[] iArr = this._quadBuffer;
        if (i5 > iArr.length) {
            this._quadBuffer = _growArrayTo(iArr, i5);
        }
        int i6 = 0;
        int i7 = this._inputPtr;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            i2 = i10 + 1;
            i3 = i6 + 1;
            this._quadBuffer[i6] = ((((((bArr[i7] & 255) << 8) | (bArr[i8] & 255)) << 8) | (bArr[i9] & 255)) << 8) | (bArr[i10] & 255);
            i -= 4;
            if (i <= 3) {
                break;
            }
            i7 = i2;
            i6 = i3;
        }
        if (i > 0) {
            int i11 = bArr[i2] & 255;
            int i12 = i - 1;
            if (i12 > 0) {
                int i13 = i2 + 1;
                i11 = (i11 << 8) + (bArr[i13] & 255);
                if (i12 - 1 > 0) {
                    i11 = (bArr[i13 + 1] & 255) + (i11 << 8);
                }
            }
            i4 = i3 + 1;
            this._quadBuffer[i3] = i11;
        } else {
            i4 = i3;
        }
        return this._symbols.findName(this._quadBuffer, i4);
    }

    private static int[] _growArrayTo(int[] iArr, int i) {
        int[] iArr2 = new int[(i + 4)];
        if (iArr != null) {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
        return iArr2;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _parseNumericValue(int i) throws IOException, JsonParseException {
        if (this._tokenIncomplete) {
            int i2 = this._typeByte;
            if (((i2 >> 5) & 7) != 1) {
                _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
            }
            this._tokenIncomplete = false;
            _finishNumberToken(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void _finishToken() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int i = this._typeByte;
        int i2 = (i >> 5) & 7;
        if (i2 == 1) {
            _finishNumberToken(i);
        } else if (i2 <= 3) {
            _decodeShortAsciiValue((i & 63) + 1);
        } else if (i2 <= 5) {
            _decodeShortUnicodeValue((i & 63) + 2);
        } else {
            if (i2 == 7) {
                int i3 = (i & 31) >> 2;
                if (i3 == 0) {
                    _decodeLongAscii();
                    return;
                } else if (i3 == 1) {
                    _decodeLongUnicode();
                    return;
                } else if (i3 == 2) {
                    this._binaryValue = _read7BitBinaryWithLength();
                    return;
                } else if (i3 == 7) {
                    _finishRawBinary();
                    return;
                }
            }
            _throwInternal();
        }
    }

    /* access modifiers changed from: protected */
    public final void _finishNumberToken(int i) throws IOException, JsonParseException {
        int i2 = i & 31;
        int i3 = i2 >> 2;
        if (i3 == 1) {
            int i4 = i2 & 3;
            if (i4 == 0) {
                _finishInt();
            } else if (i4 == 1) {
                _finishLong();
            } else if (i4 == 2) {
                _finishBigInteger();
            } else {
                _throwInternal();
            }
        } else {
            if (i3 == 2) {
                int i5 = i2 & 3;
                if (i5 == 0) {
                    _finishFloat();
                    return;
                } else if (i5 == 1) {
                    _finishDouble();
                    return;
                } else if (i5 == 2) {
                    _finishBigDecimal();
                    return;
                }
            }
            _throwInternal();
        }
    }

    private final void _finishInt() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if (b < 0) {
            i = b & 63;
        } else {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b2 = bArr2[i3];
            byte b3 = b;
            if (b2 >= 0) {
                int i4 = (b << 7) + b2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                b2 = bArr3[i5];
                b3 = i4;
                if (b2 >= 0) {
                    int i6 = (i4 << 7) + b2;
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr4 = this._inputBuffer;
                    int i7 = this._inputPtr;
                    this._inputPtr = i7 + 1;
                    b2 = bArr4[i7];
                    b3 = i6;
                    if (b2 >= 0) {
                        int i8 = (i6 << 7) + b2;
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr5 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        this._inputPtr = i9 + 1;
                        b2 = bArr5[i9];
                        b3 = i8;
                        if (b2 >= 0) {
                            _reportError("Corrupt input; 32-bit VInt extends beyond 5 data bytes");
                            b3 = i8;
                        }
                    }
                }
            }
            i = ((b3 == 1 ? 1 : 0) << 6) + (b2 & 63);
        }
        this._numberInt = SmileUtil.zigzagDecode(i);
        this._numTypesValid = 1;
    }

    private final void _finishLong() throws IOException, JsonParseException {
        long _fourBytesToInt = (long) _fourBytesToInt();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            if (b < 0) {
                this._numberLong = SmileUtil.zigzagDecode((_fourBytesToInt << 6) + ((long) (b & 63)));
                this._numTypesValid = 2;
                return;
            }
            _fourBytesToInt = (_fourBytesToInt << 7) + ((long) b);
        }
    }

    private final void _finishBigInteger() throws IOException, JsonParseException {
        this._numberBigInt = new BigInteger(_read7BitBinaryWithLength());
        this._numTypesValid = 4;
    }

    private final void _finishFloat() throws IOException, JsonParseException {
        int _fourBytesToInt = _fourBytesToInt();
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        this._numberDouble = (double) Float.intBitsToFloat((_fourBytesToInt << 7) + bArr[i]);
        this._numTypesValid = 8;
    }

    private final void _finishDouble() throws IOException, JsonParseException {
        long _fourBytesToInt = (((long) _fourBytesToInt()) << 28) + ((long) _fourBytesToInt());
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        long j = (_fourBytesToInt << 7) + ((long) bArr[i]);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        this._numberDouble = Double.longBitsToDouble((j << 7) + ((long) bArr2[i2]));
        this._numTypesValid = 8;
    }

    private final int _fourBytesToInt() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = (b << 7) + bArr2[i2];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = (i3 << 7) + bArr3[i4];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr4 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        return (i5 << 7) + bArr4[i6];
    }

    private final void _finishBigDecimal() throws IOException, JsonParseException {
        this._numberBigDecimal = new BigDecimal(new BigInteger(_read7BitBinaryWithLength()), SmileUtil.zigzagDecode(_readUnsignedVInt()));
        this._numTypesValid = 16;
    }

    private final int _readUnsignedVInt() throws IOException, JsonParseException {
        int i = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b = bArr[i2];
            if (b < 0) {
                return (i << 6) + (b & 63);
            }
            i = (i << 7) + b;
        }
    }

    private final byte[] _read7BitBinaryWithLength() throws IOException, JsonParseException {
        int _readUnsignedVInt = _readUnsignedVInt();
        byte[] bArr = new byte[_readUnsignedVInt];
        int i = _readUnsignedVInt - 7;
        int i2 = 0;
        while (i2 <= i) {
            if (this._inputEnd - this._inputPtr < 8) {
                _loadToHaveAtLeast(8);
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = bArr2[i3] << Ascii.EM;
            byte[] bArr3 = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            int i6 = i4 + (bArr3[i5] << Ascii.DC2);
            byte[] bArr4 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            int i8 = i6 + (bArr4[i7] << Ascii.VT);
            byte[] bArr5 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            int i10 = i8 + (bArr5[i9] << 4);
            byte[] bArr6 = this._inputBuffer;
            int i11 = this._inputPtr;
            this._inputPtr = i11 + 1;
            byte b = bArr6[i11];
            int i12 = i10 + (b >> 3);
            byte[] bArr7 = this._inputBuffer;
            int i13 = this._inputPtr;
            this._inputPtr = i13 + 1;
            int i14 = ((b & 7) << 21) + (bArr7[i13] << Ascii.SO);
            byte[] bArr8 = this._inputBuffer;
            int i15 = this._inputPtr;
            this._inputPtr = i15 + 1;
            int i16 = i14 + (bArr8[i15] << 7);
            byte[] bArr9 = this._inputBuffer;
            int i17 = this._inputPtr;
            this._inputPtr = i17 + 1;
            int i18 = i16 + bArr9[i17];
            int i19 = i2 + 1;
            bArr[i2] = (byte) (i12 >> 24);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i12 >> 16);
            int i21 = i20 + 1;
            bArr[i20] = (byte) (i12 >> 8);
            int i22 = i21 + 1;
            bArr[i21] = (byte) i12;
            int i23 = i22 + 1;
            bArr[i22] = (byte) (i18 >> 16);
            int i24 = i23 + 1;
            bArr[i23] = (byte) (i18 >> 8);
            bArr[i24] = (byte) i18;
            i2 = i24 + 1;
        }
        int length = bArr.length - i2;
        if (length > 0) {
            int i25 = length + 1;
            if (this._inputEnd - this._inputPtr < i25) {
                _loadToHaveAtLeast(i25);
            }
            byte[] bArr10 = this._inputBuffer;
            int i26 = this._inputPtr;
            this._inputPtr = i26 + 1;
            byte b2 = bArr10[i26];
            int i27 = 1;
            byte b3 = b2;
            while (i27 < length) {
                byte[] bArr11 = this._inputBuffer;
                int i28 = this._inputPtr;
                this._inputPtr = i28 + 1;
                int i29 = (b3 << 7) + bArr11[i28];
                bArr[i2] = (byte) (i29 >> (7 - i27));
                i27++;
                i2++;
                b3 = i29;
            }
            int i30 = (b3 == 1 ? 1 : 0) << length;
            byte[] bArr12 = this._inputBuffer;
            int i31 = this._inputPtr;
            this._inputPtr = i31 + 1;
            bArr[i2] = (byte) (i30 + bArr12[i31]);
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final void _decodeShortAsciiValue(int i) throws IOException, JsonParseException {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + i;
        while (i3 < i4) {
            emptyAndGetCurrentSegment[i2] = (char) bArr[i3];
            i3++;
            i2++;
        }
        this._inputPtr = i3;
        this._textBuffer.setCurrentLength(i);
    }

    /* access modifiers changed from: protected */
    public final void _decodeShortUnicodeValue(int i) throws IOException, JsonParseException {
        int i2;
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        int i3 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i4 = this._inputPtr;
        this._inputPtr += i;
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i5 = i + i4;
        while (i4 < i5) {
            int i6 = i4 + 1;
            int i7 = bArr[i4] & 255;
            int i8 = iArr[i7];
            if (i8 != 0) {
                if (i8 == 1) {
                    i2 = i6 + 1;
                    i7 = ((i7 & 31) << 6) | (bArr[i6] & 63);
                } else if (i8 == 2) {
                    int i9 = i6 + 1;
                    int i10 = ((i7 & 15) << 12) | ((bArr[i6] & 63) << 6);
                    i6 = i9 + 1;
                    i7 = i10 | (bArr[i9] & 63);
                } else if (i8 != 3) {
                    _reportError("Invalid byte " + Integer.toHexString(i7) + " in short Unicode text block");
                } else {
                    int i11 = i6 + 1;
                    int i12 = ((i7 & 7) << 18) | ((bArr[i6] & 63) << 12);
                    int i13 = i11 + 1;
                    int i14 = i12 | ((bArr[i11] & 63) << 6);
                    i2 = i13 + 1;
                    int i15 = (i14 | (bArr[i13] & 63)) - 65536;
                    emptyAndGetCurrentSegment[i3] = (char) (55296 | (i15 >> 10));
                    i7 = (i15 & 1023) | 56320;
                    i3++;
                }
                i6 = i2;
            }
            emptyAndGetCurrentSegment[i3] = (char) i7;
            i4 = i6;
            i3++;
        }
        this._textBuffer.setCurrentLength(i3);
    }

    private final void _decodeLongAscii() throws IOException, JsonParseException {
        int i;
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i4 = this._inputPtr;
            int i5 = this._inputEnd - i4;
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int min = Math.min(i5, emptyAndGetCurrentSegment.length - i3);
            while (true) {
                i = i4 + 1;
                byte b = this._inputBuffer[i4];
                if (b == -4) {
                    this._inputPtr = i;
                    this._textBuffer.setCurrentLength(i3);
                    return;
                }
                i2 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) b;
                min--;
                if (min <= 0) {
                    break;
                }
                i3 = i2;
                i4 = i;
            }
            this._inputPtr = i;
            i3 = i2;
        }
    }

    private final void _decodeLongUnicode() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i = 0;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i3 = this._inputEnd;
            int length = (emptyAndGetCurrentSegment.length - i) + i2;
            if (length < i3) {
                i3 = length;
            }
            while (true) {
                if (i2 >= i3) {
                    this._inputPtr = i2;
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (iArr[i5] != 0) {
                    this._inputPtr = i4;
                    if (i5 == 252) {
                        this._textBuffer.setCurrentLength(i);
                        return;
                    }
                    int i6 = iArr[i5];
                    if (i6 == 1) {
                        i5 = _decodeUtf8_2(i5);
                    } else if (i6 != 2) {
                        if (i6 != 3) {
                            _reportInvalidChar(i5);
                        } else {
                            int _decodeUtf8_4 = _decodeUtf8_4(i5);
                            int i7 = i + 1;
                            emptyAndGetCurrentSegment[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i7 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                i7 = 0;
                            }
                            i5 = (_decodeUtf8_4 & 1023) | 56320;
                            i = i7;
                        }
                    } else if (this._inputEnd - this._inputPtr >= 2) {
                        i5 = _decodeUtf8_3fast(i5);
                    } else {
                        i5 = _decodeUtf8_3(i5);
                    }
                    if (i >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i = 0;
                    }
                    emptyAndGetCurrentSegment[i] = (char) i5;
                    i++;
                } else {
                    emptyAndGetCurrentSegment[i] = (char) i5;
                    i2 = i4;
                    i++;
                }
            }
        }
    }

    private final void _finishRawBinary() throws IOException, JsonParseException {
        int _readUnsignedVInt = _readUnsignedVInt();
        this._binaryValue = new byte[_readUnsignedVInt];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i = 0;
        while (true) {
            int min = Math.min(_readUnsignedVInt, this._inputEnd - this._inputPtr);
            System.arraycopy(this._inputBuffer, this._inputPtr, this._binaryValue, i, min);
            this._inputPtr += min;
            i += min;
            _readUnsignedVInt -= min;
            if (_readUnsignedVInt > 0) {
                loadMoreGuaranteed();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _skipIncomplete() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int i = this._typeByte;
        int i2 = (i >> 5) & 7;
        if (i2 == 1) {
            int i3 = i & 31;
            int i4 = i3 >> 2;
            if (i4 == 1) {
                int i5 = i3 & 3;
                if (i5 != 0) {
                    if (i5 == 1) {
                        _skipBytes(4);
                    } else if (i5 == 2) {
                        _skip7BitBinary();
                        return;
                    }
                }
                while (true) {
                    int i6 = this._inputEnd;
                    byte[] bArr = this._inputBuffer;
                    while (this._inputPtr < i6) {
                        int i7 = this._inputPtr;
                        this._inputPtr = i7 + 1;
                        if (bArr[i7] < 0) {
                            return;
                        }
                    }
                    loadMoreGuaranteed();
                }
            } else if (i4 == 2) {
                int i8 = i3 & 3;
                if (i8 == 0) {
                    _skipBytes(5);
                    return;
                } else if (i8 == 1) {
                    _skipBytes(10);
                    return;
                } else if (i8 == 2) {
                    _readUnsignedVInt();
                    _skip7BitBinary();
                    return;
                }
            }
        } else if (i2 == 2 || i2 == 3) {
            _skipBytes((i & 63) + 1);
            return;
        } else if (i2 == 4 || i2 == 5) {
            _skipBytes((i & 63) + 2);
            return;
        } else if (i2 == 7) {
            int i9 = (i & 31) >> 2;
            if (i9 == 0 || i9 == 1) {
                while (true) {
                    int i10 = this._inputEnd;
                    byte[] bArr2 = this._inputBuffer;
                    while (this._inputPtr < i10) {
                        int i11 = this._inputPtr;
                        this._inputPtr = i11 + 1;
                        if (bArr2[i11] == -4) {
                            return;
                        }
                    }
                    loadMoreGuaranteed();
                }
            } else if (i9 == 2) {
                _skip7BitBinary();
                return;
            } else if (i9 == 7) {
                _skipBytes(_readUnsignedVInt());
                return;
            }
        }
        _throwInternal();
    }

    /* access modifiers changed from: protected */
    public void _skipBytes(int i) throws IOException, JsonParseException {
        while (true) {
            int min = Math.min(i, this._inputEnd - this._inputPtr);
            this._inputPtr += min;
            i -= min;
            if (i > 0) {
                loadMoreGuaranteed();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _skip7BitBinary() throws IOException, JsonParseException {
        int _readUnsignedVInt = _readUnsignedVInt();
        int i = _readUnsignedVInt / 7;
        int i2 = i * 8;
        int i3 = _readUnsignedVInt - (i * 7);
        if (i3 > 0) {
            i2 += i3 + 1;
        }
        _skipBytes(i2);
    }

    private final int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    private final int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i3 = ((i & 7) << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & 63)) - 65536;
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidSharedName(int i) throws IOException {
        if (this._seenNames == null) {
            _reportError("Encountered shared name reference, even though document header explicitly declared no shared name references are included");
        }
        _reportError("Invalid shared name reference " + i + "; only got " + this._seenNameCount + " names in buffer (invalid content)");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidSharedStringValue(int i) throws IOException {
        if (this._seenStringValues == null) {
            _reportError("Encountered shared text value reference, even though document header did not declared shared text value references may be included");
        }
        _reportError("Invalid shared text value reference " + i + "; only got " + this._seenStringValueCount + " names in buffer (invalid content)");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }
}
