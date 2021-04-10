package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateVersion implements CertAttrSet {
    int version;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "version";
    }

    private int getVersion() {
        return this.version;
    }

    private void construct(DerValue derValue) {
        if (derValue.isConstructed() && derValue.isContextSpecific()) {
            DerValue derValue2 = derValue.data.getDerValue();
            this.version = derValue2.getInteger();
            if (derValue2.data.available() != 0) {
                throw new IOException("X.509 version, bad format");
            }
        }
    }

    public CertificateVersion() {
        this.version = 0;
        this.version = 0;
    }

    public CertificateVersion(DerValue derValue) {
        this.version = 0;
        this.version = 0;
        construct(derValue);
    }

    public String toString() {
        return "Version: V" + (this.version + 1);
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        if (this.version != 0) {
            DerOutputStream derOutputStream = new DerOutputStream();
            derOutputStream.putInteger(this.version);
            DerOutputStream derOutputStream2 = new DerOutputStream();
            derOutputStream2.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), derOutputStream);
            outputStream.write(derOutputStream2.toByteArray());
        }
    }

    public Integer get(String str) {
        if (str.equalsIgnoreCase("number")) {
            return new Integer(getVersion());
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateVersion.");
    }

    public int compare(int i) {
        return this.version - i;
    }
}
