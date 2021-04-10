package com.android.org.bouncycastle.jcajce.provider.asymmetric.ec;

import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DSAExt;
import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.digests.AndroidDigestFactory;
import com.android.org.bouncycastle.crypto.digests.NullDigest;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.crypto.signers.DSAEncoding;
import com.android.org.bouncycastle.crypto.signers.ECDSASigner;
import com.android.org.bouncycastle.crypto.signers.StandardDSAEncoding;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.DSABase;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SignatureSpi extends DSABase {
    SignatureSpi(Digest digest, DSAExt signer, DSAEncoding encoding) {
        super(digest, signer, encoding);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        CipherParameters param = ECUtils.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, param);
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters param = ECUtil.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
        if (this.appRandom != null) {
            this.signer.init(true, new ParametersWithRandom(param, this.appRandom));
        } else {
            this.signer.init(true, param);
        }
    }

    public static class ecDSA extends SignatureSpi {
        public ecDSA() {
            super(AndroidDigestFactory.getSHA1(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }

    public static class ecDSAnone extends SignatureSpi {
        public ecDSAnone() {
            super(new NullDigest(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }

    public static class ecDSA224 extends SignatureSpi {
        public ecDSA224() {
            super(AndroidDigestFactory.getSHA224(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }

    public static class ecDSA256 extends SignatureSpi {
        public ecDSA256() {
            super(AndroidDigestFactory.getSHA256(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }

    public static class ecDSA384 extends SignatureSpi {
        public ecDSA384() {
            super(AndroidDigestFactory.getSHA384(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }

    public static class ecDSA512 extends SignatureSpi {
        public ecDSA512() {
            super(AndroidDigestFactory.getSHA512(), new ECDSASigner(), StandardDSAEncoding.INSTANCE);
        }
    }
}
