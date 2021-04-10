package sun.security.x509;

public class OCSPNoCheckExtension extends Extension implements CertAttrSet {
    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "OCSPNoCheck";
    }

    public OCSPNoCheckExtension() {
        this.extensionId = PKIXExtensions.OCSPNoCheck_Id;
        this.critical = false;
        this.extensionValue = new byte[0];
    }
}
