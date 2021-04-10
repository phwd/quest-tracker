package sun.security.x509;

import java.io.OutputStream;

public class FreshestCRLExtension extends CRLDistributionPointsExtension {
    @Override // sun.security.x509.CertAttrSet, sun.security.x509.CRLDistributionPointsExtension, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        super.encode(outputStream, PKIXExtensions.FreshestCRL_Id, false);
    }
}
