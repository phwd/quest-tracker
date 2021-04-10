package com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import java.io.IOException;
import java.io.InputStream;

public class SmileParserBootstrapper {
    protected final boolean _bufferRecyclable;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    protected int _inputEnd;
    protected int _inputProcessed;
    protected int _inputPtr;

    private static boolean likelySmileValue(byte b) {
        if (b == -32 || b == -28 || b == -24 || b == -8 || b == -6) {
            return true;
        }
        int i = b & 255;
        return i >= 128 && i <= 159;
    }

    private static boolean possibleSmileValue(byte b, boolean z) {
        int i = b & 255;
        if (i >= 128) {
            return i <= 224;
        }
        if (z) {
            if (i >= 64) {
                return true;
            }
            return i > -32 && i < 44;
        }
        return false;
    }

    public SmileParserBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public SmileParserBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i2 + i;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    public SmileParser constructParser(int i, int i2, boolean z, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer) throws IOException, JsonParseException {
        String str;
        BytesToNameCanonicalizer makeChild = bytesToNameCanonicalizer.makeChild(true, z);
        ensureLoaded(1);
        SmileParser smileParser = new SmileParser(this._context, i, i2, objectCodec, makeChild, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        int i3 = this._inputPtr;
        if (i3 < this._inputEnd) {
            byte b = 0;
            if (!(this._inputBuffer[i3] == 58 ? smileParser.handleSignature(true, true) : false) && (i2 & SmileParser.Feature.REQUIRE_HEADER.getMask()) != 0) {
                int i4 = this._inputPtr;
                if (i4 < this._inputEnd) {
                    b = this._inputBuffer[i4];
                }
                if (b == 123 || b == 91) {
                    str = "Input does not start with Smile format header (first byte = 0x" + Integer.toHexString(b & 255) + ") -- rather, it starts with '" + ((char) b) + "' (plain JSON input?) -- can not parse";
                } else {
                    str = "Input does not start with Smile format header (first byte = 0x" + Integer.toHexString(b & 255) + ") and parser has REQUIRE_HEADER enabled: can not parse";
                }
                throw new JsonParseException(str, JsonLocation.NA);
            }
        }
        return smileParser;
    }

    public static MatchStrength hasSmileFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte2 = inputAccessor.nextByte();
        if (nextByte == 58) {
            if (nextByte2 != 41) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            return inputAccessor.nextByte() == 10 ? MatchStrength.FULL_MATCH : MatchStrength.NO_MATCH;
        } else if (nextByte == -6) {
            if (nextByte2 == 52) {
                return MatchStrength.SOLID_MATCH;
            }
            int i = nextByte2 & 255;
            if (i < 128 || i >= 248) {
                return MatchStrength.NO_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        } else if (nextByte == -8) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (likelySmileValue(nextByte2) || possibleSmileValue(nextByte2, true)) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        } else if (likelySmileValue(nextByte) || possibleSmileValue(nextByte2, false)) {
            return MatchStrength.SOLID_MATCH;
        } else {
            return MatchStrength.NO_MATCH;
        }
    }

    /* access modifiers changed from: protected */
    public boolean ensureLoaded(int i) throws IOException {
        if (this._in == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            InputStream inputStream = this._in;
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputEnd;
            int read = inputStream.read(bArr, i3, bArr.length - i3);
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 += read;
        }
        return true;
    }
}
