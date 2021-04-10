package com.android.org.bouncycastle.jcajce.provider.symmetric.util;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.PBEParametersGenerator;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import java.security.spec.KeySpec;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;

public class BCPBEKey implements PBEKey {
    String algorithm;
    int digest;
    int ivSize;
    int keySize;
    ASN1ObjectIdentifier oid;
    CipherParameters param;
    PBEKeySpec pbeKeySpec;
    boolean tryWrong = false;
    int type;

    public BCPBEKey(String algorithm2, ASN1ObjectIdentifier oid2, int type2, int digest2, int keySize2, int ivSize2, PBEKeySpec pbeKeySpec2, CipherParameters param2) {
        this.algorithm = algorithm2;
        this.oid = oid2;
        this.type = type2;
        this.digest = digest2;
        this.keySize = keySize2;
        this.ivSize = ivSize2;
        this.pbeKeySpec = pbeKeySpec2;
        this.param = param2;
    }

    public BCPBEKey(String algName, KeySpec pbeSpec, CipherParameters param2) {
        this.algorithm = algName;
        this.param = param2;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "RAW";
    }

    public byte[] getEncoded() {
        KeyParameter kParam;
        CipherParameters cipherParameters = this.param;
        if (cipherParameters != null) {
            if (cipherParameters instanceof ParametersWithIV) {
                kParam = (KeyParameter) ((ParametersWithIV) cipherParameters).getParameters();
            } else {
                kParam = (KeyParameter) cipherParameters;
            }
            return kParam.getKey();
        }
        int i = this.type;
        if (i == 2) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(this.pbeKeySpec.getPassword());
        }
        if (i == 5) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.pbeKeySpec.getPassword());
        }
        return PBEParametersGenerator.PKCS5PasswordToBytes(this.pbeKeySpec.getPassword());
    }

    /* access modifiers changed from: package-private */
    public int getType() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public int getDigest() {
        return this.digest;
    }

    /* access modifiers changed from: package-private */
    public int getKeySize() {
        return this.keySize;
    }

    public int getIvSize() {
        return this.ivSize;
    }

    public CipherParameters getParam() {
        return this.param;
    }

    public char[] getPassword() {
        return this.pbeKeySpec.getPassword();
    }

    public byte[] getSalt() {
        return this.pbeKeySpec.getSalt();
    }

    public int getIterationCount() {
        return this.pbeKeySpec.getIterationCount();
    }

    public ASN1ObjectIdentifier getOID() {
        return this.oid;
    }

    public void setTryWrongPKCS12Zero(boolean tryWrong2) {
        this.tryWrong = tryWrong2;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTryWrongPKCS12() {
        return this.tryWrong;
    }
}
