package com.oculus.localmedia.crypto;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;

public class SodiumStreamEncoder implements StreamEncoder {
    public static final int DEFAULT_ENCRYPTION_CHUNK_SIZE = 32768;
    public static final int ENCRYPTION_SHARED_SECRET_SIZE = 32;
    public static final String TAG = "SodiumStreamEncoder";
    public static int sEncryptionChunkSizeOverhead;
    public final int mChunkSize;
    public final byte[] mSecretKey;

    public static native byte[] nativeDecrypt(byte[] bArr, byte[] bArr2, int i);

    public static native byte[] nativeEncrypt(byte[] bArr, byte[] bArr2, int i);

    public static native int nativeGetChunkOverhead();

    public static native byte[] nativeRandom(int i);

    static {
        try {
            System.loadLibrary("localmediacrypto");
        } catch (Throwable th) {
            Log.e(TAG, "Could not load native library", th);
        }
        try {
            int nativeGetChunkOverhead = nativeGetChunkOverhead();
            sEncryptionChunkSizeOverhead = nativeGetChunkOverhead;
            if (nativeGetChunkOverhead <= 0) {
                throw new Exception("Encryption chunksize overheard needs to be greater than 0");
            }
        } catch (Throwable th2) {
            Log.e(TAG, "Could not get native chunk overhead", th2);
        }
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public byte[] decrypt(byte[] bArr, int i) {
        return nativeDecrypt(this.mSecretKey, bArr, i);
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public byte[] encrypt(byte[] bArr, int i) {
        return nativeEncrypt(this.mSecretKey, bArr, i);
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public long getDecryptedLength(long j) throws Exception {
        if (j > 0) {
            int chunkSize = getChunkSize();
            int chunkSizeEncryptionOverhead = getChunkSizeEncryptionOverhead();
            long ceil = (long) Math.ceil(((double) j) / ((double) (chunkSize + chunkSizeEncryptionOverhead)));
            long j2 = (long) chunkSizeEncryptionOverhead;
            if (RecyclerView.FOREVER_NS / ceil >= j2) {
                return j - (ceil * j2);
            }
            throw new Exception("Zero length or overflow");
        }
        throw new Exception("EncryptedLength needs to be greater than 0");
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public long getEncryptedLength(long j) throws Exception {
        if (j > 0) {
            long ceil = (long) Math.ceil(((double) j) / ((double) getChunkSize()));
            long j2 = RecyclerView.FOREVER_NS / ceil;
            long chunkSizeEncryptionOverhead = (long) getChunkSizeEncryptionOverhead();
            if (j2 >= chunkSizeEncryptionOverhead) {
                long j3 = ceil * chunkSizeEncryptionOverhead;
                if (j3 <= RecyclerView.FOREVER_NS - j) {
                    return j + j3;
                }
                throw new Exception("Overflow");
            }
            throw new Exception("Zero length or overflow");
        }
        throw new Exception("RawLength needs to be greater than 0");
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public int getChunkSize() {
        return this.mChunkSize;
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public int getChunkSizeEncryptionOverhead() {
        return sEncryptionChunkSizeOverhead;
    }

    public int getEncryptedChunkSize() {
        return getChunkSize() + getChunkSizeEncryptionOverhead();
    }

    public byte[] getSecretKey() {
        return this.mSecretKey;
    }

    public SodiumStreamEncoder() throws Exception {
        this(nativeRandom(32));
    }

    public SodiumStreamEncoder(byte[] bArr) throws Exception {
        this(bArr, 32768);
    }

    public SodiumStreamEncoder(byte[] bArr, int i) throws Exception {
        if (i > 0) {
            this.mSecretKey = bArr;
            this.mChunkSize = i;
            return;
        }
        throw new Exception("Chunksize needs to be greater than 0");
    }
}
