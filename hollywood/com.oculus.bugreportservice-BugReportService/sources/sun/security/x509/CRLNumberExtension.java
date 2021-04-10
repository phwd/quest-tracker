package sun.security.x509;

import java.io.OutputStream;
import java.math.BigInteger;
import sun.security.util.Debug;
import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public class CRLNumberExtension extends Extension implements CertAttrSet {
    private BigInteger crlNumber;
    private String extensionLabel;
    private String extensionName;

    private void encodeThis() {
        if (this.crlNumber == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putInteger(this.crlNumber);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.extensionLabel);
        sb.append(": ");
        BigInteger bigInteger = this.crlNumber;
        sb.append(bigInteger == null ? "" : Debug.toHexString(bigInteger));
        sb.append("\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        new DerOutputStream();
        encode(outputStream, PKIXExtensions.CRLNumber_Id, true);
    }

    /* access modifiers changed from: protected */
    public void encode(OutputStream outputStream, ObjectIdentifier objectIdentifier, boolean z) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = objectIdentifier;
            this.critical = z;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return this.extensionName;
    }
}
