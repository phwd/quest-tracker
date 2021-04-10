package java.security.cert;

import java.io.ByteArrayInputStream;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.Arrays;
import sun.security.x509.X509CertImpl;

public abstract class Certificate implements Serializable {
    private static final long serialVersionUID = -3585440601605666277L;
    private int hash = -1;
    private final String type;

    public abstract byte[] getEncoded();

    public abstract PublicKey getPublicKey();

    public abstract void verify(PublicKey publicKey);

    protected Certificate(String str) {
        this.type = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Certificate)) {
            return false;
        }
        try {
            return Arrays.equals(X509CertImpl.getEncodedInternal(this), X509CertImpl.getEncodedInternal((Certificate) obj));
        } catch (CertificateException unused) {
            return false;
        }
    }

    public int hashCode() {
        int i = this.hash;
        if (i == -1) {
            try {
                i = Arrays.hashCode(X509CertImpl.getEncodedInternal(this));
            } catch (CertificateException unused) {
                i = 0;
            }
            this.hash = i;
        }
        return i;
    }

    protected static class CertificateRep implements Serializable {
        private static final long serialVersionUID = -8563758940495660020L;
        private byte[] data;
        private String type;

        protected CertificateRep(String str, byte[] bArr) {
            this.type = str;
            this.data = bArr;
        }

        /* access modifiers changed from: protected */
        public Object readResolve() {
            try {
                return CertificateFactory.getInstance(this.type).generateCertificate(new ByteArrayInputStream(this.data));
            } catch (CertificateException e) {
                throw new NotSerializableException("java.security.cert.Certificate: " + this.type + ": " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        try {
            return new CertificateRep(this.type, getEncoded());
        } catch (CertificateException e) {
            throw new NotSerializableException("java.security.cert.Certificate: " + this.type + ": " + e.getMessage());
        }
    }
}
