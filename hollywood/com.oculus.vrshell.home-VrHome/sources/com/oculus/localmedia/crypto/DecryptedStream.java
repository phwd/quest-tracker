package com.oculus.localmedia.crypto;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptedStream extends FilterInputStream {
    public static final String TAG = DecryptedStream.class.getSimpleName();
    private byte[] mDecryptedBuffer = null;
    private int mNumSentBytes = 0;
    private final byte[] mRawBuffer;
    private final StreamEncoder mStreamEncoder;

    public DecryptedStream(InputStream in, StreamEncoder encoder) {
        super(in);
        this.mStreamEncoder = encoder;
        this.mRawBuffer = new byte[(encoder.getChunkSize() + encoder.getChunkSizeEncryptionOverhead())];
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int count) throws IOException {
        if (this.mDecryptedBuffer != null && this.mNumSentBytes < this.mDecryptedBuffer.length) {
            return flush(buffer, offset, count);
        }
        int read = fillRawBuffer();
        if (read <= 0) {
            return -1;
        }
        this.mDecryptedBuffer = this.mStreamEncoder.decrypt(this.mRawBuffer, read);
        if (this.mDecryptedBuffer == null) {
            Log.e(TAG, "Error decoding chunk.");
            return 0;
        }
        this.mNumSentBytes = 0;
        return flush(buffer, offset, count);
    }

    private int flush(byte[] buffer, int offset, int count) {
        int available = Math.min(this.mDecryptedBuffer.length - this.mNumSentBytes, count);
        System.arraycopy(this.mDecryptedBuffer, this.mNumSentBytes, buffer, offset, available);
        this.mNumSentBytes += available;
        Log.d(TAG, "Sent byte downstream size=" + this.mNumSentBytes);
        return available;
    }

    private int fillRawBuffer() throws IOException {
        int read = 0;
        do {
            int size = this.in.read(this.mRawBuffer, read, this.mRawBuffer.length - read);
            if (-1 == size) {
                break;
            }
            read += size;
        } while (read != this.mRawBuffer.length);
        Log.d(TAG, "read size=" + read);
        return read;
    }
}
