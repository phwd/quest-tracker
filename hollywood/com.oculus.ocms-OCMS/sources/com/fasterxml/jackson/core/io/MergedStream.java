package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream extends InputStream {
    byte[] _buffer;
    protected final IOContext _context;
    final int _end;
    final InputStream _in;
    int _ptr;

    public MergedStream(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._end = i2;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this._buffer != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        freeMergedBuffer();
        this._in.close();
    }

    public void mark(int i) {
        if (this._buffer == null) {
            this._in.mark(i);
        }
    }

    public boolean markSupported() {
        return this._buffer == null && this._in.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = this._buffer;
        if (bArr == null) {
            return this._in.read();
        }
        int i = this._ptr;
        this._ptr = i + 1;
        int i2 = bArr[i] & 255;
        if (this._ptr >= this._end) {
            freeMergedBuffer();
        }
        return i2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this._buffer == null) {
            return this._in.read(bArr, i, i2);
        }
        int i3 = this._end - this._ptr;
        if (i2 > i3) {
            i2 = i3;
        }
        System.arraycopy(this._buffer, this._ptr, bArr, i, i2);
        this._ptr += i2;
        if (this._ptr >= this._end) {
            freeMergedBuffer();
        }
        return i2;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this._buffer == null) {
            this._in.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2;
        if (this._buffer != null) {
            int i = this._end;
            int i2 = this._ptr;
            long j3 = (long) (i - i2);
            if (j3 > j) {
                this._ptr = i2 + ((int) j);
                return j;
            }
            freeMergedBuffer();
            j2 = j3 + 0;
            j -= j3;
        } else {
            j2 = 0;
        }
        return j > 0 ? j2 + this._in.skip(j) : j2;
    }

    private void freeMergedBuffer() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            IOContext iOContext = this._context;
            if (iOContext != null) {
                iOContext.releaseReadIOBuffer(bArr);
            }
        }
    }
}
