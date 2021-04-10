package sun.security.pkcs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.CryptoPrimitive;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.SignatureException;
import java.security.Timestamp;
import java.security.cert.CertPath;
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
    private static final Set DIGEST_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.MESSAGE_DIGEST));
    private static final DisabledAlgorithmConstraints JAR_DISABLED_CHECK = new DisabledAlgorithmConstraints("jdk.jar.disabledAlgorithms");
    private static final Set SIG_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.SIGNATURE));
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

    public SignerInfo(DerInputStream derInputStream) {
        this(derInputStream, false);
    }

    public SignerInfo(DerInputStream derInputStream, boolean z) {
        this.hasTimestamp = true;
        this.version = derInputStream.getBigInteger();
        DerValue[] sequence = derInputStream.getSequence(2);
        this.issuerName = new X500Name(new DerValue((byte) 48, sequence[0].toByteArray()));
        this.certificateSerialNumber = sequence[1].getBigInteger();
        this.digestAlgorithmId = AlgorithmId.parse(derInputStream.getDerValue());
        if (z) {
            derInputStream.getSet(0);
        } else if (((byte) derInputStream.peekByte()) == -96) {
            this.authenticatedAttributes = new PKCS9Attributes(derInputStream);
        }
        this.digestEncryptionAlgorithmId = AlgorithmId.parse(derInputStream.getDerValue());
        this.encryptedDigest = derInputStream.getOctetString();
        if (z) {
            derInputStream.getSet(0);
        } else if (derInputStream.available() != 0 && ((byte) derInputStream.peekByte()) == -95) {
            this.unauthenticatedAttributes = new PKCS9Attributes(derInputStream, true);
        }
        if (derInputStream.available() != 0) {
            throw new ParsingException("extra data at the end");
        }
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putInteger(this.version);
        DerOutputStream derOutputStream2 = new DerOutputStream();
        this.issuerName.encode(derOutputStream2);
        derOutputStream2.putInteger(this.certificateSerialNumber);
        derOutputStream.write((byte) 48, derOutputStream2);
        this.digestAlgorithmId.encode(derOutputStream);
        PKCS9Attributes pKCS9Attributes = this.authenticatedAttributes;
        if (pKCS9Attributes != null) {
            pKCS9Attributes.encode((byte) -96, derOutputStream);
        }
        this.digestEncryptionAlgorithmId.encode(derOutputStream);
        derOutputStream.putOctetString(this.encryptedDigest);
        PKCS9Attributes pKCS9Attributes2 = this.unauthenticatedAttributes;
        if (pKCS9Attributes2 != null) {
            pKCS9Attributes2.encode((byte) -95, derOutputStream);
        }
        DerOutputStream derOutputStream3 = new DerOutputStream();
        derOutputStream3.write((byte) 48, derOutputStream);
        outputStream.write(derOutputStream3.toByteArray());
    }

    public X509Certificate getCertificate(PKCS7 pkcs7) {
        return pkcs7.getCertificate(this.certificateSerialNumber, this.issuerName);
    }

    public ArrayList getCertificateChain(PKCS7 pkcs7) {
        boolean z;
        X509Certificate certificate = pkcs7.getCertificate(this.certificateSerialNumber, this.issuerName);
        if (certificate == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(certificate);
        X509Certificate[] certificates = pkcs7.getCertificates();
        if (certificates == null || certificate.getSubjectDN().equals(certificate.getIssuerDN())) {
            return arrayList;
        }
        Principal issuerDN = certificate.getIssuerDN();
        int i = 0;
        do {
            int i2 = i;
            while (true) {
                if (i2 >= certificates.length) {
                    z = false;
                    continue;
                    break;
                } else if (issuerDN.equals(certificates[i2].getSubjectDN())) {
                    arrayList.add(certificates[i2]);
                    if (certificates[i2].getSubjectDN().equals(certificates[i2].getIssuerDN())) {
                        i = certificates.length;
                    } else {
                        issuerDN = certificates[i2].getIssuerDN();
                        X509Certificate x509Certificate = certificates[i];
                        certificates[i] = certificates[i2];
                        certificates[i2] = x509Certificate;
                        i++;
                    }
                    z = true;
                    continue;
                } else {
                    i2++;
                }
            }
        } while (z);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public SignerInfo verify(PKCS7 pkcs7, byte[] bArr) {
        try {
            return verify(pkcs7, new ByteArrayInputStream(bArr));
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0167, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0182, code lost:
        throw new java.security.SignatureException("InvalidKey: " + r9.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0183, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x019e, code lost:
        throw new java.security.SignatureException("IO error verifying signature:\n" + r9.getMessage());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0167 A[ExcHandler: InvalidKeyException (r9v4 'e' java.security.InvalidKeyException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public sun.security.pkcs.SignerInfo verify(sun.security.pkcs.PKCS7 r10, java.io.InputStream r11) {
        /*
        // Method dump skipped, instructions count: 415
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.SignerInfo.verify(sun.security.pkcs.PKCS7, java.io.InputStream):sun.security.pkcs.SignerInfo");
    }

    public AlgorithmId getDigestAlgorithmId() {
        return this.digestAlgorithmId;
    }

    public AlgorithmId getDigestEncryptionAlgorithmId() {
        return this.digestEncryptionAlgorithmId;
    }

    public PKCS7 getTsToken() {
        PKCS9Attribute attribute;
        PKCS9Attributes pKCS9Attributes = this.unauthenticatedAttributes;
        if (pKCS9Attributes == null || (attribute = pKCS9Attributes.getAttribute(PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_OID)) == null) {
            return null;
        }
        return new PKCS7((byte[]) attribute.getValue());
    }

    public Timestamp getTimestamp() {
        if (this.timestamp != null || !this.hasTimestamp) {
            return this.timestamp;
        }
        PKCS7 tsToken = getTsToken();
        if (tsToken == null) {
            this.hasTimestamp = false;
            return null;
        }
        byte[] data = tsToken.getContentInfo().getData();
        CertPath generateCertPath = CertificateFactory.getInstance("X.509").generateCertPath(tsToken.verify(data)[0].getCertificateChain(tsToken));
        TimestampToken timestampToken = new TimestampToken(data);
        verifyTimestamp(timestampToken);
        this.timestamp = new Timestamp(timestampToken.getDate(), generateCertPath);
        return this.timestamp;
    }

    private void verifyTimestamp(TimestampToken timestampToken) {
        String name = timestampToken.getHashAlgorithm().getName();
        if (JAR_DISABLED_CHECK.permits(DIGEST_PRIMITIVE_SET, name, null)) {
            if (!Arrays.equals(timestampToken.getHashedMessage(), MessageDigest.getInstance(name).digest(this.encryptedDigest))) {
                throw new SignatureException("Signature timestamp (#" + timestampToken.getSerialNumber() + ") generated on " + timestampToken.getDate() + " is inapplicable");
            }
            return;
        }
        throw new SignatureException("Timestamp token digest check failed. Disabled algorithm used: " + name);
    }

    public String toString() {
        HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
        String str = ((("" + "Signer Info for (issuer): " + this.issuerName + "\n") + "\tversion: " + Debug.toHexString(this.version) + "\n") + "\tcertificateSerialNumber: " + Debug.toHexString(this.certificateSerialNumber) + "\n") + "\tdigestAlgorithmId: " + this.digestAlgorithmId + "\n";
        if (this.authenticatedAttributes != null) {
            str = str + "\tauthenticatedAttributes: " + this.authenticatedAttributes + "\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str + "\tdigestEncryptionAlgorithmId: " + this.digestEncryptionAlgorithmId + "\n");
        sb.append("\tencryptedDigest: \n");
        hexDumpEncoder.encodeBuffer(this.encryptedDigest);
        throw null;
    }
}
