package sun.security.x509;

import java.io.OutputStream;
import java.util.Iterator;
import sun.security.util.DerOutputStream;

public class IssuerAlternativeNameExtension extends Extension implements CertAttrSet {
    GeneralNames names = null;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "IssuerAlternativeName";
    }

    private void encodeThis() {
        GeneralNames generalNames = this.names;
        if (generalNames == null || generalNames.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        this.names.encode(derOutputStream);
        this.extensionValue = derOutputStream.toByteArray();
    }

    public IssuerAlternativeNameExtension() {
        this.extensionId = PKIXExtensions.IssuerAlternativeName_Id;
        this.critical = false;
        this.names = new GeneralNames();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        String str2 = super.toString() + "IssuerAlternativeName [\n";
        GeneralNames generalNames = this.names;
        if (generalNames == null) {
            str = str2 + "  null\n";
        } else {
            Iterator it = generalNames.names().iterator();
            while (it.hasNext()) {
                str2 = str2 + "  " + ((GeneralName) it.next()) + "\n";
            }
            str = str2;
        }
        return str + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.IssuerAlternativeName_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
