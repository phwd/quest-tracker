package com.android.org.bouncycastle.jcajce.provider.symmetric;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERNull;
import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.EncryptionScheme;
import com.android.org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import com.android.org.bouncycastle.asn1.pkcs.PBES2Parameters;
import com.android.org.bouncycastle.asn1.pkcs.PBKDF2Params;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import com.android.org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import com.android.org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import com.android.org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Enumeration;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;

public class PBES2AlgorithmParameters {
    private PBES2AlgorithmParameters() {
    }

    private static abstract class BasePBEWithHmacAlgorithmParameters extends BaseAlgorithmParameters {
        private final ASN1ObjectIdentifier cipherAlgorithm;
        private final String cipherAlgorithmShortName;
        private final AlgorithmIdentifier kdf;
        private final String kdfShortName;
        private final int keySize;
        private PBES2Parameters params;

        private BasePBEWithHmacAlgorithmParameters(ASN1ObjectIdentifier kdf2, String kdfShortName2, int keySize2, ASN1ObjectIdentifier cipherAlgorithm2, String cipherAlgorithmShortName2) {
            this.kdf = new AlgorithmIdentifier(kdf2, DERNull.INSTANCE);
            this.kdfShortName = kdfShortName2;
            this.keySize = keySize2;
            this.cipherAlgorithm = cipherAlgorithm2;
            this.cipherAlgorithmShortName = cipherAlgorithmShortName2;
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() {
            try {
                return new DERSequence(new ASN1Encodable[]{PKCSObjectIdentifiers.id_PBES2, this.params}).getEncoded();
            } catch (IOException e) {
                throw new RuntimeException("Unable to read PBES2 parameters: " + e.toString());
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String format) {
            if (isASN1FormatString(format)) {
                return engineGetEncoded();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class parameterSpec) throws InvalidParameterSpecException {
            if (parameterSpec == PBEParameterSpec.class) {
                PBKDF2Params pbeParamSpec = (PBKDF2Params) this.params.getKeyDerivationFunc().getParameters();
                return PBES2AlgorithmParameters.createPBEParameterSpec(pbeParamSpec.getSalt(), pbeParamSpec.getIterationCount().intValue(), ((ASN1OctetString) this.params.getEncryptionScheme().getParameters()).getOctets());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBES2 parameters object.");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec paramSpec) throws InvalidParameterSpecException {
            if (paramSpec instanceof PBEParameterSpec) {
                PBEParameterSpec pbeSpec = (PBEParameterSpec) paramSpec;
                AlgorithmParameterSpec algorithmParameterSpec = PBE.Util.getParameterSpecFromPBEParameterSpec(pbeSpec);
                if (algorithmParameterSpec instanceof IvParameterSpec) {
                    this.params = new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(pbeSpec.getSalt(), pbeSpec.getIterationCount(), this.keySize, this.kdf)), new EncryptionScheme(this.cipherAlgorithm, new DEROctetString(((IvParameterSpec) algorithmParameterSpec).getIV())));
                    return;
                }
                throw new IllegalArgumentException("Expecting an IV as a parameter");
            }
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise PBES2 algorithm parameters");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] params2) throws IOException {
            Enumeration seqObjects = ASN1Sequence.getInstance(ASN1Primitive.fromByteArray(params2)).getObjects();
            if (((ASN1ObjectIdentifier) seqObjects.nextElement()).getId().equals(PKCSObjectIdentifiers.id_PBES2.getId())) {
                this.params = PBES2Parameters.getInstance(seqObjects.nextElement());
                return;
            }
            throw new IllegalArgumentException("Invalid PBES2 parameters");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] params2, String format) throws IOException {
            if (isASN1FormatString(format)) {
                engineInit(params2);
                return;
            }
            throw new IOException("Unknown parameters format in PBES2 parameters object");
        }

        /* access modifiers changed from: protected */
        public String engineToString() {
            return "PBES2 " + this.kdfShortName + " " + this.cipherAlgorithmShortName + " Parameters";
        }
    }

    public static class PBEWithHmacSHA1AES128AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA1AES128AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA1, "HmacSHA1", 16, NISTObjectIdentifiers.id_aes128_CBC, "AES128");
        }
    }

    public static class PBEWithHmacSHA224AES128AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA224AES128AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA224, "HmacSHA224", 16, NISTObjectIdentifiers.id_aes128_CBC, "AES128");
        }
    }

    public static class PBEWithHmacSHA256AES128AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA256AES128AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA256, "HmacSHA256", 16, NISTObjectIdentifiers.id_aes128_CBC, "AES128");
        }
    }

    public static class PBEWithHmacSHA384AES128AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA384AES128AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA384, "HmacSHA384", 16, NISTObjectIdentifiers.id_aes128_CBC, "AES128");
        }
    }

    public static class PBEWithHmacSHA512AES128AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA512AES128AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA512, "HmacSHA512", 16, NISTObjectIdentifiers.id_aes128_CBC, "AES128");
        }
    }

    public static class PBEWithHmacSHA1AES256AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA1AES256AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA1, "HmacSHA1", 32, NISTObjectIdentifiers.id_aes256_CBC, "AES256");
        }
    }

    public static class PBEWithHmacSHA224AES256AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA224AES256AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA224, "HmacSHA224", 32, NISTObjectIdentifiers.id_aes256_CBC, "AES256");
        }
    }

    public static class PBEWithHmacSHA256AES256AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA256AES256AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA256, "HmacSHA256", 32, NISTObjectIdentifiers.id_aes256_CBC, "AES256");
        }
    }

    public static class PBEWithHmacSHA384AES256AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA384AES256AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA384, "HmacSHA384", 32, NISTObjectIdentifiers.id_aes256_CBC, "AES256");
        }
    }

    public static class PBEWithHmacSHA512AES256AlgorithmParameters extends BasePBEWithHmacAlgorithmParameters {
        public PBEWithHmacSHA512AES256AlgorithmParameters() {
            super(PKCSObjectIdentifiers.id_hmacWithSHA512, "HmacSHA512", 32, NISTObjectIdentifiers.id_aes256_CBC, "AES256");
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = PBES2AlgorithmParameters.class.getName();

        @Override // com.android.org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider provider) {
            int[] keySizes = {128, 256};
            int[] shaVariants = {1, 224, 256, 384, 512};
            for (int keySize : keySizes) {
                for (int shaVariant : shaVariants) {
                    provider.addAlgorithm("AlgorithmParameters.PBEWithHmacSHA" + shaVariant + "AndAES_" + keySize, PREFIX + "$PBEWithHmacSHA" + shaVariant + "AES" + keySize + "AlgorithmParameters");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static PBEParameterSpec createPBEParameterSpec(byte[] salt, int iterationCount, byte[] iv) {
        try {
            return (PBEParameterSpec) PBES2AlgorithmParameters.class.getClassLoader().loadClass("javax.crypto.spec.PBEParameterSpec").getConstructor(byte[].class, Integer.TYPE, AlgorithmParameterSpec.class).newInstance(salt, Integer.valueOf(iterationCount), new IvParameterSpec(iv));
        } catch (Exception e) {
            throw new IllegalStateException("Requested creation PBES2 parameters in an SDK that doesn't support them", e);
        }
    }
}
