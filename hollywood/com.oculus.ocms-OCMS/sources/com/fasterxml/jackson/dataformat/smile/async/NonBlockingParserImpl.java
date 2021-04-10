package com.fasterxml.jackson.dataformat.smile.async;

import com.facebook.ultralight.UL;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.dataformat.smile.PackageVersion;
import com.fasterxml.jackson.dataformat.smile.SmileBufferRecycler;
import com.fasterxml.jackson.dataformat.smile.SmileConstants;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import com.fasterxml.jackson.dataformat.smile.SmileUtil;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;

public class NonBlockingParserImpl extends ParserBase implements NonBlockingParser, NonBlockingInputFeeder {
    private static final byte[] NO_BYTES = new byte[0];
    private static final int[] NO_INTS = new int[0];
    private static final String[] NO_STRINGS = new String[0];
    protected static final int STATE_HEADER = 1;
    protected static final int STATE_INITIAL = 0;
    protected static final int STATE_LONG_ASCII = 22;
    protected static final int STATE_LONG_SHARED = 24;
    protected static final int STATE_LONG_UNICODE = 23;
    protected static final int STATE_NUMBER_BIGDEC = 15;
    protected static final int STATE_NUMBER_BIGINT = 12;
    protected static final int STATE_NUMBER_DOUBLE = 14;
    protected static final int STATE_NUMBER_FLOAT = 13;
    protected static final int STATE_NUMBER_INT = 10;
    protected static final int STATE_NUMBER_LONG = 11;
    protected static final int STATE_QUOTED_BINARY = 26;
    protected static final int STATE_RAW_BINARY = 25;
    protected static final int STATE_SHORT_ASCII = 20;
    protected static final int STATE_SHORT_UNICODE = 21;
    protected static final int STATE_TOKEN_COMPLETE = 2;
    protected static final ThreadLocal<SoftReference<SmileBufferRecycler<String>>> _smileRecyclerRef = new ThreadLocal<>();
    protected final boolean _cfgRequireHeader;
    protected int _currQuad;
    protected int _currQuadBytes;
    protected boolean _endOfInput;
    protected boolean _got32BitFloat;
    protected byte[] _inputBuffer = NO_BYTES;
    protected boolean _mayContainRawBinary;
    protected ObjectCodec _objectCodec;
    protected int _origBufferLen;
    protected int _pendingInt;
    protected long _pendingLong;
    private int _quad1;
    private int _quad2;
    protected int[] _quadBuffer;
    protected int _quadCount;
    protected int _seenNameCount;
    protected String[] _seenNames;
    protected int _seenStringValueCount;
    protected String[] _seenStringValues;
    protected final SmileBufferRecycler<String> _smileBufferRecycler;
    protected int _state;
    protected int _substate;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _closeInput() throws IOException {
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public NonBlockingParserImpl(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer) {
        super(iOContext, i);
        boolean z = false;
        this._endOfInput = false;
        this._quadBuffer = NO_INTS;
        this._currQuadBytes = 0;
        this._seenNames = NO_STRINGS;
        this._seenNameCount = 0;
        this._seenStringValues = null;
        this._seenStringValueCount = -1;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._tokenInputRow = -1;
        this._tokenInputCol = -1;
        this._smileBufferRecycler = _smileBufferRecycler();
        this._currToken = JsonToken.NOT_AVAILABLE;
        this._state = 0;
        this._tokenIncomplete = true;
        this._cfgRequireHeader = (i2 & SmileParser.Feature.REQUIRE_HEADER.getMask()) != 0 ? true : z;
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
        _throwInternal();
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean _loadToHaveAtLeast(int i) throws IOException {
        _throwInternal();
        return false;
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
        super._releaseBuffers();
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
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            return _finishToken();
        }
        this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
        this._binaryValue = null;
        if (this._parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
            JsonToken _handleFieldName = _handleFieldName();
            this._currToken = _handleFieldName;
            return _handleFieldName;
        } else if (this._inputPtr >= this._inputEnd) {
            return JsonToken.NOT_AVAILABLE;
        } else {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            switch ((b >> 5) & 7) {
                case 0:
                    if (b == 0) {
                        _reportError("Invalid token byte 0x00");
                    }
                    return _handleSharedString(b - 1);
                case 1:
                    this._numTypesValid = 0;
                    int i2 = b & Ascii.US;
                    if (i2 != 26) {
                        switch (i2) {
                            case 0:
                                this._textBuffer.resetWithEmpty();
                                JsonToken jsonToken = JsonToken.VALUE_STRING;
                                this._currToken = jsonToken;
                                return jsonToken;
                            case 1:
                                JsonToken jsonToken2 = JsonToken.VALUE_NULL;
                                this._currToken = jsonToken2;
                                return jsonToken2;
                            case 2:
                                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                                this._currToken = jsonToken3;
                                return jsonToken3;
                            case 3:
                                JsonToken jsonToken4 = JsonToken.VALUE_TRUE;
                                this._currToken = jsonToken4;
                                return jsonToken4;
                            case 4:
                                this._state = 10;
                                return _nextInt(0, 0);
                            case 5:
                                this._numberLong = 0;
                                this._state = 11;
                                return _nextLong(0, 0);
                            case 6:
                                this._state = 12;
                                return _nextBigInt(0);
                            case 8:
                                this._pendingInt = 0;
                                this._state = 13;
                                this._got32BitFloat = true;
                                return _nextFloat(0, 0);
                            case 9:
                                this._pendingLong = 0;
                                this._state = 14;
                                this._got32BitFloat = false;
                                return _nextDouble(0, 0);
                            case 10:
                                this._state = 15;
                                return _nextBigDecimal(0);
                        }
                    } else if (!_handleHeader(0)) {
                        return JsonToken.NOT_AVAILABLE;
                    } else {
                        if (this._currToken == null) {
                            return nextToken();
                        }
                        this._currToken = null;
                        return null;
                    }
                case 2:
                case 3:
                    return _nextShortAscii(0);
                case 4:
                case 5:
                    this._currToken = JsonToken.VALUE_STRING;
                    if (this._seenStringValueCount >= 0) {
                        _addSeenStringValue();
                    } else {
                        this._tokenIncomplete = true;
                    }
                    return _nextShortUnicode(0);
                case 6:
                    this._numberInt = SmileUtil.zigzagDecode(b & Ascii.US);
                    this._numTypesValid = 1;
                    JsonToken jsonToken5 = JsonToken.VALUE_NUMBER_INT;
                    this._currToken = jsonToken5;
                    return jsonToken5;
                case 7:
                    int i3 = b & Ascii.US;
                    if (i3 == 0) {
                        return _nextLongAscii(0);
                    }
                    if (i3 == 4) {
                        return _nextLongUnicode(0);
                    }
                    if (i3 == 8) {
                        return _nextQuotedBinary(0);
                    }
                    if (i3 != 29) {
                        if (i3 != 31) {
                            switch (i3) {
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                    return _nextLongSharedString(0);
                                default:
                                    switch (i3) {
                                        case 24:
                                            this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
                                            JsonToken jsonToken6 = JsonToken.START_ARRAY;
                                            this._currToken = jsonToken6;
                                            return jsonToken6;
                                        case 25:
                                            if (!this._parsingContext.inArray()) {
                                                _reportMismatchedEndMarker(93, '}');
                                            }
                                            this._parsingContext = this._parsingContext.getParent();
                                            JsonToken jsonToken7 = JsonToken.END_ARRAY;
                                            this._currToken = jsonToken7;
                                            return jsonToken7;
                                        case 26:
                                            this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
                                            JsonToken jsonToken8 = JsonToken.START_OBJECT;
                                            this._currToken = jsonToken8;
                                            return jsonToken8;
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
                    return _nextRawBinary(0);
            }
            _reportError("Invalid type marker byte 0x" + Integer.toHexString(b & 255) + " for expected value token");
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

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.base.ParserBase
    public String getCurrentName() throws IOException, JsonParseException {
        return this._parsingContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        if (this._got32BitFloat) {
            return JsonParser.NumberType.FLOAT;
        }
        return super.getNumberType();
    }

    @Override // com.fasterxml.jackson.dataformat.smile.async.NonBlockingInputFeeder
    public final boolean needMoreInput() {
        return this._inputPtr >= this._inputEnd && !this._endOfInput;
    }

    @Override // com.fasterxml.jackson.dataformat.smile.async.NonBlockingInputFeeder
    public void feedInput(byte[] bArr, int i, int i2) throws IOException {
        if (this._inputPtr < this._inputEnd) {
            throw new IOException("Still have " + (this._inputEnd - this._inputPtr) + " undecoded bytes, should not call 'feedInput'");
        } else if (!this._endOfInput) {
            this._currInputProcessed += (long) this._origBufferLen;
            this._currInputRowStart -= this._origBufferLen;
            this._inputBuffer = bArr;
            this._inputPtr = i;
            this._inputEnd = i + i2;
            this._origBufferLen = i2;
        } else {
            throw new IOException("Already closed, can not feed more input");
        }
    }

    @Override // com.fasterxml.jackson.dataformat.smile.async.NonBlockingInputFeeder
    public void endOfInput() {
        this._endOfInput = true;
    }

    @Override // com.fasterxml.jackson.dataformat.smile.async.NonBlockingParser
    public JsonToken peekNextToken() throws IOException, JsonParseException {
        if (!this._tokenIncomplete) {
            return JsonToken.NOT_AVAILABLE;
        }
        int i = this._state;
        if (i == 0) {
            return JsonToken.NOT_AVAILABLE;
        }
        if (i == 1) {
            return JsonToken.NOT_AVAILABLE;
        }
        if (i == 10 || i == 11) {
            return JsonToken.VALUE_NUMBER_INT;
        }
        switch (i) {
            case 13:
            case 14:
            case 15:
                return JsonToken.VALUE_NUMBER_FLOAT;
            default:
                throw new IllegalStateException("Internal error: unknown 'state', " + this._state);
        }
    }

    private final JsonToken _nextInt(int i, int i2) throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b = bArr[i3];
            if (b < 0) {
                this._numberInt = SmileUtil.zigzagDecode((i2 << 6) | (b & 63));
                this._numTypesValid = 1;
                this._tokenIncomplete = false;
                JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken;
                return jsonToken;
            }
            i++;
            if (i >= 5) {
                _reportError("Corrupt input; 32-bit VInt extends beyond 5 data bytes");
            }
            i2 = (i2 << 7) | b;
        }
        this._tokenIncomplete = true;
        this._substate = i;
        this._pendingInt = i2;
        this._state = 10;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final JsonToken _nextLong(int i, long j) throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b = bArr[i2];
            if (b < 0) {
                this._numberLong = SmileUtil.zigzagDecode((j << 6) | ((long) (b & 63)));
                this._numTypesValid = 2;
                this._tokenIncomplete = false;
                JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken;
                return jsonToken;
            }
            i++;
            if (i >= 10) {
                _reportError("Corrupt input; 64-bit VInt extends beyond 10 data bytes");
            }
            j = (j << 7) | ((long) b);
        }
        this._tokenIncomplete = true;
        this._substate = i;
        this._pendingLong = j;
        this._state = 11;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final JsonToken _nextBigInt(int i) throws IOException, JsonParseException {
        this._tokenIncomplete = true;
        this._substate = i;
        this._state = 15;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextFloat(int i, int i2) throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            i2 = (i2 << 7) + bArr[i3];
            i++;
            if (i == 5) {
                this._numberDouble = (double) Float.intBitsToFloat(i2);
                this._numTypesValid = 8;
                this._tokenIncomplete = false;
                JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
                this._currToken = jsonToken;
                return jsonToken;
            }
        }
        this._tokenIncomplete = true;
        this._substate = i;
        this._pendingInt = i2;
        this._state = 13;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final JsonToken _nextDouble(int i, long j) throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            j = (j << 7) + ((long) bArr[i2]);
            i++;
            if (i == 10) {
                this._numberDouble = Double.longBitsToDouble(j);
                this._numTypesValid = 8;
                this._tokenIncomplete = false;
                JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
                this._currToken = jsonToken;
                return jsonToken;
            }
        }
        this._tokenIncomplete = true;
        this._substate = i;
        this._pendingLong = j;
        this._state = 14;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final JsonToken _nextBigDecimal(int i) throws IOException, JsonParseException {
        this._tokenIncomplete = true;
        this._substate = i;
        this._state = 15;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final boolean _handleHeader(int i) throws IOException, JsonParseException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b = bArr[i2];
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int i3 = (b >> 4) & 15;
                        if (i3 != 0) {
                            _reportError("Header version number bits (0x" + Integer.toHexString(i3) + ") indicate unrecognized version; only 0x0 handled by parser");
                        }
                        if ((b & 1) == 0) {
                            this._seenNames = null;
                            this._seenNameCount = -1;
                        }
                        if ((b & 2) != 0) {
                            this._seenStringValues = NO_STRINGS;
                            this._seenStringValueCount = 0;
                        }
                        this._mayContainRawBinary = (b & 4) != 0;
                        this._tokenIncomplete = false;
                        return true;
                    }
                } else if (b != 10) {
                    _reportError("Malformed content: signature not valid, starts with 0x3a, 0x29, but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr & 255]) + ", not 0x0A");
                }
            } else if (b != 41) {
                _reportError("Malformed content: header signature not valid, starts with 0x3a but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr] & 255) + ", not 0x29");
            }
        }
        this._tokenIncomplete = true;
        this._state = 1;
        this._substate = i;
        return false;
    }

    private final JsonToken _nextShortAscii(int i) throws IOException, JsonParseException {
        this._state = 20;
        this._tokenIncomplete = true;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextShortUnicode(int i) throws IOException, JsonParseException {
        this._state = 21;
        this._tokenIncomplete = true;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextLongAscii(int i) throws IOException, JsonParseException {
        this._state = 22;
        this._tokenIncomplete = true;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextLongUnicode(int i) throws IOException, JsonParseException {
        this._state = 23;
        this._tokenIncomplete = true;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextLongSharedString(int i) throws IOException, JsonParseException {
        this._tokenIncomplete = true;
        this._state = 24;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextRawBinary(int i) throws IOException, JsonParseException {
        this._tokenIncomplete = true;
        this._state = 25;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextQuotedBinary(int i) throws IOException, JsonParseException {
        this._tokenIncomplete = true;
        this._state = 26;
        this._substate = i;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public String getText() throws IOException, JsonParseException {
        JsonToken jsonToken;
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        if (this._tokenIncomplete || (jsonToken = this._currToken) == null) {
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

    /* renamed from: com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.fasterxml.jackson.core.JsonToken[] r0 = com.fasterxml.jackson.core.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken = r0
                int[] r0 = com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x001f }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x002a }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            return this._textBuffer.getTextBuffer();
        }
        if (i == 2) {
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
        } else if (i == 3 || i == 4) {
            return getNumberValue().toString().toCharArray();
        } else {
            if (this._tokenIncomplete) {
                return null;
            }
            return this._currToken.asCharArray();
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return 0;
        }
        if (this._tokenIncomplete) {
            return -1;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            return this._textBuffer.size();
        }
        if (i == 2) {
            return this._parsingContext.getCurrentName().length();
        }
        if (i == 3 || i == 4) {
            return getNumberValue().toString().length();
        }
        return this._currToken.asCharArray().length;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserMinimalBase
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            _reportError("Current token (" + this._currToken + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.base.ParserBase
    public Object getEmbeddedObject() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        throw new UnsupportedOperationException();
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
        _reportError("Invalid type marker byte 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr - 1]) + " for expected field name (or END_OBJECT marker)");
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.smile.async.NonBlockingParserImpl._decodeLongUnicodeName(int[], int, int):com.fasterxml.jackson.core.sym.Name");
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
            _reportError("No current token available, can not call accessors");
        }
    }

    /* access modifiers changed from: protected */
    public final JsonToken _finishToken() throws IOException, JsonParseException {
        String str;
        if (this._inputPtr >= this._inputEnd) {
            return JsonToken.NOT_AVAILABLE;
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        int i2 = this._state;
        if (i2 == 0) {
            if (b == 58) {
                if (!_handleHeader(0)) {
                    return JsonToken.NOT_AVAILABLE;
                }
                if (this._inputPtr >= this._inputEnd) {
                    return JsonToken.NOT_AVAILABLE;
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                byte b2 = bArr2[i3];
            } else if (this._cfgRequireHeader) {
                if (b == 123 || b == 91) {
                    str = "Input does not start with Smile format header (first byte = 0x" + Integer.toHexString(b & 255) + ") -- rather, it starts with '" + ((char) b) + "' (plain JSON input?) -- can not parse";
                } else {
                    str = "Input does not start with Smile format header (first byte = 0x" + Integer.toHexString(b & 255) + ") and parser has REQUIRE_HEADER enabled: can not parse";
                }
                throw new JsonParseException(str, JsonLocation.NA);
            }
        } else if (i2 == 1) {
            if (!_handleHeader(this._substate)) {
                return JsonToken.NOT_AVAILABLE;
            }
            if (this._inputPtr >= this._inputEnd) {
                return JsonToken.NOT_AVAILABLE;
            }
            byte[] bArr3 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b3 = bArr3[i4];
        }
        switch (this._state) {
            case 10:
                return _nextInt(this._substate, this._pendingInt);
            case 11:
                return _nextLong(this._substate, this._pendingLong);
            case 12:
                return _nextBigInt(this._substate);
            case 13:
                return _nextFloat(this._substate, this._pendingInt);
            case 14:
                return _nextDouble(this._substate, this._pendingLong);
            case 15:
                return _nextBigDecimal(this._substate);
            default:
                _throwInvalidState("Illegal state when trying to complete token: ");
                return null;
        }
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

    /* access modifiers changed from: protected */
    public void _throwInvalidState(String str) {
        throw new IllegalStateException(str + ": state=" + this._state);
    }
}
