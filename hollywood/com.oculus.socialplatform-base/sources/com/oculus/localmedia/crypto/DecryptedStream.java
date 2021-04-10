package com.oculus.localmedia.crypto;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptedStream extends FilterInputStream {
    public static final String TAG = "DecryptedStream";
    public byte[] mDecryptedBuffer = null;
    public int mNumSentBytes = 0;
    public final byte[] mRawBuffer;
    public final StreamEncoder mStreamEncoder;

    private int fillRawBuffer() throws IOException {
        int i = 0;
        do {
            InputStream inputStream = this.in;
            byte[] bArr = this.mRawBuffer;
            int read = inputStream.read(bArr, i, bArr.length - i);
            if (-1 == read) {
                break;
            }
            i += read;
        } while (i != this.mRawBuffer.length);
        return i;
    }

    private int flush(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.mDecryptedBuffer;
        int length = bArr2.length;
        int i3 = this.mNumSentBytes;
        int min = Math.min(length - i3, i2);
        System.arraycopy(bArr2, i3, bArr, i, min);
        this.mNumSentBytes += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.mDecryptedBuffer;
        if (bArr2 == null || this.mNumSentBytes >= bArr2.length) {
            int fillRawBuffer = fillRawBuffer();
            if (fillRawBuffer <= 0) {
                return -1;
            }
            byte[] decrypt = this.mStreamEncoder.decrypt(this.mRawBuffer, fillRawBuffer);
            this.mDecryptedBuffer = decrypt;
            if (decrypt == null) {
                Log.e(TAG, "Error decoding chunk.");
                return 0;
            }
            this.mNumSentBytes = 0;
        }
        return flush(bArr, i, i2);
    }

    public DecryptedStream(InputStream inputStream, StreamEncoder streamEncoder) {
        super(inputStream);
        this.mStreamEncoder = streamEncoder;
        this.mRawBuffer = new byte[(streamEncoder.getChunkSize() + streamEncoder.getChunkSizeEncryptionOverhead())];
    }
}
