package com.android.org.bouncycastle.crypto.modes;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.util.Arrays;

public class CBCBlockCipher implements BlockCipher {
    private byte[] IV;
    private int blockSize;
    private byte[] cbcNextV;
    private byte[] cbcV;
    private BlockCipher cipher = null;
    private boolean encrypting;

    public CBCBlockCipher(BlockCipher cipher2) {
        this.cipher = cipher2;
        this.blockSize = cipher2.getBlockSize();
        int i = this.blockSize;
        this.IV = new byte[i];
        this.cbcV = new byte[i];
        this.cbcNextV = new byte[i];
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public void init(boolean encrypting2, CipherParameters params) throws IllegalArgumentException {
        boolean oldEncrypting = this.encrypting;
        this.encrypting = encrypting2;
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            byte[] iv = ivParam.getIV();
            if (iv.length == this.blockSize) {
                System.arraycopy(iv, 0, this.IV, 0, iv.length);
                reset();
                if (ivParam.getParameters() != null) {
                    this.cipher.init(encrypting2, ivParam.getParameters());
                } else if (oldEncrypting != encrypting2) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
            } else {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
        } else {
            reset();
            if (params != null) {
                this.cipher.init(encrypting2, params);
            } else if (oldEncrypting != encrypting2) {
                throw new IllegalArgumentException("cannot change encrypting state without providing key.");
            }
        }
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CBC";
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        return this.encrypting ? encryptBlock(in, inOff, out, outOff) : decryptBlock(in, inOff, out, outOff);
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.cbcV, 0, bArr.length);
        Arrays.fill(this.cbcNextV, (byte) 0);
        this.cipher.reset();
    }

    private int encryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (this.blockSize + inOff <= in.length) {
            for (int i = 0; i < this.blockSize; i++) {
                byte[] bArr = this.cbcV;
                bArr[i] = (byte) (bArr[i] ^ in[inOff + i]);
            }
            int length = this.cipher.processBlock(this.cbcV, 0, out, outOff);
            byte[] bArr2 = this.cbcV;
            System.arraycopy(out, outOff, bArr2, 0, bArr2.length);
            return length;
        }
        throw new DataLengthException("input buffer too short");
    }

    /* JADX INFO: Multiple debug info for r1v5 byte[]: [D('tmp' byte[]), D('i' int)] */
    private int decryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        int i = this.blockSize;
        if (inOff + i <= in.length) {
            System.arraycopy(in, inOff, this.cbcNextV, 0, i);
            int length = this.cipher.processBlock(in, inOff, out, outOff);
            for (int i2 = 0; i2 < this.blockSize; i2++) {
                int i3 = outOff + i2;
                out[i3] = (byte) (out[i3] ^ this.cbcV[i2]);
            }
            byte[] tmp = this.cbcV;
            this.cbcV = this.cbcNextV;
            this.cbcNextV = tmp;
            return length;
        }
        throw new DataLengthException("input buffer too short");
    }
}
