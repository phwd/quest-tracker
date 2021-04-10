package sun.security.pkcs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.CryptoPrimitive;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import sun.misc.HexDumpEncoder;
import sun.security.timestamp.TimestampToken;
import sun.security.util.Debug;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.DisabledAlgorithmConstraints;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;

public class SignerInfo implements DerEncoder {
    private static final Set<CryptoPrimitive> DIGEST_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.MESSAGE_DIGEST));
    private static final DisabledAlgorithmConstraints JAR_DISABLED_CHECK = new DisabledAlgorithmConstraints(DisabledAlgorithmConstraints.PROPERTY_JAR_DISABLED_ALGS);
    private static final Set<CryptoPrimitive> SIG_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.SIGNATURE));
    PKCS9Attributes authenticatedAttributes;
    BigInteger certificateSerialNumber;
    AlgorithmId digestAlgorithmId;
    AlgorithmId digestEncryptionAlgorithmId;
    byte[] encryptedDigest;
    private boolean hasTimestamp;
    X500Name issuerName;
    Timestamp timestamp;
    PKCS9Attributes unauthenticatedAttributes;
    BigInteger version;

    public SignerInfo() {
        this.hasTimestamp = true;
    }

    public SignerInfo(X500Name issuerName2, BigInteger serial, AlgorithmId digestAlgorithmId2, AlgorithmId digestEncryptionAlgorithmId2, byte[] encryptedDigest2) {
        this.hasTimestamp = true;
        this.version = BigInteger.ONE;
        this.issuerName = issuerName2;
        this.certificateSerialNumber = serial;
        this.digestAlgorithmId = digestAlgorithmId2;
        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId2;
        this.encryptedDigest = encryptedDigest2;
    }

    public SignerInfo(X500Name issuerName2, BigInteger serial, AlgorithmId digestAlgorithmId2, PKCS9Attributes authenticatedAttributes2, AlgorithmId digestEncryptionAlgorithmId2, byte[] encryptedDigest2, PKCS9Attributes unauthenticatedAttributes2) {
        this.hasTimestamp = true;
        this.version = BigInteger.ONE;
        this.issuerName = issuerName2;
        this.certificateSerialNumber = serial;
        this.digestAlgorithmId = digestAlgorithmId2;
        this.authenticatedAttributes = authenticatedAttributes2;
        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId2;
        this.encryptedDigest = encryptedDigest2;
        this.unauthenticatedAttributes = unauthenticatedAttributes2;
    }

    public SignerInfo(DerInputStream derin) throws IOException, ParsingException {
        this(derin, false);
    }

    public SignerInfo(DerInputStream derin, boolean oldStyle) throws IOException, ParsingException {
        this.hasTimestamp = true;
        this.version = derin.getBigInteger();
        DerValue[] issuerAndSerialNumber = derin.getSequence(2);
        this.issuerName = new X500Name(new DerValue((byte) 48, issuerAndSerialNumber[0].toByteArray()));
        this.certificateSerialNumber = issuerAndSerialNumber[1].getBigInteger();
        this.digestAlgorithmId = AlgorithmId.parse(derin.getDerValue());
        if (oldStyle) {
            derin.getSet(0);
        } else if (((byte) derin.peekByte()) == -96) {
            this.authenticatedAttributes = new PKCS9Attributes(derin);
        }
        this.digestEncryptionAlgorithmId = AlgorithmId.parse(derin.getDerValue());
        this.encryptedDigest = derin.getOctetString();
        if (oldStyle) {
            derin.getSet(0);
        } else if (derin.available() != 0 && ((byte) derin.peekByte()) == -95) {
            this.unauthenticatedAttributes = new PKCS9Attributes(derin, true);
        }
        if (derin.available() != 0) {
            throw new ParsingException("extra data at the end");
        }
    }

    public void encode(DerOutputStream out) throws IOException {
        derEncode(out);
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream out) throws IOException {
        DerOutputStream seq = new DerOutputStream();
        seq.putInteger(this.version);
        DerOutputStream issuerAndSerialNumber = new DerOutputStream();
        this.issuerName.encode(issuerAndSerialNumber);
        issuerAndSerialNumber.putInteger(this.certificateSerialNumber);
        seq.write((byte) 48, issuerAndSerialNumber);
        this.digestAlgorithmId.encode(seq);
        PKCS9Attributes pKCS9Attributes = this.authenticatedAttributes;
        if (pKCS9Attributes != null) {
            pKCS9Attributes.encode((byte) -96, seq);
        }
        this.digestEncryptionAlgorithmId.encode(seq);
        seq.putOctetString(this.encryptedDigest);
        PKCS9Attributes pKCS9Attributes2 = this.unauthenticatedAttributes;
        if (pKCS9Attributes2 != null) {
            pKCS9Attributes2.encode((byte) -95, seq);
        }
        DerOutputStream tmp = new DerOutputStream();
        tmp.write((byte) 48, seq);
        out.write(tmp.toByteArray());
    }

    public X509Certificate getCertificate(PKCS7 block) throws IOException {
        return block.getCertificate(this.certificateSerialNumber, this.issuerName);
    }

    public ArrayList<X509Certificate> getCertificateChain(PKCS7 block) throws IOException {
        boolean match;
        X509Certificate userCert = block.getCertificate(this.certificateSerialNumber, this.issuerName);
        if (userCert == null) {
            return null;
        }
        ArrayList<X509Certificate> certList = new ArrayList<>();
        certList.add(userCert);
        X509Certificate[] pkcsCerts = block.getCertificates();
        if (pkcsCerts == null || userCert.getSubjectDN().equals(userCert.getIssuerDN())) {
            return certList;
        }
        Principal issuer = userCert.getIssuerDN();
        int start = 0;
        do {
            match = false;
            int i = start;
            while (true) {
                if (i >= pkcsCerts.length) {
                    break;
                } else if (issuer.equals(pkcsCerts[i].getSubjectDN())) {
                    certList.add(pkcsCerts[i]);
                    if (pkcsCerts[i].getSubjectDN().equals(pkcsCerts[i].getIssuerDN())) {
                        start = pkcsCerts.length;
                    } else {
                        issuer = pkcsCerts[i].getIssuerDN();
                        X509Certificate tmpCert = pkcsCerts[start];
                        pkcsCerts[start] = pkcsCerts[i];
                        pkcsCerts[i] = tmpCert;
                        start++;
                    }
                    match = true;
                    continue;
                } else {
                    i++;
                }
            }
        } while (match);
        return certList;
    }

    /* access modifiers changed from: package-private */
    public SignerInfo verify(PKCS7 block, byte[] data) throws NoSuchAlgorithmException, SignatureException {
        try {
            return verify(block, new ByteArrayInputStream(data));
        } catch (IOException e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0183, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0185, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0183 A[ExcHandler: InvalidKeyException (e java.security.InvalidKeyException), Splitter:B:7:0x0016] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public sun.security.pkcs.SignerInfo verify(sun.security.pkcs.PKCS7 r17, java.io.InputStream r18) throws java.security.NoSuchAlgorithmException, java.security.SignatureException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 451
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.SignerInfo.verify(sun.security.pkcs.PKCS7, java.io.InputStream):sun.security.pkcs.SignerInfo");
    }

    /* access modifiers changed from: package-private */
    public SignerInfo verify(PKCS7 block) throws NoSuchAlgorithmException, SignatureException {
        return verify(block, (byte[]) null);
    }

    public BigInteger getVersion() {
        return this.version;
    }

    public X500Name getIssuerName() {
        return this.issuerName;
    }

    public BigInteger getCertificateSerialNumber() {
        return this.certificateSerialNumber;
    }

    public AlgorithmId getDigestAlgorithmId() {
        return this.digestAlgorithmId;
    }

    public PKCS9Attributes getAuthenticatedAttributes() {
        return this.authenticatedAttributes;
    }

    public AlgorithmId getDigestEncryptionAlgorithmId() {
        return this.digestEncryptionAlgorithmId;
    }

    public byte[] getEncryptedDigest() {
        return this.encryptedDigest;
    }

    public PKCS9Attributes getUnauthenticatedAttributes() {
        return this.unauthenticatedAttributes;
    }

    public PKCS7 getTsToken() throws IOException {
        PKCS9Attribute tsTokenAttr;
        PKCS9Attributes pKCS9Attributes = this.unauthenticatedAttributes;
        if (pKCS9Attributes == null || (tsTokenAttr = pKCS9Attributes.getAttribute(PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_OID)) == null) {
            return null;
        }
        return new PKCS7((byte[]) tsTokenAttr.getValue());
    }

    public Timestamp getTimestamp() throws IOException, NoSuchAlgorithmException, SignatureException, CertificateException {
        if (this.timestamp != null || !this.hasTimestamp) {
            return this.timestamp;
        }
        PKCS7 tsToken = getTsToken();
        if (tsToken == null) {
            this.hasTimestamp = false;
            return null;
        }
        byte[] encTsTokenInfo = tsToken.getContentInfo().getData();
        CertPath tsaChain = CertificateFactory.getInstance("X.509").generateCertPath(tsToken.verify(encTsTokenInfo)[0].getCertificateChain(tsToken));
        TimestampToken tsTokenInfo = new TimestampToken(encTsTokenInfo);
        verifyTimestamp(tsTokenInfo);
        this.timestamp = new Timestamp(tsTokenInfo.getDate(), tsaChain);
        return this.timestamp;
    }

    private void verifyTimestamp(TimestampToken token) throws NoSuchAlgorithmException, SignatureException {
        String digestAlgname = token.getHashAlgorithm().getName();
        if (JAR_DISABLED_CHECK.permits(DIGEST_PRIMITIVE_SET, digestAlgname, null)) {
            if (!Arrays.equals(token.getHashedMessage(), MessageDigest.getInstance(digestAlgname).digest(this.encryptedDigest))) {
                throw new SignatureException("Signature timestamp (#" + ((Object) token.getSerialNumber()) + ") generated on " + ((Object) token.getDate()) + " is inapplicable");
            }
            return;
        }
        throw new SignatureException("Timestamp token digest check failed. Disabled algorithm used: " + digestAlgname);
    }

    public String toString() {
        HexDumpEncoder hexDump = new HexDumpEncoder();
        String out = ((("" + "Signer Info for (issuer): " + ((Object) this.issuerName) + "\n") + "\tversion: " + Debug.toHexString(this.version) + "\n") + "\tcertificateSerialNumber: " + Debug.toHexString(this.certificateSerialNumber) + "\n") + "\tdigestAlgorithmId: " + ((Object) this.digestAlgorithmId) + "\n";
        if (this.authenticatedAttributes != null) {
            out = out + "\tauthenticatedAttributes: " + ((Object) this.authenticatedAttributes) + "\n";
        }
        String out2 = (out + "\tdigestEncryptionAlgorithmId: " + ((Object) this.digestEncryptionAlgorithmId) + "\n") + "\tencryptedDigest: \n" + hexDump.encodeBuffer(this.encryptedDigest) + "\n";
        if (this.unauthenticatedAttributes == null) {
            return out2;
        }
        return out2 + "\tunauthenticatedAttributes: " + ((Object) this.unauthenticatedAttributes) + "\n";
    }
}
