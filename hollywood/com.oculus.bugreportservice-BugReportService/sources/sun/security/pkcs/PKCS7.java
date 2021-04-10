package sun.security.pkcs;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertInfo;

public class PKCS7 {
    private Principal[] certIssuerNames;
    private X509Certificate[] certificates = null;
    private ContentInfo contentInfo = null;
    private ObjectIdentifier contentType;
    private X509CRL[] crls = null;
    private AlgorithmId[] digestAlgorithmIds = null;
    private boolean oldStyle = false;
    private SignerInfo[] signerInfos = null;
    private BigInteger version = null;

    public PKCS7(byte[] bArr) {
        try {
            parse(new DerInputStream(bArr));
        } catch (IOException e) {
            ParsingException parsingException = new ParsingException("Unable to parse the encoded bytes");
            parsingException.initCause(e);
            throw parsingException;
        }
    }

    private void parse(DerInputStream derInputStream) {
        try {
            derInputStream.mark(derInputStream.available());
            parse(derInputStream, false);
        } catch (IOException e) {
            try {
                derInputStream.reset();
                parse(derInputStream, true);
                this.oldStyle = true;
            } catch (IOException e2) {
                ParsingException parsingException = new ParsingException(e2.getMessage());
                parsingException.initCause(e);
                parsingException.addSuppressed(e2);
                throw parsingException;
            }
        }
    }

    private void parse(DerInputStream derInputStream, boolean z) {
        this.contentInfo = new ContentInfo(derInputStream, z);
        ContentInfo contentInfo2 = this.contentInfo;
        this.contentType = contentInfo2.contentType;
        DerValue content = contentInfo2.getContent();
        if (this.contentType.equals(ContentInfo.SIGNED_DATA_OID)) {
            parseSignedData(content);
        } else if (this.contentType.equals(ContentInfo.OLD_SIGNED_DATA_OID)) {
            parseOldSignedData(content);
        } else if (this.contentType.equals(ContentInfo.NETSCAPE_CERT_SEQUENCE_OID)) {
            parseNetscapeCertChain(content);
        } else {
            throw new ParsingException("content type " + this.contentType + " not supported.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseNetscapeCertChain(sun.security.util.DerValue r9) {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseNetscapeCertChain(sun.security.util.DerValue):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0135  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseSignedData(sun.security.util.DerValue r14) {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseSignedData(sun.security.util.DerValue):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseOldSignedData(sun.security.util.DerValue r13) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseOldSignedData(sun.security.util.DerValue):void");
    }

    public SignerInfo verify(SignerInfo signerInfo, byte[] bArr) {
        return signerInfo.verify(this, bArr);
    }

    public SignerInfo[] verify(byte[] bArr) {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            SignerInfo[] signerInfoArr = this.signerInfos;
            if (i >= signerInfoArr.length) {
                break;
            }
            SignerInfo verify = verify(signerInfoArr[i], bArr);
            if (verify != null) {
                vector.addElement(verify);
            }
            i++;
        }
        if (vector.isEmpty()) {
            return null;
        }
        SignerInfo[] signerInfoArr2 = new SignerInfo[vector.size()];
        vector.copyInto(signerInfoArr2);
        return signerInfoArr2;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public X509Certificate[] getCertificates() {
        X509Certificate[] x509CertificateArr = this.certificates;
        if (x509CertificateArr != null) {
            return (X509Certificate[]) x509CertificateArr.clone();
        }
        return null;
    }

    public X509Certificate getCertificate(BigInteger bigInteger, X500Name x500Name) {
        if (this.certificates == null) {
            return null;
        }
        if (this.certIssuerNames == null) {
            populateCertIssuerNames();
        }
        int i = 0;
        while (true) {
            X509Certificate[] x509CertificateArr = this.certificates;
            if (i >= x509CertificateArr.length) {
                return null;
            }
            X509Certificate x509Certificate = x509CertificateArr[i];
            if (bigInteger.equals(x509Certificate.getSerialNumber()) && x500Name.equals(this.certIssuerNames[i])) {
                return x509Certificate;
            }
            i++;
        }
    }

    private void populateCertIssuerNames() {
        Principal principal;
        X509Certificate[] x509CertificateArr = this.certificates;
        if (x509CertificateArr != null) {
            this.certIssuerNames = new Principal[x509CertificateArr.length];
            int i = 0;
            while (true) {
                X509Certificate[] x509CertificateArr2 = this.certificates;
                if (i < x509CertificateArr2.length) {
                    X509Certificate x509Certificate = x509CertificateArr2[i];
                    Principal issuerDN = x509Certificate.getIssuerDN();
                    if (!(issuerDN instanceof X500Name)) {
                        try {
                            principal = (Principal) new X509CertInfo(x509Certificate.getTBSCertificate()).get("issuer.dname");
                        } catch (Exception unused) {
                        }
                        this.certIssuerNames[i] = principal;
                        i++;
                    }
                    principal = issuerDN;
                    this.certIssuerNames[i] = principal;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public String toString() {
        String str = "" + this.contentInfo + "\n";
        if (this.version != null) {
            str = str + "PKCS7 :: version: " + Debug.toHexString(this.version) + "\n";
        }
        if (this.digestAlgorithmIds != null) {
            String str2 = str + "PKCS7 :: digest AlgorithmIds: \n";
            for (int i = 0; i < this.digestAlgorithmIds.length; i++) {
                str2 = str2 + "\t" + this.digestAlgorithmIds[i] + "\n";
            }
            str = str2;
        }
        if (this.certificates != null) {
            String str3 = str + "PKCS7 :: certificates: \n";
            for (int i2 = 0; i2 < this.certificates.length; i2++) {
                str3 = str3 + "\t" + i2 + ".   " + this.certificates[i2] + "\n";
            }
            str = str3;
        }
        if (this.crls != null) {
            String str4 = str + "PKCS7 :: crls: \n";
            for (int i3 = 0; i3 < this.crls.length; i3++) {
                str4 = str4 + "\t" + i3 + ".   " + this.crls[i3] + "\n";
            }
            str = str4;
        }
        if (this.signerInfos != null) {
            str = str + "PKCS7 :: signer infos: \n";
            for (int i4 = 0; i4 < this.signerInfos.length; i4++) {
                str = str + "\t" + i4 + ".  " + this.signerInfos[i4] + "\n";
            }
        }
        return str;
    }

    public boolean isOldStyle() {
        return this.oldStyle;
    }

    /* access modifiers changed from: private */
    public static class VerbatimX509Certificate extends WrappedX509Certificate {
        private byte[] encodedVerbatim;

        public VerbatimX509Certificate(X509Certificate x509Certificate, byte[] bArr) {
            super(x509Certificate);
            this.encodedVerbatim = bArr;
        }

        @Override // java.security.cert.Certificate, sun.security.pkcs.PKCS7.WrappedX509Certificate
        public byte[] getEncoded() {
            return this.encodedVerbatim;
        }
    }

    private static class WrappedX509Certificate extends X509Certificate {
        private final X509Certificate wrapped;

        public WrappedX509Certificate(X509Certificate x509Certificate) {
            this.wrapped = x509Certificate;
        }

        @Override // java.security.cert.X509Extension
        public byte[] getExtensionValue(String str) {
            return this.wrapped.getExtensionValue(str);
        }

        @Override // java.security.cert.X509Extension
        public boolean hasUnsupportedCriticalExtension() {
            return this.wrapped.hasUnsupportedCriticalExtension();
        }

        @Override // java.security.cert.X509Certificate
        public BigInteger getSerialNumber() {
            return this.wrapped.getSerialNumber();
        }

        @Override // java.security.cert.X509Certificate
        public Principal getIssuerDN() {
            return this.wrapped.getIssuerDN();
        }

        @Override // java.security.cert.X509Certificate
        public Principal getSubjectDN() {
            return this.wrapped.getSubjectDN();
        }

        @Override // java.security.cert.X509Certificate
        public byte[] getTBSCertificate() {
            return this.wrapped.getTBSCertificate();
        }

        @Override // java.security.cert.X509Certificate
        public boolean[] getKeyUsage() {
            return this.wrapped.getKeyUsage();
        }

        @Override // java.security.cert.Certificate
        public byte[] getEncoded() {
            return this.wrapped.getEncoded();
        }

        @Override // java.security.cert.Certificate
        public void verify(PublicKey publicKey) {
            this.wrapped.verify(publicKey);
        }

        public String toString() {
            return this.wrapped.toString();
        }

        @Override // java.security.cert.Certificate
        public PublicKey getPublicKey() {
            return this.wrapped.getPublicKey();
        }

        @Override // java.security.cert.X509Certificate
        public X500Principal getIssuerX500Principal() {
            return this.wrapped.getIssuerX500Principal();
        }

        @Override // java.security.cert.X509Certificate
        public Collection getSubjectAlternativeNames() {
            return this.wrapped.getSubjectAlternativeNames();
        }

        @Override // java.security.cert.X509Certificate
        public X500Principal getSubjectX500Principal() {
            return this.wrapped.getSubjectX500Principal();
        }
    }
}
