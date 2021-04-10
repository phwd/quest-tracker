package com.android.org.bouncycastle.jcajce.provider.asymmetric.util;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import com.android.org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import com.android.org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.android.org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import com.android.org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.crypto.DerivationFunction;
import com.android.org.bouncycastle.crypto.params.DESParameters;
import com.android.org.bouncycastle.crypto.params.KDFParameters;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Integers;
import com.android.org.bouncycastle.util.Strings;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

public abstract class BaseAgreementSpi extends KeyAgreementSpi {
    private static final Map<String, ASN1ObjectIdentifier> defaultOids = new HashMap();
    private static final Hashtable des = new Hashtable();
    private static final Map<String, Integer> keySizes = new HashMap();
    private static final Map<String, String> nameTable = new HashMap();
    private static final Hashtable oids = new Hashtable();
    protected final String kaAlgorithm;
    protected final DerivationFunction kdf;
    protected byte[] ukmParameters;

    /* access modifiers changed from: protected */
    public abstract byte[] calcSecret();

    static {
        Integer i64 = Integers.valueOf(64);
        Integer i128 = Integers.valueOf(128);
        Integer i192 = Integers.valueOf(192);
        Integer i256 = Integers.valueOf(256);
        keySizes.put("DES", i64);
        keySizes.put("DESEDE", i192);
        keySizes.put("BLOWFISH", i128);
        keySizes.put("AES", i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_ECB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_ECB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_ECB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CFB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CFB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CFB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_OFB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_OFB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_OFB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_wrap.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_wrap.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CCM.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CCM.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_GCM.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_GCM.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_GCM.getId(), i256);
        keySizes.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), i128);
        keySizes.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), i192);
        keySizes.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), i256);
        keySizes.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), i128);
        keySizes.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), i192);
        keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), i192);
        keySizes.put(OIWObjectIdentifiers.desCBC.getId(), i64);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), Integers.valueOf(160));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), i256);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), Integers.valueOf(384));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), Integers.valueOf(512));
        defaultOids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        defaultOids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        defaultOids.put("CAMELLIA", NTTObjectIdentifiers.id_camellia256_cbc);
        defaultOids.put("SEED", KISAObjectIdentifiers.id_seedCBC);
        defaultOids.put("DES", OIWObjectIdentifiers.desCBC);
        nameTable.put(MiscObjectIdentifiers.cast5CBC.getId(), "CAST5");
        nameTable.put(MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC.getId(), "IDEA");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_ECB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CBC.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CFB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_OFB.getId(), "Blowfish");
        nameTable.put(OIWObjectIdentifiers.desECB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desOFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desEDE.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap.getId(), "RC2");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), "HmacSHA1");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA224.getId(), "HmacSHA224");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), "HmacSHA256");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), "HmacSHA384");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), "HmacSHA512");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), "Camellia");
        nameTable.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedCBC.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedMAC.getId(), "SEED");
        nameTable.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        oids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        oids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        oids.put("DES", OIWObjectIdentifiers.desCBC);
        des.put("DES", "DES");
        des.put("DESEDE", "DES");
        des.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DES");
    }

    public BaseAgreementSpi(String kaAlgorithm2, DerivationFunction kdf2) {
        this.kaAlgorithm = kaAlgorithm2;
        this.kdf = kdf2;
    }

    protected static String getAlgorithm(String algDetails) {
        if (algDetails.indexOf(91) > 0) {
            return algDetails.substring(0, algDetails.indexOf(91));
        }
        if (algDetails.startsWith(NISTObjectIdentifiers.aes.getId())) {
            return "AES";
        }
        String name = nameTable.get(Strings.toUpperCase(algDetails));
        if (name != null) {
            return name;
        }
        return algDetails;
    }

    protected static int getKeySize(String algDetails) {
        if (algDetails.indexOf(91) > 0) {
            return Integer.parseInt(algDetails.substring(algDetails.indexOf(91) + 1, algDetails.indexOf(93)));
        }
        String algKey = Strings.toUpperCase(algDetails);
        if (!keySizes.containsKey(algKey)) {
            return -1;
        }
        return keySizes.get(algKey).intValue();
    }

    protected static byte[] trimZeroes(byte[] secret) {
        if (secret[0] != 0) {
            return secret;
        }
        int ind = 0;
        while (ind < secret.length && secret[ind] == 0) {
            ind++;
        }
        byte[] rv = new byte[(secret.length - ind)];
        System.arraycopy(secret, ind, rv, 0, rv.length);
        return rv;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.kdf == null) {
            return calcSecret();
        }
        byte[] secret = calcSecret();
        try {
            return getSharedSecretBytes(secret, null, secret.length * 8);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] sharedSecret, int offset) throws IllegalStateException, ShortBufferException {
        byte[] secret = engineGenerateSecret();
        if (sharedSecret.length - offset >= secret.length) {
            System.arraycopy(secret, 0, sharedSecret, offset, secret.length);
            return secret.length;
        }
        throw new ShortBufferException(this.kaAlgorithm + " key agreement: need " + secret.length + " bytes");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public SecretKey engineGenerateSecret(String algorithm) throws NoSuchAlgorithmException {
        String algKey = Strings.toUpperCase(algorithm);
        String oidAlgorithm = algorithm;
        if (oids.containsKey(algKey)) {
            oidAlgorithm = ((ASN1ObjectIdentifier) oids.get(algKey)).getId();
        }
        byte[] secret = getSharedSecretBytes(calcSecret(), oidAlgorithm, getKeySize(oidAlgorithm));
        String algName = getAlgorithm(algorithm);
        if (des.containsKey(algName)) {
            DESParameters.setOddParity(secret);
        }
        return new SecretKeySpec(secret, algName);
    }

    private byte[] getSharedSecretBytes(byte[] secret, String oidAlgorithm, int keySize) throws NoSuchAlgorithmException {
        if (this.kdf != null) {
            if (keySize >= 0) {
                byte[] keyBytes = new byte[(keySize / 8)];
                this.kdf.init(new KDFParameters(secret, this.ukmParameters));
                this.kdf.generateBytes(keyBytes, 0, keyBytes.length);
                Arrays.clear(secret);
                return keyBytes;
            }
            throw new NoSuchAlgorithmException("unknown algorithm encountered: " + oidAlgorithm);
        } else if (keySize <= 0) {
            return secret;
        } else {
            byte[] keyBytes2 = new byte[(keySize / 8)];
            System.arraycopy(secret, 0, keyBytes2, 0, keyBytes2.length);
            Arrays.clear(secret);
            return keyBytes2;
        }
    }
}
