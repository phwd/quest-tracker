package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.Debug;
import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public class InhibitAnyPolicyExtension extends Extension implements CertAttrSet {
    public static ObjectIdentifier AnyPolicy_Id;
    private static final Debug debug = Debug.getInstance("certpath");
    private int skipCerts;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "InhibitAnyPolicy";
    }

    static {
        try {
            AnyPolicy_Id = new ObjectIdentifier("2.5.29.32.0");
        } catch (IOException unused) {
        }
    }

    private void encodeThis() {
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putInteger(this.skipCerts);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + "InhibitAnyPolicy: " + this.skipCerts + "\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.InhibitAnyPolicy_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
