package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FreshestCRLExtension extends CRLDistributionPointsExtension {
    public static final String NAME = "FreshestCRL";

    public FreshestCRLExtension(List<DistributionPoint> distributionPoints) throws IOException {
        super(PKIXExtensions.FreshestCRL_Id, false, distributionPoints, NAME);
    }

    public FreshestCRLExtension(Boolean critical, Object value) throws IOException {
        super(PKIXExtensions.FreshestCRL_Id, Boolean.valueOf(critical.booleanValue()), value, NAME);
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.CRLDistributionPointsExtension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        super.encode(out, PKIXExtensions.FreshestCRL_Id, false);
    }
}
