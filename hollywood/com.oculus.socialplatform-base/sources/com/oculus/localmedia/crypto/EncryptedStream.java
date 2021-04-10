package com.oculus.localmedia.crypto;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptedStream extends FilterOutputStream {
    public static final String TAG = "EncryptedStream";
    public final byte[] mBuffer;
    public int mNumBytesWritten = 0;
    public final StreamEncoder mStreamEncoder;

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public void flush() throws IOException {
        int i = this.mNumBytesWritten;
        if (i > 0) {
            byte[] encrypt = this.mStreamEncoder.encrypt(this.mBuffer, i);
            this.out.write(encrypt, 0, encrypt.length);
            this.mNumBytesWritten = 0;
        }
    }

    public EncryptedStream(OutputStream outputStream, StreamEncoder streamEncoder) {
        super(outputStream);
        this.mBuffer = new byte[streamEncoder.getChunkSize()];
        this.mStreamEncoder = streamEncoder;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.mBuffer;
        int length = bArr2.length;
        int i3 = this.mNumBytesWritten;
        int min = Math.min(i2, length - i3);
        System.arraycopy(bArr, i, bArr2, i3, min);
        int i4 = this.mNumBytesWritten + min;
        this.mNumBytesWritten = i4;
        if (i4 == this.mBuffer.length) {
            flush();
        }
        int i5 = i2 - min;
        if (i5 > 0) {
            write(bArr, i + min, i5);
        }
    }
}
