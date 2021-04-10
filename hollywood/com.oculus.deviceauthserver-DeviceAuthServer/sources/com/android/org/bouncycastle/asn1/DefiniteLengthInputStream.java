package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.io.Streams;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    DefiniteLengthInputStream(InputStream in, int length) {
        super(in, length);
        if (length >= 0) {
            this._originalLength = length;
            this._remaining = length;
            if (length == 0) {
                setParentEofDetect(true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("negative lengths not allowed");
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.LimitedInputStream
    public int getRemaining() {
        return this._remaining;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int b = this._in.read();
        if (b >= 0) {
            int i = this._remaining - 1;
            this._remaining = i;
            if (i == 0) {
                setParentEofDetect(true);
            }
            return b;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        int i = this._remaining;
        if (i == 0) {
            return -1;
        }
        int numRead = this._in.read(buf, off, Math.min(len, i));
        if (numRead >= 0) {
            int i2 = this._remaining - numRead;
            this._remaining = i2;
            if (i2 == 0) {
                setParentEofDetect(true);
            }
            return numRead;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    /* access modifiers changed from: package-private */
    public byte[] toByteArray() throws IOException {
        int i = this._remaining;
        if (i == 0) {
            return EMPTY_BYTES;
        }
        byte[] bytes = new byte[i];
        int readFully = i - Streams.readFully(this._in, bytes);
        this._remaining = readFully;
        if (readFully == 0) {
            setParentEofDetect(true);
            return bytes;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }
}
