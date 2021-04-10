package com.android.org.bouncycastle.crypto.macs;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.Mac;
import com.android.org.bouncycastle.crypto.modes.CBCBlockCipher;
import com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class CBCBlockCipherMac implements Mac {
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] mac;
    private int macSize;
    private BlockCipherPadding padding;

    public CBCBlockCipherMac(BlockCipher cipher2) {
        this(cipher2, (cipher2.getBlockSize() * 8) / 2, null);
    }

    public CBCBlockCipherMac(BlockCipher cipher2, BlockCipherPadding padding2) {
        this(cipher2, (cipher2.getBlockSize() * 8) / 2, padding2);
    }

    public CBCBlockCipherMac(BlockCipher cipher2, int macSizeInBits) {
        this(cipher2, macSizeInBits, null);
    }

    public CBCBlockCipherMac(BlockCipher cipher2, int macSizeInBits, BlockCipherPadding padding2) {
        if (macSizeInBits % 8 == 0) {
            this.cipher = new CBCBlockCipher(cipher2);
            this.padding = padding2;
            this.macSize = macSizeInBits / 8;
            this.mac = new byte[cipher2.getBlockSize()];
            this.buf = new byte[cipher2.getBlockSize()];
            this.bufOff = 0;
            return;
        }
        throw new IllegalArgumentException("MAC size must be multiple of 8");
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void init(CipherParameters params) {
        reset();
        this.cipher.init(true, params);
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void update(byte in) {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            this.cipher.processBlock(bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr2[i2] = in;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void update(byte[] in, int inOff, int len) {
        if (len >= 0) {
            int blockSize = this.cipher.getBlockSize();
            int i = this.bufOff;
            int gapLen = blockSize - i;
            if (len > gapLen) {
                System.arraycopy(in, inOff, this.buf, i, gapLen);
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
                len -= gapLen;
                inOff += gapLen;
                while (len > blockSize) {
                    this.cipher.processBlock(in, inOff, this.mac, 0);
                    len -= blockSize;
                    inOff += blockSize;
                }
            }
            System.arraycopy(in, inOff, this.buf, this.bufOff, len);
            this.bufOff += len;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public int doFinal(byte[] out, int outOff) {
        int blockSize = this.cipher.getBlockSize();
        if (this.padding == null) {
            while (true) {
                int i = this.bufOff;
                if (i >= blockSize) {
                    break;
                }
                this.buf[i] = 0;
                this.bufOff = i + 1;
            }
        } else {
            if (this.bufOff == blockSize) {
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
            }
            this.padding.addPadding(this.buf, this.bufOff);
        }
        this.cipher.processBlock(this.buf, 0, this.mac, 0);
        System.arraycopy(this.mac, 0, out, outOff, this.macSize);
        reset();
        return this.macSize;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            }
        }
    }
}
