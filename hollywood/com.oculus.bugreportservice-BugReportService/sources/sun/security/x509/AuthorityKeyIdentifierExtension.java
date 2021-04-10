package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class AuthorityKeyIdentifierExtension extends Extension implements CertAttrSet {
    private KeyIdentifier id;
    private GeneralNames names;
    private SerialNumber serialNum;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "AuthorityKeyIdentifier";
    }

    private void encodeThis() {
        if (this.id == null && this.names == null && this.serialNum == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        if (this.id == null) {
            try {
                if (this.names != null) {
                    DerOutputStream derOutputStream3 = new DerOutputStream();
                    this.names.encode(derOutputStream3);
                    derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 1), derOutputStream3);
                }
                if (this.serialNum != null) {
                    DerOutputStream derOutputStream4 = new DerOutputStream();
                    this.serialNum.encode(derOutputStream4);
                    derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 2), derOutputStream4);
                }
                derOutputStream.write((byte) 48, derOutputStream2);
                this.extensionValue = derOutputStream.toByteArray();
            } catch (Exception e) {
                throw new IOException(e.toString());
            }
        } else {
            this.id.encode(new DerOutputStream());
            throw null;
        }
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str = super.toString() + "AuthorityKeyIdentifier [\n";
        if (this.id == null) {
            if (this.names != null) {
                str = str + this.names.toString() + "\n";
            }
            if (this.serialNum != null) {
                str = str + this.serialNum.toString() + "\n";
            }
            return str + "]\n";
        }
        new StringBuilder().append(str);
        this.id.toString();
        throw null;
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.AuthorityKey_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
