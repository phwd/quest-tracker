package sun.security.x509;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import sun.security.util.DerOutputStream;

public class CertificatePoliciesExtension extends Extension implements CertAttrSet {
    private List certPolicies;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "CertificatePolicies";
    }

    private void encodeThis() {
        List list = this.certPolicies;
        if (list == null || list.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        Iterator it = this.certPolicies.iterator();
        if (!it.hasNext()) {
            derOutputStream.write((byte) 48, derOutputStream2);
            this.extensionValue = derOutputStream.toByteArray();
            return;
        }
        ((PolicyInformation) it.next()).encode(derOutputStream2);
        throw null;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        if (this.certPolicies == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("CertificatePolicies [\n");
        Iterator it = this.certPolicies.iterator();
        if (!it.hasNext()) {
            sb.append("]\n");
            return sb.toString();
        }
        ((PolicyInformation) it.next()).toString();
        throw null;
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.CertificatePolicies_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
