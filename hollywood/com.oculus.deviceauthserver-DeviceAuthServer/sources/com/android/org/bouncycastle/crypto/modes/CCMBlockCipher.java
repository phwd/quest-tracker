package com.android.org.bouncycastle.crypto.modes;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.android.org.bouncycastle.crypto.Mac;
import com.android.org.bouncycastle.crypto.OutputLengthException;
import com.android.org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import com.android.org.bouncycastle.crypto.params.AEADParameters;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.util.Arrays;
import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;

public class CCMBlockCipher implements AEADBlockCipher {
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private int blockSize;
    private BlockCipher cipher;
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private CipherParameters keyParam;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonce;

    public CCMBlockCipher(BlockCipher c) {
        this.cipher = c;
        this.blockSize = c.getBlockSize();
        int i = this.blockSize;
        this.macBlock = new byte[i];
        if (i != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean forEncryption2, CipherParameters params) throws IllegalArgumentException {
        CipherParameters cipherParameters;
        this.forEncryption = forEncryption2;
        if (params instanceof AEADParameters) {
            AEADParameters param = (AEADParameters) params;
            this.nonce = param.getNonce();
            this.initialAssociatedText = param.getAssociatedText();
            this.macSize = param.getMacSize() / 8;
            cipherParameters = param.getKey();
        } else if (params instanceof ParametersWithIV) {
            ParametersWithIV param2 = (ParametersWithIV) params;
            this.nonce = param2.getIV();
            this.initialAssociatedText = null;
            this.macSize = this.macBlock.length / 2;
            cipherParameters = param2.getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to CCM: " + params.getClass().getName());
        }
        if (cipherParameters != null) {
            this.keyParam = cipherParameters;
        }
        byte[] bArr = this.nonce;
        if (bArr == null || bArr.length < 7 || bArr.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        reset();
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CCM";
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte in) {
        this.associatedText.write(in);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] in, int inOff, int len) {
        this.associatedText.write(in, inOff, len);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte in, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        this.data.write(in);
        return 0;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] in, int inOff, int inLen, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (in.length >= inOff + inLen) {
            this.data.write(in, inOff, inLen);
            return 0;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] out, int outOff) throws IllegalStateException, InvalidCipherTextException {
        int len = processPacket(this.data.getBuffer(), 0, this.data.size(), out, outOff);
        reset();
        return len;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        this.cipher.reset();
        this.associatedText.reset();
        this.data.reset();
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        byte[] mac = new byte[this.macSize];
        System.arraycopy(this.macBlock, 0, mac, 0, mac.length);
        return mac;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int len) {
        return 0;
    }

    @Override // com.android.org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int len) {
        int totalData = this.data.size() + len;
        if (this.forEncryption) {
            return this.macSize + totalData;
        }
        int i = this.macSize;
        if (totalData < i) {
            return 0;
        }
        return totalData - i;
    }

    public byte[] processPacket(byte[] in, int inOff, int inLen) throws IllegalStateException, InvalidCipherTextException {
        byte[] output;
        if (this.forEncryption) {
            output = new byte[(this.macSize + inLen)];
        } else {
            int i = this.macSize;
            if (inLen >= i) {
                output = new byte[(inLen - i)];
            } else {
                throw new InvalidCipherTextException("data too short");
            }
        }
        processPacket(in, inOff, inLen, output, 0);
        return output;
    }

    public int processPacket(byte[] in, int inOff, int inLen, byte[] output, int outOff) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int outputLen;
        int i;
        int i2;
        if (this.keyParam != null) {
            int q = 15 - this.nonce.length;
            if (q >= 4 || inLen < (1 << (q * 8))) {
                byte[] iv = new byte[this.blockSize];
                iv[0] = (byte) ((q - 1) & 7);
                byte[] bArr = this.nonce;
                System.arraycopy(bArr, 0, iv, 1, bArr.length);
                BlockCipher ctrCipher = new SICBlockCipher(this.cipher);
                ctrCipher.init(this.forEncryption, new ParametersWithIV(this.keyParam, iv));
                int inIndex = inOff;
                int outIndex = outOff;
                if (this.forEncryption) {
                    outputLen = this.macSize + inLen;
                    if (output.length >= outputLen + outOff) {
                        calculateMac(in, inOff, inLen, this.macBlock);
                        byte[] encMac = new byte[this.blockSize];
                        ctrCipher.processBlock(this.macBlock, 0, encMac, 0);
                        while (true) {
                            i2 = this.blockSize;
                            if (inIndex >= (inOff + inLen) - i2) {
                                break;
                            }
                            ctrCipher.processBlock(in, inIndex, output, outIndex);
                            int i3 = this.blockSize;
                            outIndex += i3;
                            inIndex += i3;
                        }
                        byte[] block = new byte[i2];
                        System.arraycopy(in, inIndex, block, 0, (inLen + inOff) - inIndex);
                        ctrCipher.processBlock(block, 0, block, 0);
                        System.arraycopy(block, 0, output, outIndex, (inLen + inOff) - inIndex);
                        System.arraycopy(encMac, 0, output, outOff + inLen, this.macSize);
                    } else {
                        throw new OutputLengthException("Output buffer too short.");
                    }
                } else {
                    int n = this.macSize;
                    if (inLen >= n) {
                        outputLen = inLen - n;
                        if (output.length >= outputLen + outOff) {
                            byte b = 0;
                            System.arraycopy(in, inOff + outputLen, this.macBlock, 0, n);
                            byte[] bArr2 = this.macBlock;
                            ctrCipher.processBlock(bArr2, 0, bArr2, 0);
                            int i4 = this.macSize;
                            while (true) {
                                byte[] bArr3 = this.macBlock;
                                if (i4 == bArr3.length) {
                                    break;
                                }
                                bArr3[i4] = b;
                                i4++;
                                b = 0;
                            }
                            while (true) {
                                i = this.blockSize;
                                if (inIndex >= (inOff + outputLen) - i) {
                                    break;
                                }
                                ctrCipher.processBlock(in, inIndex, output, outIndex);
                                int i5 = this.blockSize;
                                outIndex += i5;
                                inIndex += i5;
                            }
                            byte[] block2 = new byte[i];
                            System.arraycopy(in, inIndex, block2, 0, outputLen - (inIndex - inOff));
                            ctrCipher.processBlock(block2, 0, block2, 0);
                            System.arraycopy(block2, 0, output, outIndex, outputLen - (inIndex - inOff));
                            byte[] calculatedMacBlock = new byte[this.blockSize];
                            calculateMac(output, outOff, outputLen, calculatedMacBlock);
                            if (!Arrays.constantTimeAreEqual(this.macBlock, calculatedMacBlock)) {
                                throw new InvalidCipherTextException("mac check in CCM failed");
                            }
                        } else {
                            throw new OutputLengthException("Output buffer too short.");
                        }
                    } else {
                        throw new InvalidCipherTextException("data too short");
                    }
                }
                return outputLen;
            }
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        throw new IllegalStateException("CCM cipher unitialized.");
    }

    private int calculateMac(byte[] data2, int dataOff, int dataLen, byte[] macBlock2) {
        int extra;
        Mac cMac = new CBCBlockCipherMac(this.cipher, this.macSize * 8);
        cMac.init(this.keyParam);
        byte[] b0 = new byte[16];
        if (hasAssociatedText()) {
            b0[0] = (byte) (b0[0] | SignedBytes.MAX_POWER_OF_TWO);
        }
        b0[0] = (byte) (b0[0] | ((((cMac.getMacSize() - 2) / 2) & 7) << 3));
        byte b = b0[0];
        byte[] bArr = this.nonce;
        b0[0] = (byte) (b | (((15 - bArr.length) - 1) & 7));
        System.arraycopy(bArr, 0, b0, 1, bArr.length);
        int q = dataLen;
        int count = 1;
        while (q > 0) {
            b0[b0.length - count] = (byte) (q & 255);
            q >>>= 8;
            count++;
        }
        cMac.update(b0, 0, b0.length);
        if (hasAssociatedText()) {
            int textLength = getAssociatedTextLength();
            if (textLength < 65280) {
                cMac.update((byte) (textLength >> 8));
                cMac.update((byte) textLength);
                extra = 2;
            } else {
                cMac.update((byte) -1);
                cMac.update((byte) -2);
                cMac.update((byte) (textLength >> 24));
                cMac.update((byte) (textLength >> 16));
                cMac.update((byte) (textLength >> 8));
                cMac.update((byte) textLength);
                extra = 6;
            }
            byte[] bArr2 = this.initialAssociatedText;
            if (bArr2 != null) {
                cMac.update(bArr2, 0, bArr2.length);
            }
            if (this.associatedText.size() > 0) {
                cMac.update(this.associatedText.getBuffer(), 0, this.associatedText.size());
            }
            int extra2 = (extra + textLength) % 16;
            if (extra2 != 0) {
                for (int i = extra2; i != 16; i++) {
                    cMac.update((byte) 0);
                }
            }
        }
        cMac.update(data2, dataOff, dataLen);
        return cMac.doFinal(macBlock2, 0);
    }

    private int getAssociatedTextLength() {
        int size = this.associatedText.size();
        byte[] bArr = this.initialAssociatedText;
        return size + (bArr == null ? 0 : bArr.length);
    }

    private boolean hasAssociatedText() {
        return getAssociatedTextLength() > 0;
    }

    /* access modifiers changed from: private */
    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }
}
