package com.android.org.bouncycastle.jcajce.provider.asymmetric.rsa;

import com.android.org.bouncycastle.asn1.ASN1Encoding;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.DERNull;
import com.android.org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.android.org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.DigestInfo;
import com.android.org.bouncycastle.crypto.AsymmetricBlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.digests.AndroidDigestFactory;
import com.android.org.bouncycastle.crypto.encodings.PKCS1Encoding;
import com.android.org.bouncycastle.crypto.engines.RSABlindedEngine;
import com.android.org.bouncycastle.util.Arrays;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;

public class DigestSignatureSpi extends SignatureSpi {
    private AlgorithmIdentifier algId;
    private AsymmetricBlockCipher cipher;
    private Digest digest;

    protected DigestSignatureSpi(Digest digest2, AsymmetricBlockCipher cipher2) {
        this.digest = digest2;
        this.cipher = cipher2;
        this.algId = null;
    }

    protected DigestSignatureSpi(ASN1ObjectIdentifier objId, Digest digest2, AsymmetricBlockCipher cipher2) {
        this.digest = digest2;
        this.cipher = cipher2;
        this.algId = new AlgorithmIdentifier(objId, DERNull.INSTANCE);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof RSAPublicKey) {
            CipherParameters param = RSAUtil.generatePublicKeyParameter((RSAPublicKey) publicKey);
            this.digest.reset();
            this.cipher.init(false, param);
            return;
        }
        throw new InvalidKeyException("Supplied key (" + getType(publicKey) + ") is not a RSAPublicKey instance");
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof RSAPrivateKey) {
            CipherParameters param = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey) privateKey);
            this.digest.reset();
            this.cipher.init(true, param);
            return;
        }
        throw new InvalidKeyException("Supplied key (" + getType(privateKey) + ") is not a RSAPrivateKey instance");
    }

    private String getType(Object o) {
        if (o == null) {
            return null;
        }
        return o.getClass().getName();
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineUpdate(byte[] b, int off, int len) throws SignatureException {
        this.digest.update(b, off, len);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public byte[] engineSign() throws SignatureException {
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, 0);
        try {
            byte[] bytes = derEncode(hash);
            return this.cipher.processBlock(bytes, 0, bytes.length);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SignatureException("key too small for signature type");
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public boolean engineVerify(byte[] sigBytes) throws SignatureException {
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, 0);
        try {
            byte[] sig = this.cipher.processBlock(sigBytes, 0, sigBytes.length);
            byte[] expected = derEncode(hash);
            if (sig.length == expected.length) {
                return Arrays.constantTimeAreEqual(sig, expected);
            }
            if (sig.length == expected.length - 2) {
                expected[1] = (byte) (expected[1] - 2);
                expected[3] = (byte) (expected[3] - 2);
                int sigOffset = expected[3] + 4;
                int expectedOffset = sigOffset + 2;
                int nonEqual = 0;
                for (int i = 0; i < expected.length - expectedOffset; i++) {
                    nonEqual |= sig[sigOffset + i] ^ expected[expectedOffset + i];
                }
                for (int i2 = 0; i2 < sigOffset; i2++) {
                    nonEqual |= sig[i2] ^ expected[i2];
                }
                if (nonEqual == 0) {
                    return true;
                }
                return false;
            }
            Arrays.constantTimeAreEqual(expected, expected);
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineSetParameter(AlgorithmParameterSpec params) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineSetParameter(String param, Object value) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public Object engineGetParameter(String param) {
        return null;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    private byte[] derEncode(byte[] hash) throws IOException {
        AlgorithmIdentifier algorithmIdentifier = this.algId;
        if (algorithmIdentifier == null) {
            return hash;
        }
        return new DigestInfo(algorithmIdentifier, hash).getEncoded(ASN1Encoding.DER);
    }

    public static class SHA1 extends DigestSignatureSpi {
        public SHA1() {
            super(OIWObjectIdentifiers.idSHA1, AndroidDigestFactory.getSHA1(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA224 extends DigestSignatureSpi {
        public SHA224() {
            super(NISTObjectIdentifiers.id_sha224, AndroidDigestFactory.getSHA224(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA256 extends DigestSignatureSpi {
        public SHA256() {
            super(NISTObjectIdentifiers.id_sha256, AndroidDigestFactory.getSHA256(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA384 extends DigestSignatureSpi {
        public SHA384() {
            super(NISTObjectIdentifiers.id_sha384, AndroidDigestFactory.getSHA384(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512 extends DigestSignatureSpi {
        public SHA512() {
            super(NISTObjectIdentifiers.id_sha512, AndroidDigestFactory.getSHA512(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD5 extends DigestSignatureSpi {
        public MD5() {
            super(PKCSObjectIdentifiers.md5, AndroidDigestFactory.getMD5(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }
}
