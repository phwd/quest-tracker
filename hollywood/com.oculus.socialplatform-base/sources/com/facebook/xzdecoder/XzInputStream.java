package com.facebook.xzdecoder;

import X.AnonymousClass0lD;
import java.io.IOException;
import java.io.InputStream;

public class XzInputStream extends InputStream {
    public int clientOutPos;
    public byte[] inBuf = new byte[32768];
    public InputStream inFile;
    public int inPos;
    public int inSize;
    public byte[] outBuf = new byte[32768];
    public int outPos;
    public long state = initializeState();

    public static native long decompressStream(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) throws IOException;

    public static native void end(long j);

    public static native void initializeLibrary();

    public static native long initializeState();

    private boolean readMoreInput() throws IOException {
        this.inSize = 0;
        int i = 0;
        this.inPos = 0;
        do {
            InputStream inputStream = this.inFile;
            byte[] bArr = this.inBuf;
            int read = inputStream.read(bArr, i, bArr.length - i);
            if (read == -1) {
                return this.inSize > 0;
            }
            i = this.inSize + read;
            this.inSize = i;
        } while (i < 32768);
        return true;
    }

    static {
        AnonymousClass0lD.A01("fb_xzdecoder");
        initializeLibrary();
    }

    private void decodeMoreBytes() throws IOException {
        long j = this.state;
        byte[] bArr = this.inBuf;
        int i = this.inPos;
        int i2 = this.inSize;
        byte[] bArr2 = this.outBuf;
        long decompressStream = decompressStream(j, bArr, i, i2, bArr2, 0, bArr2.length);
        this.inPos = (int) (decompressStream >>> 32);
        this.outPos = (int) decompressStream;
        this.clientOutPos = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        this.inFile.close();
        long j = this.state;
        if (j != 0) {
            end(j);
            this.state = 0;
        }
    }

    public XzInputStream(InputStream inputStream) throws IOException {
        this.inFile = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.state != 0) {
            if (this.clientOutPos == this.outPos) {
                if (this.inPos == this.inSize && !readMoreInput()) {
                    return -1;
                }
                decodeMoreBytes();
            }
            byte[] bArr = this.outBuf;
            int i = this.clientOutPos;
            this.clientOutPos = i + 1;
            return bArr[i];
        }
        throw new IOException("Stream closed");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (i2 < 0 || i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException(String.format("buf.length = %d, off = %d, len = %d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        } else if (this.state != 0) {
            int i4 = i2;
            while (i3 < i2) {
                int i5 = this.clientOutPos;
                int i6 = this.outPos;
                if (i5 < i6) {
                    int min = Math.min(i4, i6 - i5);
                    System.arraycopy(this.outBuf, i5, bArr, i, min);
                    this.clientOutPos += min;
                    i4 -= min;
                    i += min;
                    i3 += min;
                } else if (this.inPos != this.inSize || readMoreInput()) {
                    decodeMoreBytes();
                } else if (i3 == 0) {
                    return -1;
                } else {
                    return i3;
                }
            }
            return i3;
        } else {
            throw new IOException("Stream closed");
        }
    }
}
