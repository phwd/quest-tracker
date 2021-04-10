package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class SubjectAlternativeNameExtension extends Extension implements CertAttrSet<String> {
    public static final String IDENT = "x509.info.extensions.SubjectAlternativeName";
    public static final String NAME = "SubjectAlternativeName";
    public static final String SUBJECT_NAME = "subject_name";
    GeneralNames names;

    private void encodeThis() throws IOException {
        GeneralNames generalNames = this.names;
        if (generalNames == null || generalNames.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        this.names.encode(os);
        this.extensionValue = os.toByteArray();
    }

    public SubjectAlternativeNameExtension(GeneralNames names2) throws IOException {
        this(Boolean.FALSE, names2);
    }

    public SubjectAlternativeNameExtension(Boolean critical, GeneralNames names2) throws IOException {
        this.names = null;
        this.names = names2;
        this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
        this.critical = critical.booleanValue();
        encodeThis();
    }

    public SubjectAlternativeNameExtension() {
        this.names = null;
        this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
        this.critical = false;
        this.names = new GeneralNames();
    }

    public SubjectAlternativeNameExtension(Boolean critical, Object value) throws IOException {
        this.names = null;
        this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        DerValue val = new DerValue(this.extensionValue);
        if (val.data == null) {
            this.names = new GeneralNames();
        } else {
            this.names = new GeneralNames(val);
        }
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        String result = super.toString() + "SubjectAlternativeName [\n";
        GeneralNames generalNames = this.names;
        if (generalNames == null) {
            result = result + "  null\n";
        } else {
            Iterator<GeneralName> it = generalNames.names().iterator();
            while (it.hasNext()) {
                result = result + "  " + ((Object) it.next()) + "\n";
            }
        }
        return result + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.SubjectAlternativeName_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!name.equalsIgnoreCase(SUBJECT_NAME)) {
            throw new IOException("Attribute name not recognized by CertAttrSet:SubjectAlternativeName.");
        } else if (obj instanceof GeneralNames) {
            this.names = (GeneralNames) obj;
            encodeThis();
        } else {
            throw new IOException("Attribute value should be of type GeneralNames.");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public GeneralNames get(String name) throws IOException {
        if (name.equalsIgnoreCase(SUBJECT_NAME)) {
            return this.names;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:SubjectAlternativeName.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase(SUBJECT_NAME)) {
            this.names = null;
            encodeThis();
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:SubjectAlternativeName.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement(SUBJECT_NAME);
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }
}
