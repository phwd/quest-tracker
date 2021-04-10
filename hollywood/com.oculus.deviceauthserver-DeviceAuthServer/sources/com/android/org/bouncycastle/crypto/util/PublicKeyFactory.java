package com.android.org.bouncycastle.crypto.util;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.DHParameter;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.RSAPublicKey;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.DSAParameter;
import com.android.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.android.org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x9.DHPublicKey;
import com.android.org.bouncycastle.asn1.x9.DomainParameters;
import com.android.org.bouncycastle.asn1.x9.ECNamedCurveTable;
import com.android.org.bouncycastle.asn1.x9.ValidationParams;
import com.android.org.bouncycastle.asn1.x9.X962Parameters;
import com.android.org.bouncycastle.asn1.x9.X9ECParameters;
import com.android.org.bouncycastle.asn1.x9.X9ECPoint;
import com.android.org.bouncycastle.asn1.x9.X9IntegerConverter;
import com.android.org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.android.org.bouncycastle.crypto.ec.CustomNamedCurves;
import com.android.org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.crypto.params.DHPublicKeyParameters;
import com.android.org.bouncycastle.crypto.params.DHValidationParameters;
import com.android.org.bouncycastle.crypto.params.DSAParameters;
import com.android.org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import com.android.org.bouncycastle.crypto.params.ECDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECNamedDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECPublicKeyParameters;
import com.android.org.bouncycastle.crypto.params.RSAKeyParameters;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PublicKeyFactory {
    private static Map converters = new HashMap();

    static {
        converters.put(PKCSObjectIdentifiers.rsaEncryption, new RSAConverter());
        converters.put(PKCSObjectIdentifiers.id_RSASSA_PSS, new RSAConverter());
        converters.put(X509ObjectIdentifiers.id_ea_rsa, new RSAConverter());
        converters.put(X9ObjectIdentifiers.dhpublicnumber, new DHPublicNumberConverter());
        converters.put(PKCSObjectIdentifiers.dhKeyAgreement, new DHAgreementConverter());
        converters.put(X9ObjectIdentifiers.id_dsa, new DSAConverter());
        converters.put(OIWObjectIdentifiers.dsaWithSHA1, new DSAConverter());
        converters.put(X9ObjectIdentifiers.id_ecPublicKey, new ECConverter());
    }

    public static AsymmetricKeyParameter createKey(byte[] keyInfoData) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(keyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inStr).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo keyInfo) throws IOException {
        return createKey(keyInfo, null);
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo keyInfo, Object defaultParams) throws IOException {
        AlgorithmIdentifier algId = keyInfo.getAlgorithm();
        SubjectPublicKeyInfoConverter converter = (SubjectPublicKeyInfoConverter) converters.get(algId.getAlgorithm());
        if (converter != null) {
            return converter.getPublicKeyParameters(keyInfo, defaultParams);
        }
        throw new IOException("algorithm identifier in public key not recognised: " + algId.getAlgorithm());
    }

    /* access modifiers changed from: private */
    public static abstract class SubjectPublicKeyInfoConverter {
        /* access modifiers changed from: package-private */
        public abstract AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException;

        private SubjectPublicKeyInfoConverter() {
        }
    }

    private static class RSAConverter extends SubjectPublicKeyInfoConverter {
        private RSAConverter() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.android.org.bouncycastle.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        public AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo keyInfo, Object defaultParams) throws IOException {
            RSAPublicKey pubKey = RSAPublicKey.getInstance(keyInfo.parsePublicKey());
            return new RSAKeyParameters(false, pubKey.getModulus(), pubKey.getPublicExponent());
        }
    }

    private static class DHPublicNumberConverter extends SubjectPublicKeyInfoConverter {
        private DHPublicNumberConverter() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.android.org.bouncycastle.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        public AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo keyInfo, Object defaultParams) throws IOException {
            BigInteger j;
            BigInteger y = DHPublicKey.getInstance(keyInfo.parsePublicKey()).getY();
            DomainParameters dhParams = DomainParameters.getInstance(keyInfo.getAlgorithm().getParameters());
            BigInteger p = dhParams.getP();
            BigInteger g = dhParams.getG();
            BigInteger q = dhParams.getQ();
            if (dhParams.getJ() != null) {
                j = dhParams.getJ();
            } else {
                j = null;
            }
            ValidationParams dhValidationParms = dhParams.getValidationParams();
            return new DHPublicKeyParameters(y, new DHParameters(p, g, q, j, dhValidationParms != null ? new DHValidationParameters(dhValidationParms.getSeed(), dhValidationParms.getPgenCounter().intValue()) : null));
        }
    }

    private static class DHAgreementConverter extends SubjectPublicKeyInfoConverter {
        private DHAgreementConverter() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.android.org.bouncycastle.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        public AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo keyInfo, Object defaultParams) throws IOException {
            DHParameter params = DHParameter.getInstance(keyInfo.getAlgorithm().getParameters());
            ASN1Integer derY = (ASN1Integer) keyInfo.parsePublicKey();
            BigInteger lVal = params.getL();
            return new DHPublicKeyParameters(derY.getValue(), new DHParameters(params.getP(), params.getG(), null, lVal == null ? 0 : lVal.intValue()));
        }
    }

    private static class DSAConverter extends SubjectPublicKeyInfoConverter {
        private DSAConverter() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.android.org.bouncycastle.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        public AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo keyInfo, Object defaultParams) throws IOException {
            ASN1Integer derY = (ASN1Integer) keyInfo.parsePublicKey();
            ASN1Encodable de = keyInfo.getAlgorithm().getParameters();
            DSAParameters parameters = null;
            if (de != null) {
                DSAParameter params = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params.getP(), params.getQ(), params.getG());
            }
            return new DSAPublicKeyParameters(derY.getValue(), parameters);
        }
    }

    private static class ECConverter extends SubjectPublicKeyInfoConverter {
        private ECConverter() {
            super();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.android.org.bouncycastle.crypto.util.PublicKeyFactory.SubjectPublicKeyInfoConverter
        public AsymmetricKeyParameter getPublicKeyParameters(SubjectPublicKeyInfo keyInfo, Object defaultParams) {
            ECDomainParameters dParams;
            X9ECParameters x9;
            X962Parameters params = X962Parameters.getInstance(keyInfo.getAlgorithm().getParameters());
            if (params.isNamedCurve()) {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) params.getParameters();
                X9ECParameters x92 = CustomNamedCurves.getByOID(oid);
                if (x92 == null) {
                    x9 = ECNamedCurveTable.getByOID(oid);
                } else {
                    x9 = x92;
                }
                dParams = new ECNamedDomainParameters(oid, x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            } else if (params.isImplicitlyCA()) {
                dParams = (ECDomainParameters) defaultParams;
            } else {
                X9ECParameters x93 = X9ECParameters.getInstance(params.getParameters());
                dParams = new ECDomainParameters(x93.getCurve(), x93.getG(), x93.getN(), x93.getH(), x93.getSeed());
            }
            byte[] data = keyInfo.getPublicKeyData().getBytes();
            ASN1OctetString key = new DEROctetString(data);
            if (data[0] == 4 && data[1] == data.length - 2 && ((data[2] == 2 || data[2] == 3) && new X9IntegerConverter().getByteLength(dParams.getCurve()) >= data.length - 3)) {
                try {
                    key = (ASN1OctetString) ASN1Primitive.fromByteArray(data);
                } catch (IOException e) {
                    throw new IllegalArgumentException("error recovering public key");
                }
            }
            return new ECPublicKeyParameters(new X9ECPoint(dParams.getCurve(), key).getPoint(), dParams);
        }
    }

    private static byte[] getRawKey(SubjectPublicKeyInfo keyInfo, Object defaultParams, int expectedSize) {
        byte[] result = keyInfo.getPublicKeyData().getOctets();
        if (expectedSize == result.length) {
            return result;
        }
        throw new RuntimeException("public key encoding has incorrect length");
    }
}
