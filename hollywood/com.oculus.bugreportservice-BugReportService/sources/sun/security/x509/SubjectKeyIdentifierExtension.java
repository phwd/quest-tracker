package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;

public class SubjectKeyIdentifierExtension extends Extension implements CertAttrSet {
    private KeyIdentifier id;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "SubjectKeyIdentifier";
    }

    private void encodeThis() {
        if (this.id == null) {
            this.extensionValue = null;
            return;
        }
        this.id.encode(new DerOutputStream());
        throw null;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + "SubjectKeyIdentifier [\n" + String.valueOf(this.id) + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.SubjectKey_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
