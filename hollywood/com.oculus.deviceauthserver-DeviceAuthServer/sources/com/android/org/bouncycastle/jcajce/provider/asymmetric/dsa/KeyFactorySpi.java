package com.android.org.bouncycastle.jcajce.provider.asymmetric.dsa;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.android.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KeyFactorySpi extends BaseKeyFactorySpi {
    /* access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi
    public KeySpec engineGetKeySpec(Key key, Class spec) throws InvalidKeySpecException {
        if (spec.isAssignableFrom(DSAPublicKeySpec.class) && (key instanceof DSAPublicKey)) {
            DSAPublicKey k = (DSAPublicKey) key;
            return new DSAPublicKeySpec(k.getY(), k.getParams().getP(), k.getParams().getQ(), k.getParams().getG());
        } else if (!spec.isAssignableFrom(DSAPrivateKeySpec.class) || !(key instanceof DSAPrivateKey)) {
            return super.engineGetKeySpec(key, spec);
        } else {
            DSAPrivateKey k2 = (DSAPrivateKey) key;
            return new DSAPrivateKeySpec(k2.getX(), k2.getParams().getP(), k2.getParams().getQ(), k2.getParams().getG());
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi
    public Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key instanceof DSAPublicKey) {
            return new BCDSAPublicKey((DSAPublicKey) key);
        }
        if (key instanceof DSAPrivateKey) {
            return new BCDSAPrivateKey((DSAPrivateKey) key);
        }
        throw new InvalidKeyException("key type unknown");
    }

    @Override // com.android.org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo) throws IOException {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if (DSAUtil.isDsaOid(algOid)) {
            return new BCDSAPrivateKey(keyInfo);
        }
        throw new IOException("algorithm identifier " + algOid + " in key not recognised");
    }

    @Override // com.android.org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo) throws IOException {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if (DSAUtil.isDsaOid(algOid)) {
            return new BCDSAPublicKey(keyInfo);
        }
        throw new IOException("algorithm identifier " + algOid + " in key not recognised");
    }

    /* access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        return keySpec instanceof DSAPrivateKeySpec ? new BCDSAPrivateKey((DSAPrivateKeySpec) keySpec) : super.engineGeneratePrivate(keySpec);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi, com.android.org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof DSAPublicKeySpec)) {
            return super.engineGeneratePublic(keySpec);
        }
        try {
            return new BCDSAPublicKey((DSAPublicKeySpec) keySpec);
        } catch (Exception e) {
            throw new InvalidKeySpecException("invalid KeySpec: " + e.getMessage()) {
                /* class com.android.org.bouncycastle.jcajce.provider.asymmetric.dsa.KeyFactorySpi.AnonymousClass1 */

                public Throwable getCause() {
                    return e;
                }
            };
        }
    }
}
