package com.android.org.bouncycastle.crypto;

public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {
    private final BlockCipher cipher;

    /* access modifiers changed from: protected */
    public abstract byte calculateByte(byte b);

    protected StreamBlockCipher(BlockCipher cipher2) {
        this.cipher = cipher2;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public final byte returnByte(byte in) {
        return calculateByte(in);
    }

    @Override // com.android.org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) throws DataLengthException {
        if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too small");
        } else if (outOff + len <= out.length) {
            int inEnd = inOff + len;
            int outStart = outOff;
            for (int inStart = inOff; inStart < inEnd; inStart++) {
                out[outStart] = calculateByte(in[inStart]);
                outStart++;
            }
            return len;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }
}
