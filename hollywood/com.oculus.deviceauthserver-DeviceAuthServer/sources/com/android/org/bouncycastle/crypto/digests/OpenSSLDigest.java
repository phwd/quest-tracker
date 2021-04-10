package com.android.org.bouncycastle.crypto.digests;

import com.android.org.bouncycastle.crypto.ExtendedDigest;
import java.security.DigestException;
import java.security.MessageDigest;

public class OpenSSLDigest implements ExtendedDigest {
    private final int byteSize;
    private final MessageDigest delegate;

    public OpenSSLDigest(String algorithm, int byteSize2) {
        try {
            this.delegate = MessageDigest.getInstance(algorithm, "AndroidOpenSSL");
            this.byteSize = byteSize2;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.delegate.getAlgorithm();
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.delegate.getDigestLength();
    }

    @Override // com.android.org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.byteSize;
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void reset() {
        this.delegate.reset();
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte in) {
        this.delegate.update(in);
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte[] in, int inOff, int len) {
        this.delegate.update(in, inOff, len);
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int doFinal(byte[] out, int outOff) {
        try {
            return this.delegate.digest(out, outOff, out.length - outOff);
        } catch (DigestException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MD5 extends OpenSSLDigest {
        public MD5() {
            super("MD5", 64);
        }
    }

    public static class SHA1 extends OpenSSLDigest {
        public SHA1() {
            super("SHA-1", 64);
        }
    }

    public static class SHA224 extends OpenSSLDigest {
        public SHA224() {
            super("SHA-224", 64);
        }
    }

    public static class SHA256 extends OpenSSLDigest {
        public SHA256() {
            super("SHA-256", 64);
        }
    }

    public static class SHA384 extends OpenSSLDigest {
        public SHA384() {
            super("SHA-384", 128);
        }
    }

    public static class SHA512 extends OpenSSLDigest {
        public SHA512() {
            super("SHA-512", 128);
        }
    }
}
