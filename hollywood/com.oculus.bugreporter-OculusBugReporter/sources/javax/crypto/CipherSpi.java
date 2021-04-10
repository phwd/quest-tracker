package javax.crypto;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public abstract class CipherSpi {
    /* access modifiers changed from: protected */
    public abstract int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

    /* access modifiers changed from: protected */
    public abstract byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException;

    /* access modifiers changed from: protected */
    public abstract int engineGetBlockSize();

    /* access modifiers changed from: protected */
    public abstract byte[] engineGetIV();

    /* access modifiers changed from: protected */
    public abstract int engineGetOutputSize(int i);

    /* access modifiers changed from: protected */
    public abstract AlgorithmParameters engineGetParameters();

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException;

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException;

    /* access modifiers changed from: protected */
    public abstract void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException;

    /* access modifiers changed from: protected */
    public abstract void engineSetMode(String str) throws NoSuchAlgorithmException;

    /* access modifiers changed from: protected */
    public abstract void engineSetPadding(String str) throws NoSuchPaddingException;

    /* access modifiers changed from: protected */
    public abstract int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException;

    /* access modifiers changed from: protected */
    public abstract byte[] engineUpdate(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public int engineUpdate(ByteBuffer input, ByteBuffer output) throws ShortBufferException {
        try {
            return bufferCrypt(input, output, true);
        } catch (IllegalBlockSizeException e) {
            throw new ProviderException("Internal error in update()");
        } catch (BadPaddingException e2) {
            throw new ProviderException("Internal error in update()");
        }
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(ByteBuffer input, ByteBuffer output) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        return bufferCrypt(input, output, false);
    }

    static int getTempArraySize(int totalSize) {
        return Math.min(4096, totalSize);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d3 A[LOOP:0: B:21:0x008d->B:34:0x00d3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0193 A[LOOP:1: B:39:0x00fd->B:78:0x0193, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00cd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x018d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0197 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int bufferCrypt(java.nio.ByteBuffer r25, java.nio.ByteBuffer r26, boolean r27) throws javax.crypto.ShortBufferException, javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException {
        /*
        // Method dump skipped, instructions count: 457
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.crypto.CipherSpi.bufferCrypt(java.nio.ByteBuffer, java.nio.ByteBuffer, boolean):int");
    }

    /* access modifiers changed from: protected */
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType) throws InvalidKeyException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void engineUpdateAAD(byte[] src, int offset, int len) {
        throw new UnsupportedOperationException("The underlying Cipher implementation does not support this method");
    }

    /* access modifiers changed from: protected */
    public void engineUpdateAAD(ByteBuffer src) {
        throw new UnsupportedOperationException("The underlying Cipher implementation does not support this method");
    }
}
