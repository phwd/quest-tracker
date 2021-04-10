package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;

public class DeltaCRLIndicatorExtension extends CRLNumberExtension {
    @Override // sun.security.x509.CertAttrSet, sun.security.x509.CRLNumberExtension, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        new DerOutputStream();
        super.encode(outputStream, PKIXExtensions.DeltaCRLIndicator_Id, true);
    }
}
