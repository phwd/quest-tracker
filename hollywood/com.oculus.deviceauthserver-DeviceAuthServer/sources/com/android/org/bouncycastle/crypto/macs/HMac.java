package com.android.org.bouncycastle.crypto.macs;

import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.ExtendedDigest;
import com.android.org.bouncycastle.crypto.Mac;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.android.org.bouncycastle.util.Integers;
import com.android.org.bouncycastle.util.Memoable;
import java.util.Hashtable;

public class HMac implements Mac {
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private static Hashtable blockLengths = new Hashtable();
    private int blockLength;
    private Digest digest;
    private int digestSize;
    private byte[] inputPad;
    private Memoable ipadState;
    private Memoable opadState;
    private byte[] outputBuf;

    static {
        blockLengths.put("MD5", Integers.valueOf(64));
        blockLengths.put("SHA-1", Integers.valueOf(64));
        blockLengths.put("SHA-224", Integers.valueOf(64));
        blockLengths.put("SHA-256", Integers.valueOf(64));
        blockLengths.put("SHA-384", Integers.valueOf(128));
        blockLengths.put("SHA-512", Integers.valueOf(128));
    }

    private static int getByteLength(Digest digest2) {
        if (digest2 instanceof ExtendedDigest) {
            return ((ExtendedDigest) digest2).getByteLength();
        }
        Integer b = (Integer) blockLengths.get(digest2.getAlgorithmName());
        if (b != null) {
            return b.intValue();
        }
        throw new IllegalArgumentException("unknown digest passed: " + digest2.getAlgorithmName());
    }

    public HMac(Digest digest2) {
        this(digest2, getByteLength(digest2));
    }

    private HMac(Digest digest2, int byteLength) {
        this.digest = digest2;
        this.digestSize = digest2.getDigestSize();
        this.blockLength = byteLength;
        int i = this.blockLength;
        this.inputPad = new byte[i];
        this.outputBuf = new byte[(i + this.digestSize)];
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void init(CipherParameters params) {
        byte[] bArr;
        this.digest.reset();
        byte[] key = ((KeyParameter) params).getKey();
        int keyLength = key.length;
        if (keyLength > this.blockLength) {
            this.digest.update(key, 0, keyLength);
            this.digest.doFinal(this.inputPad, 0);
            keyLength = this.digestSize;
        } else {
            System.arraycopy(key, 0, this.inputPad, 0, keyLength);
        }
        int i = keyLength;
        while (true) {
            bArr = this.inputPad;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        System.arraycopy(bArr, 0, this.outputBuf, 0, this.blockLength);
        xorPad(this.inputPad, this.blockLength, IPAD);
        xorPad(this.outputBuf, this.blockLength, OPAD);
        Digest digest2 = this.digest;
        if (digest2 instanceof Memoable) {
            this.opadState = ((Memoable) digest2).copy();
            ((Digest) this.opadState).update(this.outputBuf, 0, this.blockLength);
        }
        Digest digest3 = this.digest;
        byte[] bArr2 = this.inputPad;
        digest3.update(bArr2, 0, bArr2.length);
        Digest digest4 = this.digest;
        if (digest4 instanceof Memoable) {
            this.ipadState = ((Memoable) digest4).copy();
        }
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.digestSize;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void update(byte in) {
        this.digest.update(in);
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void update(byte[] in, int inOff, int len) {
        this.digest.update(in, inOff, len);
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public int doFinal(byte[] out, int outOff) {
        this.digest.doFinal(this.outputBuf, this.blockLength);
        Memoable memoable = this.opadState;
        if (memoable != null) {
            ((Memoable) this.digest).reset(memoable);
            Digest digest2 = this.digest;
            digest2.update(this.outputBuf, this.blockLength, digest2.getDigestSize());
        } else {
            Digest digest3 = this.digest;
            byte[] bArr = this.outputBuf;
            digest3.update(bArr, 0, bArr.length);
        }
        int len = this.digest.doFinal(out, outOff);
        int i = this.blockLength;
        while (true) {
            byte[] bArr2 = this.outputBuf;
            if (i >= bArr2.length) {
                break;
            }
            bArr2[i] = 0;
            i++;
        }
        Memoable memoable2 = this.ipadState;
        if (memoable2 != null) {
            ((Memoable) this.digest).reset(memoable2);
        } else {
            Digest digest4 = this.digest;
            byte[] bArr3 = this.inputPad;
            digest4.update(bArr3, 0, bArr3.length);
        }
        return len;
    }

    @Override // com.android.org.bouncycastle.crypto.Mac
    public void reset() {
        this.digest.reset();
        Digest digest2 = this.digest;
        byte[] bArr = this.inputPad;
        digest2.update(bArr, 0, bArr.length);
    }

    private static void xorPad(byte[] pad, int len, byte n) {
        for (int i = 0; i < len; i++) {
            pad[i] = (byte) (pad[i] ^ n);
        }
    }
}
