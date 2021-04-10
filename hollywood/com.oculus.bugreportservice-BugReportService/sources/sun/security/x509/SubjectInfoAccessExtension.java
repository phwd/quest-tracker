package sun.security.x509;

import java.io.OutputStream;
import java.util.List;
import sun.security.util.DerOutputStream;

public class SubjectInfoAccessExtension extends Extension implements CertAttrSet {
    private List accessDescriptions;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "SubjectInfoAccess";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.SubjectInfoAccess_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    private void encodeThis() {
        if (this.accessDescriptions.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        for (AccessDescription accessDescription : this.accessDescriptions) {
            accessDescription.encode(derOutputStream);
        }
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.write((byte) 48, derOutputStream);
        this.extensionValue = derOutputStream2.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + "SubjectInfoAccess [\n  " + this.accessDescriptions + "\n]\n";
    }
}
