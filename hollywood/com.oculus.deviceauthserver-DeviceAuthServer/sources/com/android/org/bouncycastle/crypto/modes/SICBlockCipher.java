package com.android.org.bouncycastle.crypto.modes;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.SkippingStreamCipher;
import com.android.org.bouncycastle.crypto.StreamBlockCipher;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.primitives.UnsignedBytes;

public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    private byte[] IV;
    private final int blockSize = this.cipher.getBlockSize();
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher c) {
        super(c);
        this.cipher = c;
        int i = this.blockSize;
        this.IV = new byte[i];
        this.counter = new byte[i];
        this.counterOut = new byte[i];
        this.byteCount = 0;
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher, com.android.org.bouncycastle.crypto.StreamCipher
    public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            this.IV = Arrays.clone(ivParam.getIV());
            int i = this.blockSize;
            if (i >= this.IV.length) {
                int maxCounterSize = 8;
                if (8 > i / 2) {
                    maxCounterSize = i / 2;
                }
                if (this.blockSize - this.IV.length <= maxCounterSize) {
                    if (ivParam.getParameters() != null) {
                        this.cipher.init(true, ivParam.getParameters());
                    }
                    reset();
                    return;
                }
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - maxCounterSize) + " bytes.");
            }
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher, com.android.org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte in) throws DataLengthException, IllegalStateException {
        int i = this.byteCount;
        if (i == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (bArr[i2] ^ in);
        }
        byte[] bArr2 = this.counterOut;
        this.byteCount = i + 1;
        byte rv = (byte) (bArr2[i] ^ in);
        if (this.byteCount == this.counter.length) {
            this.byteCount = 0;
            incrementCounterAt(0);
            checkCounter();
        }
        return rv;
    }

    private void checkCounter() {
        if (this.IV.length < this.blockSize) {
            int i = 0;
            while (true) {
                byte[] bArr = this.IV;
                if (i == bArr.length) {
                    return;
                }
                if (this.counter[i] == bArr[i]) {
                    i++;
                } else {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
            }
        }
    }

    private void incrementCounterAt(int pos) {
        byte b;
        int i = this.counter.length - pos;
        do {
            i--;
            if (i >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[i] + 1);
                bArr[i] = b;
            } else {
                return;
            }
        } while (b == 0);
    }

    private void incrementCounter(int offSet) {
        byte[] bArr = this.counter;
        byte old = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + offSet);
        if (old != 0 && bArr[bArr.length - 1] < old) {
            incrementCounterAt(1);
        }
    }

    private void decrementCounterAt(int pos) {
        byte b;
        int i = this.counter.length - pos;
        do {
            i--;
            if (i >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[i] - 1);
                bArr[i] = b;
            } else {
                return;
            }
        } while (b == -1);
    }

    private void adjustCounter(long n) {
        if (n >= 0) {
            long numBlocks = (((long) this.byteCount) + n) / ((long) this.blockSize);
            long rem = numBlocks;
            if (rem > 255) {
                for (int i = 5; i >= 1; i--) {
                    long diff = 1 << (i * 8);
                    while (rem >= diff) {
                        incrementCounterAt(i);
                        rem -= diff;
                    }
                }
            }
            incrementCounter((int) rem);
            this.byteCount = (int) ((((long) this.byteCount) + n) - (((long) this.blockSize) * numBlocks));
            return;
        }
        long numBlocks2 = ((-n) - ((long) this.byteCount)) / ((long) this.blockSize);
        long rem2 = numBlocks2;
        if (rem2 > 255) {
            for (int i2 = 5; i2 >= 1; i2--) {
                long diff2 = 1 << (i2 * 8);
                while (rem2 > diff2) {
                    decrementCounterAt(i2);
                    rem2 -= diff2;
                }
            }
        }
        for (long i3 = 0; i3 != rem2; i3++) {
            decrementCounterAt(0);
        }
        int gap = (int) (((long) this.byteCount) + n + (((long) this.blockSize) * numBlocks2));
        if (gap >= 0) {
            this.byteCount = 0;
            return;
        }
        decrementCounterAt(0);
        this.byteCount = this.blockSize + gap;
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher, com.android.org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // com.android.org.bouncycastle.crypto.SkippingCipher
    public long skip(long numberOfBytes) {
        adjustCounter(numberOfBytes);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return numberOfBytes;
    }

    @Override // com.android.org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long position) {
        reset();
        return skip(position);
    }

    @Override // com.android.org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        int v;
        byte[] bArr = this.counter;
        byte[] res = new byte[bArr.length];
        System.arraycopy(bArr, 0, res, 0, res.length);
        for (int i = res.length - 1; i >= 1; i--) {
            byte[] bArr2 = this.IV;
            if (i < bArr2.length) {
                v = (res[i] & UnsignedBytes.MAX_VALUE) - (bArr2[i] & UnsignedBytes.MAX_VALUE);
            } else {
                v = res[i] & UnsignedBytes.MAX_VALUE;
            }
            if (v < 0) {
                int i2 = i - 1;
                res[i2] = (byte) (res[i2] - 1);
                v += 256;
            }
            res[i] = (byte) v;
        }
        return (Pack.bigEndianToLong(res, res.length - 8) * ((long) this.blockSize)) + ((long) this.byteCount);
    }
}
