package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class NameConstraintsExtension extends Extension implements CertAttrSet, Cloneable {
    private GeneralSubtrees excluded;
    private boolean minMaxValid;
    private GeneralSubtrees permitted;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "NameConstraints";
    }

    private void encodeThis() {
        this.minMaxValid = false;
        if (this.permitted == null && this.excluded == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        if (this.permitted != null) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            this.permitted.encode(derOutputStream3);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), derOutputStream3);
        }
        if (this.excluded != null) {
            DerOutputStream derOutputStream4 = new DerOutputStream();
            this.excluded.encode(derOutputStream4);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 1), derOutputStream4);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("NameConstraints: [");
        String str2 = "";
        if (this.permitted == null) {
            str = str2;
        } else {
            str = "\n    Permitted:" + this.permitted.toString();
        }
        sb.append(str);
        if (this.excluded != null) {
            str2 = "\n    Excluded:" + this.excluded.toString();
        }
        sb.append(str2);
        sb.append("   ]\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.NameConstraints_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public Object clone() {
        try {
            NameConstraintsExtension nameConstraintsExtension = (NameConstraintsExtension) super.clone();
            if (this.permitted != null) {
                nameConstraintsExtension.permitted = (GeneralSubtrees) this.permitted.clone();
            }
            if (this.excluded != null) {
                nameConstraintsExtension.excluded = (GeneralSubtrees) this.excluded.clone();
            }
            return nameConstraintsExtension;
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("CloneNotSupportedException while cloning NameConstraintsException. This should never happen.");
        }
    }
}
