package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;

public class BasicConstraintsExtension extends Extension implements CertAttrSet {
    private boolean ca;
    private int pathLen;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "BasicConstraints";
    }

    private void encodeThis() {
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        boolean z = this.ca;
        if (z) {
            derOutputStream2.putBoolean(z);
            int i = this.pathLen;
            if (i >= 0) {
                derOutputStream2.putInteger(i);
            }
        }
        derOutputStream.write((byte) 48, derOutputStream2);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + "BasicConstraints:[\n");
        sb.append(this.ca ? "  CA:true" : "  CA:false");
        sb.append("\n");
        String sb2 = sb.toString();
        if (this.pathLen >= 0) {
            str = sb2 + "  PathLen:" + this.pathLen + "\n";
        } else {
            str = sb2 + "  PathLen: undefined\n";
        }
        return str + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.BasicConstraints_Id;
            if (this.ca) {
                this.critical = true;
            } else {
                this.critical = false;
            }
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
