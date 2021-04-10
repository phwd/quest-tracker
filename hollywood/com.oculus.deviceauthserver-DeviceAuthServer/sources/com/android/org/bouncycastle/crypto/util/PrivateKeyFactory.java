package com.android.org.bouncycastle.crypto.util;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.pkcs.DHParameter;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.android.org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import com.android.org.bouncycastle.asn1.sec.ECPrivateKey;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.DSAParameter;
import com.android.org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x9.ECNamedCurveTable;
import com.android.org.bouncycastle.asn1.x9.X962Parameters;
import com.android.org.bouncycastle.asn1.x9.X9ECParameters;
import com.android.org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.android.org.bouncycastle.crypto.ec.CustomNamedCurves;
import com.android.org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.DSAParameters;
import com.android.org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.ECDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECNamedDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class PrivateKeyFactory {
    public static AsymmetricKeyParameter createKey(byte[] privateKeyInfoData) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(privateKeyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inStr).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo keyInfo) throws IOException {
        ECDomainParameters dParams;
        X9ECParameters x9;
        AlgorithmIdentifier algId = keyInfo.getPrivateKeyAlgorithm();
        ASN1ObjectIdentifier algOID = algId.getAlgorithm();
        if (algOID.equals(PKCSObjectIdentifiers.rsaEncryption) || algOID.equals(PKCSObjectIdentifiers.id_RSASSA_PSS) || algOID.equals(X509ObjectIdentifiers.id_ea_rsa)) {
            RSAPrivateKey keyStructure = RSAPrivateKey.getInstance(keyInfo.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(keyStructure.getModulus(), keyStructure.getPublicExponent(), keyStructure.getPrivateExponent(), keyStructure.getPrime1(), keyStructure.getPrime2(), keyStructure.getExponent1(), keyStructure.getExponent2(), keyStructure.getCoefficient());
        } else if (algOID.equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
            DHParameter params = DHParameter.getInstance(algId.getParameters());
            ASN1Integer derX = (ASN1Integer) keyInfo.parsePrivateKey();
            BigInteger lVal = params.getL();
            return new DHPrivateKeyParameters(derX.getValue(), new DHParameters(params.getP(), params.getG(), null, lVal == null ? 0 : lVal.intValue()));
        } else if (algOID.equals(X9ObjectIdentifiers.id_dsa)) {
            ASN1Integer derX2 = (ASN1Integer) keyInfo.parsePrivateKey();
            ASN1Encodable de = algId.getParameters();
            DSAParameters parameters = null;
            if (de != null) {
                DSAParameter params2 = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params2.getP(), params2.getQ(), params2.getG());
            }
            return new DSAPrivateKeyParameters(derX2.getValue(), parameters);
        } else if (algOID.equals(X9ObjectIdentifiers.id_ecPublicKey)) {
            X962Parameters params3 = new X962Parameters((ASN1Primitive) algId.getParameters());
            if (params3.isNamedCurve()) {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) params3.getParameters();
                X9ECParameters x92 = CustomNamedCurves.getByOID(oid);
                if (x92 == null) {
                    x9 = ECNamedCurveTable.getByOID(oid);
                } else {
                    x9 = x92;
                }
                dParams = new ECNamedDomainParameters(oid, x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            } else {
                X9ECParameters x93 = X9ECParameters.getInstance(params3.getParameters());
                dParams = new ECDomainParameters(x93.getCurve(), x93.getG(), x93.getN(), x93.getH(), x93.getSeed());
            }
            return new ECPrivateKeyParameters(ECPrivateKey.getInstance(keyInfo.parsePrivateKey()).getKey(), dParams);
        } else {
            throw new RuntimeException("algorithm identifier in private key not recognised");
        }
    }

    private static byte[] getRawKey(PrivateKeyInfo keyInfo, int expectedSize) throws IOException {
        byte[] result = ASN1OctetString.getInstance(keyInfo.parsePrivateKey()).getOctets();
        if (expectedSize == result.length) {
            return result;
        }
        throw new RuntimeException("private key encoding has incorrect length");
    }
}
