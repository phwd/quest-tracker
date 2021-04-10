package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class SubjectAlternativeNameExtension extends Extension implements CertAttrSet {
    GeneralNames names = null;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "SubjectAlternativeName";
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

    public SubjectAlternativeNameExtension() {
        this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
        this.critical = false;
        this.names = new GeneralNames();
    }

    public SubjectAlternativeNameExtension(Boolean bool, Object obj) {
        this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
        this.critical = bool.booleanValue();
        this.extensionValue = (byte[]) obj;
        DerValue derValue = new DerValue(this.extensionValue);
        if (derValue.data == null) {
            this.names = new GeneralNames();
        } else {
            this.names = new GeneralNames(derValue);
        }
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        String str2 = super.toString() + "SubjectAlternativeName [\n";
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
            this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public GeneralNames get(String str) {
        if (str.equalsIgnoreCase("subject_name")) {
            return this.names;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:SubjectAlternativeName.");
    }
}
