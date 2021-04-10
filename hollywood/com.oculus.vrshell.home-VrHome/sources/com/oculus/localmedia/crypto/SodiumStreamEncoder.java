package com.oculus.localmedia.crypto;

import android.util.Log;

public class SodiumStreamEncoder implements StreamEncoder {
    private static final int DEFAULT_ENCRYPTION_CHUNK_SIZE = 32768;
    private static final int ENCRYPTION_SHARED_SECRET_SIZE = 32;
    public static final String TAG = SodiumStreamEncoder.class.getSimpleName();
    private static int sEncryptionChunkSizeOverhead;
    private final int mChunkSize;
    private final byte[] mSecretKey;

    private static native byte[] nativeDecrypt(byte[] bArr, byte[] bArr2, int i);

    private static native byte[] nativeEncrypt(byte[] bArr, byte[] bArr2, int i);

    private static native int nativeGetChunkOverhead();

    private static native byte[] nativeRandom(int i);

    static {
        sEncryptionChunkSizeOverhead = 0;
        try {
            System.loadLibrary("localmediacrypto");
        } catch (Throwable t) {
            Log.e(TAG, "Could not load native library", t);
        }
        try {
            sEncryptionChunkSizeOverhead = nativeGetChunkOverhead();
            if (sEncryptionChunkSizeOverhead <= 0) {
                throw new Exception("Encryption chunksize overheard needs to be greater than 0");
            }
        } catch (Throwable t2) {
            Log.e(TAG, "Could not get native chunk overhead", t2);
        }
    }

    public SodiumStreamEncoder() throws Exception {
        this(nativeRandom(32));
    }

    public SodiumStreamEncoder(byte[] secretKey) throws Exception {
        this(secretKey, DEFAULT_ENCRYPTION_CHUNK_SIZE);
    }

    public SodiumStreamEncoder(byte[] secretKey, int chunkSize) throws Exception {
        if (chunkSize <= 0) {
            throw new Exception("Chunksize needs to be greater than 0");
        }
        this.mSecretKey = secretKey;
        this.mChunkSize = chunkSize;
    }

    public byte[] getSecretKey() {
        return this.mSecretKey;
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

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public long getEncryptedLength(long rawLength) throws Exception {
        if (rawLength <= 0) {
            throw new Exception("RawLength needs to be greater than 0");
        }
        long numChunks = (long) Math.ceil(((double) rawLength) / ((double) getChunkSize()));
        if (Long.MAX_VALUE / numChunks < ((long) getChunkSizeEncryptionOverhead())) {
            throw new Exception("Zero length or overflow");
        }
        long chunkOverhead = numChunks * ((long) getChunkSizeEncryptionOverhead());
        if (chunkOverhead <= Long.MAX_VALUE - rawLength) {
            return rawLength + chunkOverhead;
        }
        throw new Exception("Overflow");
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public long getDecryptedLength(long encryptedLength) throws Exception {
        if (encryptedLength <= 0) {
            throw new Exception("EncryptedLength needs to be greater than 0");
        }
        long numChunks = (long) Math.ceil(((double) encryptedLength) / ((double) (getChunkSize() + getChunkSizeEncryptionOverhead())));
        if (Long.MAX_VALUE / numChunks >= ((long) getChunkSizeEncryptionOverhead())) {
            return encryptedLength - (numChunks * ((long) getChunkSizeEncryptionOverhead()));
        }
        throw new Exception("Zero length or overflow");
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public byte[] encrypt(byte[] rawText, int length) {
        return nativeEncrypt(this.mSecretKey, rawText, length);
    }

    @Override // com.oculus.localmedia.crypto.StreamEncoder
    public byte[] decrypt(byte[] encryptedText, int length) {
        return nativeDecrypt(this.mSecretKey, encryptedText, length);
    }
}
