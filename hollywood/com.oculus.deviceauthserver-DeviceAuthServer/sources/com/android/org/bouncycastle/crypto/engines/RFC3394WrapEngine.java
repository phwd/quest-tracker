package com.android.org.bouncycastle.crypto.engines;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.android.org.bouncycastle.crypto.Wrapper;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.util.Arrays;

public class RFC3394WrapEngine implements Wrapper {
    private BlockCipher engine;
    private boolean forWrapping;
    private byte[] iv;
    private KeyParameter param;
    private boolean wrapCipherMode;

    public RFC3394WrapEngine(BlockCipher engine2) {
        this(engine2, false);
    }

    public RFC3394WrapEngine(BlockCipher engine2, boolean useReverseDirection) {
        this.iv = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.engine = engine2;
        this.wrapCipherMode = !useReverseDirection;
    }

    @Override // com.android.org.bouncycastle.crypto.Wrapper
    public void init(boolean forWrapping2, CipherParameters param2) {
        this.forWrapping = forWrapping2;
        if (param2 instanceof ParametersWithRandom) {
            param2 = ((ParametersWithRandom) param2).getParameters();
        }
        if (param2 instanceof KeyParameter) {
            this.param = (KeyParameter) param2;
        } else if (param2 instanceof ParametersWithIV) {
            this.iv = ((ParametersWithIV) param2).getIV();
            this.param = (KeyParameter) ((ParametersWithIV) param2).getParameters();
            if (this.iv.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    @Override // com.android.org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    @Override // com.android.org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] in, int inOff, int inLen) {
        if (this.forWrapping) {
            int n = inLen / 8;
            if (n * 8 == inLen) {
                byte[] bArr = this.iv;
                byte[] block = new byte[(bArr.length + inLen)];
                byte[] buf = new byte[(bArr.length + 8)];
                System.arraycopy(bArr, 0, block, 0, bArr.length);
                System.arraycopy(in, inOff, block, this.iv.length, inLen);
                this.engine.init(this.wrapCipherMode, this.param);
                for (int j = 0; j != 6; j++) {
                    for (int i = 1; i <= n; i++) {
                        System.arraycopy(block, 0, buf, 0, this.iv.length);
                        System.arraycopy(block, i * 8, buf, this.iv.length, 8);
                        this.engine.processBlock(buf, 0, buf, 0);
                        int t = (n * j) + i;
                        int k = 1;
                        while (t != 0) {
                            int length = this.iv.length - k;
                            buf[length] = (byte) (buf[length] ^ ((byte) t));
                            t >>>= 8;
                            k++;
                        }
                        System.arraycopy(buf, 0, block, 0, 8);
                        System.arraycopy(buf, 8, block, i * 8, 8);
                    }
                }
                return block;
            }
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        throw new IllegalStateException("not set for wrapping");
    }

    @Override // com.android.org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (!this.forWrapping) {
            int n = inLen / 8;
            if (n * 8 == inLen) {
                byte[] bArr = this.iv;
                byte[] block = new byte[(inLen - bArr.length)];
                byte[] a = new byte[bArr.length];
                int i = 8;
                byte[] buf = new byte[(bArr.length + 8)];
                System.arraycopy(in, inOff, a, 0, bArr.length);
                byte[] bArr2 = this.iv;
                System.arraycopy(in, bArr2.length + inOff, block, 0, inLen - bArr2.length);
                int i2 = 1;
                this.engine.init(!this.wrapCipherMode, this.param);
                int n2 = n - 1;
                int j = 5;
                while (j >= 0) {
                    int i3 = n2;
                    while (i3 >= i2) {
                        System.arraycopy(a, 0, buf, 0, this.iv.length);
                        System.arraycopy(block, (i3 - 1) * i, buf, this.iv.length, i);
                        int t = (n2 * j) + i3;
                        int k = 1;
                        while (t != 0) {
                            int length = this.iv.length - k;
                            buf[length] = (byte) (buf[length] ^ ((byte) t));
                            t >>>= 8;
                            k++;
                        }
                        this.engine.processBlock(buf, 0, buf, 0);
                        i = 8;
                        System.arraycopy(buf, 0, a, 0, 8);
                        System.arraycopy(buf, 8, block, (i3 - 1) * 8, 8);
                        i3--;
                        i2 = 1;
                    }
                    j--;
                    i2 = 1;
                }
                if (Arrays.constantTimeAreEqual(a, this.iv)) {
                    return block;
                }
                throw new InvalidCipherTextException("checksum failed");
            }
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        throw new IllegalStateException("not set for unwrapping");
    }
}
