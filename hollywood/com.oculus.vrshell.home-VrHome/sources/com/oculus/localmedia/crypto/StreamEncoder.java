package com.oculus.localmedia.crypto;

public interface StreamEncoder {
    byte[] decrypt(byte[] bArr, int i);

    byte[] encrypt(byte[] bArr, int i);

    int getChunkSize();

    int getChunkSizeEncryptionOverhead();

    long getDecryptedLength(long j) throws Exception;

    long getEncryptedLength(long j) throws Exception;
}
