package sun.security.x509;

import java.io.OutputStream;
import java.util.List;
import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public class CRLDistributionPointsExtension extends Extension implements CertAttrSet {
    private List distributionPoints;
    private String extensionName;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return this.extensionName;
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        encode(outputStream, PKIXExtensions.CRLDistributionPoints_Id, false);
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

    private void encodeThis() {
        if (this.distributionPoints.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        for (DistributionPoint distributionPoint : this.distributionPoints) {
            distributionPoint.encode(derOutputStream);
        }
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.write((byte) 48, derOutputStream);
        this.extensionValue = derOutputStream2.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + this.extensionName + " [\n  " + this.distributionPoints + "]\n";
    }
}
