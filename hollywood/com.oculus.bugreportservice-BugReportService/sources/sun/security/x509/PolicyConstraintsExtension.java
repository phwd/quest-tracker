package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class PolicyConstraintsExtension extends Extension implements CertAttrSet {
    private int inhibit;
    private int require;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "PolicyConstraints";
    }

    private void encodeThis() {
        if (this.require == -1 && this.inhibit == -1) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        if (this.require != -1) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            derOutputStream3.putInteger(this.require);
            derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), derOutputStream3);
        }
        if (this.inhibit != -1) {
            DerOutputStream derOutputStream4 = new DerOutputStream();
            derOutputStream4.putInteger(this.inhibit);
            derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream4);
        }
        derOutputStream2.write((byte) 48, derOutputStream);
        this.extensionValue = derOutputStream2.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        String str2;
        String str3 = super.toString() + "PolicyConstraints: [  Require: ";
        if (this.require == -1) {
            str = str3 + "unspecified;";
        } else {
            str = str3 + this.require + ";";
        }
        String str4 = str + "\tInhibit: ";
        if (this.inhibit == -1) {
            str2 = str4 + "unspecified";
        } else {
            str2 = str4 + this.inhibit;
        }
        return str2 + " ]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.PolicyConstraints_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
