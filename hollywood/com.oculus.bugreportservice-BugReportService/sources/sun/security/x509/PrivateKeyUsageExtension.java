package sun.security.x509;

import java.io.OutputStream;
import java.util.Date;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class PrivateKeyUsageExtension extends Extension implements CertAttrSet {
    private Date notAfter;
    private Date notBefore;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "PrivateKeyUsage";
    }

    private void encodeThis() {
        if (this.notBefore == null && this.notAfter == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        if (this.notBefore != null) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            derOutputStream3.putGeneralizedTime(this.notBefore);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), derOutputStream3);
        }
        if (this.notAfter != null) {
            DerOutputStream derOutputStream4 = new DerOutputStream();
            derOutputStream4.putGeneralizedTime(this.notAfter);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream4);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("PrivateKeyUsage: [\n");
        String str2 = "";
        if (this.notBefore == null) {
            str = str2;
        } else {
            str = "From: " + this.notBefore.toString() + ", ";
        }
        sb.append(str);
        if (this.notAfter != null) {
            str2 = "To: " + this.notAfter.toString();
        }
        sb.append(str2);
        sb.append("]\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.PrivateKeyUsage_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
