package com.android.org.bouncycastle.crypto.modes;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.android.org.bouncycastle.crypto.OutputLengthException;
import com.android.org.bouncycastle.crypto.modes.gcm.BasicGCMExponentiator;
import com.android.org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import com.android.org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import com.android.org.bouncycastle.crypto.modes.gcm.GCMUtil;
import com.android.org.bouncycastle.crypto.modes.gcm.Tables4kGCMMultiplier;
import com.android.org.bouncycastle.crypto.params.AEADParameters;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.primitives.UnsignedBytes;

public class GCMBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final long MAX_INPUT_SIZE = 68719476704L;
    private byte[] H;
    private byte[] J0;
    private byte[] S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
    private int blocksRemaining;
    private byte[] bufBlock;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] counter;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private boolean initialised;
    private byte[] lastKey;
    private byte[] macBlock;
    private int macSize;
    private GCMMultiplier multiplier;
    private byte[] nonce;
    private long totalLength;

    public GCMBlockCipher(BlockCipher c) {
        this(c, null);
    }

    public GCMBlockCipher(BlockCipher c, GCMMultiplier m) {
        if (c.getBlockSize() == 16) {
            m = m == null ? new Tables4kGCMMultiplier() : m;
            this.cipher = c;
            this.multiplier = m;
            return;
        }
        throw new IllegalArgumentException("cipher required with a block size of 16.");
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean forEncryption2, CipherParameters params) throws IllegalArgumentException {
        KeyParameter keyParam;
        byte[] newNonce;
        byte[] bArr;
        this.forEncryption = forEncryption2;
        this.macBlock = null;
        this.initialised = true;
        if (params instanceof AEADParameters) {
            AEADParameters param = (AEADParameters) params;
            newNonce = param.getNonce();
            this.initialAssociatedText = param.getAssociatedText();
            int macSizeBits = param.getMacSize();
            if (macSizeBits < 32 || macSizeBits > 128 || macSizeBits % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSizeBits);
            }
            this.macSize = macSizeBits / 8;
            keyParam = param.getKey();
        } else if (params instanceof ParametersWithIV) {
            ParametersWithIV param2 = (ParametersWithIV) params;
            newNonce = param2.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParam = (KeyParameter) param2.getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        }
        this.bufBlock = new byte[(forEncryption2 ? 16 : this.macSize + 16)];
        if (newNonce == null || newNonce.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (forEncryption2 && (bArr = this.nonce) != null && Arrays.areEqual(bArr, newNonce)) {
            if (keyParam != null) {
                byte[] bArr2 = this.lastKey;
                if (bArr2 != null && Arrays.areEqual(bArr2, keyParam.getKey())) {
                    throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
                }
            } else {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.nonce = newNonce;
        if (keyParam != null) {
            this.lastKey = keyParam.getKey();
        }
        if (keyParam != null) {
            this.cipher.init(true, keyParam);
            this.H = new byte[16];
            BlockCipher blockCipher = this.cipher;
            byte[] bArr3 = this.H;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            this.multiplier.init(this.H);
            this.exp = null;
        } else if (this.H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        this.J0 = new byte[16];
        byte[] bArr4 = this.nonce;
        if (bArr4.length == 12) {
            System.arraycopy(bArr4, 0, this.J0, 0, bArr4.length);
            this.J0[15] = 1;
        } else {
            gHASH(this.J0, bArr4, bArr4.length);
            byte[] X = new byte[16];
            Pack.longToBigEndian(((long) this.nonce.length) * 8, X, 8);
            gHASHBlock(this.J0, X);
        }
        this.S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0;
        this.atLengthPre = 0;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0;
        byte[] bArr5 = this.initialAssociatedText;
        if (bArr5 != null) {
            processAADBytes(bArr5, 0, bArr5.length);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        if (bArr == null) {
            return new byte[this.macSize];
        }
        return Arrays.clone(bArr);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int len) {
        int totalData = this.bufOff + len;
        if (this.forEncryption) {
            return this.macSize + totalData;
        }
        int i = this.macSize;
        if (totalData < i) {
            return 0;
        }
        return totalData - i;
    }

    private long getTotalInputSizeAfterNewInput(int newInputLen) {
        return this.totalLength + ((long) newInputLen) + ((long) this.bufOff);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int len) {
        int totalData = this.bufOff + len;
        if (!this.forEncryption) {
            int i = this.macSize;
            if (totalData < i) {
                return 0;
            }
            totalData -= i;
        }
        return totalData - (totalData % 16);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte in) {
        checkStatus();
        if (getTotalInputSizeAfterNewInput(1) <= MAX_INPUT_SIZE) {
            byte[] bArr = this.atBlock;
            int i = this.atBlockPos;
            bArr[i] = in;
            int i2 = i + 1;
            this.atBlockPos = i2;
            if (i2 == 16) {
                gHASHBlock(this.S_at, bArr);
                this.atBlockPos = 0;
                this.atLength += 16;
                return;
            }
            return;
        }
        throw new DataLengthException("Input exceeded 68719476704 bytes");
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] in, int inOff, int len) {
        checkStatus();
        if (getTotalInputSizeAfterNewInput(len) <= MAX_INPUT_SIZE) {
            for (int i = 0; i < len; i++) {
                byte[] bArr = this.atBlock;
                int i2 = this.atBlockPos;
                bArr[i2] = in[inOff + i];
                int i3 = i2 + 1;
                this.atBlockPos = i3;
                if (i3 == 16) {
                    gHASHBlock(this.S_at, bArr);
                    this.atBlockPos = 0;
                    this.atLength += 16;
                }
            }
            return;
        }
        throw new DataLengthException("Input exceeded 68719476704 bytes");
    }

    private void initCipher() {
        if (this.atLength > 0) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        int i = this.atBlockPos;
        if (i > 0) {
            gHASHPartial(this.S_atPre, this.atBlock, 0, i);
            this.atLengthPre += (long) this.atBlockPos;
        }
        if (this.atLengthPre > 0) {
            System.arraycopy(this.S_atPre, 0, this.S, 0, 16);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte in, byte[] out, int outOff) throws DataLengthException {
        checkStatus();
        if (getTotalInputSizeAfterNewInput(1) <= MAX_INPUT_SIZE) {
            byte[] bArr = this.bufBlock;
            int i = this.bufOff;
            bArr[i] = in;
            int i2 = i + 1;
            this.bufOff = i2;
            if (i2 != bArr.length) {
                return 0;
            }
            processBlock(bArr, 0, out, outOff);
            if (this.forEncryption) {
                this.bufOff = 0;
            } else {
                byte[] bArr2 = this.bufBlock;
                System.arraycopy(bArr2, 16, bArr2, 0, this.macSize);
                this.bufOff = this.macSize;
            }
            return 16;
        }
        throw new DataLengthException("Input exceeded 68719476704 bytes");
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) throws DataLengthException {
        checkStatus();
        if (getTotalInputSizeAfterNewInput(len) > MAX_INPUT_SIZE) {
            throw new DataLengthException("Input exceeded 68719476704 bytes");
        } else if (in.length - inOff >= len) {
            int resultLen = 0;
            if (this.forEncryption) {
                if (this.bufOff != 0) {
                    while (true) {
                        if (len <= 0) {
                            break;
                        }
                        len--;
                        byte[] bArr = this.bufBlock;
                        int i = this.bufOff;
                        int inOff2 = inOff + 1;
                        bArr[i] = in[inOff];
                        int i2 = i + 1;
                        this.bufOff = i2;
                        if (i2 == 16) {
                            processBlock(bArr, 0, out, outOff);
                            this.bufOff = 0;
                            resultLen = 0 + 16;
                            inOff = inOff2;
                            break;
                        }
                        inOff = inOff2;
                    }
                }
                while (len >= 16) {
                    processBlock(in, inOff, out, outOff + resultLen);
                    inOff += 16;
                    len -= 16;
                    resultLen += 16;
                }
                if (len > 0) {
                    System.arraycopy(in, inOff, this.bufBlock, 0, len);
                    this.bufOff = len;
                }
            } else {
                for (int i3 = 0; i3 < len; i3++) {
                    byte[] bArr2 = this.bufBlock;
                    int i4 = this.bufOff;
                    bArr2[i4] = in[inOff + i3];
                    int i5 = i4 + 1;
                    this.bufOff = i5;
                    if (i5 == bArr2.length) {
                        processBlock(bArr2, 0, out, outOff + resultLen);
                        byte[] bArr3 = this.bufBlock;
                        System.arraycopy(bArr3, 16, bArr3, 0, this.macSize);
                        this.bufOff = this.macSize;
                        resultLen += 16;
                    }
                }
            }
            return resultLen;
        } else {
            throw new DataLengthException("Input buffer too short");
        }
    }

    /* JADX INFO: Multiple debug info for r1v2 byte[]: [D('c' long), D('X' byte[])] */
    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] out, int outOff) throws IllegalStateException, InvalidCipherTextException {
        checkStatus();
        if (this.totalLength == 0) {
            initCipher();
        }
        int extra = this.bufOff;
        if (!this.forEncryption) {
            int i = this.macSize;
            if (extra >= i) {
                extra -= i;
                if (out.length - outOff < extra) {
                    throw new OutputLengthException("Output buffer too short");
                }
            } else {
                throw new InvalidCipherTextException("data too short");
            }
        } else if (out.length - outOff < this.macSize + extra) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (extra > 0) {
            processPartial(this.bufBlock, 0, extra, out, outOff);
        }
        long j = this.atLength;
        int i2 = this.atBlockPos;
        this.atLength = j + ((long) i2);
        if (this.atLength > this.atLengthPre) {
            if (i2 > 0) {
                gHASHPartial(this.S_at, this.atBlock, 0, i2);
            }
            if (this.atLengthPre > 0) {
                GCMUtil.xor(this.S_at, this.S_atPre);
            }
            long c = ((this.totalLength * 8) + 127) >>> 7;
            byte[] H_c = new byte[16];
            if (this.exp == null) {
                this.exp = new BasicGCMExponentiator();
                this.exp.init(this.H);
            }
            this.exp.exponentiateX(c, H_c);
            GCMUtil.multiply(this.S_at, H_c);
            GCMUtil.xor(this.S, this.S_at);
        }
        byte[] X = new byte[16];
        Pack.longToBigEndian(this.atLength * 8, X, 0);
        Pack.longToBigEndian(this.totalLength * 8, X, 8);
        gHASHBlock(this.S, X);
        byte[] tag = new byte[16];
        this.cipher.processBlock(this.J0, 0, tag, 0);
        GCMUtil.xor(tag, this.S);
        int resultLen = extra;
        int i3 = this.macSize;
        this.macBlock = new byte[i3];
        System.arraycopy(tag, 0, this.macBlock, 0, i3);
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, out, this.bufOff + outOff, this.macSize);
            resultLen += this.macSize;
        } else {
            int i4 = this.macSize;
            byte[] msgMac = new byte[i4];
            System.arraycopy(this.bufBlock, extra, msgMac, 0, i4);
            if (!Arrays.constantTimeAreEqual(this.macBlock, msgMac)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        reset(false);
        return resultLen;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        reset(true);
    }

    private void reset(boolean clearMac) {
        this.cipher.reset();
        this.S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0;
        this.atLengthPre = 0;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0;
        byte[] bArr = this.bufBlock;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        if (clearMac) {
            this.macBlock = null;
        }
        if (this.forEncryption) {
            this.initialised = false;
            return;
        }
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    private void processBlock(byte[] buf, int bufOff2, byte[] out, int outOff) {
        if (out.length - outOff >= 16) {
            if (this.totalLength == 0) {
                initCipher();
            }
            byte[] ctrBlock = new byte[16];
            getNextCTRBlock(ctrBlock);
            if (this.forEncryption) {
                GCMUtil.xor(ctrBlock, buf, bufOff2);
                gHASHBlock(this.S, ctrBlock);
                System.arraycopy(ctrBlock, 0, out, outOff, 16);
            } else {
                gHASHBlock(this.S, buf, bufOff2);
                GCMUtil.xor(ctrBlock, 0, buf, bufOff2, out, outOff);
            }
            this.totalLength += 16;
            return;
        }
        throw new OutputLengthException("Output buffer too short");
    }

    private void processPartial(byte[] buf, int off, int len, byte[] out, int outOff) {
        byte[] ctrBlock = new byte[16];
        getNextCTRBlock(ctrBlock);
        if (this.forEncryption) {
            GCMUtil.xor(buf, off, ctrBlock, 0, len);
            gHASHPartial(this.S, buf, off, len);
        } else {
            gHASHPartial(this.S, buf, off, len);
            GCMUtil.xor(buf, off, ctrBlock, 0, len);
        }
        System.arraycopy(buf, off, out, outOff, len);
        this.totalLength += (long) len;
    }

    private void gHASH(byte[] Y, byte[] b, int len) {
        for (int pos = 0; pos < len; pos += 16) {
            gHASHPartial(Y, b, pos, Math.min(len - pos, 16));
        }
    }

    private void gHASHBlock(byte[] Y, byte[] b) {
        GCMUtil.xor(Y, b);
        this.multiplier.multiplyH(Y);
    }

    private void gHASHBlock(byte[] Y, byte[] b, int off) {
        GCMUtil.xor(Y, b, off);
        this.multiplier.multiplyH(Y);
    }

    private void gHASHPartial(byte[] Y, byte[] b, int off, int len) {
        GCMUtil.xor(Y, b, off, len);
        this.multiplier.multiplyH(Y);
    }

    private void getNextCTRBlock(byte[] block) {
        int i = this.blocksRemaining;
        if (i != 0) {
            this.blocksRemaining = i - 1;
            byte[] bArr = this.counter;
            int c = 1 + (bArr[15] & UnsignedBytes.MAX_VALUE);
            bArr[15] = (byte) c;
            int c2 = (c >>> 8) + (bArr[14] & UnsignedBytes.MAX_VALUE);
            bArr[14] = (byte) c2;
            int c3 = (c2 >>> 8) + (bArr[13] & UnsignedBytes.MAX_VALUE);
            bArr[13] = (byte) c3;
            bArr[12] = (byte) ((c3 >>> 8) + (bArr[12] & UnsignedBytes.MAX_VALUE));
            this.cipher.processBlock(bArr, 0, block, 0);
            return;
        }
        throw new IllegalStateException("Attempt to process too many blocks");
    }

    private void checkStatus() {
        if (this.initialised) {
            return;
        }
        if (this.forEncryption) {
            throw new IllegalStateException("GCM cipher cannot be reused for encryption");
        }
        throw new IllegalStateException("GCM cipher needs to be initialised");
    }
}
