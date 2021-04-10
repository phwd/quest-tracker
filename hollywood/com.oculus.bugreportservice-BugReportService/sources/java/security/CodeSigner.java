package java.security;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.cert.CertPath;

public final class CodeSigner implements Serializable {
    private static final long serialVersionUID = 6819288105193937581L;
    private transient int myhash = -1;
    private CertPath signerCertPath;
    private Timestamp timestamp;

    public CodeSigner(CertPath certPath, Timestamp timestamp2) {
        if (certPath != null) {
            this.signerCertPath = certPath;
            this.timestamp = timestamp2;
            return;
        }
        throw new NullPointerException();
    }

    public CertPath getSignerCertPath() {
        return this.signerCertPath;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        if (this.myhash == -1) {
            if (this.timestamp == null) {
                this.myhash = this.signerCertPath.hashCode();
            } else {
                this.myhash = this.signerCertPath.hashCode() + this.timestamp.hashCode();
            }
        }
        return this.myhash;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r2.equals(r1) != false) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x002d
            boolean r1 = r4 instanceof java.security.CodeSigner
            if (r1 != 0) goto L_0x0008
            goto L_0x002d
        L_0x0008:
            java.security.CodeSigner r4 = (java.security.CodeSigner) r4
            if (r3 != r4) goto L_0x000e
            r3 = 1
            return r3
        L_0x000e:
            java.security.Timestamp r1 = r4.getTimestamp()
            java.security.Timestamp r2 = r3.timestamp
            if (r2 != 0) goto L_0x0019
            if (r1 == 0) goto L_0x0022
            return r0
        L_0x0019:
            if (r1 == 0) goto L_0x002d
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0022
            goto L_0x002d
        L_0x0022:
            java.security.cert.CertPath r3 = r3.signerCertPath
            java.security.cert.CertPath r4 = r4.getSignerCertPath()
            boolean r3 = r3.equals(r4)
            return r3
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.CodeSigner.equals(java.lang.Object):boolean");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        stringBuffer.append("Signer: " + this.signerCertPath.getCertificates().get(0));
        if (this.timestamp != null) {
            stringBuffer.append("timestamp: " + this.timestamp);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
