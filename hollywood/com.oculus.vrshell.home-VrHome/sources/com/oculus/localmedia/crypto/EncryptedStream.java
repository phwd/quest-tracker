package com.oculus.localmedia.crypto;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptedStream extends FilterOutputStream {
    public static final String TAG = EncryptedStream.class.getSimpleName();
    private final byte[] mBuffer;
    private int mNumBytesWritten = 0;
    private final StreamEncoder mStreamEncoder;

    public EncryptedStream(OutputStream out, StreamEncoder encoder) {
        super(out);
        this.mBuffer = new byte[encoder.getChunkSize()];
        this.mStreamEncoder = encoder;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] data, int offset, int count) throws IOException {
        int numBytesToWrite = Math.min(count, this.mBuffer.length - this.mNumBytesWritten);
        System.arraycopy(data, offset, this.mBuffer, this.mNumBytesWritten, numBytesToWrite);
        this.mNumBytesWritten += numBytesToWrite;
        if (this.mNumBytesWritten == this.mBuffer.length) {
            flush();
        }
        int numBytesRemaining = count - numBytesToWrite;
        if (numBytesRemaining > 0) {
            write(data, offset + numBytesToWrite, numBytesRemaining);
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.mNumBytesWritten > 0) {
            byte[] encrypted = this.mStreamEncoder.encrypt(this.mBuffer, this.mNumBytesWritten);
            this.out.write(encrypted, 0, encrypted.length);
            this.mNumBytesWritten = 0;
        }
    }
}
