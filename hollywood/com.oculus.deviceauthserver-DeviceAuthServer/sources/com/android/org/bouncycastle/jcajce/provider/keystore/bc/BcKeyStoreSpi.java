package com.android.org.bouncycastle.jcajce.provider.keystore.bc;

import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.android.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.PBEParametersGenerator;
import com.android.org.bouncycastle.crypto.digests.SHA1Digest;
import com.android.org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import com.android.org.bouncycastle.crypto.io.DigestInputStream;
import com.android.org.bouncycastle.crypto.io.DigestOutputStream;
import com.android.org.bouncycastle.crypto.io.MacInputStream;
import com.android.org.bouncycastle.crypto.io.MacOutputStream;
import com.android.org.bouncycastle.crypto.macs.HMac;
import com.android.org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import com.android.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.org.bouncycastle.jce.interfaces.BCKeyStore;
import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.io.Streams;
import com.android.org.bouncycastle.util.io.TeeOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class BcKeyStoreSpi extends KeyStoreSpi implements BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    private static final String KEY_CIPHER = "PBEWithSHAAnd3-KeyTripleDES-CBC";
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    private static final int KEY_SALT_SIZE = 20;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final String STORE_CIPHER = "PBEWithSHAAndTwofish-CBC";
    private static final int STORE_SALT_SIZE = 20;
    private static final int STORE_VERSION = 2;
    private final JcaJceHelper helper = new DefaultJcaJceHelper();
    protected SecureRandom random = CryptoServicesRegistrar.getSecureRandom();
    protected Hashtable table = new Hashtable();
    protected int version;

    public BcKeyStoreSpi(int version2) {
        this.version = version2;
    }

    /* access modifiers changed from: private */
    public class StoreEntry {
        String alias;
        Certificate[] certChain;
        Date date;
        Object obj;
        int type;

        StoreEntry(String alias2, Certificate obj2) {
            this.date = new Date();
            this.type = 1;
            this.alias = alias2;
            this.obj = obj2;
            this.certChain = null;
        }

        StoreEntry(String alias2, byte[] obj2, Certificate[] certChain2) {
            this.date = new Date();
            this.type = 3;
            this.alias = alias2;
            this.obj = obj2;
            this.certChain = certChain2;
        }

        StoreEntry(String alias2, Key key, char[] password, Certificate[] certChain2) throws Exception {
            this.date = new Date();
            this.type = 4;
            this.alias = alias2;
            this.certChain = certChain2;
            byte[] salt = new byte[20];
            BcKeyStoreSpi.this.random.setSeed(System.currentTimeMillis());
            BcKeyStoreSpi.this.random.nextBytes(salt);
            int iterationCount = (BcKeyStoreSpi.this.random.nextInt() & 1023) + BcKeyStoreSpi.MIN_ITERATIONS;
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DataOutputStream dOut = new DataOutputStream(bOut);
            dOut.writeInt(salt.length);
            dOut.write(salt);
            dOut.writeInt(iterationCount);
            DataOutputStream dOut2 = new DataOutputStream(new CipherOutputStream(dOut, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, password, salt, iterationCount)));
            BcKeyStoreSpi.this.encodeKey(key, dOut2);
            dOut2.close();
            this.obj = bOut.toByteArray();
        }

        StoreEntry(String alias2, Date date2, int type2, Object obj2) {
            this.date = new Date();
            this.alias = alias2;
            this.date = date2;
            this.type = type2;
            this.obj = obj2;
        }

        StoreEntry(String alias2, Date date2, int type2, Object obj2, Certificate[] certChain2) {
            this.date = new Date();
            this.alias = alias2;
            this.date = date2;
            this.type = type2;
            this.obj = obj2;
            this.certChain = certChain2;
        }

        /* access modifiers changed from: package-private */
        public int getType() {
            return this.type;
        }

        /* access modifiers changed from: package-private */
        public String getAlias() {
            return this.alias;
        }

        /* access modifiers changed from: package-private */
        public Object getObject() {
            return this.obj;
        }

        /* access modifiers changed from: package-private */
        public Object getObject(char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            DataInputStream dataInputStream;
            ByteArrayInputStream byteArrayInputStream;
            int iterationCount;
            Key k;
            if (password == null || password.length == 0) {
                Object obj2 = this.obj;
                if (obj2 instanceof Key) {
                    return obj2;
                }
            }
            if (this.type == 4) {
                DataInputStream dIn = new DataInputStream(new ByteArrayInputStream((byte[]) this.obj));
                try {
                    byte[] salt = new byte[dIn.readInt()];
                    dIn.readFully(salt);
                    try {
                        return BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dIn, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 2, password, salt, dIn.readInt()))));
                    } catch (Exception e) {
                        ByteArrayInputStream bIn = new ByteArrayInputStream((byte[]) this.obj);
                        DataInputStream dIn2 = new DataInputStream(bIn);
                        byte[] salt2 = new byte[dIn2.readInt()];
                        dIn2.readFully(salt2);
                        int iterationCount2 = dIn2.readInt();
                        try {
                            k = BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dIn2, BcKeyStoreSpi.this.makePBECipher("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, password, salt2, iterationCount2))));
                            dataInputStream = dIn2;
                            byteArrayInputStream = bIn;
                            iterationCount = iterationCount2;
                        } catch (Exception e2) {
                            ByteArrayInputStream bIn2 = new ByteArrayInputStream((byte[]) this.obj);
                            DataInputStream dIn3 = new DataInputStream(bIn2);
                            salt2 = new byte[dIn3.readInt()];
                            dIn3.readFully(salt2);
                            int iterationCount3 = dIn3.readInt();
                            k = BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dIn3, BcKeyStoreSpi.this.makePBECipher("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, password, salt2, iterationCount3))));
                            dataInputStream = dIn3;
                            byteArrayInputStream = bIn2;
                            iterationCount = iterationCount3;
                        }
                        if (k != null) {
                            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                            DataOutputStream dOut = new DataOutputStream(bOut);
                            dOut.writeInt(salt2.length);
                            dOut.write(salt2);
                            dOut.writeInt(iterationCount);
                            DataOutputStream dOut2 = new DataOutputStream(new CipherOutputStream(dOut, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, password, salt2, iterationCount)));
                            BcKeyStoreSpi.this.encodeKey(k, dOut2);
                            dOut2.close();
                            this.obj = bOut.toByteArray();
                            return k;
                        }
                        throw new UnrecoverableKeyException("no match");
                    } catch (Exception e3) {
                        throw new UnrecoverableKeyException("no match");
                    }
                } catch (Exception e4) {
                    throw new UnrecoverableKeyException("no match");
                }
            } else {
                throw new RuntimeException("forget something!");
            }
        }

        /* access modifiers changed from: package-private */
        public Certificate[] getCertificateChain() {
            return this.certChain;
        }

        /* access modifiers changed from: package-private */
        public Date getDate() {
            return this.date;
        }
    }

    private void encodeCertificate(Certificate cert, DataOutputStream dOut) throws IOException {
        try {
            byte[] cEnc = cert.getEncoded();
            dOut.writeUTF(cert.getType());
            dOut.writeInt(cEnc.length);
            dOut.write(cEnc);
        } catch (CertificateEncodingException ex) {
            throw new IOException(ex.toString());
        }
    }

    private Certificate decodeCertificate(DataInputStream dIn) throws IOException {
        String type = dIn.readUTF();
        byte[] cEnc = new byte[dIn.readInt()];
        dIn.readFully(cEnc);
        try {
            return this.helper.createCertificateFactory(type).generateCertificate(new ByteArrayInputStream(cEnc));
        } catch (NoSuchProviderException ex) {
            throw new IOException(ex.toString());
        } catch (CertificateException ex2) {
            throw new IOException(ex2.toString());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void encodeKey(Key key, DataOutputStream dOut) throws IOException {
        byte[] enc = key.getEncoded();
        if (key instanceof PrivateKey) {
            dOut.write(0);
        } else if (key instanceof PublicKey) {
            dOut.write(1);
        } else {
            dOut.write(2);
        }
        dOut.writeUTF(key.getFormat());
        dOut.writeUTF(key.getAlgorithm());
        dOut.writeInt(enc.length);
        dOut.write(enc);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Key decodeKey(DataInputStream dIn) throws IOException {
        KeySpec spec;
        int keyType = dIn.read();
        String format = dIn.readUTF();
        String algorithm = dIn.readUTF();
        byte[] enc = new byte[dIn.readInt()];
        dIn.readFully(enc);
        if (format.equals("PKCS#8") || format.equals("PKCS8")) {
            spec = new PKCS8EncodedKeySpec(enc);
        } else if (format.equals("X.509") || format.equals("X509")) {
            spec = new X509EncodedKeySpec(enc);
        } else if (format.equals("RAW")) {
            return new SecretKeySpec(enc, algorithm);
        } else {
            throw new IOException("Key format " + format + " not recognised!");
        }
        if (keyType == 0) {
            return BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(enc));
        }
        if (keyType == 1) {
            return BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(enc));
        }
        if (keyType == 2) {
            try {
                return this.helper.createSecretKeyFactory(algorithm).generateSecret(spec);
            } catch (Exception e) {
                throw new IOException("Exception creating key: " + e.toString());
            }
        } else {
            throw new IOException("Key type " + keyType + " not recognised!");
        }
    }

    /* access modifiers changed from: protected */
    public Cipher makePBECipher(String algorithm, int mode, char[] password, byte[] salt, int iterationCount) throws IOException {
        try {
            PBEKeySpec pbeSpec = new PBEKeySpec(password);
            SecretKeyFactory keyFact = this.helper.createSecretKeyFactory(algorithm);
            PBEParameterSpec defParams = new PBEParameterSpec(salt, iterationCount);
            Cipher cipher = this.helper.createCipher(algorithm);
            cipher.init(mode, keyFact.generateSecret(pbeSpec), defParams);
            return cipher;
        } catch (Exception e) {
            throw new IOException("Error initialising store of key store: " + e);
        }
    }

    @Override // com.android.org.bouncycastle.jce.interfaces.BCKeyStore
    public void setRandom(SecureRandom rand) {
        this.random = rand;
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        return this.table.keys();
    }

    public boolean engineContainsAlias(String alias) {
        return this.table.get(alias) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String alias) throws KeyStoreException {
        if (this.table.get(alias) != null) {
            this.table.remove(alias);
        }
    }

    public Certificate engineGetCertificate(String alias) {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry == null) {
            return null;
        }
        if (entry.getType() == 1) {
            return (Certificate) entry.getObject();
        }
        Certificate[] chain = entry.getCertificateChain();
        if (chain != null) {
            return chain[0];
        }
        return null;
    }

    public String engineGetCertificateAlias(Certificate cert) {
        Enumeration e = this.table.elements();
        while (e.hasMoreElements()) {
            StoreEntry entry = (StoreEntry) e.nextElement();
            if (!(entry.getObject() instanceof Certificate)) {
                Certificate[] chain = entry.getCertificateChain();
                if (chain != null && chain[0].equals(cert)) {
                    return entry.getAlias();
                }
            } else if (((Certificate) entry.getObject()).equals(cert)) {
                return entry.getAlias();
            }
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String alias) {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry != null) {
            return entry.getCertificateChain();
        }
        return null;
    }

    public Date engineGetCreationDate(String alias) {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry != null) {
            return entry.getDate();
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String alias, char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry == null || entry.getType() == 1) {
            return null;
        }
        return (Key) entry.getObject(password);
    }

    public boolean engineIsCertificateEntry(String alias) {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry == null || entry.getType() != 1) {
            return false;
        }
        return true;
    }

    public boolean engineIsKeyEntry(String alias) {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry == null || entry.getType() == 1) {
            return false;
        }
        return true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String alias, Certificate cert) throws KeyStoreException {
        StoreEntry entry = (StoreEntry) this.table.get(alias);
        if (entry == null || entry.getType() == 1) {
            this.table.put(alias, new StoreEntry(alias, cert));
            return;
        }
        throw new KeyStoreException("key store already has a key entry with alias " + alias);
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String alias, byte[] key, Certificate[] chain) throws KeyStoreException {
        this.table.put(alias, new StoreEntry(alias, key, chain));
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String alias, Key key, char[] password, Certificate[] chain) throws KeyStoreException {
        if (!(key instanceof PrivateKey) || chain != null) {
            try {
                this.table.put(alias, new StoreEntry(alias, key, password, chain));
            } catch (Exception e) {
                throw new KeyStoreException(e.toString());
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public int engineSize() {
        return this.table.size();
    }

    /* access modifiers changed from: protected */
    public void loadStore(InputStream in) throws IOException {
        Certificate[] chain;
        DataInputStream dIn = new DataInputStream(in);
        for (int type = dIn.read(); type > 0; type = dIn.read()) {
            String alias = dIn.readUTF();
            Date date = new Date(dIn.readLong());
            int chainLength = dIn.readInt();
            if (chainLength != 0) {
                Certificate[] chain2 = new Certificate[chainLength];
                for (int i = 0; i != chainLength; i++) {
                    chain2[i] = decodeCertificate(dIn);
                }
                chain = chain2;
            } else {
                chain = null;
            }
            if (type == 1) {
                this.table.put(alias, new StoreEntry(alias, date, 1, decodeCertificate(dIn)));
            } else if (type == 2) {
                this.table.put(alias, new StoreEntry(alias, date, 2, decodeKey(dIn), chain));
            } else if (type == 3 || type == 4) {
                byte[] b = new byte[dIn.readInt()];
                dIn.readFully(b);
                this.table.put(alias, new StoreEntry(alias, date, type, b, chain));
            } else {
                throw new IOException("Unknown object type in store.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void saveStore(OutputStream out) throws IOException {
        Enumeration e = this.table.elements();
        DataOutputStream dOut = new DataOutputStream(out);
        while (e.hasMoreElements()) {
            StoreEntry entry = (StoreEntry) e.nextElement();
            dOut.write(entry.getType());
            dOut.writeUTF(entry.getAlias());
            dOut.writeLong(entry.getDate().getTime());
            Certificate[] chain = entry.getCertificateChain();
            if (chain == null) {
                dOut.writeInt(0);
            } else {
                dOut.writeInt(chain.length);
                for (int i = 0; i != chain.length; i++) {
                    encodeCertificate(chain[i], dOut);
                }
            }
            int i2 = entry.getType();
            if (i2 == 1) {
                encodeCertificate((Certificate) entry.getObject(), dOut);
            } else if (i2 == 2) {
                encodeKey((Key) entry.getObject(), dOut);
            } else if (i2 == 3 || i2 == 4) {
                byte[] b = (byte[]) entry.getObject();
                dOut.writeInt(b.length);
                dOut.write(b);
            } else {
                throw new IOException("Unknown object type in store.");
            }
        }
        dOut.write(0);
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream stream, char[] password) throws IOException {
        CipherParameters macParams;
        this.table.clear();
        if (stream != null) {
            DataInputStream dIn = new DataInputStream(stream);
            int version2 = dIn.readInt();
            if (version2 == 2 || version2 == 0 || version2 == 1) {
                int saltLength = dIn.readInt();
                if (saltLength > 0) {
                    byte[] salt = new byte[saltLength];
                    dIn.readFully(salt);
                    int iterationCount = dIn.readInt();
                    HMac hMac = new HMac(new SHA1Digest());
                    if (password == null || password.length == 0) {
                        loadStore(dIn);
                        dIn.readFully(new byte[hMac.getMacSize()]);
                        return;
                    }
                    byte[] passKey = PBEParametersGenerator.PKCS12PasswordToBytes(password);
                    PBEParametersGenerator pbeGen = new PKCS12ParametersGenerator(new SHA1Digest());
                    pbeGen.init(passKey, salt, iterationCount);
                    if (version2 != 2) {
                        macParams = pbeGen.generateDerivedMacParameters(hMac.getMacSize());
                    } else {
                        macParams = pbeGen.generateDerivedMacParameters(hMac.getMacSize() * 8);
                    }
                    Arrays.fill(passKey, (byte) 0);
                    hMac.init(macParams);
                    loadStore(new MacInputStream(dIn, hMac));
                    byte[] mac = new byte[hMac.getMacSize()];
                    hMac.doFinal(mac, 0);
                    byte[] oldMac = new byte[hMac.getMacSize()];
                    dIn.readFully(oldMac);
                    if (!Arrays.constantTimeAreEqual(mac, oldMac)) {
                        this.table.clear();
                        throw new IOException("KeyStore integrity check failed.");
                    }
                    return;
                }
                throw new IOException("Invalid salt detected");
            }
            throw new IOException("Wrong version of key store.");
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream stream, char[] password) throws IOException {
        DataOutputStream dOut = new DataOutputStream(stream);
        byte[] salt = new byte[20];
        int iterationCount = (this.random.nextInt() & 1023) + MIN_ITERATIONS;
        this.random.nextBytes(salt);
        dOut.writeInt(this.version);
        dOut.writeInt(salt.length);
        dOut.write(salt);
        dOut.writeInt(iterationCount);
        HMac hMac = new HMac(new SHA1Digest());
        MacOutputStream mOut = new MacOutputStream(hMac);
        PBEParametersGenerator pbeGen = new PKCS12ParametersGenerator(new SHA1Digest());
        byte[] passKey = PBEParametersGenerator.PKCS12PasswordToBytes(password);
        pbeGen.init(passKey, salt, iterationCount);
        if (this.version < 2) {
            hMac.init(pbeGen.generateDerivedMacParameters(hMac.getMacSize()));
        } else {
            hMac.init(pbeGen.generateDerivedMacParameters(hMac.getMacSize() * 8));
        }
        for (int i = 0; i != passKey.length; i++) {
            passKey[i] = 0;
        }
        saveStore(new TeeOutputStream(dOut, mOut));
        byte[] mac = new byte[hMac.getMacSize()];
        hMac.doFinal(mac, 0);
        dOut.write(mac);
        dOut.close();
    }

    public static class BouncyCastleStore extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        @Override // com.android.org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineLoad(InputStream stream, char[] password) throws IOException {
            this.table.clear();
            if (stream != null) {
                DataInputStream dIn = new DataInputStream(stream);
                int version = dIn.readInt();
                if (version == 2 || version == 0 || version == 1) {
                    byte[] salt = new byte[dIn.readInt()];
                    if (salt.length == 20) {
                        dIn.readFully(salt);
                        int iterationCount = dIn.readInt();
                        if (iterationCount < 0 || iterationCount > 65536) {
                            throw new IOException("Key store corrupted.");
                        }
                        CipherInputStream cIn = new CipherInputStream(dIn, makePBECipher(version == 0 ? "OldPBEWithSHAAndTwofish-CBC" : BcKeyStoreSpi.STORE_CIPHER, 2, password, salt, iterationCount));
                        Digest dig = new SHA1Digest();
                        loadStore(new DigestInputStream(cIn, dig));
                        byte[] hash = new byte[dig.getDigestSize()];
                        dig.doFinal(hash, 0);
                        byte[] oldHash = new byte[dig.getDigestSize()];
                        Streams.readFully(cIn, oldHash);
                        if (!Arrays.constantTimeAreEqual(hash, oldHash)) {
                            this.table.clear();
                            throw new IOException("KeyStore integrity check failed.");
                        }
                        return;
                    }
                    throw new IOException("Key store corrupted.");
                }
                throw new IOException("Wrong version of key store.");
            }
        }

        @Override // com.android.org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineStore(OutputStream stream, char[] password) throws IOException {
            DataOutputStream dOut = new DataOutputStream(stream);
            byte[] salt = new byte[20];
            int iterationCount = (this.random.nextInt() & 1023) + BcKeyStoreSpi.MIN_ITERATIONS;
            this.random.nextBytes(salt);
            dOut.writeInt(this.version);
            dOut.writeInt(salt.length);
            dOut.write(salt);
            dOut.writeInt(iterationCount);
            CipherOutputStream cOut = new CipherOutputStream(dOut, makePBECipher(BcKeyStoreSpi.STORE_CIPHER, 1, password, salt, iterationCount));
            DigestOutputStream dgOut = new DigestOutputStream(new SHA1Digest());
            saveStore(new TeeOutputStream(cOut, dgOut));
            cOut.write(dgOut.getDigest());
            cOut.close();
        }
    }

    public static class Std extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    public static class Version1 extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }
}
