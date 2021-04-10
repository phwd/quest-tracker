package java.security.cert;

import java.io.ByteArrayInputStream;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.List;

public abstract class CertPath implements Serializable {
    private static final long serialVersionUID = 6068470306649138683L;
    private String type;

    public abstract List getCertificates();

    public abstract byte[] getEncoded();

    public String getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CertPath)) {
            return false;
        }
        CertPath certPath = (CertPath) obj;
        if (!certPath.getType().equals(this.type)) {
            return false;
        }
        return getCertificates().equals(certPath.getCertificates());
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + getCertificates().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n" + this.type + " Cert Path: length = " + getCertificates().size() + ".\n");
        stringBuffer.append("[\n");
        int i = 1;
        for (Certificate certificate : getCertificates()) {
            stringBuffer.append("=========================================================Certificate " + i + " start.\n");
            stringBuffer.append(certificate.toString());
            stringBuffer.append("\n=========================================================Certificate " + i + " end.\n\n\n");
            i++;
        }
        stringBuffer.append("\n]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        try {
            return new CertPathRep(this.type, getEncoded());
        } catch (CertificateException e) {
            NotSerializableException notSerializableException = new NotSerializableException("java.security.cert.CertPath: " + this.type);
            notSerializableException.initCause(e);
            throw notSerializableException;
        }
    }

    protected static class CertPathRep implements Serializable {
        private static final long serialVersionUID = 3015633072427920915L;
        private byte[] data;
        private String type;

        protected CertPathRep(String str, byte[] bArr) {
            this.type = str;
            this.data = bArr;
        }

        /* access modifiers changed from: protected */
        public Object readResolve() {
            try {
                return CertificateFactory.getInstance(this.type).generateCertPath(new ByteArrayInputStream(this.data));
            } catch (CertificateException e) {
                NotSerializableException notSerializableException = new NotSerializableException("java.security.cert.CertPath: " + this.type);
                notSerializableException.initCause(e);
                throw notSerializableException;
            }
        }
    }
}
