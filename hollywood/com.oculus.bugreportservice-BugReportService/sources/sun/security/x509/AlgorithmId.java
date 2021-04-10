package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class AlgorithmId implements Serializable, DerEncoder {
    public static final ObjectIdentifier AES_oid = oid(2, 16, 840, 1, 101, 3, 4, 1);
    private static final int[] DH_PKIX_data = {1, 2, 840, 10046, 2, 1};
    public static final ObjectIdentifier DH_PKIX_oid = ObjectIdentifier.newInternal(DH_PKIX_data);
    private static final int[] DH_data = {1, 2, 840, 113549, 1, 3, 1};
    public static final ObjectIdentifier DH_oid = ObjectIdentifier.newInternal(DH_data);
    private static final int[] DSA_OIW_data = {1, 3, 14, 3, 2, 12};
    public static final ObjectIdentifier DSA_OIW_oid = ObjectIdentifier.newInternal(DSA_OIW_data);
    private static final int[] DSA_PKIX_data = {1, 2, 840, 10040, 4, 1};
    public static final ObjectIdentifier DSA_oid = ObjectIdentifier.newInternal(DSA_PKIX_data);
    public static final ObjectIdentifier ECDH_oid = oid(1, 3, 132, 1, 12);
    public static final ObjectIdentifier EC_oid = oid(1, 2, 840, 10045, 2, 1);
    public static final ObjectIdentifier MD2_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 2, 2});
    public static final ObjectIdentifier MD5_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 2, 5});
    private static final int[] RSAEncryption_data = {1, 2, 840, 113549, 1, 1, 1};
    public static final ObjectIdentifier RSAEncryption_oid = ObjectIdentifier.newInternal(RSAEncryption_data);
    private static final int[] RSA_data = {2, 5, 8, 1, 1};
    public static final ObjectIdentifier RSA_oid = ObjectIdentifier.newInternal(RSA_data);
    public static final ObjectIdentifier SHA224_oid = ObjectIdentifier.newInternal(new int[]{2, 16, 840, 1, 101, 3, 4, 2, 4});
    public static final ObjectIdentifier SHA256_oid = ObjectIdentifier.newInternal(new int[]{2, 16, 840, 1, 101, 3, 4, 2, 1});
    public static final ObjectIdentifier SHA384_oid = ObjectIdentifier.newInternal(new int[]{2, 16, 840, 1, 101, 3, 4, 2, 2});
    public static final ObjectIdentifier SHA512_oid = ObjectIdentifier.newInternal(new int[]{2, 16, 840, 1, 101, 3, 4, 2, 3});
    public static final ObjectIdentifier SHA_oid = ObjectIdentifier.newInternal(new int[]{1, 3, 14, 3, 2, 26});
    private static final int[] dsaWithSHA1_PKIX_data = {1, 2, 840, 10040, 4, 3};
    private static int initOidTableVersion = -1;
    private static final int[] md2WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 2};
    public static final ObjectIdentifier md2WithRSAEncryption_oid = ObjectIdentifier.newInternal(md2WithRSAEncryption_data);
    private static final int[] md5WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 4};
    public static final ObjectIdentifier md5WithRSAEncryption_oid = ObjectIdentifier.newInternal(md5WithRSAEncryption_data);
    private static final Map nameTable = new HashMap();
    private static final Map oidTable = new HashMap(1);
    public static final ObjectIdentifier pbeWithMD5AndDES_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 5, 3});
    public static final ObjectIdentifier pbeWithMD5AndRC2_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 5, 6});
    public static final ObjectIdentifier pbeWithSHA1AndDES_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 5, 10});
    public static ObjectIdentifier pbeWithSHA1AndDESede_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 12, 1, 3});
    public static ObjectIdentifier pbeWithSHA1AndRC2_40_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 12, 1, 6});
    public static final ObjectIdentifier pbeWithSHA1AndRC2_oid = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 5, 11});
    private static final long serialVersionUID = 7205873507486557157L;
    private static final int[] sha1WithDSA_OIW_data = {1, 3, 14, 3, 2, 27};
    public static final ObjectIdentifier sha1WithDSA_OIW_oid = ObjectIdentifier.newInternal(sha1WithDSA_OIW_data);
    public static final ObjectIdentifier sha1WithDSA_oid = ObjectIdentifier.newInternal(dsaWithSHA1_PKIX_data);
    public static final ObjectIdentifier sha1WithECDSA_oid = oid(1, 2, 840, 10045, 4, 1);
    private static final int[] sha1WithRSAEncryption_OIW_data = {1, 3, 14, 3, 2, 29};
    public static final ObjectIdentifier sha1WithRSAEncryption_OIW_oid = ObjectIdentifier.newInternal(sha1WithRSAEncryption_OIW_data);
    private static final int[] sha1WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 5};
    public static final ObjectIdentifier sha1WithRSAEncryption_oid = ObjectIdentifier.newInternal(sha1WithRSAEncryption_data);
    public static final ObjectIdentifier sha224WithDSA_oid = oid(2, 16, 840, 1, 101, 3, 4, 3, 1);
    public static final ObjectIdentifier sha224WithECDSA_oid = oid(1, 2, 840, 10045, 4, 3, 1);
    private static final int[] sha224WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 14};
    public static final ObjectIdentifier sha224WithRSAEncryption_oid = ObjectIdentifier.newInternal(sha224WithRSAEncryption_data);
    public static final ObjectIdentifier sha256WithDSA_oid = oid(2, 16, 840, 1, 101, 3, 4, 3, 2);
    public static final ObjectIdentifier sha256WithECDSA_oid = oid(1, 2, 840, 10045, 4, 3, 2);
    private static final int[] sha256WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 11};
    public static final ObjectIdentifier sha256WithRSAEncryption_oid = ObjectIdentifier.newInternal(sha256WithRSAEncryption_data);
    public static final ObjectIdentifier sha384WithECDSA_oid = oid(1, 2, 840, 10045, 4, 3, 3);
    private static final int[] sha384WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 12};
    public static final ObjectIdentifier sha384WithRSAEncryption_oid = ObjectIdentifier.newInternal(sha384WithRSAEncryption_data);
    public static final ObjectIdentifier sha512WithECDSA_oid = oid(1, 2, 840, 10045, 4, 3, 4);
    private static final int[] sha512WithRSAEncryption_data = {1, 2, 840, 113549, 1, 1, 13};
    public static final ObjectIdentifier sha512WithRSAEncryption_oid = ObjectIdentifier.newInternal(sha512WithRSAEncryption_data);
    private static final int[] shaWithDSA_OIW_data = {1, 3, 14, 3, 2, 13};
    public static final ObjectIdentifier shaWithDSA_OIW_oid = ObjectIdentifier.newInternal(shaWithDSA_OIW_data);
    public static final ObjectIdentifier specifiedWithECDSA_oid = oid(1, 2, 840, 10045, 4, 3);
    private AlgorithmParameters algParams;
    private ObjectIdentifier algid;
    private boolean constructedFromDer = true;
    protected DerValue params;

    public AlgorithmId() {
    }

    private AlgorithmId(ObjectIdentifier objectIdentifier, DerValue derValue) {
        this.algid = objectIdentifier;
        this.params = derValue;
        if (this.params != null) {
            decodeParams();
        }
    }

    /* access modifiers changed from: protected */
    public void decodeParams() {
        try {
            this.algParams = AlgorithmParameters.getInstance(this.algid.toString());
            this.algParams.init(this.params.toByteArray());
        } catch (NoSuchAlgorithmException unused) {
            this.algParams = null;
        }
    }

    public final void encode(DerOutputStream derOutputStream) {
        derEncode(derOutputStream);
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream.putOID(this.algid);
        if (!this.constructedFromDer) {
            AlgorithmParameters algorithmParameters = this.algParams;
            if (algorithmParameters != null) {
                this.params = new DerValue(algorithmParameters.getEncoded());
            } else {
                this.params = null;
            }
        }
        DerValue derValue = this.params;
        if (derValue == null) {
            derOutputStream.putNull();
        } else {
            derOutputStream.putDerValue(derValue);
        }
        derOutputStream2.write((byte) 48, derOutputStream);
        outputStream.write(derOutputStream2.toByteArray());
    }

    public final ObjectIdentifier getOID() {
        return this.algid;
    }

    public String getName() {
        String str;
        String str2 = (String) nameTable.get(this.algid);
        if (str2 != null) {
            return str2;
        }
        if (this.params != null && this.algid.equals(specifiedWithECDSA_oid)) {
            try {
                makeSigAlg(parse(new DerValue(getEncodedParams())).getName(), "EC");
            } catch (IOException unused) {
            }
        }
        synchronized (oidTable) {
            reinitializeMappingTableLocked();
            str = (String) nameTable.get(this.algid);
        }
        return str == null ? this.algid.toString() : str;
    }

    public byte[] getEncodedParams() {
        DerValue derValue = this.params;
        if (derValue == null) {
            return null;
        }
        return derValue.toByteArray();
    }

    public boolean equals(AlgorithmId algorithmId) {
        DerValue derValue = this.params;
        return this.algid.equals(algorithmId.algid) && (derValue == null ? algorithmId.params == null : derValue.equals(algorithmId.params));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlgorithmId) {
            return equals((AlgorithmId) obj);
        }
        if (obj instanceof ObjectIdentifier) {
            return equals((ObjectIdentifier) obj);
        }
        return false;
    }

    public final boolean equals(ObjectIdentifier objectIdentifier) {
        return this.algid.equals(objectIdentifier);
    }

    public int hashCode() {
        return (this.algid.toString() + paramsToString()).hashCode();
    }

    /* access modifiers changed from: protected */
    public String paramsToString() {
        if (this.params == null) {
            return "";
        }
        AlgorithmParameters algorithmParameters = this.algParams;
        return algorithmParameters != null ? algorithmParameters.toString() : ", params unparsed";
    }

    public String toString() {
        return getName() + paramsToString();
    }

    public static AlgorithmId parse(DerValue derValue) {
        if (derValue.tag == 48) {
            DerInputStream derInputStream = derValue.toDerInputStream();
            ObjectIdentifier oid = derInputStream.getOID();
            DerValue derValue2 = null;
            if (derInputStream.available() != 0) {
                DerValue derValue3 = derInputStream.getDerValue();
                if (derValue3.tag != 5) {
                    derValue2 = derValue3;
                } else if (derValue3.length() != 0) {
                    throw new IOException("invalid NULL");
                }
                if (derInputStream.available() != 0) {
                    throw new IOException("Invalid AlgorithmIdentifier: extra data");
                }
            }
            return new AlgorithmId(oid, derValue2);
        }
        throw new IOException("algid parse error, not a sequence");
    }

    private static void reinitializeMappingTableLocked() {
        ObjectIdentifier objectIdentifier;
        String property;
        ObjectIdentifier objectIdentifier2;
        int version = Security.getVersion();
        if (initOidTableVersion != version) {
            Provider[] providers = Security.getProviders();
            for (int i = 0; i < providers.length; i++) {
                Enumeration keys = providers[i].keys();
                while (keys.hasMoreElements()) {
                    String str = (String) keys.nextElement();
                    String upperCase = str.toUpperCase(Locale.ENGLISH);
                    if (upperCase.startsWith("ALG.ALIAS")) {
                        int indexOf = upperCase.indexOf("OID.", 0);
                        if (indexOf != -1) {
                            int i2 = indexOf + 4;
                            if (i2 == str.length()) {
                                break;
                            }
                            String substring = str.substring(i2);
                            String property2 = providers[i].getProperty(str);
                            if (property2 != null) {
                                String upperCase2 = property2.toUpperCase(Locale.ENGLISH);
                                try {
                                    objectIdentifier2 = new ObjectIdentifier(substring);
                                } catch (IOException unused) {
                                    objectIdentifier2 = null;
                                }
                                if (objectIdentifier2 != null) {
                                    if (!oidTable.containsKey(upperCase2)) {
                                        oidTable.put(upperCase2, objectIdentifier2);
                                    }
                                    if (!nameTable.containsKey(objectIdentifier2)) {
                                        nameTable.put(objectIdentifier2, upperCase2);
                                    }
                                }
                            }
                        } else {
                            try {
                                objectIdentifier = new ObjectIdentifier(str.substring(str.indexOf(46, 10) + 1));
                            } catch (IOException unused2) {
                                objectIdentifier = null;
                            }
                            if (!(objectIdentifier == null || (property = providers[i].getProperty(str)) == null)) {
                                String upperCase3 = property.toUpperCase(Locale.ENGLISH);
                                if (!oidTable.containsKey(upperCase3)) {
                                    oidTable.put(upperCase3, objectIdentifier);
                                }
                                if (!nameTable.containsKey(objectIdentifier)) {
                                    nameTable.put(objectIdentifier, upperCase3);
                                }
                            }
                        }
                    }
                }
            }
            initOidTableVersion = version;
        }
    }

    private static ObjectIdentifier oid(int... iArr) {
        return ObjectIdentifier.newInternal(iArr);
    }

    static {
        nameTable.put(MD5_oid, "MD5");
        nameTable.put(MD2_oid, "MD2");
        nameTable.put(SHA_oid, "SHA-1");
        nameTable.put(SHA224_oid, "SHA-224");
        nameTable.put(SHA256_oid, "SHA-256");
        nameTable.put(SHA384_oid, "SHA-384");
        nameTable.put(SHA512_oid, "SHA-512");
        nameTable.put(RSAEncryption_oid, "RSA");
        nameTable.put(RSA_oid, "RSA");
        nameTable.put(DH_oid, "Diffie-Hellman");
        nameTable.put(DH_PKIX_oid, "Diffie-Hellman");
        nameTable.put(DSA_oid, "DSA");
        nameTable.put(DSA_OIW_oid, "DSA");
        nameTable.put(EC_oid, "EC");
        nameTable.put(ECDH_oid, "ECDH");
        nameTable.put(AES_oid, "AES");
        nameTable.put(sha1WithECDSA_oid, "SHA1withECDSA");
        nameTable.put(sha224WithECDSA_oid, "SHA224withECDSA");
        nameTable.put(sha256WithECDSA_oid, "SHA256withECDSA");
        nameTable.put(sha384WithECDSA_oid, "SHA384withECDSA");
        nameTable.put(sha512WithECDSA_oid, "SHA512withECDSA");
        nameTable.put(md5WithRSAEncryption_oid, "MD5withRSA");
        nameTable.put(md2WithRSAEncryption_oid, "MD2withRSA");
        nameTable.put(sha1WithDSA_oid, "SHA1withDSA");
        nameTable.put(sha1WithDSA_OIW_oid, "SHA1withDSA");
        nameTable.put(shaWithDSA_OIW_oid, "SHA1withDSA");
        nameTable.put(sha224WithDSA_oid, "SHA224withDSA");
        nameTable.put(sha256WithDSA_oid, "SHA256withDSA");
        nameTable.put(sha1WithRSAEncryption_oid, "SHA1withRSA");
        nameTable.put(sha1WithRSAEncryption_OIW_oid, "SHA1withRSA");
        nameTable.put(sha224WithRSAEncryption_oid, "SHA224withRSA");
        nameTable.put(sha256WithRSAEncryption_oid, "SHA256withRSA");
        nameTable.put(sha384WithRSAEncryption_oid, "SHA384withRSA");
        nameTable.put(sha512WithRSAEncryption_oid, "SHA512withRSA");
        nameTable.put(pbeWithMD5AndDES_oid, "PBEWithMD5AndDES");
        nameTable.put(pbeWithMD5AndRC2_oid, "PBEWithMD5AndRC2");
        nameTable.put(pbeWithSHA1AndDES_oid, "PBEWithSHA1AndDES");
        nameTable.put(pbeWithSHA1AndRC2_oid, "PBEWithSHA1AndRC2");
        nameTable.put(pbeWithSHA1AndDESede_oid, "PBEWithSHA1AndDESede");
        nameTable.put(pbeWithSHA1AndRC2_40_oid, "PBEWithSHA1AndRC2_40");
    }

    public static String makeSigAlg(String str, String str2) {
        String replace = str.replace("-", "");
        if (str2.equalsIgnoreCase("EC")) {
            str2 = "ECDSA";
        }
        return replace + "with" + str2;
    }

    public static String getEncAlgFromSigAlg(String str) {
        String str2;
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int indexOf = upperCase.indexOf("WITH");
        if (indexOf <= 0) {
            return null;
        }
        int i = indexOf + 4;
        int indexOf2 = upperCase.indexOf("AND", i);
        if (indexOf2 > 0) {
            str2 = upperCase.substring(i, indexOf2);
        } else {
            str2 = upperCase.substring(i);
        }
        return str2.equalsIgnoreCase("ECDSA") ? "EC" : str2;
    }
}
