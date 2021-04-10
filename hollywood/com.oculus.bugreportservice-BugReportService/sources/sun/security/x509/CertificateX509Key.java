package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.PublicKey;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;

public class CertificateX509Key implements CertAttrSet {
    private PublicKey key;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "key";
    }

    public CertificateX509Key(DerInputStream derInputStream) {
        this.key = X509Key.parse(derInputStream.getDerValue());
    }

    public String toString() {
        PublicKey publicKey = this.key;
        if (publicKey == null) {
            return "";
        }
        return publicKey.toString();
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.write(this.key.getEncoded());
        outputStream.write(derOutputStream.toByteArray());
    }

    public PublicKey get(String str) {
        if (str.equalsIgnoreCase("value")) {
            return this.key;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }
}
