package com.android.org.bouncycastle.jcajce.provider.keystore.pkcs12;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1Set;
import com.android.org.bouncycastle.asn1.DERBMPString;
import com.android.org.bouncycastle.asn1.DERNull;
import com.android.org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.android.org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.AuthenticatedSafe;
import com.android.org.bouncycastle.asn1.pkcs.CertBag;
import com.android.org.bouncycastle.asn1.pkcs.ContentInfo;
import com.android.org.bouncycastle.asn1.pkcs.EncryptedData;
import com.android.org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import com.android.org.bouncycastle.asn1.pkcs.MacData;
import com.android.org.bouncycastle.asn1.pkcs.PBES2Parameters;
import com.android.org.bouncycastle.asn1.pkcs.PBKDF2Params;
import com.android.org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.pkcs.Pfx;
import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.android.org.bouncycastle.asn1.pkcs.SafeBag;
import com.android.org.bouncycastle.asn1.util.ASN1Dump;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import com.android.org.bouncycastle.asn1.x509.DigestInfo;
import com.android.org.bouncycastle.asn1.x509.Extension;
import com.android.org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import com.android.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.android.org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.digests.AndroidDigestFactory;
import com.android.org.bouncycastle.jcajce.PKCS12Key;
import com.android.org.bouncycastle.jcajce.PKCS12StoreParameter;
import com.android.org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import com.android.org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import com.android.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.org.bouncycastle.jce.interfaces.BCKeyStore;
import com.android.org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.android.org.bouncycastle.jce.provider.JDKPKCS12StoreParameter;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Integers;
import com.android.org.bouncycastle.util.Properties;
import com.android.org.bouncycastle.util.Strings;
import com.android.org.bouncycastle.util.encoders.Hex;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 51200;
    static final int NULL = 0;
    static final String PKCS12_MAX_IT_COUNT_PROPERTY = "com.android.org.bouncycastle.pkcs12.max_it_count";
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private final JcaJceHelper helper = new DefaultJcaJceHelper();
    private int itCount = 102400;
    private ASN1ObjectIdentifier keyAlgorithm;
    private Hashtable keyCerts = new Hashtable();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private Hashtable localIds = new Hashtable();
    private AlgorithmIdentifier macAlgorithm = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    protected SecureRandom random = CryptoServicesRegistrar.getSecureRandom();
    private int saltLength = 20;

    /* access modifiers changed from: private */
    public class CertId {
        byte[] id;

        CertId(PublicKey key) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(key).getKeyIdentifier();
        }

        CertId(byte[] id2) {
            this.id = id2;
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CertId)) {
                return false;
            }
            return Arrays.areEqual(this.id, ((CertId) o).id);
        }
    }

    public PKCS12KeyStoreSpi(JcaJceHelper helper2, ASN1ObjectIdentifier keyAlgorithm2, ASN1ObjectIdentifier certAlgorithm2) {
        this.keyAlgorithm = keyAlgorithm2;
        this.certAlgorithm = certAlgorithm2;
        try {
            this.certFact = helper2.createCertificateFactory("X.509");
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create cert factory - " + e.toString());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SubjectKeyIdentifier createSubjectKeyId(PublicKey pubKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(pubKey.getEncoded())));
        } catch (Exception e) {
            throw new RuntimeException("error creating key");
        }
    }

    private static byte[] getDigest(SubjectPublicKeyInfo spki) {
        Digest digest = AndroidDigestFactory.getSHA1();
        byte[] resBuf = new byte[digest.getDigestSize()];
        byte[] bytes = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        return resBuf;
    }

    @Override // com.android.org.bouncycastle.jce.interfaces.BCKeyStore
    public void setRandom(SecureRandom rand) {
        this.random = rand;
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        Hashtable tab = new Hashtable();
        Enumeration e = this.certs.keys();
        while (e.hasMoreElements()) {
            tab.put(e.nextElement(), "cert");
        }
        Enumeration e2 = this.keys.keys();
        while (e2.hasMoreElements()) {
            String a = (String) e2.nextElement();
            if (tab.get(a) == null) {
                tab.put(a, "key");
            }
        }
        return tab.keys();
    }

    public boolean engineContainsAlias(String alias) {
        return (this.certs.get(alias) == null && this.keys.get(alias) == null) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String alias) throws KeyStoreException {
        Key k = (Key) this.keys.remove(alias);
        Certificate c = (Certificate) this.certs.remove(alias);
        if (c != null) {
            this.chainCerts.remove(new CertId(c.getPublicKey()));
        }
        if (k != null) {
            String id = (String) this.localIds.remove(alias);
            if (id != null) {
                c = (Certificate) this.keyCerts.remove(id);
            }
            if (c != null) {
                this.chainCerts.remove(new CertId(c.getPublicKey()));
            }
        }
    }

    public Certificate engineGetCertificate(String alias) {
        if (alias != null) {
            Certificate c = (Certificate) this.certs.get(alias);
            if (c != null) {
                return c;
            }
            String id = (String) this.localIds.get(alias);
            if (id != null) {
                return (Certificate) this.keyCerts.get(id);
            }
            return (Certificate) this.keyCerts.get(alias);
        }
        throw new IllegalArgumentException("null alias passed to getCertificate.");
    }

    public String engineGetCertificateAlias(Certificate cert) {
        Enumeration c = this.certs.elements();
        Enumeration k = this.certs.keys();
        while (c.hasMoreElements()) {
            String ta = (String) k.nextElement();
            if (((Certificate) c.nextElement()).equals(cert)) {
                return ta;
            }
        }
        Enumeration c2 = this.keyCerts.elements();
        Enumeration k2 = this.keyCerts.keys();
        while (c2.hasMoreElements()) {
            String ta2 = (String) k2.nextElement();
            if (((Certificate) c2.nextElement()).equals(cert)) {
                return ta2;
            }
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String alias) {
        Certificate c;
        if (alias == null) {
            throw new IllegalArgumentException("null alias passed to getCertificateChain.");
        } else if (!engineIsKeyEntry(alias) || (c = engineGetCertificate(alias)) == null) {
            return null;
        } else {
            Vector cs = new Vector();
            while (c != null) {
                X509Certificate x509c = (X509Certificate) c;
                Certificate nextC = null;
                byte[] bytes = x509c.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (bytes != null) {
                    try {
                        AuthorityKeyIdentifier id = AuthorityKeyIdentifier.getInstance(new ASN1InputStream(((ASN1OctetString) new ASN1InputStream(bytes).readObject()).getOctets()).readObject());
                        if (id.getKeyIdentifier() != null) {
                            nextC = (Certificate) this.chainCerts.get(new CertId(id.getKeyIdentifier()));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e.toString());
                    }
                }
                if (nextC == null) {
                    Principal i = x509c.getIssuerDN();
                    if (!i.equals(x509c.getSubjectDN())) {
                        Enumeration e2 = this.chainCerts.keys();
                        while (true) {
                            if (!e2.hasMoreElements()) {
                                break;
                            }
                            X509Certificate crt = (X509Certificate) this.chainCerts.get(e2.nextElement());
                            if (crt.getSubjectDN().equals(i)) {
                                try {
                                    x509c.verify(crt.getPublicKey());
                                    nextC = crt;
                                    break;
                                } catch (Exception e3) {
                                }
                            }
                        }
                    }
                }
                if (cs.contains(c)) {
                    c = null;
                } else {
                    cs.addElement(c);
                    if (nextC != c) {
                        c = nextC;
                    } else {
                        c = null;
                    }
                }
            }
            Certificate[] certChain = new Certificate[cs.size()];
            for (int i2 = 0; i2 != certChain.length; i2++) {
                certChain[i2] = (Certificate) cs.elementAt(i2);
            }
            return certChain;
        }
    }

    public Date engineGetCreationDate(String alias) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        } else if (this.keys.get(alias) == null && this.certs.get(alias) == null) {
            return null;
        } else {
            return new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String alias, char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (alias != null) {
            return (Key) this.keys.get(alias);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    public boolean engineIsCertificateEntry(String alias) {
        return this.certs.get(alias) != null && this.keys.get(alias) == null;
    }

    public boolean engineIsKeyEntry(String alias) {
        return this.keys.get(alias) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String alias, Certificate cert) throws KeyStoreException {
        if (this.keys.get(alias) == null) {
            this.certs.put(alias, cert);
            this.chainCerts.put(new CertId(cert.getPublicKey()), cert);
            return;
        }
        throw new KeyStoreException("There is a key entry with the name " + alias + ".");
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String alias, byte[] key, Certificate[] chain) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String alias, Key key, char[] password, Certificate[] chain) throws KeyStoreException {
        if (!(key instanceof PrivateKey)) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!(key instanceof PrivateKey) || chain != null) {
            if (this.keys.get(alias) != null) {
                engineDeleteEntry(alias);
            }
            this.keys.put(alias, key);
            if (chain != null) {
                this.certs.put(alias, chain[0]);
                for (int i = 0; i != chain.length; i++) {
                    this.chainCerts.put(new CertId(chain[i].getPublicKey()), chain[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public int engineSize() {
        Hashtable tab = new Hashtable();
        Enumeration e = this.certs.keys();
        while (e.hasMoreElements()) {
            tab.put(e.nextElement(), "cert");
        }
        Enumeration e2 = this.keys.keys();
        while (e2.hasMoreElements()) {
            String a = (String) e2.nextElement();
            if (tab.get(a) == null) {
                tab.put(a, "key");
            }
        }
        return tab.size();
    }

    /* access modifiers changed from: protected */
    public PrivateKey unwrapKey(AlgorithmIdentifier algId, byte[] data, char[] password, boolean wrongPKCS12Zero) throws IOException {
        ASN1ObjectIdentifier algorithm = algId.getAlgorithm();
        try {
            if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), validateIterationCount(pbeParams.getIterations()));
                Cipher cipher = this.helper.createCipher(algorithm.getId());
                cipher.init(4, new PKCS12Key(password, wrongPKCS12Zero), defParams);
                return (PrivateKey) cipher.unwrap(data, "", 2);
            } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                return (PrivateKey) createCipher(4, password, algId).unwrap(data, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] wrapKey(String algorithm, Key key, PKCS12PBEParams pbeParams, char[] password) throws IOException {
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        try {
            SecretKeyFactory keyFact = this.helper.createSecretKeyFactory(algorithm);
            PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
            Cipher cipher = this.helper.createCipher(algorithm);
            cipher.init(3, keyFact.generateSecret(pbeSpec), defParams);
            return cipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] cryptData(boolean forEncryption, AlgorithmIdentifier algId, char[] password, boolean wrongPKCS12Zero, byte[] data) throws IOException {
        ASN1ObjectIdentifier algorithm = algId.getAlgorithm();
        int mode = forEncryption ? 1 : 2;
        if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
            try {
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                PKCS12Key key = new PKCS12Key(password, wrongPKCS12Zero);
                Cipher cipher = this.helper.createCipher(algorithm.getId());
                cipher.init(mode, key, defParams);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
            try {
                return createCipher(mode, password, algId).doFinal(data);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: " + algorithm);
        }
    }

    private Cipher createCipher(int mode, char[] password, AlgorithmIdentifier algId) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException {
        SecretKey key;
        PBES2Parameters alg = PBES2Parameters.getInstance(algId.getParameters());
        PBKDF2Params func = PBKDF2Params.getInstance(alg.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier encScheme = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
        SecretKeyFactory keyFact = this.helper.createSecretKeyFactory(alg.getKeyDerivationFunc().getAlgorithm().getId());
        if (func.isDefaultPrf()) {
            key = keyFact.generateSecret(new PBEKeySpec(password, func.getSalt(), validateIterationCount(func.getIterationCount()), keySizeProvider.getKeySize(encScheme)));
        } else {
            key = keyFact.generateSecret(new PBKDF2KeySpec(password, func.getSalt(), validateIterationCount(func.getIterationCount()), keySizeProvider.getKeySize(encScheme), func.getPrf()));
        }
        Cipher cipher = Cipher.getInstance(alg.getEncryptionScheme().getAlgorithm().getId());
        ASN1Encodable encParams = alg.getEncryptionScheme().getParameters();
        if (encParams instanceof ASN1OctetString) {
            cipher.init(mode, key, new IvParameterSpec(ASN1OctetString.getInstance(encParams).getOctets()));
        }
        return cipher;
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream stream, char[] password) throws IOException {
        Vector chain;
        boolean wrongPKCS12Zero;
        String str;
        String str2;
        String str3;
        SafeBag b;
        ASN1OctetString localId;
        String str4;
        Pfx bag;
        int head;
        boolean wrongPKCS12Zero2;
        ASN1InputStream bIn;
        AuthenticatedSafe authSafe;
        ContentInfo[] c;
        int head2;
        String str5;
        ASN1Sequence seq;
        EncryptedData d;
        boolean wrongPKCS12Zero3;
        byte[] octets;
        String str6;
        ASN1Sequence seq2;
        String str7;
        ASN1Primitive attr;
        EncryptedPrivateKeyInfo eIn;
        Pfx bag2;
        int head3;
        ASN1InputStream bIn2;
        String alias;
        Pfx bag3;
        ASN1Primitive attr2;
        Exception e;
        char[] cArr = password;
        if (stream != null) {
            if (cArr != null) {
                BufferedInputStream bufIn = new BufferedInputStream(stream);
                bufIn.mark(10);
                int head4 = bufIn.read();
                if (head4 == 48) {
                    bufIn.reset();
                    try {
                        Pfx bag4 = Pfx.getInstance(new ASN1InputStream(bufIn).readObject());
                        ContentInfo info = bag4.getAuthSafe();
                        Vector chain2 = new Vector();
                        boolean unmarkedKey = false;
                        boolean wrongPKCS12Zero4 = false;
                        if (bag4.getMacData() != null) {
                            MacData mData = bag4.getMacData();
                            DigestInfo dInfo = mData.getMac();
                            this.macAlgorithm = dInfo.getAlgorithmId();
                            byte[] salt = mData.getSalt();
                            this.itCount = validateIterationCount(mData.getIterationCount());
                            this.saltLength = salt.length;
                            byte[] data = ((ASN1OctetString) info.getContent()).getOctets();
                            try {
                                chain = chain2;
                                try {
                                    byte[] res = calculatePbeMac(this.macAlgorithm.getAlgorithm(), salt, this.itCount, password, false, data);
                                    byte[] dig = dInfo.getDigest();
                                    if (!Arrays.constantTimeAreEqual(res, dig)) {
                                        if (cArr.length <= 0) {
                                            try {
                                                if (Arrays.constantTimeAreEqual(calculatePbeMac(this.macAlgorithm.getAlgorithm(), salt, this.itCount, password, true, data), dig)) {
                                                    wrongPKCS12Zero4 = true;
                                                } else {
                                                    throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                                                }
                                            } catch (IOException e2) {
                                                throw e2;
                                            } catch (Exception e3) {
                                                e = e3;
                                                throw new IOException("error constructing MAC: " + e.toString());
                                            }
                                        } else {
                                            throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                                        }
                                    }
                                    wrongPKCS12Zero = wrongPKCS12Zero4;
                                } catch (IOException e4) {
                                    throw e4;
                                } catch (Exception e5) {
                                    e = e5;
                                    throw new IOException("error constructing MAC: " + e.toString());
                                }
                            } catch (IOException e6) {
                                throw e6;
                            } catch (Exception e7) {
                                e = e7;
                                throw new IOException("error constructing MAC: " + e.toString());
                            }
                        } else {
                            chain = chain2;
                            wrongPKCS12Zero = false;
                        }
                        this.keys = new IgnoresCaseHashtable();
                        this.localIds = new Hashtable();
                        String str8 = "unmarked";
                        String str9 = "attempt to add existing attribute with different value";
                        if (info.getContentType().equals(data)) {
                            ASN1InputStream bIn3 = new ASN1InputStream(((ASN1OctetString) info.getContent()).getOctets());
                            AuthenticatedSafe authSafe2 = AuthenticatedSafe.getInstance(bIn3.readObject());
                            ContentInfo[] c2 = authSafe2.getContentInfo();
                            int i = 0;
                            while (i != c2.length) {
                                if (c2[i].getContentType().equals(data)) {
                                    ASN1InputStream dIn = new ASN1InputStream(((ASN1OctetString) c2[i].getContent()).getOctets());
                                    ASN1Sequence seq3 = (ASN1Sequence) dIn.readObject();
                                    int j = 0;
                                    while (true) {
                                        authSafe = authSafe2;
                                        if (j == seq3.size()) {
                                            head = head4;
                                            bIn = bIn3;
                                            bag = bag4;
                                            head2 = i;
                                            c = c2;
                                            str5 = str9;
                                            str4 = str8;
                                            wrongPKCS12Zero2 = wrongPKCS12Zero;
                                            break;
                                        }
                                        SafeBag b2 = SafeBag.getInstance(seq3.getObjectAt(j));
                                        if (b2.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                            EncryptedPrivateKeyInfo eIn2 = EncryptedPrivateKeyInfo.getInstance(b2.getBagValue());
                                            head3 = head4;
                                            PrivateKey privKey = unwrapKey(eIn2.getEncryptionAlgorithm(), eIn2.getEncryptedData(), cArr, wrongPKCS12Zero);
                                            String alias2 = null;
                                            ASN1OctetString localId2 = null;
                                            if (b2.getBagAttributes() != null) {
                                                Enumeration e8 = b2.getBagAttributes().getObjects();
                                                while (e8.hasMoreElements()) {
                                                    ASN1Sequence sq = (ASN1Sequence) e8.nextElement();
                                                    ASN1ObjectIdentifier aOid = (ASN1ObjectIdentifier) sq.getObjectAt(0);
                                                    ASN1Set attrSet = (ASN1Set) sq.getObjectAt(1);
                                                    if (attrSet.size() > 0) {
                                                        bag3 = bag4;
                                                        attr2 = (ASN1Primitive) attrSet.getObjectAt(0);
                                                        if (privKey instanceof PKCS12BagAttributeCarrier) {
                                                            PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier) privKey;
                                                            ASN1Encodable existing = bagAttr.getBagAttribute(aOid);
                                                            if (existing == null) {
                                                                bagAttr.setBagAttribute(aOid, attr2);
                                                            } else if (!existing.toASN1Primitive().equals(attr2)) {
                                                                throw new IOException(str9);
                                                            }
                                                        }
                                                    } else {
                                                        bag3 = bag4;
                                                        attr2 = null;
                                                    }
                                                    if (aOid.equals(pkcs_9_at_friendlyName)) {
                                                        String alias3 = ((DERBMPString) attr2).getString();
                                                        this.keys.put(alias3, privKey);
                                                        alias2 = alias3;
                                                    } else if (aOid.equals(pkcs_9_at_localKeyId)) {
                                                        localId2 = (ASN1OctetString) attr2;
                                                        alias2 = alias2;
                                                    } else {
                                                        alias2 = alias2;
                                                    }
                                                    bIn3 = bIn3;
                                                    eIn2 = eIn2;
                                                    bag4 = bag3;
                                                }
                                                bIn2 = bIn3;
                                                bag2 = bag4;
                                                alias = alias2;
                                            } else {
                                                alias = null;
                                                bIn2 = bIn3;
                                                bag2 = bag4;
                                            }
                                            if (localId2 != null) {
                                                String name = new String(Hex.encode(localId2.getOctets()));
                                                if (alias == null) {
                                                    this.keys.put(name, privKey);
                                                } else {
                                                    this.localIds.put(alias, name);
                                                }
                                            } else {
                                                unmarkedKey = true;
                                                this.keys.put(str8, privKey);
                                            }
                                        } else {
                                            head3 = head4;
                                            bIn2 = bIn3;
                                            bag2 = bag4;
                                            if (b2.getBagId().equals(certBag)) {
                                                chain.addElement(b2);
                                            } else {
                                                System.out.println("extra in data " + b2.getBagId());
                                                System.out.println(ASN1Dump.dumpAsString(b2));
                                            }
                                        }
                                        j++;
                                        authSafe2 = authSafe;
                                        bIn3 = bIn2;
                                        dIn = dIn;
                                        seq3 = seq3;
                                        head4 = head3;
                                        bag4 = bag2;
                                    }
                                } else {
                                    authSafe = authSafe2;
                                    head = head4;
                                    bIn = bIn3;
                                    bag = bag4;
                                    if (c2[i].getContentType().equals(encryptedData)) {
                                        EncryptedData d2 = EncryptedData.getInstance(c2[i].getContent());
                                        head2 = i;
                                        c = c2;
                                        int i2 = 0;
                                        String str10 = str9;
                                        str4 = str8;
                                        byte[] octets2 = cryptData(false, d2.getEncryptionAlgorithm(), password, wrongPKCS12Zero, d2.getContent().getOctets());
                                        ASN1Sequence seq4 = (ASN1Sequence) ASN1Primitive.fromByteArray(octets2);
                                        int j2 = 0;
                                        while (j2 != seq4.size()) {
                                            SafeBag b3 = SafeBag.getInstance(seq4.getObjectAt(j2));
                                            if (b3.getBagId().equals(certBag)) {
                                                chain.addElement(b3);
                                                d = d2;
                                                octets = octets2;
                                                seq = seq4;
                                                wrongPKCS12Zero3 = wrongPKCS12Zero;
                                                str6 = str10;
                                            } else if (b3.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                                EncryptedPrivateKeyInfo eIn3 = EncryptedPrivateKeyInfo.getInstance(b3.getBagValue());
                                                PrivateKey privKey2 = unwrapKey(eIn3.getEncryptionAlgorithm(), eIn3.getEncryptedData(), cArr, wrongPKCS12Zero);
                                                PKCS12BagAttributeCarrier bagAttr2 = (PKCS12BagAttributeCarrier) privKey2;
                                                ASN1OctetString localId3 = null;
                                                Enumeration e9 = b3.getBagAttributes().getObjects();
                                                String alias4 = null;
                                                while (e9.hasMoreElements()) {
                                                    ASN1Sequence sq2 = (ASN1Sequence) e9.nextElement();
                                                    ASN1ObjectIdentifier aOid2 = (ASN1ObjectIdentifier) sq2.getObjectAt(i2);
                                                    ASN1Set attrSet2 = (ASN1Set) sq2.getObjectAt(1);
                                                    if (attrSet2.size() > 0) {
                                                        attr = (ASN1Primitive) attrSet2.getObjectAt(0);
                                                        ASN1Encodable existing2 = bagAttr2.getBagAttribute(aOid2);
                                                        if (existing2 == null) {
                                                            seq2 = seq4;
                                                            str7 = str10;
                                                            bagAttr2.setBagAttribute(aOid2, attr);
                                                        } else if (existing2.toASN1Primitive().equals(attr)) {
                                                            seq2 = seq4;
                                                            str7 = str10;
                                                        } else {
                                                            throw new IOException(str10);
                                                        }
                                                    } else {
                                                        seq2 = seq4;
                                                        str7 = str10;
                                                        attr = null;
                                                    }
                                                    if (aOid2.equals(pkcs_9_at_friendlyName)) {
                                                        String alias5 = ((DERBMPString) attr).getString();
                                                        eIn = eIn3;
                                                        this.keys.put(alias5, privKey2);
                                                        alias4 = alias5;
                                                    } else {
                                                        eIn = eIn3;
                                                        if (aOid2.equals(pkcs_9_at_localKeyId)) {
                                                            localId3 = (ASN1OctetString) attr;
                                                        }
                                                    }
                                                    str10 = str7;
                                                    octets2 = octets2;
                                                    d2 = d2;
                                                    eIn3 = eIn;
                                                    seq4 = seq2;
                                                    i2 = 0;
                                                }
                                                d = d2;
                                                octets = octets2;
                                                seq = seq4;
                                                str6 = str10;
                                                String name2 = new String(Hex.encode(localId3.getOctets()));
                                                if (alias4 == null) {
                                                    this.keys.put(name2, privKey2);
                                                } else {
                                                    this.localIds.put(alias4, name2);
                                                }
                                                wrongPKCS12Zero3 = wrongPKCS12Zero;
                                            } else {
                                                d = d2;
                                                octets = octets2;
                                                seq = seq4;
                                                str6 = str10;
                                                if (b3.getBagId().equals(keyBag)) {
                                                    PrivateKeyInfo kInfo = PrivateKeyInfo.getInstance(b3.getBagValue());
                                                    PrivateKey privKey3 = BouncyCastleProvider.getPrivateKey(kInfo);
                                                    PKCS12BagAttributeCarrier bagAttr3 = (PKCS12BagAttributeCarrier) privKey3;
                                                    String alias6 = null;
                                                    ASN1OctetString localId4 = null;
                                                    Enumeration e10 = b3.getBagAttributes().getObjects();
                                                    while (e10.hasMoreElements()) {
                                                        ASN1Sequence sq3 = ASN1Sequence.getInstance(e10.nextElement());
                                                        ASN1ObjectIdentifier aOid3 = ASN1ObjectIdentifier.getInstance(sq3.getObjectAt(0));
                                                        ASN1Set attrSet3 = ASN1Set.getInstance(sq3.getObjectAt(1));
                                                        if (attrSet3.size() > 0) {
                                                            ASN1Primitive attr3 = (ASN1Primitive) attrSet3.getObjectAt(0);
                                                            ASN1Encodable existing3 = bagAttr3.getBagAttribute(aOid3);
                                                            if (existing3 == null) {
                                                                bagAttr3.setBagAttribute(aOid3, attr3);
                                                            } else if (!existing3.toASN1Primitive().equals(attr3)) {
                                                                throw new IOException(str6);
                                                            }
                                                            if (aOid3.equals(pkcs_9_at_friendlyName)) {
                                                                alias6 = ((DERBMPString) attr3).getString();
                                                                this.keys.put(alias6, privKey3);
                                                            } else if (aOid3.equals(pkcs_9_at_localKeyId)) {
                                                                localId4 = (ASN1OctetString) attr3;
                                                            }
                                                        }
                                                        wrongPKCS12Zero = wrongPKCS12Zero;
                                                        kInfo = kInfo;
                                                    }
                                                    wrongPKCS12Zero3 = wrongPKCS12Zero;
                                                    String name3 = new String(Hex.encode(localId4.getOctets()));
                                                    if (alias6 == null) {
                                                        this.keys.put(name3, privKey3);
                                                    } else {
                                                        this.localIds.put(alias6, name3);
                                                    }
                                                } else {
                                                    wrongPKCS12Zero3 = wrongPKCS12Zero;
                                                    System.out.println("extra in encryptedData " + b3.getBagId());
                                                    System.out.println(ASN1Dump.dumpAsString(b3));
                                                }
                                            }
                                            j2++;
                                            cArr = password;
                                            str10 = str6;
                                            octets2 = octets;
                                            wrongPKCS12Zero = wrongPKCS12Zero3;
                                            d2 = d;
                                            seq4 = seq;
                                            i2 = 0;
                                        }
                                        wrongPKCS12Zero2 = wrongPKCS12Zero;
                                        str5 = str10;
                                    } else {
                                        head2 = i;
                                        c = c2;
                                        str5 = str9;
                                        str4 = str8;
                                        wrongPKCS12Zero2 = wrongPKCS12Zero;
                                        System.out.println("extra " + c[head2].getContentType().getId());
                                        System.out.println("extra " + ASN1Dump.dumpAsString(c[head2].getContent()));
                                    }
                                }
                                i = head2 + 1;
                                cArr = password;
                                str9 = str5;
                                c2 = c;
                                authSafe2 = authSafe;
                                bIn3 = bIn;
                                wrongPKCS12Zero = wrongPKCS12Zero2;
                                head4 = head;
                                bag4 = bag;
                                str8 = str4;
                            }
                            str = str8;
                            str2 = str9;
                        } else {
                            str2 = str9;
                            str = str8;
                        }
                        this.certs = new IgnoresCaseHashtable();
                        this.chainCerts = new Hashtable();
                        this.keyCerts = new Hashtable();
                        int i3 = 0;
                        while (i3 != chain.size()) {
                            SafeBag b4 = (SafeBag) chain.elementAt(i3);
                            CertBag cb = CertBag.getInstance(b4.getBagValue());
                            if (cb.getCertId().equals(x509Certificate)) {
                                try {
                                    Certificate cert = this.certFact.generateCertificate(new ByteArrayInputStream(((ASN1OctetString) cb.getCertValue()).getOctets()));
                                    ASN1OctetString localId5 = null;
                                    String alias7 = null;
                                    if (b4.getBagAttributes() != null) {
                                        Enumeration e11 = b4.getBagAttributes().getObjects();
                                        while (e11.hasMoreElements()) {
                                            ASN1Sequence sq4 = ASN1Sequence.getInstance(e11.nextElement());
                                            ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(sq4.getObjectAt(0));
                                            ASN1Set attrSet4 = ASN1Set.getInstance(sq4.getObjectAt(1));
                                            if (attrSet4.size() > 0) {
                                                ASN1Primitive attr4 = (ASN1Primitive) attrSet4.getObjectAt(0);
                                                b = b4;
                                                if (cert instanceof PKCS12BagAttributeCarrier) {
                                                    PKCS12BagAttributeCarrier bagAttr4 = (PKCS12BagAttributeCarrier) cert;
                                                    ASN1Encodable existing4 = bagAttr4.getBagAttribute(oid);
                                                    if (existing4 != null) {
                                                        localId = localId5;
                                                        if (!existing4.toASN1Primitive().equals(attr4)) {
                                                            throw new IOException(str2);
                                                        }
                                                    } else {
                                                        localId = localId5;
                                                        bagAttr4.setBagAttribute(oid, attr4);
                                                    }
                                                } else {
                                                    localId = localId5;
                                                }
                                                if (oid.equals(pkcs_9_at_friendlyName)) {
                                                    alias7 = ((DERBMPString) attr4).getString();
                                                    localId5 = localId;
                                                } else if (oid.equals(pkcs_9_at_localKeyId)) {
                                                    localId5 = (ASN1OctetString) attr4;
                                                }
                                                b4 = b;
                                            } else {
                                                b = b4;
                                                localId = localId5;
                                            }
                                            localId5 = localId;
                                            b4 = b;
                                        }
                                    }
                                    this.chainCerts.put(new CertId(cert.getPublicKey()), cert);
                                    if (!unmarkedKey) {
                                        str3 = str;
                                        if (localId5 != null) {
                                            this.keyCerts.put(new String(Hex.encode(localId5.getOctets())), cert);
                                        }
                                        if (alias7 != null) {
                                            this.certs.put(alias7, cert);
                                        }
                                    } else if (this.keyCerts.isEmpty()) {
                                        String name4 = new String(Hex.encode(createSubjectKeyId(cert.getPublicKey()).getKeyIdentifier()));
                                        this.keyCerts.put(name4, cert);
                                        IgnoresCaseHashtable ignoresCaseHashtable = this.keys;
                                        str3 = str;
                                        ignoresCaseHashtable.put(name4, ignoresCaseHashtable.remove(str3));
                                    } else {
                                        str3 = str;
                                    }
                                    i3++;
                                    str = str3;
                                } catch (Exception e12) {
                                    throw new RuntimeException(e12.toString());
                                }
                            } else {
                                throw new RuntimeException("Unsupported certificate type: " + cb.getCertId());
                            }
                        }
                    } catch (Exception e13) {
                        throw new IOException(e13.getMessage());
                    }
                } else {
                    throw new IOException("stream does not represent a PKCS12 key store");
                }
            } else {
                throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
            }
        }
    }

    private int validateIterationCount(BigInteger i) {
        int count = i.intValue();
        if (count >= 0) {
            BigInteger maxValue = Properties.asBigInteger(PKCS12_MAX_IT_COUNT_PROPERTY);
            if (maxValue == null || maxValue.intValue() >= count) {
                return count;
            }
            throw new IllegalStateException("iteration count " + count + " greater than " + maxValue.intValue());
        }
        throw new IllegalStateException("negative iteration count found");
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(KeyStore.LoadStoreParameter param) throws IOException, NoSuchAlgorithmException, CertificateException {
        PKCS12StoreParameter bcParam;
        char[] password;
        if (param == null) {
            throw new IllegalArgumentException("'param' arg cannot be null");
        } else if ((param instanceof PKCS12StoreParameter) || (param instanceof JDKPKCS12StoreParameter)) {
            if (param instanceof PKCS12StoreParameter) {
                bcParam = (PKCS12StoreParameter) param;
            } else {
                bcParam = new PKCS12StoreParameter(((JDKPKCS12StoreParameter) param).getOutputStream(), param.getProtectionParameter(), ((JDKPKCS12StoreParameter) param).isUseDEREncoding());
            }
            KeyStore.ProtectionParameter protParam = param.getProtectionParameter();
            if (protParam == null) {
                password = null;
            } else if (protParam instanceof KeyStore.PasswordProtection) {
                password = ((KeyStore.PasswordProtection) protParam).getPassword();
            } else {
                throw new IllegalArgumentException("No support for protection parameter of type " + protParam.getClass().getName());
            }
            doStore(bcParam.getOutputStream(), password, bcParam.isForDEREncoding());
        } else {
            throw new IllegalArgumentException("No support for 'param' of type " + param.getClass().getName());
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream stream, char[] password) throws IOException {
        doStore(stream, password, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x036c A[Catch:{ CertificateEncodingException -> 0x03eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b1 A[LOOP:1: B:19:0x00ab->B:21:0x00b1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0206  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doStore(java.io.OutputStream r32, char[] r33, boolean r34) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1541
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.doStore(java.io.OutputStream, char[], boolean):void");
    }

    private Set getUsedCertificateSet() {
        Set usedSet = new HashSet();
        Enumeration en = this.keys.keys();
        while (en.hasMoreElements()) {
            Certificate[] certs2 = engineGetCertificateChain((String) en.nextElement());
            for (int i = 0; i != certs2.length; i++) {
                usedSet.add(certs2[i]);
            }
        }
        Enumeration en2 = this.certs.keys();
        while (en2.hasMoreElements()) {
            usedSet.add(engineGetCertificate((String) en2.nextElement()));
        }
        return usedSet;
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier oid, byte[] salt, int itCount2, char[] password, boolean wrongPkcs12Zero, byte[] data) throws Exception {
        PBEParameterSpec defParams = new PBEParameterSpec(salt, itCount2);
        Mac mac = this.helper.createMac(oid.getId());
        mac.init(new PKCS12Key(password, wrongPkcs12Zero), defParams);
        mac.update(data);
        return mac.doFinal();
    }

    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new DefaultJcaJceHelper(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    /* access modifiers changed from: private */
    public static class IgnoresCaseHashtable {
        private Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public void put(String key, Object value) {
            String lower = key == null ? null : Strings.toLowerCase(key);
            String k = (String) this.keys.get(lower);
            if (k != null) {
                this.orig.remove(k);
            }
            this.keys.put(lower, key);
            this.orig.put(key, value);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public Object remove(String alias) {
            String k = (String) this.keys.remove(alias == null ? null : Strings.toLowerCase(alias));
            if (k == null) {
                return null;
            }
            return this.orig.remove(k);
        }

        public Object get(String alias) {
            String k = (String) this.keys.get(alias == null ? null : Strings.toLowerCase(alias));
            if (k == null) {
                return null;
            }
            return this.orig.get(k);
        }

        public Enumeration elements() {
            return this.orig.elements();
        }
    }

    /* access modifiers changed from: private */
    public static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            Map keySizes = new HashMap();
            keySizes.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            keySizes.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            keySizes.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            keySizes.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(keySizes);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer keySize = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (keySize != null) {
                return keySize.intValue();
            }
            return -1;
        }
    }
}
