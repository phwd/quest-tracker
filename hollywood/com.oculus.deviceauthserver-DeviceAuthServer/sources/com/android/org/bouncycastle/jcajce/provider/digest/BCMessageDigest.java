package com.android.org.bouncycastle.jcajce.provider.digest;

import com.android.org.bouncycastle.crypto.Digest;
import java.security.MessageDigest;

public class BCMessageDigest extends MessageDigest {
    protected Digest digest;

    protected BCMessageDigest(Digest digest2) {
        super(digest2.getAlgorithmName());
        this.digest = digest2;
    }

    public void engineReset() {
        this.digest.reset();
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte input) {
        this.digest.update(input);
    }

    public void engineUpdate(byte[] input, int offset, int len) {
        this.digest.update(input, offset, len);
    }

    public byte[] engineDigest() {
        byte[] digestBytes = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(digestBytes, 0);
        return digestBytes;
    }
}
