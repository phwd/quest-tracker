package com.bumptech.glide.load.resource.bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.common.primitives.UnsignedBytes;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public boolean markSupported() {
        return true;
    }

    public RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) {
        this(inputStream, arrayPool, 65536);
    }

    @VisibleForTesting
    RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool, int i) {
        super(inputStream);
        this.markpos = -1;
        this.byteArrayPool = arrayPool;
        this.buf = (byte[]) arrayPool.get(i, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.buf == null || inputStream == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + inputStream.available();
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    public synchronized void release() {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        int i;
        int i2;
        int i3 = this.markpos;
        if (i3 == -1 || this.pos - i3 >= (i = this.marklimit)) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = read;
            }
            return read;
        }
        if (i3 == 0 && i > bArr.length && this.count == bArr.length) {
            int length = bArr.length * 2;
            if (length > i) {
                length = i;
            }
            byte[] bArr2 = (byte[]) this.byteArrayPool.get(length, byte[].class);
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.buf = bArr2;
            this.byteArrayPool.put(bArr);
            bArr = bArr2;
        } else {
            int i4 = this.markpos;
            if (i4 > 0) {
                System.arraycopy(bArr, i4, bArr, 0, bArr.length - i4);
            }
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int i5 = this.pos;
        int read2 = inputStream.read(bArr, i5, bArr.length - i5);
        if (read2 <= 0) {
            i2 = this.pos;
        } else {
            i2 = this.pos + read2;
        }
        this.count = i2;
        return read2;
    }

    public synchronized void mark(int i) {
        this.marklimit = Math.max(this.marklimit, i);
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] bArr = this.buf;
        InputStream inputStream = this.in;
        if (bArr == null || inputStream == null) {
            throw streamClosed();
        } else if (this.pos >= this.count && fillbuf(inputStream, bArr) == -1) {
            return -1;
        } else {
            if (bArr != this.buf && (bArr = this.buf) == null) {
                throw streamClosed();
            } else if (this.count - this.pos <= 0) {
                return -1;
            } else {
                int i = this.pos;
                this.pos = i + 1;
                return bArr[i] & UnsignedBytes.MAX_VALUE;
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        byte[] bArr2 = this.buf;
        if (bArr2 == null) {
            throw streamClosed();
        } else if (i2 == 0) {
            return 0;
        } else {
            InputStream inputStream = this.in;
            if (inputStream != null) {
                if (this.pos < this.count) {
                    int i5 = this.count - this.pos >= i2 ? i2 : this.count - this.pos;
                    System.arraycopy(bArr2, this.pos, bArr, i, i5);
                    this.pos += i5;
                    if (i5 == i2 || inputStream.available() == 0) {
                        return i5;
                    }
                    i += i5;
                    i3 = i2 - i5;
                } else {
                    i3 = i2;
                }
                while (true) {
                    int i6 = -1;
                    if (this.markpos == -1 && i3 >= bArr2.length) {
                        i4 = inputStream.read(bArr, i, i3);
                        if (i4 == -1) {
                            if (i3 != i2) {
                                i6 = i2 - i3;
                            }
                            return i6;
                        }
                    } else if (fillbuf(inputStream, bArr2) == -1) {
                        if (i3 != i2) {
                            i6 = i2 - i3;
                        }
                        return i6;
                    } else if (bArr2 == this.buf || (bArr2 = this.buf) != null) {
                        i4 = this.count - this.pos >= i3 ? i3 : this.count - this.pos;
                        System.arraycopy(bArr2, this.pos, bArr, i, i4);
                        this.pos += i4;
                    } else {
                        throw streamClosed();
                    }
                    i3 -= i4;
                    if (i3 == 0) {
                        return i2;
                    }
                    if (inputStream.available() == 0) {
                        return i2 - i3;
                    }
                    i += i4;
                }
            } else {
                throw streamClosed();
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        } else if (-1 != this.markpos) {
            this.pos = this.markpos;
        } else {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        if (j < 1) {
            return 0;
        }
        byte[] bArr = this.buf;
        if (bArr != null) {
            InputStream inputStream = this.in;
            if (inputStream == null) {
                throw streamClosed();
            } else if (((long) (this.count - this.pos)) >= j) {
                this.pos = (int) (((long) this.pos) + j);
                return j;
            } else {
                long j2 = ((long) this.count) - ((long) this.pos);
                this.pos = this.count;
                if (this.markpos == -1 || j > ((long) this.marklimit)) {
                    return j2 + inputStream.skip(j - j2);
                } else if (fillbuf(inputStream, bArr) == -1) {
                    return j2;
                } else {
                    if (((long) (this.count - this.pos)) >= j - j2) {
                        this.pos = (int) ((((long) this.pos) + j) - j2);
                        return j;
                    }
                    long j3 = (j2 + ((long) this.count)) - ((long) this.pos);
                    this.pos = this.count;
                    return j3;
                }
            }
        } else {
            throw streamClosed();
        }
    }

    /* access modifiers changed from: package-private */
    public static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        InvalidMarkException(String str) {
            super(str);
        }
    }
}
