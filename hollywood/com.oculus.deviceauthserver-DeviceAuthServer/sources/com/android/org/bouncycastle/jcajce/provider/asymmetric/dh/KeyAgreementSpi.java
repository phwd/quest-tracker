package com.android.org.bouncycastle.jcajce.provider.asymmetric.dh;

import com.android.org.bouncycastle.crypto.BasicAgreement;
import com.android.org.bouncycastle.crypto.DerivationFunction;
import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.DHPublicKeyParameters;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import com.android.org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import com.android.org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyAgreementSpi extends BaseAgreementSpi {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private BigInteger g;
    private final BasicAgreement mqvAgreement;
    private BigInteger p;
    private byte[] result;
    private BigInteger x;

    public KeyAgreementSpi() {
        this("Diffie-Hellman", null);
    }

    public KeyAgreementSpi(String kaAlgorithm, DerivationFunction kdf) {
        super(kaAlgorithm, kdf);
        this.mqvAgreement = null;
    }

    public KeyAgreementSpi(String kaAlgorithm, BasicAgreement mqvAgreement2, DerivationFunction kdf) {
        super(kaAlgorithm, kdf);
        this.mqvAgreement = mqvAgreement2;
    }

    /* access modifiers changed from: protected */
    public byte[] bigIntToBytes(BigInteger r) {
        int expectedLength = (this.p.bitLength() + 7) / 8;
        byte[] tmp = r.toByteArray();
        if (tmp.length == expectedLength) {
            return tmp;
        }
        if (tmp[0] == 0 && tmp.length == expectedLength + 1) {
            byte[] rv = new byte[(tmp.length - 1)];
            System.arraycopy(tmp, 1, rv, 0, rv.length);
            return rv;
        }
        byte[] rv2 = new byte[expectedLength];
        System.arraycopy(tmp, 0, rv2, rv2.length - tmp.length, tmp.length);
        return rv2;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean lastPhase) throws InvalidKeyException, IllegalStateException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        } else if (key instanceof DHPublicKey) {
            DHPublicKey pubKey = (DHPublicKey) key;
            if (!pubKey.getParams().getG().equals(this.g) || !pubKey.getParams().getP().equals(this.p)) {
                throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
            }
            BigInteger peerY = ((DHPublicKey) key).getY();
            if (peerY == null || peerY.compareTo(TWO) < 0 || peerY.compareTo(this.p.subtract(ONE)) >= 0) {
                throw new InvalidKeyException("Invalid DH PublicKey");
            }
            BigInteger res = peerY.modPow(this.x, this.p);
            if (res.compareTo(ONE) != 0) {
                this.result = bigIntToBytes(res);
                if (lastPhase) {
                    return null;
                }
                return new BCDHPublicKey(res, pubKey.getParams());
            }
            throw new InvalidKeyException("Shared key can't be 1");
        } else {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.x != null) {
            return super.engineGenerateSecret();
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public int engineGenerateSecret(byte[] sharedSecret, int offset) throws IllegalStateException, ShortBufferException {
        if (this.x != null) {
            return super.engineGenerateSecret(sharedSecret, offset);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public SecretKey engineGenerateSecret(String algorithm) throws NoSuchAlgorithmException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        } else if (algorithm.equals("TlsPremasterSecret")) {
            return new SecretKeySpec(trimZeroes(this.result), algorithm);
        } else {
            return super.engineGenerateSecret(algorithm);
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey privKey = (DHPrivateKey) key;
            if (params == null) {
                this.p = privKey.getParams().getP();
                this.g = privKey.getParams().getG();
            } else if (params instanceof DHParameterSpec) {
                DHParameterSpec p2 = (DHParameterSpec) params;
                this.p = p2.getP();
                this.g = p2.getG();
                this.ukmParameters = null;
            } else if (!(params instanceof UserKeyingMaterialSpec)) {
                throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
            } else if (this.kdf != null) {
                this.p = privKey.getParams().getP();
                this.g = privKey.getParams().getG();
                this.ukmParameters = ((UserKeyingMaterialSpec) params).getUserKeyingMaterial();
            } else {
                throw new InvalidAlgorithmParameterException("no KDF specified for UserKeyingMaterialSpec");
            }
            this.x = privKey.getX();
            this.result = bigIntToBytes(this.x);
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom random) throws InvalidKeyException {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey privKey = (DHPrivateKey) key;
            this.p = privKey.getParams().getP();
            this.g = privKey.getParams().getG();
            this.x = privKey.getX();
            this.result = bigIntToBytes(this.x);
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return this.result;
    }

    private DHPrivateKeyParameters generatePrivateKeyParameter(PrivateKey privKey) throws InvalidKeyException {
        if (!(privKey instanceof DHPrivateKey)) {
            throw new InvalidKeyException("private key not a DHPrivateKey");
        } else if (privKey instanceof BCDHPrivateKey) {
            return ((BCDHPrivateKey) privKey).engineGetKeyParameters();
        } else {
            DHPrivateKey pub = (DHPrivateKey) privKey;
            DHParameterSpec params = pub.getParams();
            return new DHPrivateKeyParameters(pub.getX(), new DHParameters(params.getP(), params.getG(), null, params.getL()));
        }
    }

    private DHPublicKeyParameters generatePublicKeyParameter(PublicKey pubKey) throws InvalidKeyException {
        if (!(pubKey instanceof DHPublicKey)) {
            throw new InvalidKeyException("public key not a DHPublicKey");
        } else if (pubKey instanceof BCDHPublicKey) {
            return ((BCDHPublicKey) pubKey).engineGetKeyParameters();
        } else {
            DHPublicKey pub = (DHPublicKey) pubKey;
            DHParameterSpec params = pub.getParams();
            if (params instanceof DHDomainParameterSpec) {
                return new DHPublicKeyParameters(pub.getY(), ((DHDomainParameterSpec) params).getDomainParameters());
            }
            return new DHPublicKeyParameters(pub.getY(), new DHParameters(params.getP(), params.getG(), null, params.getL()));
        }
    }
}
